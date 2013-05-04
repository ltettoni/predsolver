package org.logic2j.predsolver.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.logic2j.predsolver.api.Predicate;
import org.logic2j.predsolver.api.Provider;
import org.logic2j.predsolver.api.Var;
import org.logic2j.predsolver.util.SqlBuilder3;
import org.logic2j.predsolver.util.SqlRunner;

/**
 * Provide data from a relational {@link DataSource}, exposing queries for the
 * JDBC API.
 * 
 * @author Laurent
 */
public class JdbcProvider implements Provider {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(JdbcProvider.class);

    private static final String SRC_TEST_DB = "C:/GIT/logic2j/src/test/db";
    private static final String DERBY_VERSION_STRING = "v10.8.2.1";
    private static final String ZIPCODES_DERBY_DIR = SRC_TEST_DB + "/zipcodes1/derby-" + DERBY_VERSION_STRING;
    private static final String DERBY_USER = "APP";
    private static final String DERBY_PWD = "APP";

    private String connectionString;
    private String username;
    private String password;

    public JdbcProvider(String connectionString, String username, String password) {
        super();
        this.connectionString = connectionString;
        this.username = username;
        this.password = password;
    }

    private Connection zipcodesConnection = null;

    /**
     * @param theDerbyDatabaseDir
     *            Relative path to the derby binary directory, usually under
     *            "src/test/db/NAME"
     * @return A new Derby EmbeddedDataSource
     */
    protected DataSource derbyDataSource(String theDerbyDatabaseDir) {
        final EmbeddedDataSource ds = new EmbeddedDataSource();
        ds.setDatabaseName(theDerbyDatabaseDir);
        ds.setUser(DERBY_USER);
        ds.setPassword(DERBY_PWD);
        return ds;
    }

    /**
     * @return A {@link DataSource} to the "zipcodes" reference database.
     */
    protected DataSource zipcodesDataSource() {
        return derbyDataSource(ZIPCODES_DERBY_DIR);
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

    public void execute(SqlBuilder3 builder) {
        try {
            final SqlRunner sqlRunner = new SqlRunner(zipcodesDataSource());
            final List<Object[]> resultSet = sqlRunner.query(builder.getSelect(), builder.getParameters());
            int i=0;
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
