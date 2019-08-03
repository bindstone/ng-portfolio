package com.bindstone.portfolio.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User Object
 */
@Entity(name = "PF_USER")
@Builder
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    // No Link wanted between Account and User with Hibernate.
    private Long accountId;

    // GETTER; SETTER; TOSTRING; HASHCODE; EQUALS; BUILDER generated by Lombok
}
