package com.template.simple.config;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.flywaydb.core.api.Location;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;

/**
 * 脚本框架配置
 */
@Slf4j
@Configuration
public class FlywayConfig {

    @Autowired
    private DataSource dataSource;

    /**
     * 初始配置
     */
    @PostConstruct
    public void migrate() {
        ClassicConfiguration configuration = new ClassicConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setLocations(new Location("db/migration"));
        configuration.setEncoding(StandardCharsets.UTF_8);
        configuration.setOutOfOrder(true);
        Flyway flyway = new Flyway(configuration);

        try {
            flyway.migrate();
        } catch (FlywayException e) {
            flyway.repair();
            log.error("Flyway配置加载出错", e);
        }
    }

}
