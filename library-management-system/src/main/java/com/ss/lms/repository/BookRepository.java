package com.ss.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ss.lms.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

	Book findByIsbn(@Param(value = "isbn") String isbn);
	Book findByTitleAndAuthor(@Param(value = "title") String title, @Param(value = "author") String author);
	
}
