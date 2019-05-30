package com.qhit.baseUserRole.service.impl;

import com.qhit.baseUserRole.service.IBaseUserRoleService;
import java.util.List;
import com.qhit.baseUserRole.dao.IBaseUserRoleDao;
import com.qhit.baseUserRole.dao.impl.BaseUserRoleDaoImpl;
import com.qhit.baseUserRole.pojo.BaseUserRole;

/**
* Created by GeneratorCode on 2018/11/30
*/

public class BaseUserRoleServiceImpl  implements IBaseUserRoleService {

    IBaseUserRoleDao dao = new BaseUserRoleDaoImpl();

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
        BaseUserRole baseUserRole = findById(id); 
        return dao.delete(baseUserRole); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public BaseUserRole findById(Object id) { 
        List<BaseUserRole> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public boolean freeUpdate(String sql) {
        return dao.freeUpdate(sql);
    }

    @Override
    public void delete1(Integer userid) {
        String sql = "delete from base_user_role WHERE uid='"+userid+"'";
        dao.freeUpdate(sql);
    }


}