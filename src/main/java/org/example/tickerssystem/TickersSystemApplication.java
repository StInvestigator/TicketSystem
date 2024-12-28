package org.example.tickerssystem;

import org.example.tickerssystem.menu.MenuExecutor;
import org.example.tickerssystem.service.db.TicketsDBManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class TickersSystemApplication {

    @Autowired
    private TicketsDBManager dbManager;

    @Autowired
    private MenuExecutor menuExecutor;

    public static void main(String[] args) {
        SpringApplication.run(TickersSystemApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        dbManager.deleteAllRowsInDB();
        dbManager.fillAllRowsInDB();
        menuExecutor.startMenu();
    }
}
