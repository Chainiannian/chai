package com.qhit.baseUser.controller;

import com.qhit.baseDept.service.IBaseDeptService;
import com.qhit.baseDept.service.impl.BaseDeptServiceImpl;
import com.qhit.baseRole.pojo.BaseRole;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.baseUser.service.IBaseUserService;
import com.qhit.baseUser.service.impl.BaseUserServiceImpl;
import com.qhit.baseUserRole.pojo.BaseUserRole;
import com.qhit.baseUserRole.service.IBaseUserRoleService;
import com.qhit.baseUserRole.service.impl.BaseUserRoleServiceImpl;
import com.qhit.interfaces.Engineer;
import com.qhit.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by BaseUser on 2018/11/26.
 */
@Controller
@RequestMapping("/baseUser")
public class BaseUserCon {

    IBaseUserService service = new BaseUserServiceImpl();
    IBaseDeptService baseDeptService = new BaseDeptServiceImpl();


    IBaseUserRoleService service2 = new BaseUserRoleServiceImpl();

    @RequestMapping("/list")
    public String baseUserFind(Model model,BaseUser baseUser){
        List<BaseUser> all = null;
        List all1 = baseDeptService.findAll();
        model.addAttribute("bd",all1);
        if (baseUser.getSex()==null&&baseUser.getCname()==null){
           all = service.findAll();
        }else {
            all = service.findBy(baseUser);
        }
        model.addAttribute("searchObject",baseUser);
        model.addAttribute("list",all);
        return "/baseUser/list";
    }

    @RequestMapping("/lists")
    public String baseUserFinds(Model model){
        List<BaseUser> all = service.findAll();
        List all1 = baseDeptService.findAll();
        model.addAttribute("bd",all1);
        model.addAttribute("list",all);
        return "/baseUser/list";
    }

    @RequestMapping("/insert")
    public String baseUserAdd (BaseUser baseUser){
        MD5 md5 = new MD5();
        baseUser.setPassword(md5.getMD5ofStr(baseUser.getPassword()));
        service.insert(baseUser);
        return "forward:lists.action";
    }

    @RequestMapping("/delete")
    public String baseUserDelete (BaseUser baseUser){
        service.delete(baseUser.getUserId());
        return "forward:list.action";
    }
    @RequestMapping("/load")
    public String baseUserload (BaseUser baseUser, Model model){
        BaseUser byId = service.findById(baseUser.getUserId());
        model.addAttribute("baseUser",byId);
        List all1 = baseDeptService.findAll();
        model.addAttribute("bd",all1);
        return "/baseUser/edit";
    }
    @RequestMapping("/loads")
    public String loads(Model model){
        List all1 = baseDeptService.findAll();
        model.addAttribute("bd",all1);
        return "/baseUser/add";
    }
    @RequestMapping("/update")
    public String baseUserUpdate (BaseUser baseUser){
        service.updateSelective(baseUser);
        return "forward:lists.action";
    }
    @RequestMapping("/login")
    public String login (BaseUser baseUser, HttpSession session, Model model){
        MD5 md5 = new MD5();
        baseUser.setPassword(md5.getMD5ofStr(baseUser.getPassword()));
        baseUser = service.login(baseUser);
        if (baseUser!=null){
            session.setAttribute("bucct",baseUser);
            return "/index/home";
        }else {
            model.addAttribute("error","密码或账号错误");
            return "/login/login";
        }
    }
    @RequestMapping("/logout")
    public String logout (HttpSession session) {
        session.removeAttribute("bucct");
        return "redirect:/jsp/login/login.jsp";
    }
    @RequestMapping("/oldPassword")
    public void oldPassword (BaseUser baseUser, HttpServletResponse response) throws IOException {
        boolean flag = service.oldPassword(baseUser);
        response.getWriter().write(flag?"Y":"N");
    }
    @RequestMapping("/updatePassword")
    public String updatePassword (BaseUser baseUser, HttpSession session,Model model) throws IOException {
        BaseUser bucct = (BaseUser) session.getAttribute("bucct");
        MD5 md5 = new MD5();
        bucct.setPassword(md5.getMD5ofStr(baseUser.getPassword()));
        service.updateSelective(bucct);
        model.addAttribute("flag","true");
        return "login/changePassword";
    }
    @RequestMapping("/distributeLoad")
    public String distributeLoad (Integer userId,Model model){
        List<BaseRole> list = service.find(userId);
        List<BaseRole> list2 = service.find2(userId);
        model.addAttribute("right",list);
        model.addAttribute("left",list2);
        model.addAttribute("userId",userId);
        return "baseUser/distribute";
    }

    @RequestMapping("/inserts")
    public String Add (Integer userid, String s){
        service2.delete1(userid);
        if (!s.equals("")){
            s.substring(0,s.length()-1);
            String[] split = s.split(",");
            BaseUserRole role = new BaseUserRole();
            for (int i = 0; i < split.length; i++) {
                role.setUid(userid);
                role.setRid(Integer.valueOf(split[i]));
                service2.insert(role);
            }
        }
        return "forward:lists.action";
    }
}
