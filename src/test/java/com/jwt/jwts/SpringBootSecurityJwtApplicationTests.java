package com.jwt.jwts;

import com.jwt.jwts.models.Autor;
import com.jwt.jwts.models.Book;
import com.jwt.jwts.models.Reader;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest
public class SpringBootSecurityJwtApplicationTests {
  @Test
  public void testTransactionCreation() {
    Reader reader = new Reader("1234567890", "Иван", "Иванов", "М", "1990-01-01");
    Book book = new Book(1L, "Война и мир", 1869, "Лев Толстой");
    Transaction transaction = new Transaction("взятие", LocalDateTime.now(), reader, book);

    assertEquals("взятие", transaction.getOperationType());
    assertNotNull(transaction.getOperationDateTime());
    assertEquals(reader, transaction.getClient());
    assertEquals(book, transaction.getBook());
  }
  @Test
  public void testReaderCreation() {
    Reader reader = new Reader("1234567890", "Иван", "Иванов", "М", "1990-01-01");
    assertEquals("1234567890", reader.getPhoneNumber());
    assertEquals("Иван", reader.getFirstName());
    assertEquals("Иванов", reader.getLastName());
    assertEquals("М", reader.getGender());
    assertEquals("1990-01-01", reader.getBirthDate());
  }
  @Test
  public void testAuthorCreation() {
    Autor author = new Autor(1L, "Лев", "Толстой", "1828-09-09");
    assertEquals(1L, author.getId());
    assertEquals("Лев", author.getFirstName());
    assertEquals("Толстой", author.getLastName());
    assertEquals("1828-09-09", author.getBirthDate());
  }
  @Test
  public void testBookCreation() {
    Book book = new Book(1L, "Война и мир", 1869, "Лев Толстой");
    assertEquals(1L, book.getId());
    assertEquals("Война и мир", book.getTitle());
    assertEquals(1869, book.getPublicationYear());
    assertEquals("Лев Толстой", book.getAuthors());
  }

}