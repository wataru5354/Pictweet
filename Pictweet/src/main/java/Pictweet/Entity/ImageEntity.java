package Pictweet.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.Tolerate;

@Entity
@Data
@Table(name="images")
public class ImageEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="image_id")
	private int imageId;
	
	@Column(name="image_name")
	private String imageName;
	
	@Column(name="type")
	private String type;
	
	@Lob
	
	@Column(name="image")
	private String image;
	
	public ImageEntity(String imageName,String type, String image) {
		this.imageName = imageName;
		this.type = type;
		this.image = image;
		
	}
	
	@OneToOne(mappedBy="image")
	private TweetEntity tweetEntity;
	
	@Tolerate
	public ImageEntity() {
	}
	
}
