package Pictweet.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Pictweet.Entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {
	//新規登録のメソッドはすでにあるため記述不要
	//ログイン時の処理
	//ログイン時に必要なログイン情報を取得
	public UserEntity findByLoginId(String loginId);
}
