package com.project.bookservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookservice.entity.Book;
import com.project.bookservice.service.BookService;

@RestController
@RequestMapping(("/books"))
public class BookController {
	
	@Autowired
	private BookService bookService;

	@GetMapping("/get-book/{bookId}")
	public Book findBook(@PathVariable Long bookId) {
		return bookService.getBook(bookId);
	}
	
	@GetMapping("/books-by-genre/{genreId}")
	public List<Book> findAllBooksByGenres(@PathVariable Long genreId){
		return bookService.getBooksByGenreId(genreId);
	}
}
