package com.jwt.jwts.Dto;

import com.jwt.jwts.models.Book;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToMany;
import java.util.ArrayList;
@Getter
@Setter
public class DtoAutor {

    private String name;
    private String surname;
    @ManyToMany
    private ArrayList<Book> bookArrayList;
}
