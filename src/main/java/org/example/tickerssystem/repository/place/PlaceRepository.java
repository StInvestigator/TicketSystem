package org.example.tickerssystem.repository.place;

import org.example.tickerssystem.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Place findByAddressLikeIgnoreCase(String address);
}
