package com.qhit.produceReport.controller; 

import com.qhit.baseDevice.pojo.BaseDevice;
import com.qhit.baseDevice.service.IBaseDeviceService;
import com.qhit.baseFlow.pojo.BaseFlow;
import com.qhit.baseFlow.service.IBaseFlowService;
import com.qhit.energyConsume.pojo.EnergyConsume;
import com.qhit.energyConsume.service.IEnergyConsumeService;
import com.qhit.produceJob.pojo.ProduceJob;
import com.qhit.produceJob.service.IProduceJobService;
import com.qhit.produceReport.pojo.ProduceReport;
import com.qhit.produceReport.service.IProduceReportService; 
import org.springframework.web.bind.annotation.RequestMapping; 
import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.RestController; 

/** 
* Created by GeneratorCode on 2019/04/10
*/ 

@RestController 
@RequestMapping("/produceReport") 
public class ProduceReportController { 

    @Resource 
    IProduceReportService produceReportService;
    @Resource
    IProduceJobService produceJobService;
    @Resource
    IBaseFlowService baseFlowService;
    @Resource
    IEnergyConsumeService energyConsumeService;


    @RequestMapping("/insert") 
    public void insert(ProduceReport produceReport) { 
        produceReportService.insert(produceReport); 
    } 

    @RequestMapping("/delete") 
    public void delete(Integer reportid) { 
        produceReportService.delete(reportid); 
    } 

    @RequestMapping("/update") 
    public void update(ProduceReport produceReport) { 
        produceReportService.update(produceReport); 
    } 

    @RequestMapping("/updateSelective") 
    public void updateSelective(ProduceReport produceReport) { 
        produceReportService.updateSelective(produceReport); 
    } 

    @RequestMapping("/load") 
    public ProduceReport load(Integer reportid) { 
        ProduceReport produceReport = produceReportService.findById(reportid); 
        return produceReport; 
    } 

    @RequestMapping("/list") 
    public List<ProduceReport> list()  { 
        List<ProduceReport> list = produceReportService.findAll(); 
        return list; 
    } 

    @RequestMapping("/search") 
    public List<ProduceReport> search(ProduceReport produceReport) { 
        List<ProduceReport> list = produceReportService.search(produceReport); 
        return list; 
    }

    @RequestMapping("/completeTask")
        //reportid,flowid,startjobtime,completetime
    public void compoleteTask(ProduceReport produceReport, ProduceJob produceJob){
        //更新报岗表
        produceReportService.updateSelective(produceReport);


        //插入作业信息表
        BaseFlow baseFlow = baseFlowService.findById(produceReport.getFlowid());
        produceJob.setDevid(baseFlow.getDljid());
        produceJob.setStarttime(produceReport.getStartjobtime());
        produceJob.setCompletetime(produceReport.getCompletetime());
        produceJob.setDuration(calcDuration(produceReport.getStartjobtime(),produceReport.getCompletetime()));
        double dlj = Math.round(produceReport.getCapacity()/5*2);
        produceJob.setAmount(dlj);
        produceJob.setReportid(produceReport.getReportid());
        produceJobService.insert(produceJob);
        ProduceJob produceJob1 = new ProduceJob();
        produceJob1.setDevid(baseFlow.getZcjid());
        produceJob1.setStarttime(produceReport.getStartjobtime());
        produceJob1.setCompletetime(produceReport.getCompletetime());
        produceJob1.setDuration(calcDuration(produceReport.getStartjobtime(),produceReport.getCompletetime()));
        double zcj = Math.round(produceReport.getCapacity()/5*2);
        produceJob1.setAmount(zcj);
        produceJob1.setReportid(produceReport.getReportid());
        produceJobService.insert(produceJob1);
        ProduceJob produceJob2 = new ProduceJob();
        produceJob2.setDevid(baseFlow.getPdjid());
        produceJob2.setStarttime(produceReport.getStartjobtime());
        produceJob2.setCompletetime(produceReport.getCompletetime());
        produceJob2.setDuration(calcDuration(produceReport.getStartjobtime(),produceReport.getCompletetime()));
        double pdj = Math.round(produceReport.getCapacity()/5);
        produceJob2.setAmount(pdj);
        produceJob2.setReportid(produceReport.getReportid());
        produceJobService.insert(produceJob2);


        //插入能耗信息表
        double electric = Math.round(((Math.random()*200+100)*produceJob.getAmount())*100)/100.0;
        double water = Math.round(((Math.random()*10+1)*produceJob.getAmount()*100))/100.0;
        double oil = Math.round(((Math.random()*30+10)*produceJob.getAmount()*100))/100.0;
        EnergyConsume energyConsume = new EnergyConsume();
        energyConsume.setDevid(baseFlow.getDljid());
        energyConsume.setElectric(electric);
        energyConsume.setWater(water);
        energyConsume.setOil(oil);
        energyConsume.setReportid(produceReport.getReportid());
        energyConsumeService.insert(energyConsume);
        EnergyConsume energyConsume1 = new EnergyConsume();
        energyConsume1.setDevid(baseFlow.getZcjid());
        energyConsume1.setElectric(electric);
        energyConsume1.setWater(water);
        energyConsume1.setOil(oil);
        energyConsume1.setReportid(produceReport.getReportid());
        energyConsumeService.insert(energyConsume1);
        EnergyConsume energyConsume2 = new EnergyConsume();
        energyConsume2.setDevid(baseFlow.getPdjid());
        energyConsume2.setElectric(Math.round(((Math.random()*200+100)*produceJob2.getAmount())*100)/100.0);
        energyConsume2.setWater(Math.round(((Math.random()*10+1)*produceJob2.getAmount()*100))/100.0);
        energyConsume2.setOil(Math.round(((Math.random()*30+10)*produceJob2.getAmount()*100))/100.0);
        energyConsume2.setReportid(produceReport.getReportid());
        energyConsumeService.insert(energyConsume2);

    }

    private double calcDuration(String startDate, String endDate){
        //设置日期格式
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //将字符串转换为日期
        Date start=null;
        Date end=null;
        try {
            start=simpleDateFormat.parse(startDate);
            end=simpleDateFormat.parse(endDate);
            //得到秒数差
            long abs = Math.abs(end.getTime() - start.getTime()) / 1000;
            return Math.round(abs / 60 / 60.0 * 100) / 100.0;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

} 
