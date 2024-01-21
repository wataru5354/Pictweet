package Pictweet.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="tweets")
public class TweetEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="tweet")
	private String tweet;
	
	@ManyToOne
	@JoinColumn(name="user_id",referencedColumnName="user_id")
	private UserEntity user;
	
	@OneToOne
	@JoinColumn(name="image_id",referencedColumnName="image_id")
	private ImageEntity image;
	
}
