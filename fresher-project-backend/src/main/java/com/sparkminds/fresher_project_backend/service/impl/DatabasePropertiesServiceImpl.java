package com.sparkminds.fresher_project_backend.service.impl;

import com.sparkminds.fresher_project_backend.service.DatabasePropertiesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DatabasePropertiesServiceImpl implements DatabasePropertiesService {
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;
    @Override
    public void printDatabaseProperties() {
        System.out.println("Database URL: " + dbUrl);
        System.out.println("Database Username: " + dbUsername);
        System.out.println("Database Password: " + dbPassword);
    }
}
