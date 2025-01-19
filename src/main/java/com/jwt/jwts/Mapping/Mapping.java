package com.jwt.jwts.Mapping;

import com.jwt.jwts.Dto.DtoAutor;
import com.jwt.jwts.Dto.DtoBook;
import com.jwt.jwts.Dto.DtoReader;
import com.jwt.jwts.models.Autor;
import com.jwt.jwts.models.Book;
import com.jwt.jwts.models.Reader;

public class Mapping {
    public static DtoReader ReaderToDtoReader(Reader reader){
        DtoReader dtoReader = new DtoReader();
        dtoReader.setBirthday(reader.getBirthday());
        dtoReader.setName(reader.getName());
        dtoReader.setSurname(reader.getSurname());
        dtoReader.setBirthday(reader.getBirthday());
        return dtoReader;
    }
    public static Reader DtoReaderToReader(DtoReader dtoReader){
        Reader reader = new Reader();
        reader.setBirthday(dtoReader.getBirthday());
        reader.setName(dtoReader.getName());
        reader.setSurname(dtoReader.getSurname());
        reader.setBirthday(dtoReader.getBirthday());
        return reader;
    }
    public static DtoAutor AutorToDtoAutor(Autor autor){
        DtoAutor dtoAutor = new DtoAutor();
        dtoAutor.setName(autor.getName());
        dtoAutor.setSurname(autor.getSurname());
        dtoAutor.setBookArrayList(autor.getBookArrayList());
        return dtoAutor;
    }
    public static DtoBook BookToDtoBook(Book book){
        DtoBook dtoBook = new DtoBook();
        dtoBook.setAutorArrayList(book.getAutorArrayList());
        dtoBook.setDateCreate(book.getDateCreate());
        dtoBook.setName(book.getName());
        return dtoBook;
    }
}
