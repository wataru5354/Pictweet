package Pictweet.Form;

import lombok.Data;

@Data
public class UserRegistrationForm {
	private String nickname;
	private String loginId;
	private String password;
	private String passwordConfirmation;
}
