package org.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatabaseInitializer {
 
    @Autowired
    private JdbcTemplate jdbcTemplate;
     
    @Bean
    CommandLineRunner loadDatabase() {
        return new CommandLineRunner() {
             
            @Override
            public void run(String... args) throws Exception {
                 
                jdbcTemplate.execute("create table COIN_PRICE_HISTORY (id int primary key "
                        + "auto_increment, symbol varchar(30), price varchar(200), date date)");

            }
        };
    }
}