# 관리항목 API version-1

## 목차

### 공통
#### Request Header
- 모든 API 호출에는 아래의 Request Header 가 반드시 필요하다.
- `x-ims-version`: 호출하고자 하는 API 버전을 뜻한다. (ex. version-1, version-2, ...)
- `x-ims-key` : 호출 시 사전에 발급한 `로그인계정(username)` 와 `secret_key` 를 콜론(:) 으로 묶어 base64 인코딩을 하여 입력한다.
```json
{
  "x-ims-version": "version-1",
  "x-ims-key": "Base64.encode(username:secret_key)"
}
```

---

## 카카오 관리 API
### 발신프로필
#### 발신프로필 등록
#### 발신프로필 조회

### 템플릿
#### 카카오 템플릿 등록
#### 카카오 템플릿 수정
#### 카카오 템플릿 조회
#### 카카오 템플릿 최근 변경 조회

---

## 문자 관리 API
### 발신번호
#### 발신번호 등록
#### 발신번호 수정
#### 발신번호 삭제
#### 발신번호 조회

### 문자 템플릿
#### 문자 템플릿 등록
#### 문자 템플릿 수정
#### 문자 템플릿 조회
#### 문자 템플릿 최근 변경 조회

---

## 앱푸시 관리 API
### 토큰 관리
#### 토큰 등록
#### 토큰 수정
#### 토큰 삭제
#### 토큰 조회

### 앱푸시 템플릿
#### 앱푸시 템플릿 등록
#### 앱푸시 템플릿 수정
#### 앱푸시 템플릿 조회
#### 앱푸시 템플릿 최근 변경 조회

---

## 이메일 관리 API
### 템플릿
#### 이메일 템플릿 등록
#### 이메일 템플릿 수정
#### 이메일 템플릿 조회
#### 이메일 템플릿 최근 변경 조회

---

## 파일 관리 API
### 채널별 컨텐츠 (image, html 등)
#### 파일 업로드
#### 파일 조회
#### 파일 삭제

---

## 통계
### 채널별 통계
#### 전체 통계
- URL: /ims/{serviceKey}/stat
- HttpMethod: GET
#### Request Param
```json
{
  "from": "yyyy-MM-dd",
  "to": "yyyy-MM-dd"
}
```
#### Response Body
```json
{
  "email": null,
  "kakao": {
    "AT": {
      "total": 100,
      "success": 99,
      "fail": 1
    },
    "AI": {
      "total": 100,
      "success": 99,
      "fail": 1
    },
    "FT": {
      "total": 10,
      "success": 10,
      "fail": 0
    },
    "FI": {
      "total": 10,
      "success": 10,
      "fail": 0
    },
    "FW": {
      "total": 10,
      "success": 10,
      "fail": 0
    }
  },
  "mt": {
    "SMS": {
      "total": 100,
      "success": 99,
      "fail": 1
    },
    "LMS": {
      "total": 10,
      "success": 10,
      "fail": 0
    },
    "MMS": {
      "total": 4,
      "success": 4,
      "fail": 0
    }
  },
  "push": null
}
```

#### 카카오
- URL: /ims/{serviceKey}/stat/kakao
- HttpMethod: GET
#### Request Param
```json
{
  "from": "yyyy-MM-dd",
  "to": "yyyy-MM-dd",
  "type": "AT|AI|FT|FI|FW"
}
```
#### Response Body
```json
{
  "code": "0000",
  "data": {
    "AT": {
      "total": 100,
      "success": 99,
      "fail": 1
    },
    "AI": {
      "total": 100,
      "success": 99,
      "fail": 1
    },
    "FT": {
      "total": 10,
      "success": 10,
      "fail": 0
    },
    "FI": {
      "total": 10,
      "success": 10,
      "fail": 0
    },
    "FW": {
      "total": 10,
      "success": 10,
      "fail": 0
    }
  }
}
```

#### 문자
- URL: /ims/{serviceKey}/stat/mt
- HttpMethod: GET
#### Request Param
```json
{
  "from": "yyyy-MM-dd",
  "to": "yyyy-MM-dd",
  "type": "SMS|LMS|MMS"
}
```
#### Response Body
```json
{
  "code": "0000",
  "data": {
    "SMS": {
      "total": 100,
      "success": 99,
      "fail": 1
    },
    "LMS": {
      "total": 10,
      "success": 10,
      "fail": 0
    },
    "MMS": {
      "total": 4,
      "success": 4,
      "fail": 0
    }
  }
}
```

#### 이메일
- URL: /ims/{serviceKey}/stat/email
- HttpMethod: GET
#### Request Param
```json
{
  "from": "yyyy-MM-dd",
  "to": "yyyy-MM-dd",
  "type": ""
}
```
#### Response Body
```json
{
  "code": "0000",
  "data": {
    "EMAIL": {
      "total": 100,
      "success": 99,
      "fail": 1
    }
  }
}
```

#### 앱푸시
- URL: /ims/{serviceKey}/stat/push
- HttpMethod: GET
#### Request Param
```json
{
  "from": "yyyy-MM-dd",
  "to": "yyyy-MM-dd",
  "type": ""
}
```
#### Response Body
```json
{
  "code": "0000",
  "data": {
    "PUSH": {
      "total": 100,
      "success": 99,
      "fail": 1
    }
  }
}
```

---

## 이력조회
### 채널별 이력
#### 카카오
- URL: /ims/{serviceKey}/hist/kakao
- HttpMethod: GET
- message_id, 수신자번호, 앱토큰, 수신자이메일 조회 등 
#### Request Param
```json
{

}
```
#### Response Body
```json
{

}
```

---
#### 문자
- URL: /ims/{serviceKey}/hist/mt
- HttpMethod: GET
#### Request Param
```json
{

}
```
#### Response Body
```json
{

}
```

---
#### 이메일
- URL: /ims/{serviceKey}/hist/email
- HttpMethod: GET
#### Request Param
```json
{
  
}
```
#### Response Body
```json
{

}
```

---
#### 앱푸시
- URL: /ims/{serviceKey}/hist/push
- HttpMethod: GET
#### Request Param
```json
{
  
}
```
#### Response Body
```json
{
  
}
```

---