package com.qhit.energyConsume.service.impl;

import com.qhit.energyConsume.service.IEnergyConsumeService;
import java.util.List;
import com.qhit.energyConsume.dao.IEnergyConsumeDao;
import com.qhit.energyConsume.pojo.EnergyConsume;
import org.springframework.stereotype.Service;
import javax.annotation.Resource; 

/**
* Created by GeneratorCode on 2019/04/16
*/

@Service 
public class EnergyConsumeServiceImpl  implements IEnergyConsumeService {

    @Resource 
    IEnergyConsumeDao dao;

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
        EnergyConsume energyConsume = findById(id); 
        return dao.delete(energyConsume); 
    } 

    @Override 
    public List<EnergyConsume> findAll() {
        return dao.findAll(); 
    } 

    @Override 
    public EnergyConsume findById(Object id) { 
        List<EnergyConsume> list = dao.findById(id); 
        return  list.get(0); 
    } 

    @Override 
    public List<EnergyConsume> search(EnergyConsume energyConsume) { 
        return dao.search(energyConsume); 
    }

    @Override
    public List flowConsume(String year, String month) {
        return dao.flowConsume(year,month);
    }

    @Override
    public List devTypeConsume(String year, String month) {
        return dao.devTypeConsume(year,month);
    }

    @Override
    public List devConsume(String year, String month) {
        return dao.devConsume(year,month);
    }

    @Override
    public List electricConsume(String year, String month) {
        return dao.electricConsume(year,month);
    }

    @Override
    public List waterConsume(String year, String month) {
        return dao.waterConsume(year,month);
    }

    @Override
    public List oilConsume(String year, String month) {
        return dao.oilConsume(year,month);
    }

}