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

## 4. ブランチを切り替える

デフォルトだと master branch にいるので、Pull Request 用の branch `PR-me` に checkout します

```
$ git checkout -b PR-me origin/PR-me
```

```
$ git branch 

* PR-me
  master
```

で自分のブランチに**＊**マークがあることを確認する

**-------------初回でここまで-------------**

**-------------ここから各問題毎に繰り返し-------------**

## 5. 問題の branch を自分の branch から切る

```
$ git checkout -b shimizu_ryoichi_elm_1
```

## 6. コーディング

当該の問題の branch にコミットを積んでいく。解き終わってから commit するよりは、粒度を決めてこまめに commit する方があとからレビューしやすそう。思考の流れとかが。

## 7. ローカルレポジトリにコミットする

```
$ git add shimizu_ryoichi/elm_1/*
$ git commit -m 'コミットメッセージ'
```

## 8. プログラムの目処がついたら、GitHubの自分のレポジトリにプッシュする

```
$ git push origin shimizu_ryoichi_elm_1
```

## 9. Pull request を送る

現場等で PR を送るときには内容などをしっかりと簡潔に明記するほうがよいですが、今回はあまり気にせず面白さ重視でやっていきましょう。

問題の branch から PR-me の branch に PR を送ります。

`PR-me:shimizu_ryoichi_elm_1` というような感じで。

### merge のフロー

PR は3人以上のレビューがあるのが望ましいです。レビューをして気になる箇所があれば、適宜ソースコードにコメントを追加すると PR で参照することができて便利です。3人以上のレビューが通ったら自分で close します。

(例)

1. Aさんがpush
2. AさんがPRを送る
3. BさんとCさんのLGTM(Looks Good To Me)をもらえたが、Dさんに指摘をうけた
4. Aさんがコードを直してpush
5. DさんのLGTMをもらう
6. Aさんがclose

このこのようなサイクルを回してレビューしていきましょう。

別の問題にトライするときには、一旦 `PR-me` の branch に checkout してから5番からやります。

わからない場合には https://github.com/matabesu/codereview2014/pull/19 を参考にしてみてください。
