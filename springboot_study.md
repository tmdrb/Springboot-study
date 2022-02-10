# Spring Boot_study

## was

웹서버(아파치)는 클라이언트가 요청하면 이전에 만들었던 페이지를 클라이언트에게 전송하는 방식이었다.

이 방식은 동적으로 작동하는게 힘들다.

미리 페이지내에 많은 코드를 작성해야 했다.

이런 한계를 극복하고자 was(톰캣)가 만들어졌다. 

기존 웹서버는 클라이언트가 요청하면 그대로 페이지를 응답했다면 WebApplicationServer는 클라이언트가 요청하면 내부의 로직으로 요청을 처리한 뒤 페이지를 응답한다.

이렇기 때문에 클라이언트 부분에서 로직을 알 수가 없어 보안이 강화되고 복잡한 코드들을 서버에서 처리하게 되므로 클라이언트의 성능과 상관없이 잘 작동한다.

이제는 더 나아가서 client <-> WebServer <-> WAS <-> DB 구조로 사용된다.

정적인 이미지, css 등 연산이 필요없는 것 들 은 webserver에서 처리하고 연산이 필요한 동적인 것 들은 WAS 에서 처리하고 데이터를 저장하고 불러오는 것은 DB 에서 처리하도록 하였다. 


## spring framwork 란?

순수 자바코드로만 이루어진(POJO) 프레임워크로 IOC,DI 등 다양한 기능을 쉽게 지원한다.

### IOC 란?

코드를 작성할 때 클래스들 끼리의 결합도가 낮아야 의존성이 낮아지고 독립적으로 수행 할 수 있다.

독립적으로 수행하게 된다면 각각의 기능만 수행하면 되기 때문에 코드는 단순해지고 다른 코드에 영향을 받는 일 도 없다.

따라서 유지보수하기가 쉬워진다.

보통 코드를 작성하게 되면 아래와 같이 개발자가 new 연산자를 사용해서 인스턴스를 할당한다.

```
class A {

}
class B {
  A a = new A();
}

```

이렇게 개발자가 직접 new를 호출하는 코드는 결합도가 높다. A 클래스가 조금이라도 달라지면 결국 A 인스턴스를 생성하는 부분도 변경해야 한다.

이러한 문제 때문에 결합도를 낮추기 위한 방법으로 사용자가 직접 인스턴스를 할당 하지말고 할당 받아서 사용하면 어떨까? 라는 개념이 나왔다.

이러한 개념을 제어의 역전 (IOC)라고 한다.

```
class A {
}
class B{
  private A a;
  public B(){
    a = Factory.getA();
  }

}
class Factory{
  
  public static A getA(){
    return new A() 
  }
}
```
이런식으로 사용자가 직접 new를 사용하지 않고 factory에서 주입 받아서 사용한다.

이렇게 되면 A 클래스가 아무리 변경되어도 Factory 부분에 getA 메소드만 변경시키면 된다.

### DI 란?

앞에 설명했던 제어의 역전은 결국은 사용자가 인스턴스를 생성하고 관리하는것을 포기하고 다른 곳에서 받아서 사용한다는 것이다.

외부에서 인스턴스를 받는 것을 DI(의존성 주입) 이라고 한다.

## spring framework 구조 

spring framework 에서는 IOC의 역할을 하는것을 스프링 컨테이너라고 표현한다.

단순히 DI 역할을 하는 BeanFactory 보다 더 많은 역할을 하기 위해서 여러가지 컨테이너들의 기능을 추가한 것을 어플리케이션 컨텍스트라고 부른다.

또한 spring framework 에서 ioc 컨테이너는 싱글톤을 지원한다.

따라서 싱글톤패턴을 만들지 않더라도 쉽게 사용 할 수 있다.

### 싱글톤 패턴

싱글톤 패턴이란 객채를 여러개 만들어서 사용하는 것이 아닌 하나의 인스턴스만을 만들어서 공유해서 사용하는 것이다.

![image](https://user-images.githubusercontent.com/31639082/153121701-41f79c6e-a4a3-483a-a0bb-14eeec633798.png)

위의 그림에서 spring에서는 webapplicationcontext를 가장 많이 사용한다.

### webapplicationcontext

webapplicationContext는 ApplicationContext를 확장한 것으로 웹 환경에서 작동하기 위해 사용한다.

webApplicationContext 에서는 ServletContext를 얻을 수 있다.

### ServletContext

ServletContext는 Servlet들이 공유하는 Context이다.

![image](https://user-images.githubusercontent.com/31639082/153130328-ebfb8f3c-d9a3-4bdb-8b50-9009cb01e377.png)

## MVC 패턴

웹에서 사용하는 패턴으로 Model, View, Controller 로 역할을 나눠서 만든다.

Model은 data와 관련된 부분만 처리하는 곳으로 data만 처리해야 한다. View, Controller와는 전혀 상관없어야 한다.

View는 클라이언트에게 보여지는 화면만 처리한다. 마찬가지로 Model과 Controller과는 전혀 상관없이 작동한다.

Controller은 클라이언트에게 받은 요청을 분류해준다. 

MVC 패턴은 서버의 규모가 커질수록 Controller의 부분의 역할의 비중이 늘어난다.

따라서 이걸 해결하고자 요즘에는 REDUX, MVVM 등 다양한 패턴등이 나오고 있는 추세이다.

## Spring boot 

앞에서 설명했던 Spring Framework에는 개발을 하기 까지 설정해야 할 것들이 너무 많았다.

또한 was도 직접 설치해야 했다.

이런 불편함을 없애고자 나온것이 Spring Boot 이다.

spring boot는 starter를 통해서 dependency들을 자동으로 관리 해주기 때문에 버전등 다른 디펜던시들을 신경 쓸 필요가 없어졌다.

또한 was도 내장 돼있기 때문에 따로 설치 할 필요가 없다.

jar 파일로도 배포가 가능해 손쉽게 배포가 가능하다.

# Spring Security

spring 기반으 하위 프레임워크로 인증과 인가 보안을 담당한다.





