package org.example.tickerssystem.repository.event;

import org.example.tickerssystem.entity.Event;
import org.example.tickerssystem.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findEventsByDateBetweenOrderByDateAsc(Date date, Date date2);
    List<Event> findEventsByPlaceAndDate(Place place, Date date);
}
