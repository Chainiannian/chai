package com.qhit.medicineCode.dao;

import com.qhit.utils.BaseDao;
import java.util.List;

/**
* Created by GeneratorCode on 2018/12/03
*/

public interface IMedicineCodeDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    boolean freeUpdate(String sql);

    List findByMedicineName(Object medicineName);

    List findByAliasName(Object aliasName);

    List findBySpecification(Object specification);

    List findByManCode(Object manCode);

    List findByManChnName(Object manChnName);

    List findByTypeName(Object typeName);

    List findByTypeCode(Object typeCode);

    List findByStockUnit(Object stockUnit);

    List findByRetailPrice(Object retailPrice);

    List findByStockPrice(Object stockPrice);

    List findByDrugNotesPatient(Object drugNotesPatient);

    List findByDrugNote(Object drugNote);

    List findByDrugForm(Object drugForm);

}