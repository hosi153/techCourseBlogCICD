package com.example._0907_pjt_blog;

import com.example._0907_pjt_blog.controller.dto.MemberPostDto;
import io.swagger.models.HttpMethod;

import java.util.HashMap;
import java.util.Map;

public class StubData {

    private static Map<HttpMethod, Object> stubRequestBody;
    static {
        stubRequestBody = new HashMap<>();
        stubRequestBody.put(HttpMethod.POST, new MemberPostDto("jy","pwd","name"));
    }

    public static class MockMember{
        public static Object getRequestBody(org.springframework.http.HttpMethod method) {
            return stubRequestBody.get(method);
        }
    }
}
