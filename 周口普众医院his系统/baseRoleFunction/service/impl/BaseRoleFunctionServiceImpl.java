package com.qhit.baseRoleFunction.service.impl;

import com.qhit.baseRoleFunction.service.IBaseRoleFunctionService;
import java.util.List;
import com.qhit.baseRoleFunction.dao.IBaseRoleFunctionDao;
import com.qhit.baseRoleFunction.dao.impl.BaseRoleFunctionDaoImpl;
import com.qhit.baseRoleFunction.pojo.BaseRoleFunction;

/**
* Created by GeneratorCode on 2018/12/02
*/

public class BaseRoleFunctionServiceImpl  implements IBaseRoleFunctionService {

    IBaseRoleFunctionDao dao = new BaseRoleFunctionDaoImpl();

    @Override 
    public boolean insert(Object object) { 
        return dao.insert(object); 
    } 


    @Override 
    public boolean update(Object object) { 
        return dao.update(object); 
    } 


    @Override 
    public boolean updateSelective(Object object) { 
        return dao.updateSelective(object); 
    } 


    @Override 
    public boolean delete(Object id) { 
        BaseRoleFunction baseRoleFunction = findById(id); 
        return dao.delete(baseRoleFunction); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public BaseRoleFunction findById(Object id) { 
        List<BaseRoleFunction> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public boolean freeUpdate(String sql) {
        return dao.freeUpdate(sql);
    }

    @Override
    public void delete1(Integer rid) {
        String sql = "delete from base_role_function WHERE rid='"+rid+"'";
        boolean b = dao.freeUpdate(sql);
        System.out.println(b);
    }


}