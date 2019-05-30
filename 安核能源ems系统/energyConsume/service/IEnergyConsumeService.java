package com.qhit.energyConsume.service;

import java.util.List;
import com.qhit.energyConsume.pojo.EnergyConsume;
/**
* Created by GeneratorCode on 2019/04/16
*/
public interface IEnergyConsumeService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List<EnergyConsume> findAll();

    EnergyConsume findById(Object id);

    List<EnergyConsume> search(EnergyConsume energyConsume);

    List flowConsume(String year,String month);
    List devTypeConsume(String year,String month);
    List devConsume(String year,String month);
    List electricConsume(String year,String month);
    List waterConsume(String year,String month);
    List oilConsume(String year,String month);
}