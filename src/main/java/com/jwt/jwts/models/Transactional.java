package com.jwt.jwts.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transactional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeOfOperation;
    private LocalDate dateOperation;
    @ManyToOne
    private Reader reader;
    @ManyToOne
    private Book book;



}