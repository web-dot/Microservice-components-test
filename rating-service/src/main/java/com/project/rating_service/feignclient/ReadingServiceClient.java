package com.project.rating_service.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.rating_service.dto.ReadingStatusResponse;

@FeignClient(name = "reading-service")
public interface ReadingServiceClient {

	@GetMapping("/reading/{userId}/{bookId}")
	public ReadingStatusResponse getReadingStatus(
			@PathVariable Long userId,
			@PathVariable Long bookId);
}
