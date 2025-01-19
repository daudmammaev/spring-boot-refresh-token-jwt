package com.jwt.jwts.security.services;

import com.jwt.jwts.Dto.DtoAutor;
import com.jwt.jwts.Dto.DtoBook;
import com.jwt.jwts.Dto.DtoReader;
import com.jwt.jwts.models.Autor;
import com.jwt.jwts.models.Book;
import com.jwt.jwts.repository.ReaderRepository;
import com.jwt.jwts.models.Reader;
import com.jwt.jwts.models.Transactional;
import com.jwt.jwts.repository.TransactionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static com.jwt.jwts.Mapping.Mapping.*;

@Service
public class TransactionalServicesImp implements TransactionalServices{
    @Autowired
    public TransactionalRepository transactionalRepository;
    @Autowired
    public ReaderRepository readerRepository;
    @Override
    public List<DtoReader> listOfAllReadersOfUndeliveredBooks() {
        List<Transactional> transactionalList = transactionalRepository.findAll();
        Map<Reader, Integer> countmap = new HashMap<>();
        transactionalList.forEach(e -> {
            if(countmap.containsKey(e) == false) {
                countmap.put(e.getReader(), 1);
            }else {
                countmap.put(e.getReader(), countmap.get(e.getReader()) + 1);
            }
        });

        Map<Reader, Integer> countmap2 = new HashMap<>();
        transactionalList.forEach(e -> {
            if(e.getTypeOfOperation() == "OutLibrary"){
                if(countmap2.containsKey(e) == false) {
                    countmap2.put(e.getReader(), 1);
                }else {
                    countmap2.put(e.getReader(), countmap2.get(e.getReader()) + 1);
                }
            }
        });
        Map<Reader, Integer> countmap3 = new HashMap<>();
        countmap.forEach((e1,v1) -> {
            countmap2.forEach((e2,v2) ->{
                if(e2 == e1){
                    countmap3.put(e1, v1-v2);
                }
            });
        });
        List<Reader> readerList = new ArrayList<>();
        countmap3.entrySet().stream().sorted(Map.Entry.comparingByValue());
        countmap3.forEach((e,v) -> readerList.add(e));
        List<DtoReader> readerList1 = new ArrayList<>();
        readerList.forEach(e -> readerList1.add(ReaderToDtoReader(e)));
        return readerList1;
    }

    @Override
    public DtoReader theMostReadingClient() {
        List<Transactional> transactionalList = transactionalRepository.findAll();
        Map<Reader, Integer> countmap = new HashMap<>();
        transactionalList.forEach(e -> {
            if(e.getTypeOfOperation() == "OutLibrary"){
                if(countmap.containsKey(e) == false ) {
                    countmap.put(e.getReader(), 1);
                }else {
                    countmap.put(e.getReader(), countmap.get(e.getReader()) + 1);
                }
            }

        });
        int max = 0;
        Reader reader = new Reader();
        for(Map.Entry<Reader, Integer> item : countmap.entrySet()) {
            if(item.getValue() > max){
                max = item.getValue();
                reader = item.getKey();
            }
        }
        return ReaderToDtoReader(reader);
    }

    @Override
    public DtoAutor theMostPopularAuthor(LocalDate start, LocalDate finish) {
        List<Transactional> transactionalList = transactionalRepository.findByDateOperationBetween(start,finish);
        Map<Autor, Integer> countmap = new HashMap<>();
        transactionalList.forEach(e -> {
            if(countmap.containsKey(e) == false) {
                e.getBook().getAutorArrayList().forEach(y -> {
                    if(countmap.containsKey(e) == false){
                        countmap.put(y, 1);
                    }else{
                        countmap.put(y, countmap.get(e.getReader()) + 1);
                    }
                });
            }
        });
        int max = 0;
        Autor autor = new Autor();
        for(Map.Entry<Autor, Integer> item : countmap.entrySet()) {
            if(item.getValue() > max){
                max = item.getValue();
                autor = item.getKey();
            }
        }

        return AutorToDtoAutor(autor);
    }

    @Override
    public List<DtoBook> transactional(long id, ArrayList<Book> list) {
        list.forEach(e -> {
            if (readerRepository.findById(id).isPresent()){
                if (transactionalRepository.findByReader(
                        readerRepository.findById(id).get()).getTypeOfOperation() == "InLibrary"){
                    Transactional transactional = new Transactional();
                    transactional.setBook(e);
                    transactional.setReader(readerRepository.findById(id).get());
                    transactional.setDateOperation(LocalDate.now());
                    transactional.setTypeOfOperation("OutLibrary");
                    transactionalRepository.save(transactional);
                }
            }
        });
        ArrayList<DtoBook> list1 = new ArrayList<>();
        list.forEach(e -> list1.add(BookToDtoBook(e)));
        return list1;
    }
}
