package com.mashibing;

import org.springframework.web.servlet.view.InternalResourceView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyResourceView extends InternalResourceView {


    @Override
    protected HttpServletRequest getRequestToExpose(HttpServletRequest originalRequest) {
        return new HttpMethodRequestWrapper(super.getRequestToExpose(originalRequest), "GET");
    }

    private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {

        private final String method;

        public HttpMethodRequestWrapper(HttpServletRequest request, String method) {
            super(request);
            this.method = method;
        }

        @Override
        public String getMethod() {
            return this.method;
        }
    }
}