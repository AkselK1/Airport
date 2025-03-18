package com.cgi.airport;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TestDataobject {

    @Id
    private long id;
    @Column
    private String username;
}
