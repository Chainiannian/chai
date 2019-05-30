package com.qhit.baseUser.service;

import java.util.List;

import com.qhit.baseRole.pojo.BaseRole;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.baseUserRole.pojo.BaseUserRole;

/**
* Created by GeneratorCode on 2018/11/26
*/

public interface IBaseUserService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    BaseUser findById(Object id);

    List<BaseUser> findBy(BaseUser baseUser);

    BaseUser login(BaseUser baseUser);

    boolean oldPassword(BaseUser baseUser);

    List<BaseRole> find(Integer userId);

    List<BaseRole> find2(Integer userId);

    List<BaseUser> findteptId(int va);
}