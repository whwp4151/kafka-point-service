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

---

## 아키텍처 설계

본 프로젝트는 **헥사고날 아키텍처(Hexagonal Architecture)** 기반으로 설계되었습니다.

비즈니스 로직을 중심으로 외부 입출력(Controller, Kafka, DB)을 독립시켜,

테스트 용이성과 유지보수성을 높이는 구조입니다.

---

### User 도메인 패키지 구조

```
user
├── application
│   ├── adapter
│   │   └── UserAdapter.kt
│   ├── service
│   │   └── UserService.kt
│   └── usecase
│       └── UserUseCase.kt
├── controller
│   ├── AuthController.kt
│   └── dto
│       └── UserJoinRequestDto.kt
└── infrastructure
    ├── UserRepository.kt
    └── jpa
        ├── UserEntity.kt
        └── UserJpaRepository.kt

```

---

### 계층별 역할 설명

| 계층 | 설명 |
| --- | --- |
| **controller** | 외부 요청(HTTP)을 수신하는 API 계층입니다. DTO를 통해 요청/응답을 처리하며, 비즈니스 로직은 포함하지 않습니다. |
| **application** | 유스케이스(UseCase) 단위로 비즈니스 로직의 흐름을 제어하는 계층입니다.도메인 로직을 직접 수행하지 않고, 도메인 계층의 기능을 조합하거나 트랜잭션 단위를 관리합니다. |
| ├── **adapter** | `infrastructure`와 `application`을 연결하는 인터페이스(Port) 정의부입니다.Repository, Kafka 등 외부 기술과의 의존성을 추상화합니다. |
| ├── **service** | 실제 도메인 로직을 실행하는 애플리케이션 내부의 구현체입니다. |
| └── **usecase** | 한 기능 단위(회원가입, 포인트 적립 등)를 명시적으로 표현하여, 기능 단위 테스트 및 유지보수를 쉽게 만듭니다. |
| **infrastructure** | 외부 기술(JPA, Redis, Kafka 등)에 대한 구현체(Adaptor)를 모아둡니다.`UserJpaRepository`는 Spring Data JPA 인터페이스를 상속받으며, `UserRepository`가 이를 감싼 형태로 구현됩니다. |