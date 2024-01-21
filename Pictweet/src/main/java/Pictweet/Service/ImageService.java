package Pictweet.Service;

import java.util.List;

import Pictweet.Entity.ImageEntity;

public interface ImageService {
	//画像情報全取得
	public List<ImageEntity> findAll();
	
	//個別画像取得メソッド
	public ImageEntity findByImageId(Integer imageId);
}
