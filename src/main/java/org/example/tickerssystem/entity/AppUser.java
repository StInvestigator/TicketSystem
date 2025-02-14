package org.example.tickerssystem.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String encryptedPassword;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String phone;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    @Column
    private boolean enabled;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return enabled == appUser.enabled && Objects.equals(id, appUser.id) && Objects.equals(name, appUser.name) && Objects.equals(encryptedPassword, appUser.encryptedPassword) && Objects.equals(email, appUser.email) && Objects.equals(phone, appUser.phone) && Objects.equals(tickets, appUser.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, encryptedPassword, name, email, phone, tickets, enabled);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", encryptedPassword='" + encryptedPassword + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", tickets=" + tickets +
                ", enabled=" + enabled +
                '}';
    }
}


