package Pictweet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Pictweet.Entity.TweetEntity;

@Repository
public interface TweetRepository extends JpaRepository<TweetEntity,Integer> {

}
