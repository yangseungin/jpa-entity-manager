package database.sql.util;

import database.sql.util.column.EntityColumn;
import database.sql.util.column.FieldToEntityColumnConverter;
import database.sql.util.type.TypeConverter;
import jakarta.persistence.Transient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ColumnsMetadata {

    private final List<EntityColumn> allEntityColumns;
    private final EntityColumn primaryKey;
    private final List<EntityColumn> generalColumns;

    public ColumnsMetadata(Class<?> entityClass) {
        allEntityColumns = Arrays.stream(entityClass.getDeclaredFields())
                .filter(field -> !field.isAnnotationPresent(Transient.class))
                .map(field -> new FieldToEntityColumnConverter(field).convert())
                .collect(Collectors.toList());

        primaryKey = allEntityColumns.stream()
                .filter(EntityColumn::isPrimaryKeyField).findFirst().get();

        generalColumns = allEntityColumns.stream()
                .filter(columnMetadata -> !columnMetadata.isPrimaryKeyField())
                .collect(Collectors.toList());
    }

    public List<String> getAllColumnNames() {
        return allEntityColumns.stream().map(EntityColumn::getColumnName).collect(Collectors.toList());
    }

    public List<String> getColumnDefinitions(TypeConverter typeConverter) {
        return allEntityColumns.stream()
                .map(entityColumn -> entityColumn.toColumnDefinition(typeConverter))
                .collect(Collectors.toList());
    }

    public String getPrimaryKeyColumnName() {
        return primaryKey.getColumnName();
    }

    public List<String> getGeneralColumnNames() {
        return generalColumns.stream().map(EntityColumn::getColumnName).collect(Collectors.toList());
    }

    public List<EntityColumn> getGeneralColumns() {
        return generalColumns;
    }

    public Long getPrimaryKeyValue(Object entity) {
        return (Long) primaryKey.getValue(entity);
    }
}