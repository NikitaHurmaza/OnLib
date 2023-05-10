package com.example.OnLib.dao;

import com.example.OnLib.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findBookByTitle(String title);

    List<Book> findByGenre(String genre);
}