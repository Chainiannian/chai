package com.qhit.baseRole.controller;

import com.qhit.baseFunction.pojo.BaseFunction;
import com.qhit.baseRole.pojo.BaseRole;
import com.qhit.baseRole.service.IBaseRoleService;
import com.qhit.baseRole.service.impl.BaseRoleServiceImpl;
import com.qhit.baseRoleFunction.pojo.BaseRoleFunction;
import com.qhit.baseRoleFunction.service.IBaseRoleFunctionService;
import com.qhit.baseRoleFunction.service.impl.BaseRoleFunctionServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by 16682 on 2018/11/29.
 */
@Controller

@RequestMapping("/baseRole")
public class BaseRoleCon {
    IBaseRoleService service = new BaseRoleServiceImpl();
    IBaseRoleFunctionService service2 = new BaseRoleFunctionServiceImpl();
    @RequestMapping("/list")
    public String list(Model model){
        List all = service.findAll();
        model.addAttribute("list",all);
        return "/baseRole/list";
    }
    @RequestMapping("/insert")
    public String insert(BaseRole baseRole){
        service.insert(baseRole);
        return "forward:list.action";
    }
    @RequestMapping("/load")
    public String load (BaseRole baseRole, Model model){
        BaseRole byId = service.findById(baseRole.getRid());
        model.addAttribute("baseRole",byId);
        return "/baseRole/edit";
    }
    @RequestMapping("/update")
    public String Update (BaseRole baseRole){
        service.updateSelective(baseRole);
        return "forward:list.action";
    }
    @RequestMapping("/delete")
    public String Delete (BaseRole baseRole){
        service.delete(baseRole.getRid());
        return "forward:list.action";
    }
    @RequestMapping("/lists")
    public String lists (BaseRole baseRole,Model model) {
        List<BaseRole> all = service.find(baseRole);
        model.addAttribute("list", all);
        model.addAttribute("searchObject", baseRole);
        return "/baseRole/list";
    }
    @RequestMapping("/distributeLoad")
    public String distributeLoad (Integer rid,Model model){
        List<BaseFunction> list = service.find1(rid);
        List<BaseFunction> list2 = service.find2(rid);
        model.addAttribute("right",list);
        model.addAttribute("left",list2);
        model.addAttribute("rid",rid);
        return "baseRole/distribute";
    }
    @RequestMapping("/inserts")
    public String Add (Integer rid, String s){
        service2.delete1(rid);
        if (!s.equals("")){
            s.substring(0,s.length()-1);
            String[] split = s.split(",");
            BaseRoleFunction function = new BaseRoleFunction();
            for (int i = 0; i < split.length; i++) {
                function.setRid(rid);
                function.setFid(Integer.valueOf(split[i]));
                service2.insert(function);
            }
        }
        return "forward:list.action";
    }
}
