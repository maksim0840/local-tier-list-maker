package org.tierlistapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.tierlistapp.config.propreties.S3Properties;

@SpringBootApplication
@EnableConfigurationProperties({S3Properties.class}) // регистрируем конфигурационные бины
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}