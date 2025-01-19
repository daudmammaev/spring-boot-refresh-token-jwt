package com.jwt.jwts.controllers;

import com.jwt.jwts.Dto.DtoAutor;
import com.jwt.jwts.Dto.DtoBook;
import com.jwt.jwts.Dto.DtoReader;
import com.jwt.jwts.models.Book;
import com.jwt.jwts.models.Transactional;
import com.jwt.jwts.security.services.TransactionalServices;
import com.jwt.jwts.security.services.TransactionalServicesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	public TransactionalServicesImp transactionalServicesImp;
	@PostMapping("/transactional")
	public ResponseEntity<List<DtoBook>> transactional(long id, ArrayList<Book> list){
		return ResponseEntity.ok(transactionalServicesImp.transactional(id,list));
	}
	@GetMapping("/theMostPopularAuthor")
	public ResponseEntity<DtoAutor> theMostPopularAuthor(LocalDate start, LocalDate finish){
		return ResponseEntity.ok(transactionalServicesImp.theMostPopularAuthor(start,finish));
	}
	@GetMapping("/theMostReadingClient")
	public ResponseEntity<DtoReader> theMostReadingClient(){
		return ResponseEntity.ok(transactionalServicesImp.theMostReadingClient());
	}
	@GetMapping("/listOfAllReadersOfUndeliveredBooks")
	public ResponseEntity<List<DtoReader>> listOfAllReadersOfUndeliveredBooks(){
		return ResponseEntity.ok(transactionalServicesImp.listOfAllReadersOfUndeliveredBooks());
	}
}
