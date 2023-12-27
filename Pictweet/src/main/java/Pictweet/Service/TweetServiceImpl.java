package Pictweet.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Pictweet.Entity.TweetEntity;
import Pictweet.Form.TweetForm;
import Pictweet.Repository.TweetRepository;

@Service
public class TweetServiceImpl implements TweetService {
	
	@Autowired
	TweetRepository tweetRepository;
	
	public void createTweet(TweetForm tweetForm) {
		TweetEntity tweet = new TweetEntity();
		tweet.setTweet(tweetForm.getTweet());
		tweet.setUserId(tweetForm.getUserId());
		
		tweetRepository.save(tweet);
	}
}
