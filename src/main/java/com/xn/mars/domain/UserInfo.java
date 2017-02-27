package com.xn.mars.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 实体关系映射 ORM
 * author Liang.qinjie
 * on 2017-02-18 16:05
 */
@Entity
@Table(name = "s_user") //指定此实体类对应的表名
public class UserInfo implements java.io.Serializable {

    @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "name") //此处可省略注解
    private String name;

    private String loginName;

    private String password;
    private String mobilePhone;
    private Integer status;

    public UserInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserInfo{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", loginName='").append(loginName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", mobilePhone='").append(mobilePhone).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
