[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

---
#### English | [简体中文](README_cn.md)

## Introduce
**Easy-Url**: An easy-to-use short link application.

Easy-Url is designed to shorten long URLs into easy-to-share and memorable short links. Simply input the long URL that needs to be shortened, and the application will generate a unique short link, making it convenient to share on social media, via text messages, or in emails. Short links not only save character length but also help users analyze link access through click statistics and other features. Additionally, the application supports custom short links, expiration time settings, and more.

## Features

- **Short link conversion**: Converts long URLs into short URLs for easy access.
- **Brute-force prevention**: Internally uses the Luhm check digit algorithm and chaotic base conversion.
- **URL access expiration limit**: Sets a valid access time for the generated short link.
- **Caching feature**: The application uses local caching to handle hot access data.

## Get Started
### Quick Start
To quickly experience Easy-Url, you can deploy it using Docker Compose.


```shell
# Enter the EasyUrl directory
cd EasyUrl/
# Deploy
docker-compose up --build -d
```

After deployment, create a short link with the following request, and then access the short link via a browser:

```shell
curl --location '127.0.0.1:8080/api/simplify' \
--header 'Content-Type: application/json' \
--data '{
"url": "https://www.baidu.com/"
}'
```

![](docker-compose/image/dashboard-import.jpg)

### Run Easy-Url Locally

`java -version`:
```shell
$ java -version
java version "1.8.0_161" 
```

**1) Create Database and Table**

Initialize the database and table structure in Easy-Url; refer to the init.sql table creation statements.

**2) Start Easy-Url**
```shell
### Start Easy-Url
$ nohup java -jar easy-url-1.0.0.jar > run.out 2>&1 &

### Check whether Easy-Url started successfully
$ tail -f ./logs/info.log
easyurl-easy-url-1  | 2024-09-23 21:39:07.661 [] [main] INFO  org.springframework.boot.web.embedded.tomcat.TomcatWebServer - Tomcat started on port(s): 8080 (http) with context path ''
easyurl-easy-url-1  | 2024-09-23 21:39:07.684 [] [main] INFO  com.dagm.shorter.EasyUrlApplication - Started EasyUrlApplication in 19.85 seconds (JVM running for 23.115)
```

You can run it by packaging or directly running the `com.dagm.shorter.EasyUrlApplication` class.

**3) Call the API to create a short link**
```shell
curl --location '127.0.0.1:8787/api/simplify' \
--header 'Content-Type: application/json' \
--data '{
"url": "https://www.baidu.com/"
}'
```

After completing these three steps, you can create a dashboard and start using it.

## TODO

- **Web UI Management**: Add admin management for short link data.
- **Service Monitoring**: Support open-source monitoring solutions, e.g., Prometheus, Grafana, etc.
- **Short Link Access Statistics**: Add access statistics to short links for business analysis.
- **Custom Storage Options**: Currently defaults to storage in MySQL; various storage options like OSS, Redis, etc., can be implemented based on business needs.
- **Custom Validation Algorithms**: The default validation algorithm is Luhm; it can be changed to a custom one.
- **Custom Distributed ID Generation**: Currently uses the Snowflake algorithm by default; it can be replaced with other distributed ID solutions that ensure integer output.

## Contact

If you wish to participate in building Easy-Url or need to discuss issues, you can join the WeChat group (both corporate and personal versions are welcome).

## License

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html) Copyright (C) Apache Software Foundation

