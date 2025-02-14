package org.example.tickerssystem.service.db;

import org.example.tickerssystem.service.event.EventService;
import org.example.tickerssystem.service.place.PlaceDTO;
import org.example.tickerssystem.service.place.PlaceService;
import org.example.tickerssystem.service.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TicketsDBManager {
    @Value("${data.places}")
    private String placesPath;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EventService eventService;

    @Autowired
    private PlaceService placeService;

    private final Random RANDOM_GENERATOR = new Random();

    public void fillAllRowsInDB() {
        createRandomPlaces();
    }

    public void deleteAllRowsInDB() {
        ticketService.deleteAll();
        eventService.deleteAll();
        placeService.deleteAll();
    }

    public void createRandomPlaces() {
        List<String> randomPlaces;
        try {
            randomPlaces = Files.readAllLines(Paths.get(placesPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<PlaceDTO> placeDTOs = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int index = RANDOM_GENERATOR.nextInt(20);
            PlaceDTO placeDTO = new PlaceDTO(
                    randomPlaces.get(index).split(":")[0].trim(),
                    randomPlaces.get(index).split(":")[1].trim()
            );
            placeDTOs.add(placeDTO);
        }
        placeService.saveAll(placeDTOs);
    }
}
