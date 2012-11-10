package org.logic2j.predsolver.impl;

import javax.sql.DataSource;

import org.logic2j.predsolver.JdbcQuery;
import org.logic2j.predsolver.Provider;
import org.logic2j.predsolver.model.Predicate;
import org.logic2j.predsolver.model.Var;

/**
 * Provide data from a relational {@link DataSource}, exposing queries
 * for the JDBC API.
 * 
 * @author Laurent
 */
public class JdbcProvider implements Provider {
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(JdbcProvider.class);
	
	/**
	 * Will solve thePredicate and project tuples of theVars
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
