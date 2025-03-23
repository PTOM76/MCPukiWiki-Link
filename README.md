# MCPukiWiki Link
無駄に利用用途が不明なmodですが、マイクラからPukiWikiのページを読み込んだり書き込んだりとかできます。
PukiBot使ってるので使うにはbot.inc.phpをPukiWiki側に入れてセットアップする必要があります。

## repositories
- build.gradle
```build.gradle
    maven { url "https://maven.pkg.github.com/PTOM76/maven" }
```

- pom.xml
```pom.xml
        <repository>
          <id>github</id>
          <url>https://maven.pkg.github.com/OWNER/REPOSITORY</url>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
        </repository>
```

## dependencies
- build.gradle
```build.gradle
    implementation "net.pitan76:pukibot:1.0.0"
```

- pom.xml
```pom.xml
<dependency>
  <groupId>net.pitan76</groupId>
  <artifactId>pukibot</artifactId>
  <version>1.0.0</version>
</dependency>
```
