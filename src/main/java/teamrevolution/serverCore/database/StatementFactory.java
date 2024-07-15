package teamrevolution.serverCore.database;

import java.util.*;

public class StatementFactory {
    private final HashMap<String, Object> parameters;
    private final String tableName;

    public StatementFactory(String databaseName) {
        this.tableName = databaseName;
        this.parameters = new HashMap<>();
    }
    public void addValue(String column, Object value) {
        this.parameters.put(column, value);
    }
    public String insertStatement() {
        StringBuilder sql = new StringBuilder("INSERT INTO ");
        sql.append(this.tableName);

        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            columns.append(entry.getKey());
            columns.append(",");
            values.append(entry.getValue().toString());
            values.append(",");
        }
        columns.deleteCharAt(columns.length() - 1); // remove last comma
        columns.append(")");

        values.deleteCharAt(values.length() - 1); // remove last comma
        values.append(")");


        sql.append(columns);
        sql.append(" VALUES ");
        sql.append(values);
        return sql.toString();
    }

    public String createDatabaseStatement() {
        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        sql.append(this.tableName);
        sql.append(" (id INTEGER PRIMARY KEY, ");
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            sql.append(entry.getKey());

            sql.append(getType(entry.getValue()));

            sql.append(",");
        }
        sql.deleteCharAt(sql.length() - 1); // remove last comma
        sql.append(")");

        return sql.toString();
    }
    private String getType(Object object) {
        if (object instanceof Integer) return "INTEGER";
        return "TEXT";
    }
}
