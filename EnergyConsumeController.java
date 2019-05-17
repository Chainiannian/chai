package com.qhit.energyConsume.controller; 

import com.qhit.baseDevice.pojo.BaseDevice;
import com.qhit.baseDevice.service.IBaseDeviceService;
import com.qhit.baseDevtype.pojo.BaseDevtype;
import com.qhit.baseDevtype.service.IBaseDevtypeService;
import com.qhit.baseFlow.pojo.BaseFlow;
import com.qhit.baseFlow.service.IBaseFlowService;
import com.qhit.energyConsume.pojo.EnergyConsume;
import com.qhit.energyConsume.service.IEnergyConsumeService;
import com.sun.deploy.net.HttpResponse;
import org.apache.catalina.connector.Response;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController; 


@RestController 
@RequestMapping("/energyConsume")
public class EnergyConsumeController { 

    @Resource 
    IEnergyConsumeService energyConsumeService;
    @Resource
    IBaseFlowService baseFlowService;
    @Resource
    IBaseDevtypeService baseDevtypeService;
    @Resource
    IBaseDeviceService baseDeviceService;

    @RequestMapping("/insert") 
    public void insert(EnergyConsume energyConsume) { 
        energyConsumeService.insert(energyConsume); 
    } 

    @RequestMapping("/delete") 
    public void delete(Integer consumeid) { 
        energyConsumeService.delete(consumeid); 
    } 

    @RequestMapping("/update") 
    public void update(EnergyConsume energyConsume) { 
        energyConsumeService.update(energyConsume); 
    } 

    @RequestMapping("/updateSelective") 
    public void updateSelective(EnergyConsume energyConsume) { 
        energyConsumeService.updateSelective(energyConsume); 
    } 

    @RequestMapping("/load") 
    public EnergyConsume load(Integer consumeid) { 
        EnergyConsume energyConsume = energyConsumeService.findById(consumeid); 
        return energyConsume; 
    } 

    @RequestMapping("/list")
    public List<EnergyConsume> list()  { 
        List<EnergyConsume> list = energyConsumeService.findAll(); 
        return list; 
    } 

    @RequestMapping("/search") 
    public List<EnergyConsume> search(EnergyConsume energyConsume) { 
        List<EnergyConsume> list = energyConsumeService.search(energyConsume); 
        return list; 
    }

    @RequestMapping("/flowConsume")
    public Object flowConsume(String year){
        Map map = new HashMap();
        List<BaseFlow> BaseFlowList = baseFlowService.findAll();
        String arr[] = new String[BaseFlowList.size()+1];
        arr[0]="月份";
        for (int i = 0; i < BaseFlowList.size(); i++) {
            arr[i+1] = BaseFlowList.get(i).getFlowname();
        }
        map.put("columns",arr);
        List<Map> list = new ArrayList<Map>();
        for (int i = 1; i < 13; i++) {
            List<EnergyConsume> flowConsume = energyConsumeService.flowConsume(year, String.valueOf(i));
            Map<String,String> stringMap = new HashMap<>();
            stringMap.put("月份",i+"月");
            for (int j = 0; j < flowConsume.size(); j++) {
                stringMap.put(flowConsume.get(j).getFlowname(), flowConsume.get(j).getEnergy());
            }
            list.add(stringMap);
        }
        map.put("rows",list);
        return map;
    }

    @RequestMapping("/devTypeConsume")
    public Object devTypeConsume(String year){
        Map map = new HashMap();
        List<BaseDevtype> BaseDevtypeList = baseDevtypeService.findAll();
        String arr[] = new String[BaseDevtypeList.size()+1];
        arr[0]="月份";
        for (int i = 0; i < BaseDevtypeList.size(); i++) {
            arr[i+1] = BaseDevtypeList.get(i).getTypename();
        }
        map.put("columns",arr);
        List<Map> list = new ArrayList<Map>();
        for (int i = 1; i < 13; i++) {
            List<EnergyConsume> flowConsume = energyConsumeService.devTypeConsume(year, String.valueOf(i));
            Map<String,String> stringMap = new HashMap<>();
            stringMap.put("月份",i+"月");
            for (int j = 0; j < flowConsume.size(); j++) {
                stringMap.put(flowConsume.get(j).getFlowname(), flowConsume.get(j).getEnergy());
            }
            list.add(stringMap);
        }
        map.put("rows",list);
        return map;
    }

    @RequestMapping("/devConsume")
    public Object devConsume(String year){
        Map map = new HashMap();
        List<BaseDevice> BaseDeviceList = baseDeviceService.findAll();
        String arr[] = new String[BaseDeviceList.size()+1];
        arr[0]="月份";
        for (int i = 0; i < BaseDeviceList.size(); i++) {
            arr[i+1] = BaseDeviceList.get(i).getDevname();
        }
        map.put("columns",arr);
        List<Map> list = new ArrayList<Map>();
        for (int i = 1; i < 13; i++) {
            List<EnergyConsume> flowConsume = energyConsumeService.devConsume(year, String.valueOf(i));
            Map<String,String> stringMap = new HashMap<>();
            stringMap.put("月份",i+"月");
            for (int j = 0; j < flowConsume.size(); j++) {
                stringMap.put(flowConsume.get(j).getFlowname(), flowConsume.get(j).getEnergy());
            }
            list.add(stringMap);
        }
        map.put("rows",list);
        return map;
    }

    @RequestMapping("/electricConsume")
    public Object electricConsume(String year){
        Map map = new HashMap();
        List<BaseDevice> BaseDeviceList = baseDeviceService.findAll();
        String arr[] = new String[BaseDeviceList.size()+1];
        arr[0]="月份";
        for (int i = 0; i < BaseDeviceList.size(); i++) {
            arr[i+1] = BaseDeviceList.get(i).getDevname();
        }
        map.put("columns",arr);
        List<Map> list = new ArrayList<Map>();
        for (int i = 1; i < 13; i++) {
            List<EnergyConsume> flowConsume = energyConsumeService.electricConsume(year, String.valueOf(i));
            Map<String,String> stringMap = new HashMap<>();
            stringMap.put("月份",i+"月");
            for (int j = 0; j < flowConsume.size(); j++) {
                stringMap.put(flowConsume.get(j).getFlowname(), flowConsume.get(j).getEnergy());
            }
            list.add(stringMap);
        }
        map.put("rows",list);
        return map;
    }

    @RequestMapping("/waterConsume")
    public Object waterConsume(String year){
        Map map = new HashMap();
        List<BaseDevice> BaseDeviceList = baseDeviceService.findAll();
        String arr[] = new String[BaseDeviceList.size()+1];
        arr[0]="月份";
        for (int i = 0; i < BaseDeviceList.size(); i++) {
            arr[i+1] = BaseDeviceList.get(i).getDevname();
        }
        map.put("columns",arr);
        List<Map> list = new ArrayList<Map>();
        for (int i = 1; i < 13; i++) {
            List<EnergyConsume> flowConsume = energyConsumeService.waterConsume(year, String.valueOf(i));
            Map<String,String> stringMap = new HashMap<>();
            stringMap.put("月份",i+"月");
            for (int j = 0; j < flowConsume.size(); j++) {
                stringMap.put(flowConsume.get(j).getFlowname(), flowConsume.get(j).getEnergy());
            }
            list.add(stringMap);
        }
        map.put("rows",list);
        return map;
    }

    @RequestMapping("/oilConsume")
    public Object oilConsume(String year){
        Map map = new HashMap();
        List<BaseDevice> BaseDeviceList = baseDeviceService.findAll();
        String arr[] = new String[BaseDeviceList.size()+1];
        arr[0]="月份";
        for (int i = 0; i < BaseDeviceList.size(); i++) {
            arr[i+1] = BaseDeviceList.get(i).getDevname();
        }
        map.put("columns",arr);
        List<Map> list = new ArrayList<Map>();
        for (int i = 1; i < 13; i++) {
            List<EnergyConsume> flowConsume = energyConsumeService.oilConsume(year, String.valueOf(i));
            Map<String,String> stringMap = new HashMap<>();
            stringMap.put("月份",i+"月");
            for (int j = 0; j < flowConsume.size(); j++) {
                stringMap.put(flowConsume.get(j).getFlowname(), flowConsume.get(j).getEnergy());
            }
            list.add(stringMap);
        }
        map.put("rows",list);
        return map;
    }

    //PIO导出excel表格
    @RequestMapping("/export")
    public  void reportExport(){
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建sheet对象(excel表单)
        HSSFSheet sheet = wb.createSheet("能耗信息表");
        //创建头部
        HSSFRow row = sheet.createRow(0);
        //创建单元格,写入样式内容
        HSSFCell cell = row.createCell(0);
        //单元格样式
        HSSFCellStyle cellStyle = wb.createCellStyle();
        //设置字体样式，大小
        HSSFFont fontStyle = wb.createFont();
        fontStyle.setFontName("宋体");
        fontStyle.setFontHeightInPoints((short) 14);
        fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置行高,列宽
        sheet.setDefaultRowHeightInPoints(10);
        sheet.setColumnWidth(cell.getColumnIndex(), 256 * 15);
        cellStyle.setFont(fontStyle);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        cell.setCellValue("能耗信息表");
        cell.setCellStyle(cellStyle);
        row.setRowStyle(cellStyle);
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
        //在sheet里创建第二行
        HSSFRow row2=sheet.createRow(1);
        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("设备信息");
        row2.createCell(1).setCellValue("电耗");
        row2.createCell(2).setCellValue("水耗");
        row2.createCell(3).setCellValue("油耗");
        row2.createCell(4).setCellValue("报岗id");
        row2.setRowStyle(cellStyle);
        //创建第n行并写入单元格内容
        List<EnergyConsume> list = energyConsumeService.findAll();
        HSSFRow row3 = null;
        for (int i = 0; i < list.size(); i++) {
             row3=sheet.createRow(2+i);
             row3.createCell(0).setCellValue(list.get(i).getDevname());
             row3.createCell(1).setCellValue(list.get(i).getElectric());
             row3.createCell(2).setCellValue(list.get(i).getWater());
             row3.createCell(3).setCellValue(list.get(i).getOil());
             row3.createCell(4).setCellValue(list.get(i).getReportid());
             row3.setRowStyle(cellStyle);
        }

        //输出Excel文件
        try {
            FileOutputStream output=new FileOutputStream("C:\\Users\\16035\\Desktop\\能耗信息表.xls");
            wb.write(output);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
} 
