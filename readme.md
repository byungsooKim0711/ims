# 통합 메시지 발송 시스템 (Integration Messaging System.)

_대충적는 초안_

## 기술 스택

### Spring boot

- Java8
- Junit5
- Rest-docs
- Spring boot >= 2.3.x
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

API(GATEWAY, MESSAGE) / Loadbalancer / kako, mt, email, push, rcs / report(callback_uri) / store / analyzer / sba / data-flow / admin, user admin / common, protocol



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



## 팀 소개



#### 기타
- 배너사용 (https://devops.datenkollektiv.de/banner.txt/ - jerusalem)
- _이슈관리 템플릿_, 
---



