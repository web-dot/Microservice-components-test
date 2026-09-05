package com.project.reading_service;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.reading_service.entity.ReadingStatus;
import com.project.reading_service.service.ReadingService;


@RestController
@RequestMapping("/reading")
public class ReadingController {
	
	@Autowired
	public ReadingService readingService;
	
	@PostMapping("/update")
	public ResponseEntity<ReadingStatus> updateStatus(@RequestParam Long userId, @RequestParam Long bookId, @RequestParam String status) {
		ReadingStatus result = readingService.setStatus(userId, bookId, status);
		return ResponseEntity.status(HttpStatus.SC_CREATED).body(result);
	}
	
	@GetMapping("/{userId}/{bookId}")
	public ResponseEntity<ReadingStatus> getReadingStatus(
	        @PathVariable Long userId,
	        @PathVariable Long bookId) {

	    return ResponseEntity.ok(
	        readingService.getStatus(userId, bookId)
	    );
	}
}
