# 1) 영속성 컨텍스트
- **영속성 컨텍스트란?**
  : 엔티티를 영구적으로 저장하는 환경 
     즉, 엔티티들을 관리하는 일종의 ‘*가상의 데이터베이스’* 라고 생각하자!
        
    
- **영속성 컨텍스트는 누가 관리하는가?**
  : entity manager를 통해 관리된다
        
  예시)
        
  ```java
   em.persist(memberA); // 영속성 컨텍스트에 memberA 엔티티를 저장한다.
  ```
        
- **영속성 컨텍스트 내에서의 엔티티 객체의 여러 상태**
        
        
   | 비영속 상태 (Transient) | 객체가 단순히 메모리에만 존재. 영속성 컨텍스트와 전혀 관련 X |
   | --- | --- |
   | 영속 상태 (Managed) | 객체가 영속성 컨텍스트에 의해 관리되는 상태. 영속성 컨텍스트가 객체의 변화를 추적한다 |
   | 준영속 상태 (Detached) | 한 번 영속 상태였다가 영속성 컨텍스트에서 분리된 상태이다. 더 이상 영속성 컨텍스트가 관리하지 않음 |
   | 삭제 상태 (Removed) | 영속성 컨텍스트에서 제거된 상태. |
   |  |  |
        

  ![영속성](./images/스크린샷 2024-05-28 오후 5.30.52)
    
- **영속성 컨텍스트 vs DB → 무엇이 다른가?**
        
        
  |  | 영속성 컨텍스트 | DB(데이터 베이스) |
  | --- | --- | --- |
  | 정의 | 엔티티 객체들을 관리하는 가상 DB(중간 역할) | 데이터를 영구적으로 저장하고 관리하는 시스템 |
  | 목적 | 엔티티 객체의 상태를 추적, 데이터 베이스와의 상호작용 관리 | 데이터들을 저장, 검색, 수정, 삭제 함 |
  | 데이터 반영 | 필요한 시점에 데이터 베이스 조회해, 연관된 엔티티 로딩 (지연 로딩) | 트랜잭션 commit 시 즉시 반영 |
        
  → 영속성 컨텍스트와 DB는 상호 보완적인 역할을 하며 데이터를 관리한다.







# 2) 양방향 매핑

- **양방향 매핑이란?**
  : ORM에서 서로 다른 두 엔티티가 있을때,
        
   엔티티A가 엔티티B를 참조하고, 엔티티B도 엔티티A를 참조하는 관계

- **양방향 매핑의 장단점**
        
	[장점]
	        
	  - 객체 그래프 탐색을 용이하게한다 ( 양쪽에서 서로 참조할 수 있으니까 )
	        
	[단점]
	        
	  - 코드 복잡성 ↑ - 양방향 관계 유지를 위해 더 많은 코드가 필요함
	            
	     특히, 엔티티 간의 관계를 설정하고 관리하는 메서드를 구현해야 함
            
        
**🚨 예시를 통해 이해해보자**
    
| 엔티티 | User(사용자), Post (게시글) |
| --- | --- |
| 관계 | User 1명 - Post n개 [1:N관계] |
   
- User 엔티티 코드
        
```java
	@Entity
 	@Getter
	public class User{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
        	private Long id;
        	
        	@NotNull
        	private String name;
        	
        	private Long age;
        	
        	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
        	private List<Post> posts =new ArrayList<>();
        	
        }
```
        
    
- Post 엔티티 코드
  
```java
	@Entity
	@Getter
	public class Post{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		private String context;
		
		@ManyToOne
		@JoingColumn(name="user_id")
		private User user
	}
```






# 3) N+1문제
- **N+1의 문제**
    
    : JPA에서 ORM의 지연로딩 전략때문에 발생하는 성능 저하 문제
    
- **N+1는 언제 발생하는가?**
    
    1) 1:N or N:N 관계가 있는 entity를 조회할때
    
    → 부모 entity를 가져오는 쿼리 1번 발생
    
    → 각 부모에 연결된 자식 entity를 가져오는 쿼리 N번 발생
    
    ex) 1개의 order - 10개이 orderItem
    
    총 1+10 =11번의 쿼리가 실행됨
    
- **N+1 문제의 영향**
    
    쿼리수 증가 → 성능 저하↓, 트래픽과부화, 서버 부하↑
    
- **N+1 문제의 해결방안**
    
    1) fetch join
    
    : 연관된 엔티티들을 1번의 쿼리로 미리 가져옴
    
    2) 페이징 처리
    
    : 한번에 가져올 연관된 entity개수를 설정함 → by `@BatchSize`




