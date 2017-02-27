package com.xn.mars.service.impl;

import com.xn.mars.dao.UserInfoRepository;
import com.xn.mars.domain.Result;
import com.xn.mars.domain.UserInfo;
import com.xn.mars.service.UserInfoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

import static com.xn.mars.domain.Result.fail;
import static com.xn.mars.domain.Result.success;

/**
 * 用户相关业务逻辑
 * author Liang.qinjie
 * on 2017-02-18 17:18
 */
@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoRepository userInfoRepository;

    @Override
    public List<UserInfo> queryUser() {
        return userInfoRepository.findAll();
    }


    @Override
    public UserInfo findOne(String id) {
        return userInfoRepository.getOne(id);
    }

    @Override
    public Result<UserInfo> save(UserInfo userInfo) {

        UserInfo user = userInfoRepository.save(userInfo);

        return (null != user) ? success(user) : fail("保存失败");
    }

    @Override
    public Result<UserInfo> login(String loginName, String password) {

        return success(new UserInfo());
    }

    /**
     * 锁定用户
     *
     * @param id
     * @return
     */
    @Override
    public Result<UserInfo> lock(String id) {
        UserInfo userInfo = userInfoRepository.findOne(id);
        if (null != userInfo) {
            userInfo.setStatus(-1);
            userInfo = userInfoRepository.save(userInfo);
            return (null != userInfo) ? success("锁定成功！",userInfo) : fail("锁定失败！");
        } else {
            return fail("用户不存在！");
        }

    }

    /**
     * 根据id查询用户是否存在，返回true存在
     * @param id
     * @return
     */
    private boolean exist(String id){
        UserInfo userInfo = userInfoRepository.findOne(id);
        return null!=userInfo;
    }

    @Override
    public Result<UserInfo> remove(String id) {

        if (exist(id)) {

            userInfoRepository.delete(id);
            return success("删除成功");

        } else {

            return fail("用户不存在！");

        }
    }

    @Override
    public Result<Page<UserInfo>> query(Pageable pageable) {
        Page<UserInfo> page = userInfoRepository.findAll(pageable);

        return success(page);
    }




}
