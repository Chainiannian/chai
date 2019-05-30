package com.qhit.patientRegisterRecord.controller; 

import com.qhit.baseDept.pojo.BaseDept;
import com.qhit.baseDept.service.IBaseDeptService;
import com.qhit.baseDept.service.impl.BaseDeptServiceImpl;
import com.qhit.basePatientInfo.pojo.BasePatientInfo;
import com.qhit.basePatientInfo.service.IBasePatientInfoService;
import com.qhit.basePatientInfo.service.impl.BasePatientInfoServiceImpl;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.baseUser.service.IBaseUserService;
import com.qhit.baseUser.service.impl.BaseUserServiceImpl;
import com.qhit.patientRegisterRecord.pojo.PatientRegisterRecord;
import com.qhit.patientRegisterRecord.service.IPatientRegisterRecordService; 
import com.qhit.patientRegisterRecord.service.impl.PatientRegisterRecordServiceImpl; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 
import javax.annotation.Resource; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import com.alibaba.fastjson.JSON; 
import java.io.IOException; 
import java.util.List; 

/**
* Created by GeneratorCode on 2018/12/21
*/
@Controller 
@RequestMapping("/patientRegisterRecord") 
public class PatientRegisterRecordController { 

    IPatientRegisterRecordService patientRegisterRecordService = new PatientRegisterRecordServiceImpl();;
    IBaseDeptService baseDeptService = new BaseDeptServiceImpl();
    IBaseUserService baseUserService = new BaseUserServiceImpl();
    IBasePatientInfoService basePatientInfoService = new BasePatientInfoServiceImpl();
    @RequestMapping("/insert") 
    public String insert(PatientRegisterRecord patientRegisterRecord) { 
        patientRegisterRecordService.insert(patientRegisterRecord); 
        return "forward:list.action";
    } 
 
    @RequestMapping("/delete") 
    public String delete(Integer registerId, HttpServletResponse response) throws IOException { 
        patientRegisterRecordService.delete(registerId); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/update") 
    public String update(PatientRegisterRecord patientRegisterRecord) { 
        patientRegisterRecordService.update(patientRegisterRecord); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(PatientRegisterRecord patientRegisterRecord) { 
        patientRegisterRecordService.updateSelective(patientRegisterRecord); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer registerId, Model model) { 
        PatientRegisterRecord patientRegisterRecord = patientRegisterRecordService.findById(registerId); 
        model.addAttribute("patientRegisterRecord",patientRegisterRecord); 
        return "patientRegisterRecord/edit"; 
    }
    @RequestMapping("/loads")
    public String loads(Model model) {
        List<BaseDept> all = baseDeptService.findAll();
        model.addAttribute("bd",all);
        List<BaseUser> all1 = baseUserService.findAll();
        model.addAttribute("bu",all1);
        List<BasePatientInfo> all2 = patientRegisterRecordService.findAlls();
        model.addAttribute("prr",all2);
        return "patientRegisterRecord/add";
    }
 
    @RequestMapping("/list") 
    public String list(Model model) throws IOException {
        List<BaseUser> all = baseUserService.findAll();
        List<BaseDept> all1 = baseDeptService.findAll();
        List<BasePatientInfo> all2 = basePatientInfoService.findAll();
        model.addAttribute("bu",all);
        model.addAttribute("bd",all1);
        model.addAttribute("bp",all2);
        List<PatientRegisterRecord> list = patientRegisterRecordService.findAll();
        model.addAttribute("list",list); 
        return "patientRegisterRecord/list"; 
    } 
 
    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<PatientRegisterRecord> list = patientRegisterRecordService.findAll(); 
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    }
    @RequestMapping("/ajaxload")
    public void ajaxload(Integer a ,HttpServletResponse response) throws IOException {
//        List<PatientRegisterRecord> list = patientRegisterRecordService.findAll();
//        String s = JSON.toJSONString(list);
//        response.getWriter().write(s);
        List<BaseUser> baseUsers = baseUserService.findteptId(a);
        String s = JSON.toJSONString(baseUsers);
        response.getWriter().write(s);
    }
 
    @RequestMapping("/search") 
    public String search(PatientRegisterRecord patientRegisterRecord,Model model) { 
        List<PatientRegisterRecord> list = patientRegisterRecordService.search(patientRegisterRecord); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",patientRegisterRecord); 
        return "patientRegisterRecord/list"; 
    } 
 
} 
