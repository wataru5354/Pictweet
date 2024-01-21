package Pictweet.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import Pictweet.Entity.ImageEntity;
import Pictweet.Entity.TweetEntity;
import Pictweet.Entity.UserEntity;
import Pictweet.Form.TweetForm;

public interface TweetService {
	//一覧表示のメソッド
	public List<TweetEntity> findAll();
	
	//新規投稿のためのメソッド
	public void createTweet(TweetForm tweetForm,MultipartFile multipartFile) throws IOException;
	
	//個別情報取得メソッド
	public TweetEntity findTweet(Integer id);

	//編集メソッド
	public void editTweet(TweetForm tweetForm,MultipartFile multipartFile) throws IOException;
	
	//削除メソッド
	public void deleteTweet(Integer id);
	
	//マイページ取得
	public List<TweetEntity> findByUser(UserEntity user);
	
	public ImageEntity commonImage(MultipartFile multupartFiel) throws IOException ;
}
