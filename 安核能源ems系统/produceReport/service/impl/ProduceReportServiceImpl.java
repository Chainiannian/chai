package com.qhit.produceReport.service.impl;

import com.qhit.produceReport.service.IProduceReportService;
import java.util.List;
import com.qhit.produceReport.dao.IProduceReportDao;
import com.qhit.produceReport.pojo.ProduceReport;
import org.springframework.stereotype.Service;
import javax.annotation.Resource; 

/**
* Created by GeneratorCode on 2019/04/10
*/

@Service 
public class ProduceReportServiceImpl  implements IProduceReportService {

    @Resource 
    IProduceReportDao dao;

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
        ProduceReport produceReport = findById(id); 
        return dao.delete(produceReport); 
    } 

    @Override 
    public List<ProduceReport> findAll() {
        List<ProduceReport> list = dao.findAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStartjobtime()==null && list.get(i).getCompletetime()==null){
                list.get(i).setStatus("未完成");
            }else if (list.get(i).getStartjobtime()!=null && list.get(i).getCompletetime()==null){
                list.get(i).setStatus("作业中");
            }else {
                list.get(i).setStatus("已完成");
            }
        }
        return list;
    } 

    @Override 
    public ProduceReport findById(Object id) { 
        List<ProduceReport> list = dao.findById(id); 
        return  list.get(0); 
    } 

    @Override 
    public List<ProduceReport> search(ProduceReport produceReport) { 
        return dao.search(produceReport); 
    } 

}