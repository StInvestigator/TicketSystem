package org.example.tickerssystem.service.db;

import org.example.tickerssystem.service.customer.CustomerDTO;
import org.example.tickerssystem.service.customer.CustomerService;
import org.example.tickerssystem.service.event.EventCreationDTO;
import org.example.tickerssystem.service.event.EventService;
import org.example.tickerssystem.service.place.PlaceDTO;
import org.example.tickerssystem.service.place.PlaceService;
import org.example.tickerssystem.service.ticket.TicketPackDTO;
import org.example.tickerssystem.service.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class TicketsDBManager {
    @Value("${data.names}")
    private String namesPath;

    @Value("${data.emails}")
    private String emailsPath;

    @Value("${data.phone_numbers}")
    private String phonesPath;

    @Value("${data.eventNames}")
    private String eventNamesPath;

    @Value("${data.places}")
    private String placesPath;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EventService eventService;

    @Autowired
    private PlaceService placeService;

    private final Random RANDOM_GENERATOR = new Random();

    public void fillAllRowsInDB() {
        createRandomCustomers();
        createRandomPlaces();
        creatRandomEventsWithTickets();
    }

    public void deleteAllRowsInDB() {
        customerService.deleteAll();
        ticketService.deleteAll();
        eventService.deleteAll();
        placeService.deleteAll();
    }

    public void createRandomCustomers() {
        List<String> randomNames;
        List<String> randomPhones;
        List<String> randomEmails;
        try {
            randomNames = Files.readAllLines(Paths.get(namesPath));
            randomPhones = Files.readAllLines(Paths.get(phonesPath));
            randomEmails = Files.readAllLines(Paths.get(emailsPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<CustomerDTO> customerDTOs = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            CustomerDTO customerDTO = new CustomerDTO(
                    randomNames.get(RANDOM_GENERATOR.nextInt(randomNames.size())),
                    randomEmails.get(RANDOM_GENERATOR.nextInt(randomEmails.size())),
                    randomPhones.get(RANDOM_GENERATOR.nextInt(randomPhones.size()))
            );
            customerDTOs.add(customerDTO);
        }
        customerService.saveAll(customerDTOs);
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

    public void creatRandomEventsWithTickets() {
        List<String> randomEventNames;
        List<String> randomPlaces;
        try {
            randomEventNames = Files.readAllLines(Paths.get(eventNamesPath));
            randomPlaces = Files.readAllLines(Paths.get(placesPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<EventCreationDTO> eventCreationDTOS = new ArrayList<>();
        List<TicketPackDTO> ticketPacks = new ArrayList<>();
        ticketPacks.add(new TicketPackDTO(BigDecimal.valueOf(199.99), 20));
        ticketPacks.add(new TicketPackDTO(BigDecimal.valueOf(299.99), 10));

        LocalDate now = LocalDate.now();

        for (int i = 0; i < 20; i++) {
            Date date = Date.from(now.plusDays(RANDOM_GENERATOR.nextInt(20)).atStartOfDay(ZoneId.systemDefault()).toInstant());
            int index = RANDOM_GENERATOR.nextInt(20);
            PlaceDTO placeDTO = new PlaceDTO(
                    randomPlaces.get(index).split(":")[0].trim(),
                    randomPlaces.get(index).split(":")[1].trim()
            );

            EventCreationDTO eventCreationDTO = new EventCreationDTO(
                    date,
                    randomEventNames.get(RANDOM_GENERATOR.nextInt(randomEventNames.size())),
                    ticketPacks,
                    placeDTO
            );
            eventCreationDTOS.add(eventCreationDTO);
        }
        eventService.saveAll(eventCreationDTOS);
    }
}
