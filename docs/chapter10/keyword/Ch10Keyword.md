## Spring Data JPA의 Paging


둘다 데이터의 일부분을 가져와, 페이지네이션을 지원하는 클래스들이다.

**`공통점`**

- 페이징된 데이터를 포함한다
- 페이징 정보를 제공한다 ( 페이지 번호, 페이지 크기, 정렬 정보 )
- Spring Data JPA 페이징 메서드를 통해 반환된다.

**`차이점`**

- Page
    
    Page는 페이징된 데이터의 전체 정보를 제공한다.
    
    - **데이터** - 요청한 페이지의 데이터 목록 포함
    - **페이징 정보** - 현재 페이지, 페이지 크기, 총 페이지 수, 총 요소
    - **제공 메서드** - getTotalElements(), getTotalPages(), hasNext(), hasPrevious()
    - 예시 코드
        
        ```java
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name").ascending());
        Page<Store> storesPage = storeRepository.findAll(pageable);
        
        int totalPages = storesPage.getTotalPages();
        long totalItems = storesPage.getTotalElements();
        List<Store> stores = storesPage.getContent();
        boolean hasNext = storesPage.hasNext();
        ```
        
- Slice
    
    Slice는 요청된 데이터와 추가 페이지의 여부만 제공한다.
    
    - **데이터** - 요청한 페이지의 목록 포함
    - **페이징 정보** - 현재 페이지, 페이지 크기, 정렬 정보
    - **다음 페이지의 존재 여부** - hasNext()
    - Slice는 전체 데이터의 총 요소 수는 제공하지 않음!
    - 예시 코드
        
        ```java
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name").ascending());
        Slice<Store> storesSlice = storeRepository.findAll(pageable);
        
        List<Store> stores = storesSlice.getContent();
        boolean hasNext = storesSlice.hasNext();
        ```


## 객체 그래프 탐색
객체 그래프 탐색이란?

: 복잡한 객체 모델에서, 각 객체가 다른 객체와 연결되어있는 방식에 따라 데이터를 접근 및 조작하는 방법

ex) Store - Region - Review 객체가 관계를 맺고있다면

→ 이 관계들을 모두 포함하는 구조가 객체 그래프

**`그래프 탐색 방법`**

**1) 직접 탐색**

객체가 다른 객체에 대한 참조를 직접 포함하고 있을때, 해당 참조를 통해 탐색하는 방법

ex) store.getReviews() //store의 review객체를 탐색

**2) 반대 방향 탐색**

양방향 관계일 때에는 각 객체에서 서로를 탐색할 수 O


##  JPQL

Java Persistence Query Language 는 JPA에서 제공하는 객체지향 쿼리 언어이다.

SQL과 유사하지만 데이터베이스 테이블이 아닌 **엔티티 객체를 대상으로 쿼리를 작성한다는 점이** 차이점

**`JPQL의 특징`**

- 객체 지향 쿼리 언어 - 엔티티 클래스와 그 속성을 기반으로 쿼리를 작성함
- 플랫폼 독립성 - 특정 DB에 종속되지 X → DB 변경해도 jpql쿼리는 수정하지 않아도됨
- 정적 쿼리와 동적 쿼리 지원 - 둘다 지원함

**`예시`**

```java
String jpql = "SELECT r FROM Review r WHERE r.store.id = :storeId ORDER BY r.rating DESC";
```
