# Spring Boot 프로젝트

## crud 게시판 만들기

- 로그인(관리자, 사용자 권한에 따른 접근)
- 페이징 
- 대댓글
- 조회수 

### 프로젝트 진행 계획 

--------------------------------------------------------------------------

- Spring Boot 프로젝트 생성 + 환경 설정 -> o
- table 관계 생성 (User(pk=userid), Board(pk=boardid,fk=userid,fk=replyid), Reply(pk=replyid, fk=userid)) -> o
- 게시판에 필요한 Entity 생성(Board, User, Reply) -> o
- Create 기능 구현 -> o (board, user)
- Read 기능 구현 -> o (board, user)
- Update 기능 구현 -> o (board, user)
- Delete 기능 구현 -> o (board, user)

--------------------------------------------------------------------------

- reply crud 기능 구현 -> o
- spring security 적용해서 로그인 -> o
- page 구현 -> o
- 조회수 구현 -> o

-------------------------------------------------------------------------
- 카카오 OAuth 로그인 -> o
- 대댓글 -> o
- OAuth 과 Security form login 합쳐서 세션 관리 -> o
-------------------------------------------------------------------------
- redis 적용
- 채팅 기능
- AOP 
- junit 테스트

## spring boot를 서버로 사용 (rest api 서버)

- spring 개념 정리 
- spring security 개념 정리
- JPA 개념 정리

## react를 프론트 엔드로 사용

- react 개념 정리
- csr vs ssr 개념 정리
- javascript 개념 정리

## git 사용

- git 개념 정리 

## Mysql 사용

- 관계형 database 개념 정리
