package Pictweet.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Pictweet.Entity.UserEntity;
import Pictweet.Form.LoginForm;
import Pictweet.Form.UserRegistrationForm;
import Pictweet.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	public void createUser(UserRegistrationForm userRegistrationForm) {
		//インスタンスを生成し、必要な情報を格納
		UserEntity user = new UserEntity();
		user.setLoginId(userRegistrationForm.getLoginId());
		user.setNickname(userRegistrationForm.getNickname());
		user.setPassword(userRegistrationForm.getPassword());

		//格納した情報を保存する
		userRepository.save(user);
	}

	//ログイン機能の実装
	public UserEntity loginUser(LoginForm loginForm) {
		UserEntity loginUser = new UserEntity();
		String loginId = loginForm.getLoginId();
		loginUser = userRepository.findByLoginId(loginId);
		String password = loginUser.getPassword();
		if (password.equals(loginForm.getPassword())) {
			return loginUser;
		} else {
			return null;
		}
	}
}
