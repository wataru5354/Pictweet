package Pictweet.Form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegistrationForm {
	@NotBlank
	private String nickname;
	@NotBlank
	private String loginId;
	@NotBlank
	private String password;
	@NotBlank
	private String passwordConfirmation;
}
