package com.qhit.medicineReqPlan.controller; 

import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.baseUser.service.IBaseUserService;
import com.qhit.baseUser.service.impl.BaseUserServiceImpl;
import com.qhit.medicineCode.service.IMedicineCodeService;
import com.qhit.medicineCode.service.impl.MedicineCodeServiceImpl;
import com.qhit.medicineReqPlan.pojo.MedicineReqPlan;
import com.qhit.medicineReqPlan.service.IMedicineReqPlanService; 
import com.qhit.medicineReqPlan.service.impl.MedicineReqPlanServiceImpl; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 
import javax.annotation.Resource; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON; 
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
* Created by GeneratorCode on 2018/12/04
*/
@Controller 
@RequestMapping("/medicineReqPlan") 
public class MedicineReqPlanController { 

    IMedicineReqPlanService medicineReqPlanService = new MedicineReqPlanServiceImpl();; 

    @RequestMapping("/insert") 
    public String insert(MedicineReqPlan medicineReqPlan) { 
        medicineReqPlanService.insert(medicineReqPlan); 
        return "forward:list.action"; 
    }
    @RequestMapping("/insertBoss")
    public String insertBoss(MedicineReqPlan medicineReqPlan) {
        medicineReqPlanService.insert(medicineReqPlan);
        return "forward:listBoss.action";
    }
 
    @RequestMapping("/delete") 
    public String delete(Integer reqplnno, HttpServletResponse response) throws IOException { 
        medicineReqPlanService.delete(reqplnno); 
        return "forward:list.action"; 
    }
    @RequestMapping("/deleteBoss")
    public String deleteBoss(Integer reqplnno, HttpServletResponse response) throws IOException {
        medicineReqPlanService.delete(reqplnno);
        return "forward:listBoss.action";
    }
 
    @RequestMapping("/update") 
    public String update(MedicineReqPlan medicineReqPlan) {
        medicineReqPlanService.update(medicineReqPlan); 
        return "forward:list.action"; 
    }
    @RequestMapping("/updateBoss")
    public String updateBoss(MedicineReqPlan medicineReqPlan) {
        medicineReqPlanService.update(medicineReqPlan);
        return "forward:listBoss.action";
    }
    @RequestMapping("/updateBossTowe")
    public String updateBossTowe(String a,HttpSession session) {
        BaseUser bucct = (BaseUser) session.getAttribute("bucct");
        String substring = a.substring(0, a.length() - 1);
        String[] split = substring.split(",");
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(date);
        for (int i = 0; i < split.length; i++) {
            MedicineReqPlan byId = medicineReqPlanService.findById(split[i]);
            byId.setStatus(2);
            byId.setApprvUserid(bucct.getUserId());
            byId.setApprvDate(format1);
            medicineReqPlanService.update(byId);
        }

        return "forward:listBoss.action";
    }
    @RequestMapping("/updateBossThere")
    public String updateBossThere(String a,HttpSession session) {
        BaseUser bucct = (BaseUser) session.getAttribute("bucct");
        String substring = a.substring(0, a.length() - 1);
        String[] split = substring.split(",");
        for (int i = 0; i < split.length; i++) {
            MedicineReqPlan byId = medicineReqPlanService.findById(split[i]);
            byId.setStatus(1);
            byId.setApprvUserid(null);
            byId.setApprvDate("");
            medicineReqPlanService.update(byId);
        }

        return "forward:listBoss.action";
    }
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(MedicineReqPlan medicineReqPlan) {
        medicineReqPlanService.updateSelective(medicineReqPlan); 
        return "forward:list.action"; 
    }
    @RequestMapping("/updateSelectiveBoss")
    public String updateSelectiveBoss(MedicineReqPlan medicineReqPlan) {
        medicineReqPlanService.updateSelective(medicineReqPlan);
        return "forward:listBoss.action";
    }
 
    @RequestMapping("/load") 
    public String load(Integer reqplnno, Model model) {
        IMedicineCodeService service = new MedicineCodeServiceImpl();
        List all = service.findAll();
        model.addAttribute("medicineCode",all);
        MedicineReqPlan medicineReqPlan = medicineReqPlanService.findById(reqplnno); 
        model.addAttribute("medicineReqPlan",medicineReqPlan); 
        return "medicineReqPlan/edit"; 
    }
    @RequestMapping("/loadBosss")
    public String loadBosss(Integer reqplnno, Model model) {
        IMedicineCodeService service = new MedicineCodeServiceImpl();
        List all = service.findAll();
        model.addAttribute("medicineCode",all);
        MedicineReqPlan medicineReqPlan = medicineReqPlanService.findById(reqplnno);
        model.addAttribute("medicineReqPlan",medicineReqPlan);
        IBaseUserService service1 = new BaseUserServiceImpl();
        List all1 = service1.findAll();
        model.addAttribute("baseUser",all1);
        return "medicineReqPlanBoss/edit";
    }
    @RequestMapping("/loads")
    public String loads( Model model) {
        IMedicineCodeService service = new MedicineCodeServiceImpl();
        List all = service.findAll();
        model.addAttribute("medicineCode",all);
        return "medicineReqPlan/add";
    }
    @RequestMapping("/loadBoss")
    public String loadBoss( Model model) {
        IMedicineCodeService service = new MedicineCodeServiceImpl();
        List all = service.findAll();
        model.addAttribute("medicineCode",all);
        return "medicineReqPlanBoss/add";
    }
 
    @RequestMapping("/list") 
    public String list(Model model, HttpSession session) throws IOException {
        IMedicineCodeService service = new MedicineCodeServiceImpl();
        List all = service.findAll();
        model.addAttribute("medicineCode",all);
        IBaseUserService service1 = new BaseUserServiceImpl();
        List all1 = service1.findAll();
        model.addAttribute("baseUser",all1);
        BaseUser bucct = (BaseUser) session.getAttribute("bucct");
        List<MedicineReqPlan> list = medicineReqPlanService.findAll1(bucct.getUserId());
        model.addAttribute("list",list); 
        return "medicineReqPlan/list"; 
    }
    @RequestMapping("/listBoss")
    public String listBoss(Model model, HttpSession session) throws IOException {
        IMedicineCodeService service = new MedicineCodeServiceImpl();
        List all = service.findAll();
        model.addAttribute("medicineCode",all);
        IBaseUserService service1 = new BaseUserServiceImpl();
        List all1 = service1.findAll();
        model.addAttribute("baseUser",all1);
        List<MedicineReqPlan> list = medicineReqPlanService.findAll();
        model.addAttribute("list",list);
        return "medicineReqPlanBoss/list";
    }
 
    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<MedicineReqPlan> list = medicineReqPlanService.findAll();
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    } 
 
    @RequestMapping("/search") 
    public String search(MedicineReqPlan medicineReqPlan,Model model,HttpSession session) {
        BaseUser bucct = (BaseUser) session.getAttribute("bucct");

        List<MedicineReqPlan> list = medicineReqPlanService.search(medicineReqPlan,bucct.getUserId());
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",medicineReqPlan);
        IBaseUserService service1 = new BaseUserServiceImpl();
        List all1 = service1.findAll();
        model.addAttribute("baseUser",all1);
        IMedicineCodeService service = new MedicineCodeServiceImpl();
        List all = service.findAll();
        model.addAttribute("medicineCode",all);

        return "medicineReqPlan/list"; 
    }
    @RequestMapping("/searchBoss")
    public String searchBoss(MedicineReqPlan medicineReqPlan,Model model) {
        int a = -233;
        List<MedicineReqPlan> list = medicineReqPlanService.search(medicineReqPlan,a);
        model.addAttribute("list",list);
        model.addAttribute("searchObject",medicineReqPlan);
        IBaseUserService service1 = new BaseUserServiceImpl();
        List all1 = service1.findAll();
        model.addAttribute("baseUser",all1);
        IMedicineCodeService service = new MedicineCodeServiceImpl();
        List all = service.findAll();
        model.addAttribute("medicineCode",all);
        return "medicineReqPlan/list";
    }
 
} 
