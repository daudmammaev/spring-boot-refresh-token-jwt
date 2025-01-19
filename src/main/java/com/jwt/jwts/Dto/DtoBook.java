package com.jwt.jwts.Dto;

import com.jwt.jwts.models.Autor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToMany;
import java.util.ArrayList;
@Getter
@Setter
public class DtoBook {

    private String name;
    private int dateCreate;
    @ManyToMany
    private ArrayList<Autor> autorArrayList;
}
