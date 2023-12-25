package Pictweet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import Pictweet.Form.UserRegistrationForm;
import Pictweet.Service.UserService;

@Controller
public class PictweetController {
	
	@Autowired
	UserService userService;
	
	
	@GetMapping("/")
	public String login() {
		return "login";
	}
	
	/**
	 * 新規登録画面への遷移
	 * @return
	 */
	@GetMapping("registration")
	public String registration() {
		return "registration";
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
}
