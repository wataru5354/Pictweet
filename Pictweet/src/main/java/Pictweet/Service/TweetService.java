package Pictweet.Service;

import java.util.List;

import Pictweet.Entity.TweetEntity;
import Pictweet.Form.TweetForm;

public interface TweetService {
	//一覧表示のメソッド
	public List<TweetEntity> findAll();
	
	//新規投稿のためのメソッド
	public void createTweet(TweetForm tweetForm);
	
	//編集用のメソッド
	public TweetEntity edit(Integer id);
	
	public void editTweet(TweetForm tweetForm);
}
