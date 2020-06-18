package com.example.iot.controller;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * redirect the routes with '#' and the wrong routes to '/index.html'
 * @author Debonex
 * @date 2020年6月17日
 */
@Component
public class Redirector implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage errorPage = new ErrorPage(HttpStatus.NOT_FOUND,"/index.html");
        registry.addErrorPages(errorPage);
    }
}
