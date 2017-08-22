package com.pmo.dashboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage")
public class LoginController
{

    private static Logger logger = LoggerFactory
            .getLogger(LoginController.class);


    @RequestMapping("/loginPage")
    public String loginPage(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "login";
    }

    @RequestMapping("/top")
    public String top(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "top";
    }

    @RequestMapping("/left")
    public String left(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "left";
    }

    @RequestMapping("/footer")
    public String footer(final HttpServletRequest request,
            final HttpServletResponse response)
    {
        return "footer";
    }

}
