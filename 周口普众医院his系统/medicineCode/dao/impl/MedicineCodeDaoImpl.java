package com.qhit.medicineCode.dao.impl;

import com.qhit.medicineCode.dao.IMedicineCodeDao;
import com.qhit.utils.BaseDao;
import java.util.List;

/**
* Created by GeneratorCode on 2018/12/03
*/

public class MedicineCodeDaoImpl extends BaseDao implements IMedicineCodeDao {

    @Override 
    public List findAll() { 
        String sql = "select * from medicine_code"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findById(Object id) { 
        String sql = "select * from medicine_code where code_id='"+id+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByMedicineName(Object medicineName) { 
        String sql = "select * from medicine_code where MEDICINE_NAME='"+medicineName+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByAliasName(Object aliasName) { 
        String sql = "select * from medicine_code where alias_name='"+aliasName+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findBySpecification(Object specification) { 
        String sql = "select * from medicine_code where specification='"+specification+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByManCode(Object manCode) { 
        String sql = "select * from medicine_code where man_code='"+manCode+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByManChnName(Object manChnName) { 
        String sql = "select * from medicine_code where man_chn_name='"+manChnName+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByTypeName(Object typeName) { 
        String sql = "select * from medicine_code where type_name='"+typeName+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByTypeCode(Object typeCode) { 
        String sql = "select * from medicine_code where type_code='"+typeCode+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByStockUnit(Object stockUnit) { 
        String sql = "select * from medicine_code where stock_unit='"+stockUnit+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByRetailPrice(Object retailPrice) { 
        String sql = "select * from medicine_code where retail_price='"+retailPrice+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByStockPrice(Object stockPrice) { 
        String sql = "select * from medicine_code where stock_price='"+stockPrice+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByDrugNotesPatient(Object drugNotesPatient) { 
        String sql = "select * from medicine_code where drug_notes_patient='"+drugNotesPatient+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByDrugNote(Object drugNote) { 
        String sql = "select * from medicine_code where drug_note='"+drugNote+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByDrugForm(Object drugForm) { 
        String sql = "select * from medicine_code where drug_form='"+drugForm+"'"; 
        return freeFind(sql); 
    } 




}