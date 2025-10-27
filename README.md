# Point Service Project

기존 모놀리식 환경에서 외부 포인트 서버와의 **동기 통신으로 인한 장애 전파 문제**를 개선하기 위해,

**Kafka 기반 비동기 이벤트 구조**로 리팩토링한 **포인트 시스템 개선 프로젝트**

---

## 아키텍처 구성

```
┌──────────────────────────────┐
│        BFF (Point API)       │
│  └─ REST API 요청 수신       │
│  └─ Kafka Producer 발행      │
└──────────────────────────────┘
              │
              ▼
┌──────────────────────────────┐
│     Point Connector Service   │
│  └─ Kafka Consumer 구독       │
│  └─ 포인트 원장 반영 / DLQ    │
└──────────────────────────────┘
```

- **Point API Service (BFF)**
    - 사용자 요청을 수신하고, 비동기 이벤트를 발행
    - 요청 즉시 `HTTP 202 Accepted` 응답 반환
    - 사용자별 순서 보장을 위해 `userId`를 Partition Key로 설정
- **Point Connector Service**
    - Kafka 이벤트를 구독하고 실제 포인트 적립/차감 처리
    - Mock 외부 통신 및 오류/지연 상황 시뮬레이션
    - 실패 이벤트는 DLQ(`DLQ_POINT_FAILED`)로 격리

## API 명세

### `/api/home`

- 포인트 적립 가능 여부 확인
- 현재 남은 포인트 조회

---

### `/api/auth/join`

- 테스트용 회원 가입 API
- 단순히 유저를 등록하고 `userId` 반환

---

### `/api/quiz/answer`

- 퀴즈 정답 제출 API
- 정답일 경우 포인트 적립 이벤트 발행 (`POINT_REQUEST_TOPIC`)

---

### `/api/point/earn`

- 걸음수 미션 달성 시 호출
- 요청 시 추가 검증 후 포인트 적립 이벤트 발행

---

### `/api/point/use`

- 포인트 사용 (테스트용)
- 사용 가능 금액 검증 후 차감 이벤트 발행

---

### `/api/point/history`

- 포인트 적립/사용 내역 리스트 조회
