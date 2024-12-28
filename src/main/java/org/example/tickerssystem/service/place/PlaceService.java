package org.example.tickerssystem.service.place;

import org.example.tickerssystem.entity.Place;

import java.util.Date;
import java.util.List;

public interface PlaceService {
    void save(Place place);
    void saveAll(List<PlaceDTO> placeDTOs);
    List<Place> findAll();
    void deleteAll();
    Place findByAddress(String address);
}
