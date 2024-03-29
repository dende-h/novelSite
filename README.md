## JavaとSpringbootの学習のためのサンプルCRUDアプリケーションです。
- アーキテクチャ構成は下記のような構成になります。
![Architecture drawio](https://github.com/dende-h/novelSite/assets/84645413/f63d7c46-fe1e-4edb-944b-31e1c99cf56b)


- ER図  
![NovelSiteEntity drawio](https://github.com/dende-h/novelSite/assets/84645413/b4af5d74-68ec-4e3b-a759-e3981dae5692)

### 機能面:
1. **ユーザー認証および認可:**
   - このアプリケーションは、ユーザーログインおよびサインアップ機能を提供します。
   - ユーザーは、自分の資格情報を使用してサインアップおよびログインできます。
   - アプリケーションは、認証および認可のためにSpring Securityを使用します。

2. **小説の作成:**
   - ユーザーは小説のドラフトを書き、保存できます。
   - アプリケーションは、小説のドラフトを書き、保存するためのインターフェースを提供します。

### 使用技術:
1. **Spring Boot:** アプリケーションはSpring Bootを使用して構築されています。
2. **Thymeleaf:** ビューのレンダリングのためのテンプレートエンジンとして使用されます。
3. **MyBatis:** MySQLデータベースとの対話のためのORMフレームワークとして使用されます。
4. **MySQL:** アプリケーションは、`application.properties`ファイルに示されているように、データの永続化のためにMySQLデータベースを使用します。
5. **Spring Security:** アプリケーションのセキュリティ、ログインの処理、および認可のために使用されます。
6. **Maven:** プロジェクトの依存関係とビルドライフサイクルの管理のために使用されます。

### 各レイヤーのロジック:
1. **コントローラーレイヤー:**
   - `UserLoginController`はログインページのレンダリングを処理します。
   - `UserSignUpController`はユーザーのサインアップを処理し、サインアップフォームの表示、入力チェック、およびサインアッププロセスの完了を含みます。
   - `NovelContoller`は小説の執筆ページのレンダリングと小説のドラフトの保存を処理します。

2. **サービスレイヤー:**
   - `UserSignUpService`は新しいユーザーの作成を処理し、保存前にパスワードを暗号化します。
   - `UserDetailsServiceImpl`は認証のためのユーザー詳細をロードします。
   - `NovelService`は小説のドラフトの保存を処理します。

3. **ORマッパー:**
   - `NovelMapper`、`LoginMapper`、および`UserSignUpMapper`は、MyBatisのアノテーションを使用してデータベースと対話するためのメソッドを定義するインターフェースです。
   - `NovelMapper`には、`draft_novels`テーブルに小説のドラフトを挿入するための`save`メソッドがあります。
   - `UserSignUpMapper`には、ユーザー詳細とロールをそれぞれのテーブルに挿入するための`create`および`createRole`メソッドがあります。
   - `LoginMapper`には、認証のためのユーザー詳細を取得する`identifyUser`メソッドがあります。

### 設定:
- `application.properties`ファイルには、MySQLデータベース接続の設定が含まれており、URL、ユーザー名、およびパスワードを含みます。
- `SecurityConfig`クラスには、Spring Securityの設定が含まれており、認証が必要なURLパターンとログインプロセスの設定を含みます。

### コードスニペットの例:
```java
// NovelContoller.java
@PostMapping("/novel/save/draft")
public String saveDraft(@ModelAttribute NovelRequest draft, Model model) {
    novelService.save(draft);
    return "novel/new_novel";
}
```

```java
// NovelService.java
public void save(NovelRequest draft) {
    novelMapper.save(draft);
}
```

```java
// NovelMapper.java
@Insert("INSERT INTO draft_novels(genre,novel_length,title,chapter,section,clause,text,user_id)"
        + "VALUES (#{genre},#{novelLength},#{title},#{chapter},#{section},#{clause},#{text},#{userId})")
NovelEntity save(NovelRequest draft);
```

このコードスニペットは、コントローラーレイヤーから始まり、サービスレイヤーを経由し、最終的にORマッパーを使用してMySQLデータベースに保存するロジックを表しています。

---
