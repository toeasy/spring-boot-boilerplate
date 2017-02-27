package com.xn.mars.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * 基础dao类，继承spring封装的JpaRepository，
 * 泛型T代表实体类，ID是此实体类的id的数据类型
 * 注解NoRepositoryBean标示此类是不用spring管理的bean，spring容器会忽略对此bean的初始化
 * Created by Liang
 * 2017-02-16 17:12
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable>   extends JpaRepository<T, ID> {


}
