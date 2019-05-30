package com.qhit.baseRole.service.impl;

import com.qhit.baseFunction.pojo.BaseFunction;
import com.qhit.baseRole.service.IBaseRoleService;
import java.util.List;
import com.qhit.baseRole.dao.IBaseRoleDao;
import com.qhit.baseRole.dao.impl.BaseRoleDaoImpl;
import com.qhit.baseRole.pojo.BaseRole;

/**
* Created by GeneratorCode on 2018/11/29
*/

public class BaseRoleServiceImpl  implements IBaseRoleService {

    IBaseRoleDao dao = new BaseRoleDaoImpl();

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
        BaseRole baseRole = findById(id); 
        return dao.delete(baseRole); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public BaseRole findById(Object id) { 
        List<BaseRole> list = dao.findById(id); 
        return  list.get(0); 
    }

    @Override
    public List<BaseRole> find(BaseRole baseRole) {
        return dao.findByRname(baseRole.getRname());
    }

    @Override
    public List<BaseFunction> find2(Integer rid) {
        String sql = "SELECT bfs.* from base_function bfs\n" +
                "WHERE  bfs.fid NOT IN\n" +
                "(SELECT bf.fid\n" +
                "from base_function bf LEFT OUTER JOIN base_role_function brf ON bf.fid=brf.fid LEFT OUTER JOIN base_role br ON br.rid=brf.rid WHERE br.rid='"+rid+"'\n" +
                ")\n";
        return dao.freeFind(sql);
    }

    @Override
    public List<BaseFunction> find1(Integer rid) {
        String sql = "SELECT bf.* \n" +
                "from base_function bf LEFT OUTER JOIN base_role_function brf ON bf.fid=brf.fid LEFT OUTER JOIN base_role br ON br.rid=brf.rid WHERE br.rid='"+rid+"'\n";
        return dao.freeFind(sql);
    }


}