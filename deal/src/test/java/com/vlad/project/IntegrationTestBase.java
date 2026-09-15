package com.vlad.project;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

@Testcontainers
@SpringBootTest
@Sql(scripts = {"classpath:sql/drop.sql",
        "classpath:sql/data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public abstract class IntegrationTestBase {

    private static final PostgreSQLContainer container =
            new PostgreSQLContainer("postgres:17");

    static {
        container.start();
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry source) {
        source.add("spring.datasource.url", container::getJdbcUrl);
        source.add("spring.datasource.username", container::getUsername);
        source.add("spring.datasource.password", container::getPassword);
    }
}
