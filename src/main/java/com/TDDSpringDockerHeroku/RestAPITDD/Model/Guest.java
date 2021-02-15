package com.TDDSpringDockerHeroku.RestAPITDD.Model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Guest {

    @Id
    @GeneratedValue
    private Long Id;
    private String firstName;
    private String lastName;
    private String relationship;


    public Guest(String firstName, String lastName, String relationship) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.relationship = relationship;
    }
}
