package com.itechart.error.handling.mvc;

import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository {

    public Person getPerson(String name) {
        return new Person(name);
    }
}
