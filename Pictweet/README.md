# PicTweetのJava版を作成する。
※本来READMEはプロジェクトの重要性やユーザーに提供できる価値を記載す流ものだが、今回はアプリを作成する際の情報をまとめるために使用する。

## やるべきこと
1. 必要なデータを抽出  
2. データをまとめる  
3. データベースを図面で作成  
4. SQL文作成  
5. MySQLにてデータベース作成  
6. 実際にデータをいじり問題がないか調査  
7. Viewを作成(Java構文を使用せず、HTMLおよびCSSのみでUIのみを作成)  
8. データベースに沿ってモデルを作成(後で考えても良い)  
9. メソッド作成  
10. 以下の順に沿ってコントローラ作成すること  
    * ログイン
    * 新規登録  
    * 新規
    * 更新
    * 削除  
    ※ 一つずつ確認しながら行うこと。

### それぞれチェックボックスを用意しているので、これを元に進めること

- [x] データ抽出  
    ここに必要なデータを抽出
    * user_id：ユーザーID(指定可)
    * user_name：ユーザー名  
    * password1： パスワード
    * password2：パスワード確認用  
    * tweet：投稿内容  
    * user_id：登録した際のユーザーID(参照制約時に使用)
    * tweet_id：投稿ID(参照制約に使用)
    * comment：投稿内容に対するコメント
    * comment_id：コメントID

- [x] データ整理
    情報整理  
    * **user関連**  
      * user_id
      * user_name
      * password1
      * password2  

    * **投稿関連**
      * tweet
      * user_id
    
    * **コメント関連(後で作成する)**
    * comment
    * tweet_id
    * user_id

- [X] テーブル設計作成
    ## usersテーブル
    | Column            | Type   |Options                |
    |-------------------|--------|-----------------------|
    | user_id           | string | not null, unique:true |
    | name              | string | not null              |
    | password          | string | not null              |

    ### Association

    - has_many :tweets

    ## tweetsテーブル
    | Column           | Type       |Options                     |
    |------------------|------------|----------------------------|
    | tweet            | string     | not null                   |
    | user_id          | references | not null,foreign_key: true |

    ### Association

    - belongs_to :user

- [x] SQL文作成
    ## users　テーブル
    CREATE TABLE users(
        user_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
        login_id VARCHAR(30) NOT NULL,
        nickname VARCHAR(30) NOT NULL,
        password VARCHAR(255) NOT NULL);

    ## tweets テーブル
    CREATE TABLE tweets(
        id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
        tweet VARCHAR(255) NOT NULL,
        user_id INT NOT NULL,
        FOREIGN KEY (user_id) REFERENCES user (user_id) 
    );

- [x] MySQLにて実際にデータベース作成

- [x] 動作確認


- [ ] View作成


- [ ] モデル作成

- [ ] コントローラ作成  







