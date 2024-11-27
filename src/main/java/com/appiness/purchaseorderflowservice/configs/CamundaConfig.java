package com.appiness.purchaseorderflowservice.configs;

//used camunda-bpm-spring-boot-starter so no need to define beans for ProcessEngine as starter does it for us
/*
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;


@Configuration
public class CamundaConfig {

    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration(DataSource dataSource) {
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setDatabaseSchemaUpdate(SpringProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        configuration.setJobExecutorActivate(true);
        return configuration;
    }
}
*/