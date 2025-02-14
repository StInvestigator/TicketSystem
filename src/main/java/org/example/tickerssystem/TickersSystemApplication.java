package org.example.tickerssystem;

import lombok.extern.log4j.Log4j2;
import org.example.tickerssystem.service.db.TicketsDBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Log4j2
@SpringBootApplication
public class TickersSystemApplication {

    @Autowired
    private TicketsDBManager dbManager;

    public static void main(String[] args) {
        SpringApplication.run(TickersSystemApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        log.info("ApplicationRunner has started");
        return args -> {
            dbManager.deleteAllRowsInDB();
            dbManager.fillAllRowsInDB();
        };
    }
}
