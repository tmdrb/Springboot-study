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

spring 기반의 하위 프레임워크로 인증과 인가 보안을 담당한다.

## 인증

인증은 해당 사용자가 본인이 맞는지를 확인하는 절차이다.

ex) id,password를 입력 했을 때 DB에 저장되어 있는 회원인지 아닌지 확인

## 인가

인증된 사용자가 요청한 자원에 접근 가능한지를 결정하는 절차이다.

ex) id,password를 입력해서 로그인한 사용자의 권한을 확인해서 user인지 admin인지 확인하고 사용자가 요청한 자원이 권한에 맞게 접근가능하지 확인

spring security는 filter 기반으로 동작하기 때문에 MVC와 분리되어서 동작한다.

전통적인 spring security는 세션-쿠키 방식

1. 유저가 로그인을 요청
2. AuthenticationFilter 에서 DB 까지 이동
3. DB에서 유저가 맞는지 확인후 UserDetails로 꺼내서 유저 session 생성
4. SecurityContextHolder 에 session 저장
5. 유저에게 Session id 응답
6. 요청 쿠키에서 session id를 비교해서 Authentication 확인

### 세션 방식의 문제점

로그인 완료시 사용자에게 sessionid 를 전달하고 server는 sessionid를 저장한다(메모리,하드,db 등 저장공간에).

인가 시 server가 직접 session을 가지고 있고 사용자의 쿠키를 받아서 session이 유효한지 비교한다. 

하지만 이러한 방법은 server에 부하가 많이 생긴다.

대규모 클라이언트들 이 있는 경우 session을 메모리에 저장 할 경우 메모리가 부족 할 것이고 서버가 재부팅되면 모든 session이 날라갈 것이다.

하드디스크와 db에 저장 할 경우 읽어 들이는데 시간이 많이 걸린다.

또한 서버를 여러대 두고 운용 할 경우 특정서버에만 session이 저장 되어 있어서 어떤 경우에는 인가 되고 안되고 하는 경우가 발생한다.

### JWT 토큰

JWT token은 session 방식을 개선하기 위해 나온 방식이다.

json web token으로 인가방식에 더 가깝다.

jwt는 server가 저장해야되는 정보가 존재 하지 않는다. 

오로지 client가 관리한다.

jwt는 base64방식으로 인코딩된 문자열이고 . 이 두개 존재한다.

`eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c`

. 을 기준으로 3개의 부분으로 나눈다.

첫번째는 header, 두번째는 payload(data) (claim이라고도 불린다), 세번째는 서명 (첫번째 + 두번째 + server의 secret key를 인코딩한 값)이다.

```
//header 구조
{
  "alg": "HS256", // 세번째 값을 생성하는데 어떤 알고리즘을 사용할지 결정한다
  "typ": "JWT" // jwt라고 알리는 고정값
}

//payload 구조

//iss: 토큰 발급자 (issuer)
//sub: 토큰 제목 (subject)
//aud: 토큰 대상자 (audience)
//exp: 토큰의 만료시간 (expiraton), 시간은 NumericDate 형식으로 되어있어야 하며 (예: 1480849147370) 언제나 현재 시간보다 이후로 설정되어있어야합니다.
//nbf: Not Before 를 의미하며, 토큰의 활성 날짜와 비슷한 개념입니다. 여기에도 NumericDate 형식으로 날짜를 지정하며, 이 날짜가 지나기 전까지는 토큰이 처리되지 않습니다.
//iat: 토큰이 발급된 시간 (issued at), 이 값을 사용하여 토큰의 age 가 얼마나 되었는지 판단 할 수 있습니다.
//jti: JWT의 고유 식별자로서, 주로 중복적인 처리를 방지하기 위하여 사용됩니다. 일회용 토큰에 사용하면 유용합니다.

{
  "sub": "1234567890",
  "name": "John Doe",
  "iat": 1516239022
}
// 여기에는 누가 토큰을 발급했고 발급대상자 만료기간등 토큰의 정보가 담겨있다.

```

마지막 서명은 위의 값중 한부분이라도 달라지면 아예 다른 값이 나오기 때문에 유효성 검증으로 사용한다.

jwt 방식은 server에 부하없이 사용 할 수 있다.

하지만 client 부분에서 token을 관리하기 때문에 token이 누군가에게 탈취 당했을 경우 대응 할 수 없다.

이런 방식을 해결하고자 access token, refresh token 두개를 발급한다.

access token은 유효기간을 짧게 설정해서 언제든지 인가 될 수 있다.

refresh token은 유효기간을 길게 설정해서 서버의 db에 저장한다.

금방 만료한 access token은 refresh token이 다시 새로운 access token을 발급해준다.

## OAuth2.0

OAuth 방식은 현재 서버에서 사용자가 다른 서버(사용자의 정보가 있는 서버)에 요청한 정보를 안전하게 가져오는 방법이다.

### resource owner(resource의 주인)
### resource server(resource owner의 정보가 있는 서버, google, naver 같은)
### client (resource가 없는 다른 서버)

이전 방식은 client에서 resource owner의 id,pw를 직접 가지고 있어서 client에서 resource server로 요청해서 정보를 가져왔다.

이 방식은 굉장히 위험 

이 방식을 개선하고자 OAuth 방식이 나옴

![image](./img.png)



