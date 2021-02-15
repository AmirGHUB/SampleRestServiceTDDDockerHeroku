package com.TDDSpringDockerHeroku.RestAPITDD.Service;

import com.TDDSpringDockerHeroku.RestAPITDD.Model.Guest;
import com.TDDSpringDockerHeroku.RestAPITDD.Model.GuestBook;
import com.TDDSpringDockerHeroku.RestAPITDD.Repository.GuestBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestbookService {

    @Autowired
    private GuestBookRepository repository;


    public GuestBook addGuestBook(GuestBook guestBook) {
        return repository.save(guestBook);
    }

    public List<Guest> getAllGuests() {
       List<Guest> guests = new ArrayList<>();

       for(Guest g : repository.findAll().get(0).getGuests()){
           guests.add(g);
       }

       return guests;
    }
}
