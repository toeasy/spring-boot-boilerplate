package com.xn.mars.service;

import com.xn.mars.domain.Result;
import com.xn.mars.domain.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户相关的业务操作
 * author Liang.qinjie
 * on 2017-02-18 17:18
 */
public interface UserInfoService {

    /**
     * 查询用户
     *
     * @return
     */
    List<UserInfo> queryUser();

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    UserInfo findOne(String id);

    /**
     * 保存用户信息，包含新增和修改
     *
     * @param userInfo
     */
    Result<UserInfo> save(UserInfo userInfo);

    Result<UserInfo> login(String loginName, String password);

    Result<UserInfo> lock(String id);

    Result<UserInfo> remove(String id);

    Result<Page<UserInfo>> query(Pageable pageable);
}
