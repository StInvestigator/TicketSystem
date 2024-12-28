package org.example.tickerssystem.menu;

import org.example.tickerssystem.entity.Customer;
import org.example.tickerssystem.entity.Event;
import org.example.tickerssystem.entity.Place;
import org.example.tickerssystem.entity.Ticket;
import org.example.tickerssystem.service.customer.CustomerService;
import org.example.tickerssystem.service.event.EventService;
import org.example.tickerssystem.service.place.PlaceService;
import org.example.tickerssystem.service.ticket.TicketService;
import org.example.tickerssystem.service.ticketStatus.TicketStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static org.example.tickerssystem.menu.MenuPublisher.showMenu;

@Service
@Transactional
public class MenuExecutor {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private EventService eventService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketStatusService ticketStatusService;

    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            showMenu();
            choice = scanner.nextInt();

            if (choice == 1) {
                executeCreateCustomer();
            }
            if (choice == 2) {
                executeShowFreeTicketsOnEvent();
            }
            if (choice == 3) {
                executeShowEventsOnNextTwoWeeks();
            }
            if (choice == 4) {
                executeAssignTicketToCustomer();
            }
            if (choice == 5) {
                showAllTicketsOfCustomer();
            }
            if (choice == 6) {
                runNegativeTests();
                System.out.println("All positive scenarios were checked when loading the program");
            }
        } while (choice != -1);
    }

    private void executeCreateCustomer() {
        Scanner scanner = new Scanner(System.in);

        Customer customer = new Customer();
        System.out.println("Enter customer name: ");
        customer.setName(scanner.nextLine());
        System.out.println("Enter customer email: ");
        customer.setEmail(scanner.nextLine());
        System.out.println("Enter customer phone number: ");
        customer.setPhone(scanner.nextLine());

        customerService.save(customer);
        System.out.println("Customer successfully created!");
    }

    private void executeShowFreeTicketsOnEvent() {
        Scanner scanner = new Scanner(System.in);

        int counter = 1;
        List<Event> events = eventService.findAll();
        for (Event event : events) {
            System.out.println(counter++ + ". " + event.toString());
        }
        System.out.print("Select an event number: ");
        int answ = scanner.nextInt();

        System.out.println("All tickets available on this event: ");
        for (Ticket ticket : ticketService.findAllFreeTicketsByEventId(events.get(answ - 1).getId())) {
            System.out.println(ticket.toString());
        }
    }

    private void executeShowEventsOnNextTwoWeeks() {
        for (Event event : eventService.findClosestEventsIn2Weeks()) {
            System.out.println(event.toString());
        }
    }

    public void executeAssignTicketToCustomer() {
        Scanner scanner = new Scanner(System.in);

        int counter = 1;
        List<Customer> customers = customerService.findAll();
        for (Customer customer : customers) {
            System.out.println(counter++ + ". " + customer.toString());
        }
        System.out.print("Select a customer number: ");
        int answ = scanner.nextInt();

        Customer customer = customers.get(answ - 1);

        counter = 1;
        List<Event> events = eventService.findAll();
        for (Event event : events) {
            System.out.println(counter++ + ". " + event.toString());
        }
        System.out.print("Select an event number: ");
        answ = scanner.nextInt();

        List<Ticket> tickets = ticketService.findAllFreeTicketsByEventId(events.get(answ - 1).getId());

        counter = 1;
        System.out.println("All tickets available on this event: ");
        for (Ticket ticket : tickets) {
            System.out.println(counter++ + ". " + ticket.toString());
        }
        System.out.print("Select a ticket number: ");

        ticketService.sellTicket(tickets.get(scanner.nextInt() - 1),customer);
        System.out.println("Ticket was successfully sold!");

    }

    private void showAllTicketsOfCustomer() {
        Scanner scanner = new Scanner(System.in);

        int counter = 1;
        List<Customer> customers = customerService.findAll().stream().filter(x -> (x.getTickets() != null && !x.getTickets().isEmpty())).toList();
        if (customers.isEmpty()) {
            System.out.println("There is no customers with tickets!");
            return;
        }
        for (Customer customer : customers) {
            System.out.println(counter++ + ". " + customer.toString());
        }
        System.out.print("Select a customer number: ");
        int answ = scanner.nextInt();

        for (Ticket ticket : customers.get(answ - 1).getTickets()) {
            System.out.println(ticket.toString());
        }
    }

    private void runNegativeTests(){
        System.out.println("--Trying to create an event on place that is already busy...");

        Event event = eventService.findAll().get(0);
        Event testEvent = new Event();
        testEvent.setPlace(event.getPlace());
        testEvent.setDate(event.getDate());
        testEvent.setName("Test event");
        eventService.save(testEvent);

        System.out.println("--Trying to create a place with already existing address...");

        Place testPlace = new Place();
        testPlace.setName("Test Place");
        testPlace.setAddress(event.getPlace().getAddress());
        placeService.save(testPlace);
    }
}
