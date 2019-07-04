0. Git 주소

1. 로그인 사용자
    admin // admin
    user // user

2. 우대 사항에 대한 작업
    1. Cache를 이용하여 인기 키워드 목록 조회
    2. 로그인 동시에 안되도록 막음.

3. jav 파일 다운로드 경로 :



<!-- thymeleaf-layout-dialect -->
        <dependency>
            <groupId>nz.net.ultraq.thymeleaf</groupId>
            <artifactId>thymeleaf-layout-dialect</artifactId>
            <version>2.4.1</version>
        </dependency>

        <!-- EhCache -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
            <version>2.1.1.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>javax.cache</groupId>
            <artifactId>cache-api</artifactId>
            <version>1.1.0</version>
        </dependency>
        <dependency>
            <groupId>org.ehcache</groupId>
            <artifactId>ehcache</artifactId>
            <version>3.4.0</version>
        </dependency>

        <!-- bootstrap -->
        <dependency>
            <groupId>org.webjars</groupId>
            <artifactId>bootstrap</artifactId>
            <version>4.3.1</version>
        </dependency>

        <!-- jQuery -->
        <dependency>
            <groupId>org.webjars</groupId>
            <artifactId>jquery</artifactId>
            <version>3.4.1</version>
        </dependency>