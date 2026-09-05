package com.project.recommendation_service.feignclient;

import java.awt.print.Book;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.recommendation_service.entity.BookResponseDto;

@FeignClient(name = "book-service")
public interface BookServiceClient {

	@GetMapping("/books/get-book/{bookId}")
	BookResponseDto getBook(@PathVariable Long bookId);
	
	@GetMapping("/books/books-by-genre/{genreId}")
	List<BookResponseDto> getBooksByGenre(@PathVariable Long genreId);
}
