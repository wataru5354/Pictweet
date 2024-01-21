package Pictweet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Pictweet.Entity.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity,Integer> {
	//画像のID取得
	public ImageEntity findByImageId(Integer imageId);
}
