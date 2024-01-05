package Pictweet.Service;

import java.util.List;

import Pictweet.Entity.TweetEntity;
import Pictweet.Form.TweetForm;

public interface TweetService {
	//一覧表示のメソッド
	public List<TweetEntity> findAll();
	
	//新規投稿のためのメソッド
	public void createTweet(TweetForm tweetForm);
	
	//個別情報取得メソッド
	public TweetEntity findTweet(Integer id);

	//編集メソッド
	public void editTweet(TweetForm tweetForm);
	
	//削除メソッド
	public void deleteTweet(Integer id);
}
