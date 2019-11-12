package com.thoughtworks.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
@Entity
@Table(name = "user")
public class User {

    @Id
    private String id;


    private String username;

    private String fullName;

    private String password;
}
