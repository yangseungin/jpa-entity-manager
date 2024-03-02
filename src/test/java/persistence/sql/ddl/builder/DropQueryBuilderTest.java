package persistence.sql.ddl.builder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import persistence.domain.Person;
import persistence.sql.ddl.dialect.H2Dialect;

import static org.assertj.core.api.Assertions.assertThat;

public class DropQueryBuilderTest {
    @Test
    @DisplayName("@Entity 클래스의 drop 쿼리 만들기")
    void makeDropQuery() {
        QueryBuilder dropQueryBuilder = new DropQueryBuilder(new H2Dialect());

        String query = dropQueryBuilder.generateSQL(Person.class);

        assertThat(query).isEqualTo("drop table users;");
    }
}