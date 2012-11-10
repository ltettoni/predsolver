package org.logic2j.predsolver;

import javax.sql.DataSource;

/**
 * A wrapper allowing to run a SQL query standalone: 
 * would contain a {@link DataSource}, a query strings and its bound parameters.
 * Also allows to render the query with or without bound parameters and '?' placeholders, 
 * (not recommended but some ORMs requires it).
 * 
 * @author Laurent
 */
public class JdbcQuery {

	/**
	 * @return SQL text with '?' placeholders
	 */
	public String getSql() {
		return null;
	}

	/**
	 * @return SQL text with "inlined" literal parameters, no '?' placeholders
	 */
	public String getLiteralSql() {
		return null;
	}

	public Object[] getParametersArray() {
		return null;
	}

	public int getNumberOfProjections() {
		return 0;
	}
}
