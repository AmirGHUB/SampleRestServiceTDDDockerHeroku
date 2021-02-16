package com.TDDSpringDockerHeroku.RestAPITDD.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@Profile("heroku")
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        //databse_url = postgres://ltlyzhrwpoecqh:e277c6c08f2af0720ccd8c2f3c326b84ae214021da6c46f20a51b8e0cc2f9f2e
        //                    @ec2-54-90-13-87.compute-1.amazonaws.com:5432/d891q0kl4a5a9t

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        return DataSourceBuilder.create()
            .url(dbUrl)
            .username(username)
            .password(password)
            .build();
    }
}