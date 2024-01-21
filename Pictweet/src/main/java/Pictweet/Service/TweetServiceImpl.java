package Pictweet.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import Pictweet.Entity.ImageEntity;
import Pictweet.Entity.TweetEntity;
import Pictweet.Entity.UserEntity;
import Pictweet.Form.TweetForm;
import Pictweet.Repository.ImageRepository;
import Pictweet.Repository.TweetRepository;
import Pictweet.Repository.UserRepository;

@Service
public class TweetServiceImpl implements TweetService {

	@Autowired
	TweetRepository tweetRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ImageRepository imageRepository;

	//一覧表示メソッド
	public List<TweetEntity> findAll() {
		return tweetRepository.findAll();
	}
	//新規投稿メソッド
	public void createTweet(TweetForm tweetForm, MultipartFile multipartFile) throws IOException {
		String imageName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		byte[] bytes = multipartFile.getBytes();
		String imageByte = Base64.getEncoder().encodeToString(bytes);
		ImageEntity image = new ImageEntity(imageName,multipartFile.getContentType(),imageByte);
		imageRepository.save(image);
		TweetEntity tweet = new TweetEntity();
		UserEntity tweetUser = userRepository.findByUserId(tweetForm.getUserId());
		tweet.setTweet(tweetForm.getTweet());
		tweet.setUser(tweetUser);
		tweet.setImage(image);
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
	
	//削除メソッド
	public void deleteTweet(Integer id) {
		TweetEntity deleteTweet = tweetRepository.getById(id);
		ImageEntity deleteImage = deleteTweet.getImage();
		tweetRepository.delete(deleteTweet);
		imageRepository.delete(deleteImage);
	}
	
	//マイページ一覧表示メソッド
	public List<TweetEntity> findByUser(UserEntity user){
		return tweetRepository.findByUser(user);
	}
}
