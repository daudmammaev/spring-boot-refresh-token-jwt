package com.jwt.jwts.security.services;

import com.jwt.jwts.Dto.DtoAutor;
import com.jwt.jwts.Dto.DtoBook;
import com.jwt.jwts.Dto.DtoReader;
import com.jwt.jwts.models.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface TransactionalServices {
    List<DtoReader> listOfAllReadersOfUndeliveredBooks();
    DtoReader theMostReadingClient();
    DtoAutor theMostPopularAuthor(LocalDate start, LocalDate finish);
    List<DtoBook> transactional(long id, ArrayList<Book> list);
}
