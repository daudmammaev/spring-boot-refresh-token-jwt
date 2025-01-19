package com.jwt.jwts.repository;

import com.jwt.jwts.models.Reader;
import com.jwt.jwts.models.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionalRepository extends JpaRepository<Transactional, Long> {
    Transactional findByReader(Reader reader);
    List<Transactional> findByDateOperationBetween(LocalDate start, LocalDate finish);
}
