package persistence.sql.dml;

import persistence.sql.mapping.Table;

import java.util.Collections;
import java.util.List;

public class Delete {

    private final Table table;

    private final Wheres whereClause;

    public Delete(final Table table) {
        this(table, Collections.emptyList());
    }

    public Delete(final Table table, final List<Where> wheres) {
        this.table = table;
        this.whereClause = new Wheres(wheres);
    }

    public Table getTable() {
        return table;
    }

    public String getWhereClause() {
        return this.whereClause.wheresClause();
    }
}