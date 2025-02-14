package org.example.tickerssystem.repository.user;


import org.example.tickerssystem.entity.AppUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<AppUser, Long> {

    @Query("from AppUser a where a.name = ?1")
    Optional<AppUser> findUserAccount(String userName);

    @EntityGraph(attributePaths = {"tickets.event"})
    @Query("SELECT u FROM AppUser u WHERE u.email = :email")
    AppUser findByEmailWithTickets(@Param("email") String email);

}
