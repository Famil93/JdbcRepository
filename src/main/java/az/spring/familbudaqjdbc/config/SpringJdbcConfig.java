package az.spring.familbudaqjdbc.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "az.spring.familbudaqjdbc.dao")


public class SpringJdbcConfig {


    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource(){

        return DataSourceBuilder
                .create()
                .url("jdbc:mysql://localhost:3306/jdbcdb")
                .username("root")
                .password("1234")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
    @Bean
    @Autowired
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource){
       return new NamedParameterJdbcTemplate(dataSource);
    }
}
