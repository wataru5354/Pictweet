package Pictweet.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Pictweet.Entity.UserEntity;
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
	
}
