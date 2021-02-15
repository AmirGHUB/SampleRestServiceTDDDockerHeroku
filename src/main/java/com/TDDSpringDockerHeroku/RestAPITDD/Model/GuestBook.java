package com.TDDSpringDockerHeroku.RestAPITDD.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class GuestBook {

    @Id
    @GeneratedValue
    private Long Id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Guest> guests;

    public GuestBook(String name) {
        this.name = name;
        guests = new ArrayList<>();
    }

    public void addGuest(Guest guest) {
        this.guests.add(guest);
    }
}


