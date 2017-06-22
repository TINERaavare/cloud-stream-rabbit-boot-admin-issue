# cloud-stream-rabbit-boot-admin-issue

Stackoverflow question:
https://stackoverflow.com/questions/44683347/spring-cloud-stream-and-rabbitmq-health-check

Spring Cloud Stream GitHub issue:
https://github.com/spring-cloud/spring-cloud-stream/issues/998

Steps to reproduce:

* Start eureka-server (port 8761)
* Start boot-admin (port 8080)
* Start RabbitMQ and update configuration in cloud-stream-rabbit\src\main\resource\application.yml
* Start cloud-stream-rabbit and watch logs (and boot-admin dashboard). 

Logs should show health check failures against rabbitmq. There should also be a "Hello"-message printed to console every second. 
This message is sent from PingService to rabbitmq and processed by PingProcessor.

Stacktrace for health check failure:


```2017-06-21 22:03:42.101  WARN 16640 --- [nio-8301-exec-1] o.s.b.a.health.RabbitHealthIndicator     : Health check failed

org.springframework.amqp.AmqpConnectException: java.net.ConnectException: Connection refused: connect
	at org.springframework.amqp.rabbit.support.RabbitExceptionTranslator.convertRabbitAccessException(RabbitExceptionTranslator.java:62) ~[spring-rabbit-1.7.3.RELEASE.jar:na]
	at org.springframework.amqp.rabbit.connection.AbstractConnectionFactory.createBareConnection(AbstractConnectionFactory.java:368) ~[spring-rabbit-1.7.3.RELEASE.jar:na]
	at org.springframework.amqp.rabbit.connection.CachingConnectionFactory.createConnection(CachingConnectionFactory.java:565) ~[spring-rabbit-1.7.3.RELEASE.jar:na]
	at org.springframework.amqp.rabbit.core.RabbitTemplate.doExecute(RabbitTemplate.java:1430) ~[spring-rabbit-1.7.3.RELEASE.jar:na]
	at org.springframework.amqp.rabbit.core.RabbitTemplate.execute(RabbitTemplate.java:1411) ~[spring-rabbit-1.7.3.RELEASE.jar:na]
	at org.springframework.amqp.rabbit.core.RabbitTemplate.execute(RabbitTemplate.java:1387) ~[spring-rabbit-1.7.3.RELEASE.jar:na]
	at org.springframework.boot.actuate.health.RabbitHealthIndicator.getVersion(RabbitHealthIndicator.java:49) ~[spring-boot-actuator-1.5.4.RELEASE.jar:1.5.4.RELEASE]
	at org.springframework.boot.actuate.health.RabbitHealthIndicator.doHealthCheck(RabbitHealthIndicator.java:45) ~[spring-boot-actuator-1.5.4.RELEASE.jar:1.5.4.RELEASE]
	at org.springframework.boot.actuate.health.AbstractHealthIndicator.health(AbstractHealthIndicator.java:43) ~[spring-boot-actuator-1.5.4.RELEASE.jar:1.5.4.RELEASE]
	at org.springframework.boot.actuate.health.CompositeHealthIndicator.health(CompositeHealthIndicator.java:68) [spring-boot-actuator-1.5.4.RELEASE.jar:1.5.4.RELEASE]
	at org.springframework.boot.actuate.endpoint.HealthEndpoint.invoke(HealthEndpoint.java:81) [spring-boot-actuator-1.5.4.RELEASE.jar:1.5.4.RELEASE]
	at org.springframework.boot.actuate.endpoint.mvc.HealthMvcEndpoint.getHealth(HealthMvcEndpoint.java:171) [spring-boot-actuator-1.5.4.RELEASE.jar:1.5.4.RELEASE]
	at org.springframework.boot.actuate.endpoint.mvc.HealthMvcEndpoint.invoke(HealthMvcEndpoint.java:145) [spring-boot-actuator-1.5.4.RELEASE.jar:1.5.4.RELEASE]
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:1.8.0_101]
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:1.8.0_101]
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:1.8.0_101]
	at java.lang.reflect.Method.invoke(Method.java:498) ~[na:1.8.0_101]
	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205) [spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:133) [spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:97) [spring-webmvc-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827) [spring-webmvc-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738) [spring-webmvc-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85) [spring-webmvc-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:967) [spring-webmvc-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:901) [spring-webmvc-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970) [spring-webmvc-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:861) [spring-webmvc-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:635) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846) [spring-webmvc-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52) [tomcat-embed-websocket-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.springframework.boot.web.filter.ApplicationContextHeaderFilter.doFilterInternal(ApplicationContextHeaderFilter.java:55) [spring-boot-1.5.4.RELEASE.jar:1.5.4.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) [spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.springframework.boot.actuate.trace.WebRequestTraceFilter.doFilterInternal(WebRequestTraceFilter.java:110) [spring-boot-actuator-1.5.4.RELEASE.jar:1.5.4.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) [spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99) [spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) [spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:105) [spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) [spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81) [spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) [spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197) [spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) [spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.springframework.boot.actuate.autoconfigure.MetricsFilter.doFilterInternal(MetricsFilter.java:106) [spring-boot-actuator-1.5.4.RELEASE.jar:1.5.4.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) [spring-web-4.3.9.RELEASE.jar:4.3.9.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:478) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:80) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:799) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:861) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1455) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142) [na:1.8.0_101]
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617) [na:1.8.0_101]
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) [tomcat-embed-core-8.5.15.jar:8.5.15]
	at java.lang.Thread.run(Thread.java:745) [na:1.8.0_101]
Caused by: java.net.ConnectException: Connection refused: connect
	at java.net.DualStackPlainSocketImpl.waitForConnect(Native Method) ~[na:1.8.0_101]
	at java.net.DualStackPlainSocketImpl.socketConnect(DualStackPlainSocketImpl.java:85) ~[na:1.8.0_101]
	at java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:350) ~[na:1.8.0_101]
	at java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:206) ~[na:1.8.0_101]
	at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:188) ~[na:1.8.0_101]
	at java.net.PlainSocketImpl.connect(PlainSocketImpl.java:172) ~[na:1.8.0_101]
	at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392) ~[na:1.8.0_101]
	at java.net.Socket.connect(Socket.java:589) ~[na:1.8.0_101]
	at com.rabbitmq.client.impl.SocketFrameHandlerFactory.create(SocketFrameHandlerFactory.java:50) ~[amqp-client-4.0.2.jar:4.0.2]
	at com.rabbitmq.client.ConnectionFactory.newConnection(ConnectionFactory.java:907) ~[amqp-client-4.0.2.jar:4.0.2]
	at com.rabbitmq.client.ConnectionFactory.newConnection(ConnectionFactory.java:859) ~[amqp-client-4.0.2.jar:4.0.2]
	at com.rabbitmq.client.ConnectionFactory.newConnection(ConnectionFactory.java:799) ~[amqp-client-4.0.2.jar:4.0.2]
	at org.springframework.amqp.rabbit.connection.AbstractConnectionFactory.createBareConnection(AbstractConnectionFactory.java:352) ~[spring-rabbit-1.7.3.RELEASE.jar:na]
	... 77 common frames omitted```
