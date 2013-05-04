package org.logic2j.predsolver.api;

public interface DBPredicate {

    public static class ColumnInfo {
        public final String table;
        public final String column;
        public final String operator;
        public final Binding<?> value;
        public ColumnInfo(String table, String column, Binding<?> value) {
            this.table = table;
            this.column = column;
            this.operator = "=";
            this.value = value;
        }
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Column(");
            sb.append(table);
            sb.append('.');
            sb.append(column);
            sb.append(' ');
            sb.append(operator);
            sb.append(' ');
            sb.append(value);
            return sb.toString();
        }
    }
    
    public ColumnInfo[] getColumnSpec();
    
}
