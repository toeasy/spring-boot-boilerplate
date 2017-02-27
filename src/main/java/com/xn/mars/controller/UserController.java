package com.xn.mars.controller;

import com.xn.mars.common.Constant;
import com.xn.mars.common.ID;
import com.xn.mars.domain.Result;
import com.xn.mars.domain.UserInfo;
import com.xn.mars.service.UserInfoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * author Liang.qinjie
 * on 2017-02-18 16:23
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserInfoService userInfoService;

    @RequestMapping("/list")
    public String list(ModelMap map) {
        List<UserInfo> userInfoList = userInfoService.queryUser();
        map.addAttribute("list", userInfoList);
        System.out.println("userInfoList = " + userInfoList);

        return "user/list";
    }

    @RequestMapping("/page")
    @ResponseBody
    public Page<UserInfo> listPage(
            @PageableDefault(value = Constant.PAGE_SIZE, sort = {"id"}, direction = Sort.Direction.DESC)
                    Pageable pageable, ModelMap map) {
        System.out.println(pageable);
        Result<Page<UserInfo>> userInfoPage = userInfoService.query(pageable);
        map.addAttribute("page", userInfoPage);
        System.out.println("userInfoPage = " + userInfoPage);

        return userInfoPage.getData();
    }

    @RequestMapping("/find/{id}")
    public String find(@PathVariable String id) {

        return "user/show";
    }

    /**
     * 创建新用户、注册用户
     *
     * @param userInfo
     * @return
     */

    @RequestMapping("/register")
    public String toRegister(@ModelAttribute UserInfo userInfo) {

        return "user/register";
    }

    /**
     * 提交注册信息并处理
     *
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(final UserInfo userInfo, final RedirectAttributes redirectAttributes) {

        userInfo.setId(new ID().toHexString());
        userInfo.setStatus(0);
        Result<UserInfo> result = userInfoService.save(userInfo);
        result.setMessage("注册成功！");

        //传递信息给重定向页面
        redirectAttributes.addFlashAttribute("result", result);

        //重定向到列表页面
        return "redirect:/user/list";
    }

    /**
     * 进入修改编辑用户页面
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String toEdit(@PathVariable String id, ModelMap map) {
        UserInfo userInfo = userInfoService.findOne(id);
        map.addAttribute("userInfo", userInfo);
        return "user/edit";
    }

    /**
     * 提交修改编辑用户信息并处理
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable String id,
                       final UserInfo userInfo,
                       final BindingResult bindingResult,
                       final RedirectAttributes redirectAttributes) {

        //校验未通过，返回当前页面重填
        if (bindingResult.hasErrors()) {
            return "/user/edit";
        }
        //处理业务逻辑
        Result<UserInfo> result = userInfoService.save(userInfo);

        redirectAttributes.addFlashAttribute("result", result);
        System.out.println("result = " + result);
        return "redirect:/user/list";
    }

    /**
     * 锁定用户，禁用，使用户无效
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/lock/{id}", method = RequestMethod.GET)
    public String lock(@PathVariable String id, final RedirectAttributes redirectAttributes) {

        //处理业务逻辑:锁定用户
        Result<UserInfo> result = userInfoService.lock(id);

        redirectAttributes.addFlashAttribute("result", result);
        return "redirect:/user/list";
    }

    /**
     * 删除用户
     *
     * @return
     */
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String remove(final @PathVariable String id,
                         final RedirectAttributes redirectAttributes) {

        //处理业务逻辑:删除用户
        Result<UserInfo> result = userInfoService.remove(id);

        redirectAttributes.addFlashAttribute("result", result);

        return "redirect:/user/list";

    }

    /**
     * 用户是否存在，存在返回true
     *
     * @param id
     * @return
     */
    private boolean exist(String id) {

        return id != null && !"".equals(id);
    }
}
