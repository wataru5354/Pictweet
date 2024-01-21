package Pictweet.Form;

import lombok.Data;

@Data
public class TweetForm {
	private int id;
	private String tweet;
	private int userId; 
	private int imageId;
}
