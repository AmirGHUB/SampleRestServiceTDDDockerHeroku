package com.TDDSpringDockerHeroku.RestAPITDD.ServiceUnitTest;

import com.TDDSpringDockerHeroku.RestAPITDD.Model.Guest;
import com.TDDSpringDockerHeroku.RestAPITDD.Model.GuestBook;
import com.TDDSpringDockerHeroku.RestAPITDD.Repository.GuestBookRepository;
import com.TDDSpringDockerHeroku.RestAPITDD.Service.GuestbookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GuestBookServiceUnitTest {

    @Mock
    private GuestBookRepository repository;

    @InjectMocks
    private GuestbookService service;

    @Test
    public void creatGuestBook(){

        GuestBook guestBook = new GuestBook("wedding");

        when(repository.save(any())).thenReturn(guestBook);

        GuestBook returnedGuestBook = service.addGuestBook(guestBook);

        assertEquals(guestBook.getName() , returnedGuestBook.getName());
    }

    @Test
    public void addGuestToGuestBook(){

        GuestBook guestBook = new GuestBook("wedding");

        Guest guest1 = new Guest("bob","jhon","brother");

        guestBook.addGuest(guest1);

        when(repository.save(any())).thenReturn(guestBook);

        GuestBook returnedGuestBook = service.addGuestBook(guestBook);

        assertEquals(guestBook.getName() , returnedGuestBook.getName());
        assertEquals(guestBook.getGuests().size(), returnedGuestBook.getGuests().size());

        assertEquals(guestBook.getGuests().get(0).getFirstName() , returnedGuestBook.getGuests().get(0).getFirstName());
        assertEquals(guestBook.getGuests().get(0).getLastName() , returnedGuestBook.getGuests().get(0).getLastName());
        assertEquals(guestBook.getGuests().get(0).getRelationship() , returnedGuestBook.getGuests().get(0).getRelationship());
    }

    @Test
    public void getAllGuestsFromGuestBook(){

        GuestBook guestBook = new GuestBook("wedding");

        Guest guest1 = new Guest("bob","jhon","brother");
        Guest guest2 = new Guest("mark","go","cousin");
        Guest guest3 = new Guest("sci","fi","uncle");

        guestBook.addGuest(guest1);
        guestBook.addGuest(guest2);
        guestBook.addGuest(guest3);

        List<Guest> expectedList = guestBook.getGuests();

        when(repository.findAll()).thenReturn(List.of(guestBook));

        List<Guest> actualList = service.getAllGuests();

        assertEquals(expectedList.size(), actualList.size());
    }

}
