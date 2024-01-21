package Pictweet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Pictweet.Entity.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity,Integer> {
}
