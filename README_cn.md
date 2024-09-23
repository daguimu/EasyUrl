[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

---


## Introduce

Easy-Url: 一个易于使用的短链应用

Easy-Url 旨在将长URL缩短为易于分享和记忆的短链接。只需输入需要缩短的长链接，应用便会生成一个唯一的短链接，方便在社交媒体、短信或电子邮件中分享。短链接不仅节省字符长度，还能通过点击统计等功能帮助用户分析链接的访问情况。此外，应用可以支持自定义短链接、过期时间设置等功能。


## Features

- 短链接转换: 将长的Url转换为短的Url,以便进行访问.
- 防爆破遍历: 内部使用了Luhm校验码算法以及混乱进制转换.
- Url 访问过期限制: 该应用生成的短链地址设置可访问的有效时间.
- 缓存功能: 应用内部使用本地缓存,处理热点访问数据.

## Get started
### quick start
要快速体验Easy-Url，可以通过docker-compose进行快速部署

[easy-url-quick-start文档]()

```shell
# 进入EasyUrl目录
cd EasyUrl/
# 部署
docker-compose up --build -d
```
部署完成后，通过如下请求创建短链，然后通过浏览器访问短链
curl --location '127.0.0.1:8080/api/simplify' \
--header 'Content-Type: application/json' \
--data '{
"url": "https://www.baidu.com/"
}'


![](docker-compose/image/dashboard-import.jpg)


### 本地运行 Easy-Url

`java -version`:
```shell
$ java -version
java version "1.8.0_161" 
```

**1）建库、建表**

在Easy-Url中初始化库、表结构，可以参考init.sql 建表语句部分.

**2）启动Easy-Url**
```shell
### start easy-url
$ nohup java -jar easy-url-1.0.0.jar > run.out 2>&1 &

### check whether easy-url is successfully started
$ tail -f ./logs/info.log
easyurl-easy-url-1  | 2024-09-23 21:39:07.661 [] [main] INFO  org.springframework.boot.web.embedded.tomcat.TomcatWebServer - Tomcat started on port(s): 8080 (http) with context path ''
easyurl-easy-url-1  | 2024-09-23 21:39:07.684 [] [main] INFO  com.dagm.shorter.EasyUrlApplication - Started EasyUrlApplication in 19.85 seconds (JVM running for 23.115)
```
打包运行或者本地运行直接com.dagm.shorter.EasyUrlApplication类即可

**3）调用接口创建短链**
```shell
curl --location '127.0.0.1:8787/api/simplify' \
--header 'Content-Type: application/json' \
--data '{
"url": "https://www.baidu.com/"
}'
```


以上3步都操作完以后，就可以创建大盘，开始进行使用了

## TODO

- WebUI 管理: 增加对短链数据的admin 管理
- 服务监控: 支持开源监控相关, eg: prometheus、grafana 等
- 短链访问统计: 对短链加上访问统计,便于业务分析.
- 存储源自定义: 目前默认是存储在mysql 中的,可根据业务需要实现多种存储如OSS、Redis 等
- 校验算法自定义: 目前默认的校验算法是Luhm,可改为自定义;
- 分布式ID生成自定义: 目前默认采用的是雪花算法, 可根据需要改为其他的分布式ID, 保证整型即可;

## 贡献者

<a href=""><img src="?width=890" /></a>

## Contact

愿意参与构建Easy-Url或者是需要交流问题可以加入微信群(企业版微信和个人版本微信均可)


## License

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html) Copyright (C) Apache Software Foundation

## Star ⭐
[![Stargazers over time]()]()