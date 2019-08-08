package com.itechart.error.handling.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class PersonRepository {

    public Person getPerson(String name) {
        if (name.toLowerCase().startsWith("i")) {
            throw new DataAccessException("name cannot start with i");
        }
        return new Person(name);
    }
}
