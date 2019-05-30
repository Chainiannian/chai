package com.qhit.baseUser.service.impl;

import com.qhit.baseRole.pojo.BaseRole;
import com.qhit.baseUser.service.IBaseUserService;
import java.util.List;
import com.qhit.baseUser.dao.IBaseUserDao;
import com.qhit.baseUser.dao.impl.BaseUserDaoImpl;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.baseUserRole.pojo.BaseUserRole;
import com.qhit.utils.MD5;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* Created by GeneratorCode on 2018/11/26
*/

public class BaseUserServiceImpl  implements IBaseUserService {

    IBaseUserDao dao = new BaseUserDaoImpl();

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
        BaseUser baseUser = findById(id); 
        return dao.delete(baseUser); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public BaseUser findById(Object id) { 
        List<BaseUser> list = dao.findById(id); 
        return  list.get(0); 
    }

    @Override
    public List<BaseUser> findBy(BaseUser baseUser) {
        String sql = "SELECT * from base_user WHERE cname LIKE '%"+baseUser.getCname()+"%' AND sex LIKE '%"+baseUser.getSex()+"%'";
        List list = dao.freeFind(sql);
        return list;
    }

    @Override
    public BaseUser login(BaseUser baseUser) {
        String sql = "select * from base_user where user_name='"+baseUser.getUserName()+"' and password = '"+baseUser.getPassword()+"'";
        List<BaseUser> list = dao.freeFind(sql);
        if (list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean oldPassword(BaseUser baseUser) {
        MD5 md5 = new MD5();
        String md5ofStr = md5.getMD5ofStr(baseUser.getPassword());
        String sql = "select * from base_user where user_id = '"+baseUser.getUserId()+"' and password = '"+md5ofStr+"'";
        List list = dao.freeFind(sql);
        if(list!=null && list.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public List<BaseRole> find(Integer userId) {
        String sql = "SELECT br.* \n" +
                "from base_role br LEFT OUTER JOIN base_user_role bur ON bur.rid=br.rid LEFT OUTER JOIN base_user bu ON bur.uid=bu.user_id WHERE bu.user_id="+userId+"";
        return dao.freeFind(sql);
    }
    @Override
    public List<BaseRole> find2(Integer userId) {
        String sql ="SELECT b.* from base_role b\n" +
                "WHERE  b.rid NOT IN\n" +
                "(SELECT br.rid \n" +
                "from base_role br LEFT OUTER JOIN base_user_role bur ON bur.rid=br.rid LEFT OUTER JOIN base_user bu ON bur.uid=bu.user_id WHERE bu.user_id="+userId+" \n" +
                ")";
        return dao.freeFind(sql);
    }

    @Override
    public List<BaseUser> findteptId(int va) {
        List byDeptId = dao.findByDeptId(va);
        return byDeptId;
    }
}