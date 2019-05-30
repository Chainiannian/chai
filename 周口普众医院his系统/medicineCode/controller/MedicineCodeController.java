package com.qhit.medicineCode.controller; 

import com.qhit.baseManufacturer.pojo.BaseManufacturer;
import com.qhit.baseManufacturer.service.IBaseManufacturerService;
import com.qhit.baseManufacturer.service.impl.BaseManufacturerServiceImpl;
import com.qhit.medicineCode.pojo.MedicineCode;
import com.qhit.medicineCode.service.IMedicineCodeService; 
import com.qhit.medicineCode.service.impl.MedicineCodeServiceImpl;
import com.qhit.medicineType.pojo.MedicineType;
import com.qhit.medicineType.service.IMedicineTypeService;
import com.qhit.medicineType.service.impl.MedicineTypeServiceImpl;
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
* Created by GeneratorCode on 2018/12/03
*/
@Controller 
@RequestMapping("/medicineCode") 
public class MedicineCodeController { 

    IMedicineCodeService medicineCodeService = new MedicineCodeServiceImpl();; 

    @RequestMapping("/insert") 
    public String insert(MedicineCode medicineCode) {
        IBaseManufacturerService service = new BaseManufacturerServiceImpl();
        BaseManufacturer baseManufacturer = service.findById(medicineCode.getManCode());
        medicineCode.setManChnName(baseManufacturer.getManChnName());
        IMedicineTypeService service1 = new MedicineTypeServiceImpl();
        MedicineType byId = service1.findById(medicineCode.getTypeCode());
        medicineCode.setTypeName(byId.getTypeName());
        medicineCodeService.insert(medicineCode); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/delete") 
    public String delete(Integer codeId, HttpServletResponse response) throws IOException { 
        medicineCodeService.delete(codeId); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/update") 
    public String update(MedicineCode medicineCode) {
        IBaseManufacturerService service = new BaseManufacturerServiceImpl();
        BaseManufacturer baseManufacturer = service.findById(medicineCode.getManCode());
        medicineCode.setManChnName(baseManufacturer.getManChnName());
        IMedicineTypeService service1 = new MedicineTypeServiceImpl();
        MedicineType byId = service1.findById(medicineCode.getTypeCode());
        medicineCode.setTypeName(byId.getTypeName());
        medicineCodeService.update(medicineCode); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(MedicineCode medicineCode) {
        IBaseManufacturerService service = new BaseManufacturerServiceImpl();
        BaseManufacturer baseManufacturer = service.findById(medicineCode.getManCode());
        medicineCode.setManChnName(baseManufacturer.getManChnName());
        IMedicineTypeService service1 = new MedicineTypeServiceImpl();
        MedicineType byId = service1.findById(medicineCode.getTypeCode());
        medicineCode.setTypeName(byId.getTypeName());
        medicineCodeService.updateSelective(medicineCode); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer codeId, Model model) { 
        MedicineCode medicineCode = medicineCodeService.findById(codeId); 
        model.addAttribute("medicineCode",medicineCode);
        IBaseManufacturerService service = new BaseManufacturerServiceImpl();
        IMedicineTypeService service1 = new MedicineTypeServiceImpl();
        List all = service.findAll();
        List all1 = service1.findAll();
        model.addAttribute("searchManu",all);
        model.addAttribute("searchMedT",all1);
        return "medicineCode/edit"; 
    } 
 
    @RequestMapping("/list") 
    public String list(Model model) throws IOException { 
        List<MedicineCode> list = medicineCodeService.findAll(); 
        model.addAttribute("list",list); 
        return "medicineCode/list"; 
    } 
 
    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<MedicineCode> list = medicineCodeService.findAll(); 
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    } 
 
    @RequestMapping("/search") 
    public String search(MedicineCode medicineCode,Model model) { 
        List<MedicineCode> list = medicineCodeService.search(medicineCode); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",medicineCode); 
        return "medicineCode/list"; 
    }
    @RequestMapping("/loade")
    public String loade(Model model) {
        IBaseManufacturerService service = new BaseManufacturerServiceImpl();
        IMedicineTypeService service1 = new MedicineTypeServiceImpl();
        List all = service.findAll();
        List all1 = service1.findAll();
        model.addAttribute("searchManu",all);
        model.addAttribute("searchMedT",all1);
        return "medicineCode/add";
    }
 
} 
