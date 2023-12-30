package Pictweet.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class UserEntity {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	
	@Id
	@Column(name="login_id")
	private String loginId;
	
	@Column(name="nickname")
	private String nickname;
	
	@Column(name="password")
	private String password;
	
	@OneToMany(mappedBy="user")
	private List<TweetEntity> tweets;
}
