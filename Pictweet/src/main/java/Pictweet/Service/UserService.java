package Pictweet.Service;

import Pictweet.Entity.UserEntity;
import Pictweet.Form.LoginForm;
import Pictweet.Form.UserRegistrationForm;

public interface UserService {
	//新規登録の処理
	public void createUser(UserRegistrationForm userRegistrationForm);
	//ログイン時の処理
	public UserEntity loginUser(LoginForm loginForm);
}
