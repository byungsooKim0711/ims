# 통합 메시지 발송 시스템 (Integration Messaging System.)

_대충적는 초안_

## 기술 스택

### Spring boot

- Java8
- Junit5
- Rest-docs
- Spring boot >= 2.4.x
- Spring cloud
- Api gateway

### Database

- JPA, QueryDSL
- Mysql
- MongoDB > =4.x (analyzer)
  - Replica
- Flyway
- Redis
  - Standalon
  - Lettuce

### Message broker

- kafka (kafka streams)
  - Cluster 구성(3 대)

### CI / CD

- jenkins
- docker & k8s(kubernetes)

### WEB

- Vue.js OR React.js(미정)



## 모듈 구성

API(GATEWAY, MESSAGE) / router / kako, mt, email, push, rcs / report(callback_uri) / store / analyzer / sba / data-flow / admin, user admin / common, protocol


| Application       | PORT (web)    |      |
| ----------------- | ------------- | ---- |
| IMS-SBA           | 29000         |      |
|                   |               |      |
| IMS-GATEWAY       | 30001 ~ 30100 |      |
| IMS-API-KAKAO     | 30101 ~ 30200 |      |
| IMS-API-EMAIL     | 30201 ~ 30300 |      |
| IMS-API-PUSH      | 30301 ~ 30400 |      |
| IMS-API-MT        | 30401 ~ 30500 |      |
|                   |               |      |
| IMS-ROUTER        | 40001 ~ 40100 |      |
| IMS-CHANNEL-KAKAO | 40101 ~ 40200 |      |
| IMS-CHANNEL-EMAIL | 40201 ~ 40300 |      |
| IMS-CHANNEL-PUSH  | 40301 ~ 40400 |      |
| IMS-CHANNEL-MT    | 40401 ~ 40500 |      |
|                   |               |      |







## 모니터링

### 어플리케이션 모니터링

1. Spring boot → Spring Boot Admin(SBA)
2. KAFKA → 카프카 매니저(CMAK), 프로메테우스 + jmx exporter + grafana
3. Redis → redis stat
4. job → Spring cloud data flow ?
5. ETC ...



## 알람 (Notification)

- 내부알람
  - 슬랙(slack) 연동, 문자
- API 사용자 알람
  - 문자



## 소스코드 형상관리

- Git & GitHub



## 프로젝트 아키텍처


## 테이블 설계
https://aquerytool.com:443/aquerymain/index/?rurl=4308e55d-5bdb-49df-9b26-4000992e60c7 / 2i3i2w


## 팀 소개



#### 기타
- 배너사용 (https://devops.datenkollektiv.de/banner.txt/ - jerusalem)
- _이슈관리 템플릿_, 
---



