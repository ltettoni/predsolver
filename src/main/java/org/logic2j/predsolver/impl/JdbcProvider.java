package org.logic2j.predsolver.impl;

import org.apache.derby.jdbc.ClientDataSource;
import org.apache.derby.jdbc.EmbeddedDataSource;
import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.Provider;
import org.logic2j.predsolver.api.Var;
import org.logic2j.predsolver.util.SqlBuilder3;
import org.logic2j.predsolver.util.SqlRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Provide data from a relational {@link DataSource}, exposing queries for the
 * JDBC API.
 * 
 * @author Laurent
 */
public class JdbcProvider implements Provider {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(JdbcProvider.class);

    private static final String DERBY_VERSION_STRING = "v10.10.2.0";
    private static final String SRC_TEST_DB = "C:/GIT/logic2j/src/test/resource/db";
    private static final String ZIPCODES_DERBY_DIR = SRC_TEST_DB + "/zipcodes1/derby-" + DERBY_VERSION_STRING;
    private static final String DERBY_USER = "APP";
    private static final String DERBY_PWD = "APP";

    private String databaseName;
    private String username;
    private String password;
    private DataSource dataSource;
    private Connection zipcodesConnection = null;

    protected JdbcProvider(String databaseName, String username, String password) {
        super();
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
    }

    public JdbcProvider createZipcodeDataSource() {
        final JdbcProvider result = new JdbcProvider(ZIPCODES_DERBY_DIR, DERBY_USER, DERBY_PWD);
        setDataSource(zipcodesDataSource());
        return result;
    }
    
    /**
     * @return A {@link DataSource} to the "zipcodes" reference database.
     */
    private DataSource zipcodesDataSource() {
        return derbyEmbeddedDataSource();
    }

    /**
     *            Relative path to the derby binary directory, usually under
     *            "src/test/db/NAME"
     * @return A new Derby EmbeddedDataSource
     */
    protected DataSource derbyEmbeddedDataSource() {
        final EmbeddedDataSource ds = new EmbeddedDataSource();
        ds.setDatabaseName(this.databaseName);
        ds.setUser(this.username);
        ds.setPassword(this.password);
        return ds;
    }


    /**
     * @return A {@link DataSource} to the specified database.
     */
    protected DataSource derbyNetworkDataSource() {
        final ClientDataSource ds = new ClientDataSource();
        ds.setDatabaseName(this.databaseName);
        ds.setUser(this.username);
        ds.setPassword(this.password);
        return ds;
    }
    
    public DataSource getDataSource() {
        return this.dataSource;
    }
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    
    /**
     * @return A (previously obtained and reused) {@link Connection} to the
     *         "zipcodes" reference database.
     * @throws SQLException
     */
    protected Connection zipcodesConnection() throws SQLException {
        if (this.zipcodesConnection == null) {
            this.zipcodesConnection = zipcodesDataSource().getConnection();
            logger.debug("Instantiated new connection to zipcodes DB");
        }
        return this.zipcodesConnection;
    }

    public List<Object[]> execute(SqlBuilder3 builder) {
        try {
            final SqlRunner sqlRunner = new SqlRunner(derbyNetworkDataSource());
            final List<Object[]> resultSet = sqlRunner.query(builder.getSelect(), builder.getParameters());
            // Convert ResultSet to list of tuples
            logger.info("Result set to {} has size={}", builder, resultSet.size());
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Will solve thePredicate and project tuples of theVars
     * 
     * @param thePredicate
     * @param theVars
     * @return
     */
    // TODO I wonder if "solve" should be part of a Provider or a Solver?
    public JdbcQuery solveAsQuery(Predicate thePredicate, Var<?>... theVars) {
        logger.info("Solving as query: {}", thePredicate);
        return new JdbcQuery();
    }

}
