package com.xn.mars.dao;

import com.xn.mars.domain.UserInfo;

/**
 * 只需简单继承BaseRepository，即可拥有crud常用方法，包含分页、排序多种查询和一些批量操作方法，
 * 也可以自行扩展，只需在此接口新定义方法（不用实现类）
 * author Liang.qinjie
 * on 2017-02-18 16:20
 */

public interface UserInfoRepository extends BaseRepository<UserInfo, String> {

}
