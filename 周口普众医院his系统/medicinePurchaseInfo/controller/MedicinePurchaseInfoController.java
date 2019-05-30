package com.qhit.medicinePurchaseInfo.controller; 

import com.qhit.baseManufacturer.service.IBaseManufacturerService;
import com.qhit.baseManufacturer.service.impl.BaseManufacturerServiceImpl;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.baseUser.service.IBaseUserService;
import com.qhit.baseUser.service.impl.BaseUserServiceImpl;
import com.qhit.medicineCode.service.IMedicineCodeService;
import com.qhit.medicineCode.service.impl.MedicineCodeServiceImpl;
import com.qhit.medicinePurchaseInfo.pojo.MedicinePurchaseInfo;
import com.qhit.medicinePurchaseInfo.service.IMedicinePurchaseInfoService; 
import com.qhit.medicinePurchaseInfo.service.impl.MedicinePurchaseInfoServiceImpl;
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
@RequestMapping("/medicinePurchaseInfo") 
public class MedicinePurchaseInfoController { 

    IMedicinePurchaseInfoService medicinePurchaseInfoService = new MedicinePurchaseInfoServiceImpl();
    IMedicineReqPlanService service = new MedicineReqPlanServiceImpl();
    IMedicineCodeService medicineCodeService = new MedicineCodeServiceImpl();
    IBaseManufacturerService baseManufacturerService = new BaseManufacturerServiceImpl();
    IBaseUserService baseUserService = new BaseUserServiceImpl();

    @RequestMapping("/insert") 
    public String insert(MedicinePurchaseInfo medicinePurchaseInfo) {
        int count = 0;
        List<MedicineReqPlan> allby = service.findAllby();
        for (int i = 0; i <allby.size() ; i++) {
            if (allby.get(i).getMedicineCodeid()==medicinePurchaseInfo.getMedicineCodeid()){
                count += allby.get(i).getReqamt();
            }
        }
        medicinePurchaseInfo.setPchAmt(count);
        medicinePurchaseInfo.setPchTotal(medicinePurchaseInfo.getPchPrice()*medicinePurchaseInfo.getPchAmt());
        medicinePurchaseInfoService.insert(medicinePurchaseInfo);
//
        List<MedicinePurchaseInfo> all = medicinePurchaseInfoService.findAll();
        MedicinePurchaseInfo medicinePurchaseInfo1 = all.get(all.size() - 1);
        service.findAllByUp(medicinePurchaseInfo1.getPchId(),medicinePurchaseInfo1.getMedicineCodeid());
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/delete") 
    public String delete(Integer pchId, HttpServletResponse response) throws IOException {
        List<MedicineReqPlan>list = service.findAllpchId(pchId);
        for (int i = 0; i < list.size() ; i++) {
            MedicineReqPlan medicineReqPlan = list.get(i);
            medicineReqPlan.setPchId(null);
            medicineReqPlan.setStatus(2);
            service.update(medicineReqPlan);
        }
        medicinePurchaseInfoService.delete(pchId); 
        return "forward:list.action"; 
    }
    @RequestMapping("/deletes")
    public String deletes(Integer pchId, HttpServletResponse response) throws IOException {
        List<MedicineReqPlan>list = service.findAllpchId(pchId);
        for (int i = 0; i < list.size() ; i++) {
            MedicineReqPlan medicineReqPlan = list.get(i);
            medicineReqPlan.setPchId(null);
            medicineReqPlan.setStatus(2);
            service.update(medicineReqPlan);
        }
        medicinePurchaseInfoService.delete(pchId);
        return "forward:lists.action";
    }
 
    @RequestMapping("/update") 
    public String update(MedicinePurchaseInfo medicinePurchaseInfo) {
        medicinePurchaseInfo.setPchTotal(medicinePurchaseInfo.getPchPrice()*medicinePurchaseInfo.getPchAmt());
        medicinePurchaseInfoService.update(medicinePurchaseInfo); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(MedicinePurchaseInfo medicinePurchaseInfo) { 
        medicinePurchaseInfoService.updateSelective(medicinePurchaseInfo); 
        return "forward:lists.action";
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
            MedicinePurchaseInfo byId = medicinePurchaseInfoService.findById(split[i]);
            byId.setStatus(2);
            byId.setApprvUserid(bucct.getUserId());
            byId.setApprvDate(format1);
            medicinePurchaseInfoService.update(byId);
        }
        return "forward:lists.action";
    }
    @RequestMapping("/updateBossThere")
    public String updateBossThere(String a,HttpSession session) {
        BaseUser bucct = (BaseUser) session.getAttribute("bucct");
        String substring = a.substring(0, a.length() - 1);
        String[] split = substring.split(",");

        for (int i = 0; i < split.length; i++) {
            MedicinePurchaseInfo byId = medicinePurchaseInfoService.findById(split[i]);
            byId.setStatus(1);
            byId.setApprvUserid(null);
            byId.setApprvDate("");
            medicinePurchaseInfoService.update(byId);
        }
        return "forward:lists.action";
    }
 
    @RequestMapping("/load") 
    public String load(Integer pchId, Model model) {
        List all1 = medicineCodeService.findAll();
        //生产厂商
        List all = baseManufacturerService.findAll();
        model.addAttribute("cod",all1);
        model.addAttribute("mft",all);

        MedicinePurchaseInfo medicinePurchaseInfo = medicinePurchaseInfoService.findById(pchId); 
        model.addAttribute("medicinePurchaseInfo",medicinePurchaseInfo); 
        return "medicinePurchaseInfo/edit"; 
    }
    @RequestMapping("/loadse")
    public String loadse(Integer pchId, Model model) {
        List all1 = medicineCodeService.findAll();
        //生产厂商
        List all = baseManufacturerService.findAll();
        model.addAttribute("cod",all1);
        model.addAttribute("mft",all);

        MedicinePurchaseInfo medicinePurchaseInfo = medicinePurchaseInfoService.findById(pchId);
        model.addAttribute("medicinePurchaseInfo",medicinePurchaseInfo);
        return "medicinePurchaseInfoBoss/edit";
    }
    @RequestMapping("/loads")
    public String loads( Model model) {

        //药剂名
        List all1 = medicineCodeService.findAllby();
        //生产厂商
        List all = baseManufacturerService.findAll();
        model.addAttribute("cod",all1);
        model.addAttribute("mft",all);
        return "medicinePurchaseInfo/add";
    }
 
    @RequestMapping("/list") 
    public String list(Model model) throws IOException { 
        List<MedicinePurchaseInfo> list = medicinePurchaseInfoService.findAll();
        List all = medicineCodeService.findAll();
        List all1 = baseManufacturerService.findAll();
        List all2 = baseUserService.findAll();

        model.addAttribute("list",list); 
        model.addAttribute("mdc",all);
        model.addAttribute("bmf",all1);
        model.addAttribute("bus",all2);
        return "medicinePurchaseInfo/list";
    }
    @RequestMapping("/lists")
    public String lists(Model model) throws IOException {
        List<MedicinePurchaseInfo> list = medicinePurchaseInfoService.findAll();
        List all = medicineCodeService.findAll();
        List all1 = baseManufacturerService.findAll();
        List all2 = baseUserService.findAll();

        model.addAttribute("list",list);
        model.addAttribute("mdc",all);
        model.addAttribute("bmf",all1);
        model.addAttribute("bus",all2);
        return "medicinePurchaseInfoBoss/list";
    }
    @RequestMapping("/listPI")
    public String listsPI(Model model) throws IOException {
        List<MedicinePurchaseInfo> list = medicinePurchaseInfoService.findAllByStatus();
        List all = medicineCodeService.findAll();
        List all1 = baseManufacturerService.findAll();
        List all2 = baseUserService.findAll();

        model.addAttribute("list",list);
        model.addAttribute("mdc",all);
        model.addAttribute("bmf",all1);
        model.addAttribute("bus",all2);
        return "medicineInstock/listPI";
    }
 
    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<MedicinePurchaseInfo> list = medicinePurchaseInfoService.findAll(); 
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    } 
 
    @RequestMapping("/search") 
    public String search(MedicinePurchaseInfo medicinePurchaseInfo,Model model) { 
        List<MedicinePurchaseInfo> list = medicinePurchaseInfoService.search(medicinePurchaseInfo); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",medicinePurchaseInfo);
        List all = medicineCodeService.findAll();
        model.addAttribute("mdc",all);

        List all1 = baseManufacturerService.findAll();
        List all2 = baseUserService.findAll();
        model.addAttribute("bus",all2);
        model.addAttribute("bmf",all1);
        return "medicinePurchaseInfo/list"; 
    }
    @RequestMapping("/searchs")
    public String searchs(MedicinePurchaseInfo medicinePurchaseInfo,Model model) {
        List<MedicinePurchaseInfo> list = medicinePurchaseInfoService.search(medicinePurchaseInfo);
        model.addAttribute("list",list);
        model.addAttribute("searchObject",medicinePurchaseInfo);
        List all = medicineCodeService.findAll();
        model.addAttribute("mdc",all);

        List all1 = baseManufacturerService.findAll();
        List all2 = baseUserService.findAll();
        model.addAttribute("bus",all2);
        model.addAttribute("bmf",all1);
        return "medicinePurchaseInfoBoss/list";
    }
 
} 
