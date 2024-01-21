package Pictweet.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Pictweet.Entity.ImageEntity;
import Pictweet.Repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {
	@Autowired
	ImageRepository imageRepository;
	
	
	public List<ImageEntity> findAll(){
		List<ImageEntity> images = imageRepository.findAll();
		return images;
	}
	
	public ImageEntity findByImageId(Integer imageId) {
		ImageEntity image = imageRepository.getById(imageId);
		return image;
	}
}
