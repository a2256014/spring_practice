package com.example.hello.interceptor;

import com.example.hello.annotation.Auth;
import com.example.hello.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //filter에서 캐시화 했으면 형변환 가능 그 이유는 filter가 가장 앞단에 있기 때문이다.
        String url = request.getRequestURI();

        URI uri = UriComponentsBuilder.fromUriString(url)
                .query(request.getQueryString())
                .build()
                .toUri();

        log.info("request url : {}", url);
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);

        //나의 서버는 모두 퍼블릭으로 동작을 하는데
        //단!! auth 권한을 가진 요청에 대해서는 쿠키, 세션 체크
        if (hasAnnotation) {
            //권한 체크
            String query = uri.getQuery();
            log.info("query : {}", query);
            if (query.equals("name=steve")) {
                return true;
            }
            throw new AuthException();
        }

        return true;
    }

    private boolean checkAnnotation(Object handler, Class clazz) {
        // 리소스 요청일 경우 통과
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }
        //annotation check
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (handlerMethod.getMethodAnnotation(clazz) != null ||
                handlerMethod.getBeanType().getAnnotation(clazz) != null) {
            return true;
        }

        return false;
    }
}
