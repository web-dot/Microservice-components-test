package com.project.bookservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bookservice.entity.Book;
import com.project.bookservice.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public Book getBook(Long bookId) {
		return bookRepository.findByBookId(bookId);
	}
	
	public List<Book> getBooksByGenreId(Long genreId){
		return bookRepository.findByGenreId(genreId);
	}
}
