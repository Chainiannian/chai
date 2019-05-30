package com.qhit.medicineReqPlan.service.impl;

import com.qhit.medicineReqPlan.service.IMedicineReqPlanService;
import java.util.List;
import com.qhit.medicineReqPlan.dao.IMedicineReqPlanDao;
import com.qhit.medicineReqPlan.dao.impl.MedicineReqPlanDaoImpl;
import com.qhit.medicineReqPlan.pojo.MedicineReqPlan;

/**
* Created by GeneratorCode on 2018/12/04
*/

public class MedicineReqPlanServiceImpl  implements IMedicineReqPlanService {

    IMedicineReqPlanDao dao = new MedicineReqPlanDaoImpl();

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
        MedicineReqPlan medicineReqPlan = findById(id); 
        return dao.delete(medicineReqPlan); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public MedicineReqPlan findById(Object id) { 
        List<MedicineReqPlan> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public boolean freeUpdate(String sql) {
        return dao.freeUpdate(sql);
    }


    @Override 
    public List<MedicineReqPlan> search(MedicineReqPlan medicineReqPlan,Integer integer) {
            String sql = "select * from medicine_req_plan where 1=1";
            if (medicineReqPlan.getMedicineCodeid()!=null && !"".equals(medicineReqPlan.getMedicineCodeid())){        
                sql+=" and MEDICINE_CODEID like '%"+medicineReqPlan.getMedicineCodeid()+"%' ";        
            } 
            if (medicineReqPlan.getReqamt()!=null && !"".equals(medicineReqPlan.getReqamt())){        
                sql+=" and REQAMT like '%"+medicineReqPlan.getReqamt()+"%' ";        
            } 
            if (medicineReqPlan.getAppUserid()!=null && !"".equals(medicineReqPlan.getAppUserid())){        
                sql+=" and APP_USERID like '%"+medicineReqPlan.getAppUserid()+"%' ";        
            } 
            if (medicineReqPlan.getAppDate()!=null && !"".equals(medicineReqPlan.getAppDate())){        
                sql+=" and APP_DATE like '%"+medicineReqPlan.getAppDate()+"%' ";        
            } 
            if (medicineReqPlan.getPurpose()!=null && !"".equals(medicineReqPlan.getPurpose())){        
                sql+=" and PURPOSE like '%"+medicineReqPlan.getPurpose()+"%' ";        
            } 
            if (medicineReqPlan.getStatus()!=null && !"".equals(medicineReqPlan.getStatus())){        
                sql+=" and STATUS like '%"+medicineReqPlan.getStatus()+"%' ";        
            } 
            if (medicineReqPlan.getApprvUserid()!=null && !"".equals(medicineReqPlan.getApprvUserid())){        
                sql+=" and APPRV_USERID like '%"+medicineReqPlan.getApprvUserid()+"%' ";        
            } 
            if (medicineReqPlan.getApprvDate()!=null && !"".equals(medicineReqPlan.getApprvDate())){        
                sql+=" and APPRV_DATE like '%"+medicineReqPlan.getApprvDate()+"%' ";        
            }
            if (integer!=-233){
                sql+=" and APP_USERID='"+integer+"'";
            }
            List<MedicineReqPlan> list = dao.freeFind(sql);        
            return list;        
    }

    @Override
    public List<MedicineReqPlan> findAll1(Integer userId) {
        String sql = "SELECT * from medicine_req_plan WHERE APP_USERID='"+userId+"'";
        return dao.freeFind(sql);
    }

    @Override
    public List findAllby() {
        String sql = "SELECT * from medicine_req_plan WHERE STATUS=2";
        return dao.freeFind(sql);
    }

    @Override
    public void findAllByUp(Integer pchId, Integer medicineCodeid) {
        List<MedicineReqPlan> allby = findAllby();
        for (int i = 0; i < allby.size(); i++) {
            if (allby.get(i).getMedicineCodeid()==medicineCodeid){
                MedicineReqPlan medicineReqPlan = allby.get(i);
                medicineReqPlan.setStatus(3);
                medicineReqPlan.setPchId(pchId);
                updateSelective(medicineReqPlan);
            }
        }
    }

    @Override
    public List<MedicineReqPlan> findAllpchId(Integer pchId) {
        String sql = "SELECT * FROM medicine_req_plan WHERE pch_id="+pchId+"";
        return dao.freeFind(sql);
    }


}