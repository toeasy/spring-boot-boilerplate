package com.xn.mars.controller;

import org.springframework.ui.ModelMap;
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


    @RequestMapping("/index")
    public String index(ModelMap map) {
        map.addAttribute("aa", LocalDateTime.now());
        return "user/new";
    }
//    @RequestMapping("/show")
//    public Page<Article> query() {
//        return articleService.queryLatest();
//    }
//
//    @RequestMapping("/find")
//    public Article get(String title) {
//        return articleService.findOne(title);
//    }
//
//    @RequestMapping(value = "/find2", method = RequestMethod.POST)
//    public Article get2(String title) {
//        return articleService.findOne(title);
//    }
//
//
//    @RequestMapping(value = "/params", method = RequestMethod.GET)
//    public Page<Article> getEntryByParams(@RequestParam(value = "page", defaultValue = "0") Integer page,
//                                          @RequestParam(value = "size", defaultValue = "15") Integer size) {
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
//        Pageable pageable = new PageRequest(page, size, sort);
//        return null;
//
//    }

    /**
     * 异常示例
     * @return
     * @throws Exception
     */
    @RequestMapping("/ex")
    public Object throwMyException1() throws Exception {
        throw new Exception("有错误发生！！");
    }
}
