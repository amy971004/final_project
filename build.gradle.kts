plugins {
    id("java")
    id("war")
    kotlin("jvm")
}



group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    // 테스트 관련 의존성
    testImplementation("org.springframework:spring-test:6.0.11")
    testImplementation("junit:junit:4.13.2")

    // Lombok - 코드 간소화 도구
    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")

    // 서블릿 API - 서블릿 개발 기반
    compileOnly("jakarta.servlet:jakarta.servlet-api:5.0.0") // 최신 버전 사용 권장
    // compileOnly("jakarta.servlet.jsp:jakarta.servlet.jsp-api:5.0.0")  // JSP API - JSP 개발 기반
    compileOnly("jakarta.servlet.jsp:jakarta.servlet.jsp-api:3.0.0")  // JSP API - JSP 개발 기반

    // 스프링 프레임워크 기본 의존성
    implementation("org.springframework:spring-context:6.0.11")
    implementation("org.springframework:spring-webmvc:6.0.11")
    implementation("org.springframework:spring-jdbc:6.0.11")
    implementation ("org.springframework:spring-context-support:6.0.11")
    // Spring Websocket
    implementation ("org.springframework:spring-websocket:6.0.11")


    // JSTL - JSP 페이지에서 사용
    // Jakarta Standard Tag Library
    implementation ("jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api:2.0.0")
    implementation ("org.glassfish.web:jakarta.servlet.jsp.jstl:2.0.0")
    implementation ("jakarta.platform:jakarta.jakartaee-api:8.0.0")

    // MyBatis - 데이터베이스 연동
    implementation("org.mybatis:mybatis:3.5.15")
    implementation("org.mybatis:mybatis-spring:3.0.3")

    // Kotlin 지원 - Kotlin 사용시 필요
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.21")

    // 데이터베이스 연동
    implementation("com.oracle.database.jdbc:ojdbc11:21.3.0.0") // 오라클 21c JDBC 드라이버
    implementation("org.apache.commons:commons-dbcp2:2.9.0") // DBCP 커넥션 풀

    // JSON 처리
    // implementation("com.googlecode.json-simple:json-simple:1.1.1")
    implementation("com.google.code.gson:gson:2.10.1")

    // SLF4J - 로깅 추상화 레이어
    implementation ("org.slf4j:slf4j-api:2.0.3")

    // Logback - SLF4J 구현체, 로깅 프레임워크
    implementation ("ch.qos.logback:logback-classic:1.4.14")

    // jBCrypt 의존성 추가
    implementation ("org.mindrot:jbcrypt:0.4")

    // 멀티파트
    implementation ("commons-fileupload:commons-fileupload:1.4")

    // 이메일 인증
    // Jakarta Mail API를 위한 종속성
    implementation ("jakarta.mail:jakarta.mail-api:2.0.1")
    // Jakarta Mail의 구현체를 포함하는 종속성
    implementation ("com.sun.mail:jakarta.mail:2.0.1")

    // Jackson Databind
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.16.0")


    // Log4j
    implementation("log4j:log4j:1.2.17")



// 주석 처리된 의존성은 프로젝트 요구에 따라 해제하여 사용
// Thymeleaf 템플릿 엔진
//implementation("org.thymeleaf:thymeleaf-spring5:3.1.2.RELEASE")
//implementation("org.thymeleaf:thymeleaf:3.0.12.RELEASE")

// 스프링 시큐리티 - 보안 관련
//implementation("org.springframework.security:spring-security-core:5.7.10")
//implementation("org.springframework.security:spring-security-web:6.2.1")
//implementation("org.springframework.security:spring-security-config:6.2.1")

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}