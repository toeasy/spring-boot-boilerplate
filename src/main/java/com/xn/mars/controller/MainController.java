package com.xn.mars.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Map;


/**
 * controller 示例
 * Created by Liang
 * 2017-02-16 16:26.
 */
@Controller
public class MainController {

    final static Logger logger = LoggerFactory.getLogger(UserController.class);


    @RequestMapping("/index")
    public String index(Map<String, Object> model) {
        System.out.println(" = 时间 ");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        model.put("username", username);
        model.put("message", LocalDateTime.now());
        return "new";
    }

    @RequestMapping("/user")
    public String user() {
        return "user";
    }
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

    /**
     * 异常示例
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/ex")
    public Object throwMyException1() throws Exception {
        Exception e = new Exception("有错误发生！！");
        logger.error(e.getMessage());
        throw e;
    }
}
