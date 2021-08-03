# 알림톡 발송 연동 규약 version-1
### 공통
#### Request Header
- 모든 message 방송에는 아래의 Request Header 가 반드시 필요하다.
- `x-ims-version`: 호출하고자 하는 API 버전을 뜻한다. (ex. version-1, version-2, ...)
- `x-ims-key` : 호출 시 사전에 발급한 `로그인계정(username)` 와 `secret_key` 를 콜론(:) 으로 묶어 base64 인코딩을 하여 입력한다.
```json
{
  "x-ims-version": "version-1",
  "x-ims-key": "Base64.encode(username:secret_key)"
}
```
---
### 알림톡 발송
#### URL: /ims/{serviceKey}/at/sendMessage
#### HttpMethod: POST
#### Request Body
```json
{
  "message_id": "string(30)",
  "message_type": "AT",
  "sender_key": "string(40)",
  "template_code": "string(30)",
  "message": "string(1000)",
  "title": "string(50)",
  "header": "string(16)",
  "attachment": {},
  "supplement": {},
  "phone_number": "string(20)",
  "app_user_id": "string(20)",
  "mapping": {}
}
```
#### Response Body
- `code`: 0000 성공, 그 외 실패
- `data`: 발송시 입력한 message_id
- 실패코드는 별도 표 참고
```json
{
  "code": "0000",
  "data": "message_id"
}
```

### 친구톡 발송
#### URL: /ims/{serviceKey}/ft/sendMessage
#### HttpMethod: POST
#### Request Body
```json
{
  "message_id": "string(30)",
  "message_type": "FT",
  "sender_key": "string(40)",
  "message": "string(1000)",
  "ad_flag": "string(1)",
  "attachment": {},
  "phone_number": "string(20)",
  "app_user_id": "string(20)",
  "template_code": "string(30)",
  "mapping": {}
}
```
#### Response Body
- `code`: 0000 성공, 그 외 실패
- `data`: 발송시 입력한 message_id
- 실패코드는 별도 표 참고
```json
{
  "code": "0000",
  "data": "message_id"
}
```

### 브랜드톡 발송
#### URL: /ims/{serviceKey}/bt/sendMessage
#### HttpMethod: POST
#### Request Body
```json
{

}
```
#### Response Body
- `code`: 0000 성공, 그 외 실패
- `data`: 발송시 입력한 message_id 
- 실패코드는 별도 표 참고
```json
{
  "code": "0000",
  "data": "message_id"
}
```
---
### 결과코드 표
[결과코드 보기](./report_code.md)