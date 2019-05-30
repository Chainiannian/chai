package com.qhit.medicineInstock.controller; 

import com.qhit.baseManufacturer.service.IBaseManufacturerService;
import com.qhit.baseManufacturer.service.impl.BaseManufacturerServiceImpl;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.baseUser.service.IBaseUserService;
import com.qhit.baseUser.service.impl.BaseUserServiceImpl;
import com.qhit.medicineCode.service.IMedicineCodeService;
import com.qhit.medicineCode.service.impl.MedicineCodeServiceImpl;
import com.qhit.medicineInstock.pojo.MedicineInstock;
import com.qhit.medicineInstock.service.IMedicineInstockService; 
import com.qhit.medicineInstock.service.impl.MedicineInstockServiceImpl;
import com.qhit.medicinePurchaseInfo.pojo.MedicinePurchaseInfo;
import com.qhit.medicinePurchaseInfo.service.IMedicinePurchaseInfoService;
import com.qhit.medicinePurchaseInfo.service.impl.MedicinePurchaseInfoServiceImpl;
import com.qhit.medicineReqPlan.pojo.MedicineReqPlan;
import com.qhit.medicineReqPlan.service.IMedicineReqPlanService;
import com.qhit.medicineReqPlan.service.impl.MedicineReqPlanServiceImpl;
import com.qhit.medicineStockinfo.pojo.MedicineStockinfo;
import com.qhit.medicineStockinfo.service.IMedicineStockinfoService;
import com.qhit.medicineStockinfo.service.impl.MedicineStockinfoServiceImpl;
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
* Created by GeneratorCode on 2018/12/11
*/
@Controller 
@RequestMapping("/medicineInstock") 
public class MedicineInstockController { 

    IMedicineInstockService medicineInstockService = new MedicineInstockServiceImpl();; 
    IMedicinePurchaseInfoService medicinePurchaseInfoService = new MedicinePurchaseInfoServiceImpl();
    IMedicineReqPlanService medicineReqPlanService = new MedicineReqPlanServiceImpl();
    IMedicineStockinfoService medicineStockinfoService = new MedicineStockinfoServiceImpl();
    IBaseUserService baseUserService = new BaseUserServiceImpl();
    IMedicineCodeService medicineCodeService = new MedicineCodeServiceImpl();
    IBaseManufacturerService baseManufacturerService = new BaseManufacturerServiceImpl();
    @RequestMapping("/insert") 
    public String insert(String a, MedicineInstock medicineInstock,HttpSession session) {
        a=a.substring(0,a.length()-1);
        String[] split = a.split(",");
        BaseUser bucct = (BaseUser) session.getAttribute("bucct");
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(date);
        for (int i = 0; i < split.length; i++) {
            String[] split1 = split[i].split("/");
            MedicinePurchaseInfo byId = medicinePurchaseInfoService.findById(split1[0]);
            medicineInstock.setInvno(split1[1]);
            medicineInstock.setMedicineCodeid(byId.getMedicineCodeid());
            medicineInstock.setInamt(byId.getPchAmt());
            medicineInstock.setUnitprc(byId.getPchPrice());
            medicineInstock.setZje(byId.getPchTotal());
            medicineInstock.setInstockUserid(bucct.getUserId());
            medicineInstock.setInstockDate(format1);
            medicineInstock.setManCode(byId.getManCode());
            medicineInstockService.insert(medicineInstock);
            byId.setStatus(3);
            medicinePurchaseInfoService.update(byId);
            List<MedicineReqPlan> allpchId = medicineReqPlanService.findAllpchId(byId.getPchId());
            for (int j = 0; j < allpchId.size(); j++) {
                MedicineReqPlan medicineReqPlan = allpchId.get(j);
                medicineReqPlan.setStatus(4);
                medicineReqPlanService.update(medicineReqPlan);
            }
            //库存
            MedicineStockinfo stockinfo = new MedicineStockinfo();
            List<MedicineStockinfo> all = medicineStockinfoService.findAll();
            if (all==null){
                stockinfo.setMedicinecodeId(medicineInstock.getMedicineCodeid());
                stockinfo.setAmt(medicineInstock.getInamt());
                stockinfo.setUnitprc(medicineInstock.getUnitprc());
                stockinfo.setSaleunitprc(stockinfo.getUnitprc()*1.5);
                stockinfo.setZje(stockinfo.getAmt()*stockinfo.getUnitprc());
                medicineStockinfoService.insert(stockinfo);
            }else {
                boolean flag = true;
                ae:for (int j = 0; j < all.size(); j++) {
                    MedicineStockinfo stockinfo1 = all.get(i);
                    if (stockinfo1.getMedicinecodeId()==medicineInstock.getMedicineCodeid()){
                        stockinfo1.setAmt(stockinfo1.getAmt()+medicineInstock.getInamt());
                        stockinfo1.setZje(stockinfo1.getZje()+medicineInstock.getZje());
                        stockinfo1.setUnitprc(stockinfo1.getZje()/stockinfo1.getAmt());
                        stockinfo1.setSaleunitprc(stockinfo1.getUnitprc()*1.5);
                        medicineStockinfoService.update(stockinfo1);
                        break ae;
                    }else {
                        flag=false;
                    }
                }
                if (flag==false){
                    stockinfo.setMedicinecodeId(medicineInstock.getMedicineCodeid());
                    stockinfo.setAmt(medicineInstock.getInamt());
                    stockinfo.setUnitprc(medicineInstock.getUnitprc());
                    stockinfo.setSaleunitprc(stockinfo.getUnitprc()*1.5);
                    stockinfo.setZje(stockinfo.getAmt()*stockinfo.getUnitprc());
                    medicineStockinfoService.insert(stockinfo);
                }
            }

        }
        return "forward:list.action";
    } 
 
    @RequestMapping("/delete") 
    public String delete(Integer instockId, HttpServletResponse response) throws IOException { 
        medicineInstockService.delete(instockId); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/update") 
    public String update(MedicineInstock medicineInstock) { 
        medicineInstockService.update(medicineInstock); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(MedicineInstock medicineInstock) { 
        medicineInstockService.updateSelective(medicineInstock); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer instockId, Model model) { 
        MedicineInstock medicineInstock = medicineInstockService.findById(instockId); 
        model.addAttribute("medicineInstock",medicineInstock); 
        return "medicineInstock/edit"; 
    } 
 
    @RequestMapping("/list") 
    public String list(Model model) throws IOException { 
        List<MedicineInstock> list = medicineInstockService.findAll(); 
        model.addAttribute("list",list);
        List all = medicineCodeService.findAll();
        List all1 = baseManufacturerService.findAll();
        List all2 = baseUserService.findAll();
        model.addAttribute("mdc",all);
        model.addAttribute("bmf",all1);
        model.addAttribute("bus",all2);
        return "medicineInstock/list"; 
    } 
 
    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<MedicineInstock> list = medicineInstockService.findAll(); 
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    } 
 
    @RequestMapping("/search") 
    public String search(MedicineInstock medicineInstock,Model model) { 
        List<MedicineInstock> list = medicineInstockService.search(medicineInstock); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",medicineInstock);
        List all = medicineCodeService.findAll();
        List all1 = baseManufacturerService.findAll();
        List all2 = baseUserService.findAll();
        model.addAttribute("mdc",all);
        model.addAttribute("bmf",all1);
        model.addAttribute("bus",all2);
        return "medicineInstock/list"; 
    } 
 
} 
