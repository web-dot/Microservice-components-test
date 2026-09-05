package com.project.bookservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bookservice.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	public Book findByBookId(Long bookId);
	public List<Book> findByGenreId(Long genreId);
}
