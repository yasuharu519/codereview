## 目次

1. Gitをインストールする
2. SSH公開鍵の設定をする
3. レポジトリをcloneする
4. ブランチに切り替える
5. コーディング
6. ローカルレポジトリにコミットする
7. プログラムの目処がついたら、GitHubの自分のレポジトリにプッシュする

## 1. Gitをインストールする
会社から借りているPCにはすでに**Git**が入ってるかもしれないのでまずは確認
```
$ git --version
```
以下のような結果になればGitはすでに入っているので2番にスキップ
```
$ git version 1.8.5.2
```

_"gitというコマンドが見当たらないないよ"_と言われたら[インストール](http://git-scm.com/)


## 2. SSH公開鍵の設定をする

[ここ](https://help.github.com/articles/generating-ssh-keys)を見てSSH鍵作成をする

## 3. このレポジトリをcloneする

```
$ git clone https://github.com/matabesu/codereview.git
```

## 4. ブランチに切り替える

```
$ git checkout -b きみの会社メールアドレスの@以前
```

例：
````
$ git checkout -b shimizu_ryoichi
```

```
$ git branch 

* shimizu_ryoichi
  master
```

で自分のブランチに**＊**マークがあることを確認する

## 5. コーディング

...

## 6. ローカルレポジトリにコミットする

```
$ git add shimizu_ryoichi/*
$ git commit -m 'コミットメッセージ'
```

## 7. プログラムの目処がついたら、GitHubの自分のレポジトリにプッシュする

```
$ git push origin shimizu_ryoichi
```

以上


