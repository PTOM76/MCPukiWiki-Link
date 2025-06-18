# MCPukiWiki Link
無駄に利用用途が不明なmodですが、マイクラからPukiWikiのページを読み込んだり書き込んだりとかできます。
PukiBot使ってるので使うにはbot.inc.phpをPukiWiki側に入れてセットアップする必要があります。

## MCPukiWiki-Link MOD コマンド一覧

### `/pkwklink`
ルートコマンドです。実行するとMODの挨拶メッセージが表示されます。

-   **権限レベル:** 0 (誰でも実行可能)
-   **サブコマンド:**
    -   `help`: ヘルプメッセージを表示します。
    -   `reload`: 設定ファイルを再読み込みします。
    -   `link`: PukiWikiとの連携設定を行います。
    -   `info`: PukiWikiから情報を取得します。
    -   `page`: PukiWikiのページを操作します。

---

### `/pkwklink help`
MODのコマンドヘルプを表示します。

-   **権限レベル:** (PWLCommand.javaで定義されているため、PWLCommandの権限レベルに依存。デフォルトは0)
-   **実行例:** `/pkwklink help`
-   **説明:** 利用可能なコマンドの一覧とその簡単な説明を表示します。

---

### `/pkwklink reload`
MODの設定ファイルを再読み込みします。

-   **権限レベル:** (ReloadCommand.javaで定義されているため、PWLCommandの権限レベルに依存。デフォルトは0)
-   **実行例:** `/pkwklink reload`
-   **説明:** `pkwklink.properties` などの設定ファイルを再読み込みし、変更をMODに反映させます。

---

### `/pkwklink link <url> <token>`
PukiWikiとの連携設定を行います。

-   **権限レベル:** 3 (オペレーター権限が必要)
-   **引数:**
    -   `<url>`: 連携するPukiWikiのURL (例: `http://example.com/pukiwiki/`)
    -   `<token>`: PukiWikiのAPIトークン
-   **実行例:** `/pkwklink link http://example.com/pukiwiki/ abcdef12345`
-   **説明:** 指定されたURLとトークンを使用して、MODとPukiWikiを連携させます。設定は保存され、MODがPukiWikiと通信できるようになります。
-   **引数なしで実行した場合:** 現在の連携情報を表示します（トークンは一部マスクされます）。

---

### `/pkwklink info`
連携しているPukiWikiから基本情報を取得します。

-   **権限レベル:** (InfoCommand.javaで定義されているため、PWLCommandの権限レベルに依存。デフォルトは0)
-   **実行例:** `/pkwklink info`
-   **説明:** 連携設定済みのPukiWikiからサイトのタイトルや管理者名などの情報を取得して表示します。連携に失敗している場合はエラーメッセージが表示されます。

---

### `/pkwklink page`
PukiWikiのページを操作するための親コマンドです。

-   **権限レベル:** 2
-   **サブコマンド:**
    -   `read <page>`: 指定したページを読み込みます。
    -   `write <page> <source>`: 指定したページに内容を書き込みます。
    -   `append <page> <source> (<notimestamp>)`: 指定したページに内容を追記します。
-   **実行例 (サブコマンドなし):** `/pkwklink page` (使用方法のヒントが表示されます)
-   **説明:** PukiWikiのページの閲覧、編集、追記を行うためのコマンド群です。

---

### `/pkwklink page read <page>`
PukiWikiの指定されたページの内容を読み込み、表示します。

-   **権限レベル:** (PageCommandのサブコマンドのため、PageCommandの権限レベルに依存。デフォルトは2)
-   **引数:**
    -   `<page>`: 読み込むPukiWikiのページ名
-   **実行例:** `/pkwklink page read FrontPage`
-   **説明:** 指定されたPukiWikiのページの内容を取得し、チャット欄に表示します。

---

### `/pkwklink page write <page> <source>`
PukiWikiの指定されたページに新しい内容を書き込みます（既存の内容は上書きされます）。

-   **権限レベル:** (PageCommandのサブコマンドのため、PageCommandの権限レベルに依存。デフォルトは2)
-   **引数:**
    -   `<page>`: 書き込むPukiWikiのページ名
    -   `<source>`: 書き込む内容
-   **実行例:** `/pkwklink page write TestPage "これはテストページです。"`
-   **説明:** 指定されたPukiWikiのページに、指定された内容を書き込みます。ページが存在しない場合は新規作成されることがあります。

---

### `/pkwklink page append <page> <source> (<notimestamp>)`
PukiWikiの指定されたページに内容を追記します。

-   **権限レベル:** (PageCommandのサブコマンドのため、PageCommandの権限レベルに依存。デフォルトは2)
-   **引数:**
    -   `<page>`: 追記するPukiWikiのページ名
    -   `<source>`: 追記する内容
    -   `(<notimestamp>)`: (オプション) `true` を指定すると、追記時にタイムスタンプを付与しません。デフォルトはタイムスタンプを付与します。
-   **実行例:**
    -   `/pkwklink page append LogPage "新しいログエントリ"`
    -   `/pkwklink page append LogPage "タイムスタンプなしのログ" true`
-   **説明:** 指定されたPukiWikiのページの末尾に、指定された内容を追記します。
