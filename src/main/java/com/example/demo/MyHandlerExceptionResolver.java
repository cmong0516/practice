package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MyHandlerExceptionResolver implements org.springframework.web.servlet.HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex instanceof RuntimeException) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            if ("application/json".equals(request.getHeader("accept"))) {
                Map<String, Object> errorResult = new HashMap<>();
                errorResult.put("ex", ex.getClass());
                errorResult.put("message", ex.getMessage());

                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                try {
                    response.getWriter().write(String.valueOf(errorResult));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return new ModelAndView();
            }
        }


        return null;
    }
}
