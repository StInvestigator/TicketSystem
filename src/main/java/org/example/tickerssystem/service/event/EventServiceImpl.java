package org.example.tickerssystem.service.event;

import org.example.tickerssystem.entity.Event;
import org.example.tickerssystem.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.tickerssystem.repository.event.EventRepository;
import org.example.tickerssystem.service.place.PlaceService;
import org.example.tickerssystem.service.ticket.TicketPackDTO;
import org.example.tickerssystem.service.ticket.TicketService;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private TicketService ticketService;

    @Override
    public void save(Event event) {
        if (!isPlaceFree(event.getPlace(), event.getDate())) {
            System.out.println(event.getPlace().getName() + " is already taken by another event at the " + event.getDate());
            return;
        }
        eventRepository.save(event);
    }

    @Override
    public void saveAll(List<EventCreationDTO> events) {
        for (EventCreationDTO event : events) {
            if (event == null || event.getTicketPacks().isEmpty()) {
                return;
            }
            Event eventEntity = new Event();
            eventEntity.setDate(event.getDate());
            eventEntity.setName(event.getName());

            Place placeEntity = placeService.findByAddress(event.getPlace().getAddress());
            if (placeEntity == null) {
                placeEntity = new Place();
                placeEntity.setName(event.getPlace().getName());
                placeEntity.setAddress(event.getPlace().getAddress());
                placeService.save(placeEntity);
            } else if (!isPlaceFree(placeEntity, event.getDate())) {
                System.out.println(event.getPlace().getName() + " is already taken by another event at the " + event.getDate());
                return;
            }
            eventEntity.setPlace(placeEntity);

            eventRepository.save(eventEntity);

            for (TicketPackDTO ticketPackDTO : event.getTicketPacks()) {
                ticketService.saveAll(ticketPackDTO, eventEntity);
            }
        }
    }

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> findClosestEventsIn2Weeks() {
        LocalDate now = LocalDate.now();
        Date nowDate = Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date twoWeeksLaterDate = Date.from(now.plusWeeks(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return eventRepository.findEventsByDateBetweenOrderByDateAsc(nowDate, twoWeeksLaterDate);
    }

    @Override
    public void deleteAll() {
        eventRepository.deleteAll();
    }

    @Override
    public boolean isPlaceFree(Place place, Date date) {
        return eventRepository.findEventsByPlaceAndDate(place, date).isEmpty();
    }
}
