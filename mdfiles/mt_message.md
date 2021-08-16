# 문자메시지 발송 연동 규약 version-1
### 공통
#### Request Header
- 모든 message 발송에는 아래의 Request Header 가 반드시 필요하다.
- `x-ims-version`: 호출하고자 하는 API 버전을 뜻한다. (ex. version-1, version-2, ...)
- `x-ims-key` : 호출 시 사전에 발급한 `로그인계정(username)` 와 `secret_key` 를 콜론(:) 으로 묶어 base64 인코딩을 하여 입력한다.
```json
{
  "x-ims-version": "version-1",
  "x-ims-key": "Base64.encode(username:secret_key)"
}
```
---

### 문자 메시지 발송
> #### **URL**: /ims/{serviceKey}/email/sendMessage
> #### **HttpMethod**: POST
#### Request Body
```json
{
  "message_id": "string(30)",
  "message_type": "SMS",
  "title": "string(25)",
  "message": "string(2000)",
  "callback": "string(20)",
  "attach_file_list": ["file_key1", "file_key2", "file_key3"],
  "phone_number": "string(20)",
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
| Key              | Type         | Required | Description                                   | Example            | 비고                                 |
| ---------------- | ------------ | -------- | --------------------------------------------- | ------------------ | ------------------------------------ |
| message_id       | String(30)   | Y        | 문자메시지 발송에 대한 유니크한 메시지 아이디 | "message_id":1"                |                                      |
| message_type      | String(3)    | Y        | 문자메시지 발송 타입                          | "message_type":"SMS"              | "SMS", "LMS", "MMS"                  |
| title            | String(50)   | N        | euc-kr 기준 50바이트 까지 사용 가능           | "title":"문자 제목"        | LMS, MMS 에서만 사용 가능            |
| message          | String(2000) | N        | euc-kr 기준 2000 바이트 까지 사용 가능        | "message":"문자 내용"        |    SMS: 90byte 이하, LMS/MMS 2000byte 이하                                  |
| callback         | String(20)   | Y        | 발신번호                                      | "callback":"01049492891"      | 사전에 등록한 발신번호로만 발송 가능 |
| phone_number     | String(20)   | Y        | 수신자 번호                                   | "phone_number":"01049492891"      |                                      |
| template_code    | String(30)   | N        | 문자메시지 템플릿 코드                        | "template_code":"TEMPLATE_001"     |                                      |
| mapping          | Json         | N        | 템플릿 사용 또는 개인화 변수 사용 시          | "mapping":{"key1": "value1"} |                                      |
| attach_file_list | List(3)      | N        | MMS 발송 시 첨부할 이미지 (최대 3개)          | "attach_file_list":["key1", "key2"]   | MMS 에서만 사용 가능 (사전에 업로드한 image file key 만 사용가능)                 |
|                  |              |          |                                               |                    |                                      |

---
### 발송 예시
#### SMS 예시 (변수사용)
```json
{
    "message_id": "2021080323090033345",
    "message_type": "SMS",
    "message": "#{customer_info} 님 안녕하세요. #{company_name} 입니다.",
    "callback": "01049492891",
    "phone_number": "01049492891",
    "mapping": {
      "customer_info": "김병수",
      "company_name": "kbs"
    }
}
```

#### LMS 예시 (템플릿 코드 사용, 변수 사용)
```json
{
    "message_id": "202108032309014213",
    "message_type": "LMS",
    "callback": "01049492891",
    "phone_number": "01049492891",
    "template_code": "TEMPLATE_CODE_001",
    "mapping": {
      "var1": "변수1",
      "var2": "변수2"
    }
}
```
#### MMS 예시 (변수사용)
```json
{
    "message_id": "202108032309083211",
    "message_type": "MMS",
    "message": "#{customer_info} 님 안녕하세요. #{company_name} 입니다.",
    "callback": "01049492891",
    "phone_number": "01049492891",
    "attach_file_list": ["file_key1"],
    "mapping": {
      "customer_info": "김병수",
      "company_name": "kbs"
    }
}
```
---
### 결과코드 표
[결과코드 보기](./report_code.md)