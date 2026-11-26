# 일정 관리 API

## API 목록
| 기능 | Method | Endpoint |
|---|---|---|
| 일정 생성 | POST | `/schedule` |
| 일정 전체 조회 | GET | `/schedule?author={author}` |
| 일정 단건 조회 | GET | `/schedule/{id}` |
| 일정 수정 | PUT | `/schedule/{id}` |
| 일정 삭제 | DELETE | `/schedule/{id}` |

### 일정 생성
- Method: `POST`
- Endpoint: `/schedule`
- Request Body: 제목, 내용, 작성자, 비밀번호

#### Request Example
```json
{
  "title": "Spring 공부",
  "content": "API 명세서 작성",
  "author": "김영희",
  "password": "1234"
}
```

- Response Body: 식별자, 제목, 내용, 작성자, 작성일, 수정일
- Success Status: `201 Created`

#### Response Example

```json
{
  "id": 1,
  "title": "Spring 공부",
  "content": "API 명세서 작성",
  "author": "김영희",
  "createdAt": "2026-11-25T17:30:00",
  "modifiedAt": "2026-11-25T17:30:00"
}
```

### 일정 전체 조회
- Method: `GET`
- Endpoint: `/schedule?author={author}`
- Request Body:
- Response Body: 식별자, 제목, 내용, 작성자, 작성일, 수정일
- Success Status: `200 OK`

#### Response Example

```json
[
{
  "id": 1,
  "title": "Spring 공부",
  "content": "API 명세서 작성",
  "author": "김영희",
  "createdAt": "2026-11-25T17:30:00",
  "modifiedAt": "2026-11-25T17:30:00"
}
]
```

### 일정 단건 조회
- Method: `GET`
- Endpoint: `/schedule/{id}`
- Request Body:
- Response Body: 식별자, 제목, 내용, 작성자, 작성일, 수정일
- Success Status: `200 OK`

#### Response Example

```json

{
  "id": 1,
  "title": "Spring 공부",
  "content": "API 명세서 작성",
  "author": "김영희",
  "createdAt": "2026-11-25T17:30:00",
  "modifiedAt": "2026-11-25T17:30:00"
}
```

### 일정 수정
- Method: `PUT`
- Endpoint: `/schedule/{id}`
- Request Body: 수정할 제목, 수정할 작성자, 비밀번호

#### Request Example
```json
{
  "title": "일정 관리 앱 만들기",
  "author": "김영희",
  "password": "1234"
}
```

- Response Body:  식별자, 제목, 내용, 작성자, 작성일, 수정일
- Success Status: `200 OK`

#### Response Example

```json
{
  "id": 1,
  "title": "일정 관리 앱 만들기",
  "content": "API 명세서 작성",
  "author": "김영희",
  "createdAt": "2026-11-25T17:30:00",
  "modifiedAt": "2026-11-25T18:00:00"
}
```

### 일정 삭제
- Method: `DELETE`
- Endpoint: `/schedule/{id}`
- Request Body: 비밀번호

#### Request Example
```json
{
  "password": "1234"
}
```

- Response Body:
- Success Status: `204 No Content`

#### Error Responses
- `404 Not Found`: 해당 식별자의 일정이 존재하지 않는 경우
```JSON
{
  "message": "해당 일정이 존재하지 않습니다."
}
```
- `403 Forbidden`: 비밀번호가 일치하지 않는 경우
```JSON
{
  "message": "비밀번호가 일치하지 않습니다."
}
```