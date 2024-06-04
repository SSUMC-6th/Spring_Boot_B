## Spring의 의존성 주입(DI)

- **의존성 주입(DI=dependency injection)이란?**
    
    : 객체간의 의존관계를 관리하는 한 방법!
    
    → 객체가 직접 의존성을 찾지 않고, 외부에서 관계를 주입받는 방식을 말한다.
    
    +) 결합도 ↓ 유연성 & 테스트 용이성 ↑
    
- **의존성 주입 방식**
    - **필드 주입**
        
        : 클래스의 필드값에 @Autowired를 붙여 직접 의존관계를 주입한다.
        
        ```java
        @Component // 스프링 컨테이너가 관리할 객체임을 의미
        public class OrderServiceImpl implements OrderService {
        
            @Autowired
            private MemberRepository memberRepository; 
            ...
        }
        ```
        
    
    - **수정자 주입 (=setter 주입)**
        
        ```java
        @Component
        public class OrderServiceImpl implements OrderService {
        
            private MemberRepository memberRepository;
        
            @Autowired
            public void setMemberRepository(MemberRepository memberRepository) {
                this.memberRepository = memberRepository;
            
            }
            ...
        }
        ```
        
    
    - ⭐️ **생성자 주입** ⭐️
        
        ```java
        @Component
        public class OrderServiceImpl implements OrderService {
        
            private **final** MemberRepository memberRepository; //final을 사용할 수 있다!
            
        
        	  @Autowired
        	  public OrderServiceImpl(MemberRepository memberRepository) {
        	         this.memberRepository = memberRepository;
        	  }
        }
        ```
        
        [생성자 주입의 특징]
        
        - 객체 생성시 딱 1번만 호출되므로 이후에는 호출될 일이 없다!
        - 필드에 final값을 사용할 수 있어, 초기값 미설정시 발생하는 컴파일 오류를 알려줌!
        - 만약, 생성자가 딱 한개만 있다면 @Autowired를 생략해도된다!



## IoC 컨테이너
- **IoC란** inversion of control로, “제어의 역전”을 뜻한다.

- **IoC 컨테이너란? (=spring container = Di container)**
    
    : 개발자 대신 객체를 생성, 생명 주기 관리, 의존관계, 객체의 설정 정보 관리 등을 수행해주는 스프링 컨테이너
    
    ⇒ 개발자는 비즈니스 로직 구현에 집중할 수 있다!
    
- **IoC 컨테이너의 종류**
    
    1) BeanFactory 
    
    : IoC 컨테이너의 기본 인터페이스
    
    역할) 객체 생성 및 의존성 주입
    
    2) ApplicationContext
    
    : BeanFactory의 확장판으로 더 많은 기능을 제공한다.
    
    역학) 메시지 리소스 처리, 이벤트 발행 , AOP기능 지원
    
    ⭐️ 일반적으로 스프링 application 에서는 ApplicationContext를 더 많이 사용함
    
- **IoC 컨테이너의 동작 원리**
    
    1) 설정 파일/ 클래스 로드 - XML파일, 자바 설정 파일, annotation을 통해 빈 설정을 로드한다
    
    2) 빈 정의 등록 - 설정 파일을 파싱해 빈 정의를 메모리에 등록함
    
    3) 빈 생성 및 초기화 - 빈 정의 에 따라 빈을 생성 + 의존관계 주입
    
    4) 빈 제공 - 애플리케이션이 빈을 요청하면 해당 빈을 제공하고 관리함
 

## RestControllerAdvice

- **RestControllerAdvice란?**
    
    : 전역적으로 발생하는 예외를 한곳에서 처리해 일관된 예외 응답을 제공해줌
    
    → ControllerAdvice와 달리 @ResponseBody가 붙어있어 응답을 JSON형태로 내려다준다.
    
- **사용 방법**
    
    ```java
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.RestControllerAdvice;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    
    @RestControllerAdvice
    public class GlobalExceptionHandler {
    
    		//예외의 종류별로 처리함
    		
    		//1) ResourceNotFoundException
        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
    		    // 커스터 마이징된 응답구조로 errorResponse를 생성해주고
            ErrorResponse errorResponse = new ErrorResponse("RESOURCE_NOT_FOUND", ex.getMessage());
            
            //ResponseEntity에 넣어 반환해준다.
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    
    		//2) InvalidInputException
        @ExceptionHandler(InvalidInputException.class)
        public ResponseEntity<ErrorResponse> handleInvalidInputException(InvalidInputException ex) {
            ErrorResponse errorResponse = new ErrorResponse("INVALID_INPUT", ex.getMessage());
           
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    
    		//응답 구조를 커스터마이징함
        public static class ErrorResponse {
            private String errorCode;
            private String errorMessage;
    
            public ErrorResponse(String errorCode, String errorMessage) {
                this.errorCode = errorCode;
                this.errorMessage = errorMessage;
            }
    
        }
    }
    
    ```
    
    - 특정 컨트롤러 또는 패키지에만 적용할 수도 있다!
 
## lombok
- Spring intializr의 Dependencies에서 추가했던 Lombok
    
    ![스크린샷 2024-06-03 오전 10.12.37.png](스크린샷 2024-06-03 오전 10.12.37.png)
    
- **lombok이란?**
    
    : Java 애플리케이션 개발에서 반복적인 코드 작성을 줄여주는 라이브러리이다.
    
    → 코드의 간결성 유지, 개발 생산성 ↑
    
- **lombok의 주요 기능**
    - Getter와 Setter 자동 생성
        
        ```java
        import lombok.Getter;
        import lombok.Setter;
        
        @Getter
        @Setter
        public class Person {
            private String name;
            private int age;
        }
        ```
        
    - 생성자 자동 생성
        
        ```java
        import lombok.NoArgsConstructor;
        import lombok.AllArgsConstructor;
        import lombok.RequiredArgsConstructor;
        import lombok.NonNull;
        
        @NoArgsConstructor
        @AllArgsConstructor
        @RequiredArgsConstructor
        public class Person {
            private String name;
            @NonNull
            private int age;
        }
        ```
        
    - toString, equals, hashCode 메서드 자동 생성
        
        ```java
        import lombok.ToString;
        import lombok.EqualsAndHashCode;
        
        @ToString
        @EqualsAndHashCode
        public class Person {
            private String name;
            private int age;
        }
        ```
        
    - 빌더 패턴
        
        ```java
        import lombok.Builder;
        
        @Builder
        public class Person {
            private String name;
            private int age;
        }
        
        // 사용 예시
        Person person = Person.builder()
                              .name("John")
                              .age(30)
                              .build();
        
        ```
        
    - 값 객체
        
        ```java
        import lombok.Value;
        
        @Value
        public class Person {
            private String name;
            private int age;
        }
        ```
        
    - 로깅
        
        ```java
        import lombok.extern.slf4j.Slf4j;
        
        @Slf4j
        public class MyClass {
            public void doSomething() {
                log.info("Doing something");
            }
        }
        ```
        

- **lombok의 장단점**
    - 장점
        
        반복적인 코드를 줄이고, 코드의 유지 보수성을 향상 시킴
        
        수동으로 작성해야하는 많은 코드를 자동으로 생성해줌
        
    - 단점
        
        Lombok이 자동으로 생성한 코드는 보이지 않기 때문에 디버깅이 어려울 수 있음

