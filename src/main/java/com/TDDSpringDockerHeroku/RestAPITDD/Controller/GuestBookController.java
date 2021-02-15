package com.TDDSpringDockerHeroku.RestAPITDD.Controller;

import com.TDDSpringDockerHeroku.RestAPITDD.Model.Guest;
import com.TDDSpringDockerHeroku.RestAPITDD.Model.GuestBook;
import com.TDDSpringDockerHeroku.RestAPITDD.Service.GuestbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guestbook")
public class GuestBookController {

    @Autowired
    private GuestbookService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GuestBook createGuestBook(@RequestBody GuestBook guestBook){
        return service.addGuestBook(guestBook);
    }

    @GetMapping("/get-guests")
    public List<Guest> getAllGuestsFromGuestBook(){
        return service.getAllGuests();
    }
}
