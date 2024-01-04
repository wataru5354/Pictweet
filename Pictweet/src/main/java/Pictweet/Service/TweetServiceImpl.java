package Pictweet.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Pictweet.Entity.TweetEntity;
import Pictweet.Entity.UserEntity;
import Pictweet.Form.TweetForm;
import Pictweet.Repository.TweetRepository;
import Pictweet.Repository.UserRepository;

@Service
public class TweetServiceImpl implements TweetService {

	@Autowired
	TweetRepository tweetRepository;
	@Autowired
	UserRepository userRepository;

	//一覧表示メソッド
	public List<TweetEntity> findAll() {
		return tweetRepository.findAll();
	}

	//新規投稿メソッド
	public void createTweet(TweetForm tweetForm) {
		TweetEntity tweet = new TweetEntity();
		UserEntity tweetUser = userRepository.findByUserId(tweetForm.getUserId());
		tweet.setTweet(tweetForm.getTweet());
		tweet.setUser(tweetUser);
		tweetRepository.save(tweet);
	}
	
	//投稿情報取得メソッド
	public TweetEntity findTweet(Integer id) {
		TweetEntity findTweet = tweetRepository.getById(id);
		return findTweet; 
	}
	
	//編集メソッド
	public void editTweet(TweetForm tweetForm) {
		TweetEntity editTweet = new TweetEntity();
		UserEntity tweetUser = userRepository.findByUserId(tweetForm.getUserId());
		editTweet.setId(tweetForm.getId());
		editTweet.setTweet(tweetForm.getTweet());
		editTweet.setUser(tweetUser);
		tweetRepository.save(editTweet);
	}
}
