# 알림톡 발송 연동 규약 version-1
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
### HOST
> #### [운영] https://{domain}.com/
---
### 알림톡 발송
> #### Path: /ims/{serviceKey}/at/sendMessage
> #### HttpMethod: POST
> #### serviceKey: 사전에 발급된 서비스 키 - alpha-numeric(40)
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
  "country_code": "string(10)",
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



| Key           | Type         | Required | Description                                                  | Example                                                      |
| ------------- | ------------ | -------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| message_id    | String(30)   | Y        | 메시지 유니크 키                                             | "message_id":"20170101-605498276"                            |
| message_type  | String(2)    | Y        | 메시지 타입 (AT: 알림톡, AI: 이미지 알림톡)                  | "message_type":"AT"                                          |
| sender_key    | String(40)   | Y        | 발신 프로필 키                                               | "sender_key":"2662e99eb7a1f21abb3955278e9955f5a9a99b62"      |
| phone_number  | String(16)   | N        | 사용자 전화번호 phone_number 혹은 app_user_id 둘 중 하나는 반드시 있어야 한다.** | "phone_number":"821012345678"                                |
| country_code  | String(10)   | N        | 국가코드가 존재 할 경우 phone_number의 앞자리 0 을 제외하고 국가코드를 붙인다. | "country_code":"82"                                          |
| app_user_id   | String(20)   | N        | 앱유저아이디 **phone_number 혹은 app_user_id 둘 중 하나는 반드시 있어야 한다.** phone_number와 app_user_id의 정보가 동시에 요청된 경우 phone_number로만 발송합니다. | "app_user_id":"12345"                                        |
| template_code | String(30)   | Y        | 템플릿코드 (실제 발송할 메시지 유형으로 등록된 템플릿의 코드) | "template_code":"A001_01"                                    |
| message       | String(1000) | Y        | 사용자에게 전달될 메시지 (공백 포함 1000자로 제한)           | "message":"고객님의 택배가 금일 (18~20)시에 배달 예정입니다." |
| title         | String(50)   | N        | 템플릿 내용 중 강조 표기할 핵심 정보 (CBT, 템플릿 검수 가이드 참고) | "title":"20분 내 도착 예정"                                  |
| header        | String(16)   | N        | 메시지 상단에 표기할 제목                                    | "header":"포인트 적립 안내"                                  |
| attachment    | Json         | N        | 메시지에 첨부할 내용 (링크 버튼 / "target":"out" 속성 추가시 아웃링크) | "attachment":{"button":[{"name":"버튼명","type":"WL","url_pc":"http://naver.com", "url_mobile":"http://daum.net","target":"out"}]} |
| supplement    | Json         | N        | 메시지에 첨부할 바로연결 (링크 버튼 / "target":"out" 속성 추가시 아웃링크) | "supplement":{"quick_reply":[{"name":"버튼명","type":"WL","url_pc":"http://naver.com", "url_mobile":"http://daum.net","target":"out"}]} |
| mapping       | Json         | N        | 변수가 있는 템플릿의 가변 값들                               | "mapping":{"member_name":"김병수","price":1000}              |



---

### 친구톡 발송
> #### Path: /ims/{serviceKey}/ft/sendMessage
> #### HttpMethod: POST
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
> #### Path: /ims/{serviceKey}/bt/sendMessage
> #### HttpMethod: POST
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