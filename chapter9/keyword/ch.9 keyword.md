- java의 Exception 종류들
    
    Java의 Exception은 모두 Throwable의 자식이다.
    
    Throwable의 자식 중에는 Error라고 사용자가 처리해서는 안되는 심각한 에러 또한 존재하는데, 이 존재 때문에 개발자는 try-catch 문 사용시 catch에 Throwable을 사용하지 않고, Exception을 잡는다. Error는 터지도록 나둬야 하기 때문이다.
    
    그리고 이제 Checked Exception과 Unchecked Exception으로 나눌 수 있는데 보통 Runtime Exception이 unchecked Exception이다.
    
    Checked와 UnChecked의 차이점은 바로 컴파일러가 체크할 수 있냐 없냐 차이다.
    
    Checked는 컴파일러가 그 발생을 예상할 수 있기 때문에 throws나 try-catch문을 사용하지 않으면 예외 처리를 할 수 없다.
    
    throws를 사용한다는 것은 해당 매서드에서 그 예외를 처리하지 않고 호출자에게 위임한다는 뜻으로, 호출자가 예외를 처리해야 정상흐름으로 되돌아갈 수 있으며 만약 처리하지 못한다면 프로그램은 abort되어 종료된다.
    
    예외는 이렇듯 처리되지 못하면 호출자에게로 넘어가는 이를 propagation이라고 한다.
    
    try-catch는 try문 안에서 발생한 예외는 handler인 catch에서 잡아서 처리할 수 있다. 이 때 catch에 처리할 예외를 지정해야하고, 객체지향 프로그래밍언어 답게 다형성 또한 가능하기에 Exception을 적어주면 모든 예외를 처리할 수 있다. 반대로 catch에 없는 예외는 처리할 수 없다 (다형성 제외하고)
    
    Unchecked는 컴파일러가 잡아낼 수 없는 예외로 이말은 즉 컴파일 타임에 예외 발생을 예상할 수 없는 예외를 뜻한다.
    
    JVM에서 정의된 예외가 있으며 미리 정의되어있기에 OS로부터 예외에 대한 신호를 받으면 JVM은 해당 예외들을 가지고 예외를 터트린다.
    
    사용자가 예외를 직접 정의할 수도 있는데 사용자 정의 예외는 사용자가 직접 throw하지 않으면 터지지 않고, 특정 조건하에 사용자가 throw를 사용하는 방식으로 터트려야 예외가 발생한다. 물론 미리 정의된 예외 또한 사용자가 임의로 터트릴 수 있다.
    
- @Valid
    
    Bean Validation은 특정한 구현체가 아니라 Bean Validation 2.0(JSR-380)이라는 기술 표준이다. 쉽게 이야 기해서 검증 애노테이션과 여러 인터페이스의 모음이다. 마치 JPA가 표준 기술이고 그 구현체로 하이버네이트가 있는 것과 같다