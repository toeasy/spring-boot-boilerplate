package com.xn.mars.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


/**
 * controller 示例
 * Created by Liang
 * 2017-02-16 16:26.
 */
@RestController
public class MainController {

    final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("aa", LocalDateTime.now());
        return "user/new";
    }


    /**
     * 异常示例
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
