package org.example.tickerssystem.service.event;

import org.example.tickerssystem.entity.Event;
import org.example.tickerssystem.entity.Place;

import java.util.Date;
import java.util.List;

public interface EventService {
    void save(Event event);
    boolean save(EventCreationDTO event);
    void saveAll(List<EventCreationDTO> events);
    List<Event> findAll();
    List<Event> findClosestEventsIn2Weeks();
    void deleteAll();
    boolean isPlaceFree(Place place, Date date);
}
