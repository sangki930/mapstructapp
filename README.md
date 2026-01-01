# MapStruct 데모 애플리케이션

이 프로젝트는 Spring Boot 환경에서 **MapStruct**를 활용한 효율적인 Java 객체 매핑(Bean Mapping)과 **MyBatis**를 이용한 데이터 영속성 계층 연동을 보여주는 예제 애플리케이션입니다.

## 🛠 기술 스택 (Tech Stack)

- **Java**: 21
- **Framework**: Spring Boot 4.0.1
- **Persistence**: MyBatis 3.0.4
- **Database**: H2 (In-Memory)
- **Mapping**: MapStruct 1.6.3
- **Boilerplate Reduction**: Lombok
- **Build Tool**: Gradle

## 🚀 주요 기능 (Features)

- **Entity-DTO 매핑**: MapStruct 인터페이스를 사용하여 `User` 엔티티와 `UserDto` 간의 원활한 변환 구현.
- **제네릭 타입 매핑 (Generic Type Mapping)**: `DataReqDto<T>` -> `DataADto<T>`와 같은 제네릭 타입 변환 시 발생하는 MapStruct의 한계점을 해결하기 위한 `default` 메소드 활용 예제 포함.
- **사용자 관리 (User Management)**: Controller, Service, Repository 계층의 기본적인 구조 구현.

## 🏃 시작하기 (Getting Started)

### 사전 요구사항 (Prerequisites)
- JDK 21 이상 설치 필요

### 빌드 및 실행 (Build and Run)

포함된 Gradle 래퍼(Wrapper)를 사용하여 애플리케이션을 실행할 수 있습니다:

```bash
# Linux/macOS
./gradlew bootRun

# Windows
.\gradlew.bat bootRun
```

### 테스트 실행 (Run Tests)

단위 테스트 및 통합 테스트를 실행하려면 다음 명령어를 사용하세요:

```bash
# Linux/macOS
./gradlew test

# Windows
.\gradlew.bat test
```

## 📝 코드 예시 (Code Example)

**UserMapper.java**:
```java
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "username", target = "name")
    UserDto toDto(User user);
    
    // 제네릭 타입 T 매핑을 위한 Custom Default Method
    default <T> DataADto<T> toDataADto(T input) { ... }
}
```
