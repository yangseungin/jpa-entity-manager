package persistence.sql.ddl.constraints.impl;

import jakarta.persistence.Id;
import java.lang.reflect.Field;
import persistence.exception.UnsupportedFieldException;
import persistence.sql.ddl.constraints.ConstraintsTranslator;

public class AutoIncrementConstraintsTranslator implements ConstraintsTranslator {
    private static final String AUTO_INCREMENT = "AUTO_INCREMENT";

    @Override
    public String getConstraintsFrom(Field field) {
        if (!supports(field)) {
            throw new UnsupportedFieldException(field);
        }

        return AUTO_INCREMENT;
    }

    @Override
    public boolean supports(Field field) {
        return field.isAnnotationPresent(Id.class);
    }
}