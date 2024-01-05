package Pictweet.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import Pictweet.Entity.TweetEntity;
import Pictweet.Entity.UserEntity;
import Pictweet.Form.LoginForm;
import Pictweet.Form.TweetForm;
import Pictweet.Form.UserRegistrationForm;
import Pictweet.Service.TweetService;
import Pictweet.Service.UserService;

@Controller
public class PictweetController {
	
	@Autowired
	UserService userService;
	@Autowired
	TweetService tweetService;
	
	
	@GetMapping("/")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	/**
	 * 新規登録画面への遷移
	 * @return
	 */
	@GetMapping("registration")
	public String registrationPage() {
		return "registration";
	}
	/*
	 * 新規投稿画面への遷移
	 */
	@GetMapping("tweet/{userId}")
	public String newTweetPage(@PathVariable("userId") Integer userId, 
			Model model) {
		UserEntity loginUser = userService.findByUserId(userId);
		model.addAttribute("loginUser",loginUser);
		return "new";
	}
	
	/*
	 * 編集画面への遷移
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id,
			Model model) {
		TweetEntity findTweet =tweetService.findTweet(id);
		UserEntity tweetUser = findTweet.getUser();
		model.addAttribute("findTweet",findTweet);
		model.addAttribute("tweetUser",tweetUser);
		return "edit";
	}
	
	/*
	 * 詳細画面への遷移処理
	 */
	@GetMapping("/show/{id}")
	public String show(@PathVariable("id") Integer id,
			Model model) {
		TweetEntity showTweet = tweetService.findTweet(id);
		model.addAttribute("showTweet",showTweet);
		return "show";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteTweet(@PathVariable("id") Integer id) {
		tweetService.deleteTweet(id);
		return "destroy";
	}
	
	
	@PostMapping("userRegistration")
	/**
	 * 新規登録の処理
	 * @param userRegistrationForm
	 * @param model
	 * @return
	 */
	public String userRegistration(@ModelAttribute UserRegistrationForm userRegistrationForm,
			Model model) {
		String password = userRegistrationForm.getPassword();
		String passwordConfirmation = userRegistrationForm.getPasswordConfirmation();
		String message = "";
		if(password.equals(passwordConfirmation)) {
			userService.createUser(userRegistrationForm);
			message = "登録に成功しました。";
			model.addAttribute("message",message);
			return "login";
		} else {
			message = "登録に失敗しました。";
			model.addAttribute("message",message);
			return "registration";
		}
	}
	
	/*
	 * ログイン制御
	 */
	@PostMapping("userLogin")
	public String userLogin(@ModelAttribute LoginForm loginForm,
			Model model) {
		UserEntity loginUser =  userService.loginUser(loginForm);
		String message = "";
		if(loginUser != null) {
			model.addAttribute("loginUser",loginUser);
			List<TweetEntity> tweets = tweetService.findAll();
			model.addAttribute("tweets",tweets);
			return "index";
		}else {
			message = "ログインに失敗しました。";
			model.addAttribute("message",message);
			return "login";
		}
		
	}
	
	//新規投稿
	@PostMapping("tweet/newTweet")
	public String newTweet(@ModelAttribute TweetForm tweetForm) {
		tweetService.createTweet(tweetForm);
		return "/create";
	}
	
	//編集
	@PostMapping("edit/editTweet")
	public String editTweet(@ModelAttribute TweetForm tweetForm) {
		tweetService.editTweet(tweetForm);
		return "/update";
	}
}
