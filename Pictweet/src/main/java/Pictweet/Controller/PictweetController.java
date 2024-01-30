package Pictweet.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import Pictweet.Entity.ImageEntity;
import Pictweet.Entity.TweetEntity;
import Pictweet.Entity.UserEntity;
import Pictweet.Form.LoginForm;
import Pictweet.Form.TweetForm;
import Pictweet.Form.UserRegistrationForm;
import Pictweet.Service.ImageService;
import Pictweet.Service.TweetService;
import Pictweet.Service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("LoginForm")
public class PictweetController {

	@Autowired
	UserService userService;
	@Autowired
	TweetService tweetService;
	@Autowired
	ImageService imageService;
	@Autowired
	HttpSession session;

	@GetMapping("/")
	public String TopPage(Model model) {
		UserEntity loginUser =(UserEntity)session.getAttribute("loginUser");
		if(loginUser != null) {
			List<TweetEntity> tweets = tweetService.findAll();
			model.addAttribute("tweets", tweets);
			model.addAttribute("loginUser",loginUser);
			return "index";
		}
			return "login";
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
	@GetMapping("/tweet/{userId}")
	public String newTweetPage(@PathVariable("userId") Integer userId,
			Model model) {
		UserEntity loginUser = userService.findByUserId(userId);
		model.addAttribute("loginUser", loginUser);
		return "new";
	}

	/*
	 * 編集画面への遷移
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id,
			Model model) {
		TweetEntity findTweet = tweetService.findTweet(id);
		UserEntity tweetUser = findTweet.getUser();
		model.addAttribute("findTweet", findTweet);
		model.addAttribute("tweetUser", tweetUser);
		return "edit";
	}

	/*
	 * 詳細画面への遷移処理
	 */
	@GetMapping("/show/{id}")
	public String show(@PathVariable("id") Integer id,
			Model model) {
		UserEntity loginUser =(UserEntity)session.getAttribute("loginUser");
		TweetEntity showTweet = tweetService.findTweet(id);
		ImageEntity tweetImage = showTweet.getImage();
		model.addAttribute("showTweet", showTweet);
		model.addAttribute("loginUser",loginUser);
		return "show";
	}

	//削除処理
	@GetMapping("/delete/{id}")
	public String deleteTweet(@PathVariable("id") Integer id) {
		tweetService.deleteTweet(id);
		return "destroy";
	}
	
	//ログアウト処理
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "login";
	}
	
	//マイページ表示機能
	@GetMapping("mypage/{userId}")
	public String Myindex(@PathVariable("userId") Integer userId, Model model) {
		UserEntity loginUser = (UserEntity)session.getAttribute("loginUser");
		UserEntity user = userService.findByUserId(userId);
		List<TweetEntity> myTweets = tweetService.findByUser(user);
		model.addAttribute("tweets",myTweets);
		model.addAttribute("loginUser",loginUser);
		return "index";
	}

	@PostMapping("userRegistration")
	/**
	 * 新規登録の処理
	 * @param userRegistrationForm
	 * @param model
	 * @return
	 */
	public String userRegistration(@Validated @ModelAttribute UserRegistrationForm userRegistrationForm,
			BindingResult bindingResult,Model model) {
		String password = userRegistrationForm.getPassword();
		String passwordConfirmation = userRegistrationForm.getPasswordConfirmation();
		String message = "";
		if (bindingResult.hasErrors()) {
			message = "登録に失敗しました";
			model.addAttribute("message",message);
			return "registration";
		}
		if (password.equals(passwordConfirmation)) {
			userService.createUser(userRegistrationForm);
			message = "登録に成功しました。";
			model.addAttribute("message", message);
			return "login";
		} else {
			message = "パスワードが一致しません。";
			model.addAttribute("message", message);
			return "registration";
		}
	}

	/*
	 * ログイン制御
	 */
	@PostMapping("userLogin")
	public String userLogin(@Validated @ModelAttribute LoginForm loginForm,
			BindingResult bindingResult,Model model) {
		UserEntity loginUser = userService.loginUser(loginForm);
		String message = "";
		if(bindingResult.hasErrors()) {
			message = "ログインIDとパスワードを入力してください";
			model.addAttribute("message",message);
			return "login";
		}
		if (loginUser != null) {
			session.setAttribute("loginUser", loginUser);
			model.addAttribute("loginUser", loginUser);
			List<TweetEntity> tweets = tweetService.findAll();
			model.addAttribute("tweets", tweets);
			return "index";
		} else {
			message = "ログインに失敗しました。";
			model.addAttribute("message", message);
			return "login";
		}

	}

	//新規投稿
	@PostMapping("tweet/newTweet")
	public String newTweet(@ModelAttribute TweetForm tweetForm,@RequestParam("file") MultipartFile multipartFile) throws IOException {
		tweetService.createTweet(tweetForm,multipartFile);
		return "/create";
	}

	//編集
	@PostMapping("edit/editTweet")
	public String editTweet(@ModelAttribute TweetForm tweetForm,@RequestParam("file") MultipartFile multipartFile)throws IOException {
		tweetService.editTweet(tweetForm,multipartFile);
		return "/update";
	}
}
