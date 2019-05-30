package com.qhit.baseUserRole.service;

import java.util.List;
import com.qhit.baseUserRole.pojo.BaseUserRole;
/**
* Created by GeneratorCode on 2018/11/30
*/

public interface IBaseUserRoleService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    BaseUserRole findById(Object id);

    boolean freeUpdate(String sql);

    void delete1(Integer userid);
}