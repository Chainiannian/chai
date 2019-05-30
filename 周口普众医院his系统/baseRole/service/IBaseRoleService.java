package com.qhit.baseRole.service;

import java.util.List;

import com.qhit.baseFunction.pojo.BaseFunction;
import com.qhit.baseRole.pojo.BaseRole;
/**
* Created by GeneratorCode on 2018/11/29
*/

public interface IBaseRoleService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    BaseRole findById(Object id);

    List<BaseRole> find(BaseRole baseRole);

    List<BaseFunction> find2(Integer rid);

    List<BaseFunction> find1(Integer rid);
}