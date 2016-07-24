#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
>  프로젝트가 실행되면 서블릿 컨텍스트에 등록된 Listener 가 실행되면서 jwp.sql 리소스를 읽어서 저장된 쿼리를 데이터베이스에 적용하는 과정을 거치고
@WebServlet annotation 이 붙은 클래스를 서블릿으로 등록하는 과정이 진행된다. DispatcherServlet class는 모든 요청을 받는 역할을 하고(등록된 url pattern에 따라서) RequestMapping class 에 연결된 url로 컨트롤러를 실행하는 작업을 하게 된다.



#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
> http://localhost:8080 으로 접속되면 톰캣에 등록된 서블릿이 url 이 mapping 된게 있다면 서블릿이 실행되게 되고(service method) 서블릿에서 request, response 처리를 하게된다. request 에는 http method, url, version와 header 정보들이 들어있고 response 에는 http status code 와 body로 이루어져 return이 되게된다. 

#### 7. next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
> 초기화 될때 RequestMapping 에서 ShowController가 단 한번 생성되게 된다. 따라서 안에 있는 전역 클래스변수들의 상태값을 공유하게 되면서 멀티 쓰레드 상황에서
문제가 발생되게 된다.
