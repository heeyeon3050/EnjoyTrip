package com.ssafy.enjoytrip.image.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoytrip.image.repository.ImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {
	private final S3UploadService s3UploadService;
	private final ImageRepository imageRepository;

	// public Image upload(MultipartFile image, Long boardId) throws IOException {
	// 	System.out.println("Diary service saveDiary");
	// 	if(!image.isEmpty()) {
	// 		String storedFileName = s3UploadService.upload(image,"images");
	// 		Image newImage = Image.builder()
	// 			.url(storedFileName)
	// 			.board()
	// 			.build();
	// 		//board.getImages().add(newImage);
	// 		Image savedImage = imageRepository.save(newImage);
	// 		return savedImage;
	// 	} else {
	// 		throw new RuntimeException();
	// 	}
	// }

	public String upload(MultipartFile image) throws IOException {
		System.out.println("Diary service saveDiary");
		if(!image.isEmpty()) {
			return s3UploadService.upload(image,"images");
		} else {
			throw new RuntimeException();
		}
	}
}
