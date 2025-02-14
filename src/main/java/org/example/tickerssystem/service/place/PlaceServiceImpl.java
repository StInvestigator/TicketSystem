package org.example.tickerssystem.service.place;

import org.apache.logging.log4j.util.PropertySource;
import org.example.tickerssystem.entity.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.tickerssystem.repository.place.PlaceRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Override
    public void save(Place place) {
        if (placeRepository.findByAddressLikeIgnoreCase(place.getAddress()) != null){
            System.out.println("There is already a place with the same address");
            return;
        }
        placeRepository.save(place);
    }

    @Override
    public void saveAll(List<PlaceDTO> placeDTOs) {
        List<Place> places = new ArrayList<Place>();
        for (PlaceDTO placeDTO : placeDTOs) {
            if (placeRepository.findByAddressLikeIgnoreCase(placeDTO.getAddress()) != null ||
                    places.stream().map(Place::getAddress).anyMatch(x -> x.equals(placeDTO.getAddress()))) continue;
            Place placeEntity = new Place();
            placeEntity.setName(placeDTO.getName());
            placeEntity.setAddress(placeDTO.getAddress());
            places.add(placeEntity);
        }
        placeRepository.saveAll(places);
    }

    @Override
    public List<Place> findAll() {
        return placeRepository.findAll();
    }

    @Override
    public void deleteAll() {
        placeRepository.deleteAll();
    }

    @Override
    public Place findByAddress(String address) {
        return placeRepository.findByAddressLikeIgnoreCase(address);
    }

    @Override
    public Place findById(Long id) {
        return placeRepository.findById(id).orElse(null);
    }
}
