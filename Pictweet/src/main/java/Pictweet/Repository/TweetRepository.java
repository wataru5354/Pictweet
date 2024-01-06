package Pictweet.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Pictweet.Entity.TweetEntity;
import Pictweet.Entity.UserEntity;

@Repository
public interface TweetRepository extends JpaRepository<TweetEntity,Integer> {
	public List<TweetEntity> findByUser(UserEntity user);
}
