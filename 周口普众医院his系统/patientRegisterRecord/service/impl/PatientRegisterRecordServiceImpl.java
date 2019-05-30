package com.qhit.patientRegisterRecord.service.impl;

import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.patientRegisterRecord.service.IPatientRegisterRecordService;
import java.util.List;
import com.qhit.patientRegisterRecord.dao.IPatientRegisterRecordDao;
import com.qhit.patientRegisterRecord.dao.impl.PatientRegisterRecordDaoImpl;
import com.qhit.patientRegisterRecord.pojo.PatientRegisterRecord;

/**
* Created by GeneratorCode on 2018/12/21
*/

public class PatientRegisterRecordServiceImpl  implements IPatientRegisterRecordService {

    IPatientRegisterRecordDao dao = new PatientRegisterRecordDaoImpl();

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
        PatientRegisterRecord patientRegisterRecord = findById(id); 
        return dao.delete(patientRegisterRecord); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public PatientRegisterRecord findById(Object id) { 
        List<PatientRegisterRecord> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public boolean freeUpdate(String sql) {
        return dao.freeUpdate(sql);
    }


    @Override 
    public List<PatientRegisterRecord> search(PatientRegisterRecord patientRegisterRecord) {
            String sql = "select * from patient_register_record where 1=1 "; 
            if (patientRegisterRecord.getPatientId()!=null && !"".equals(patientRegisterRecord.getPatientId())){        
                sql+=" and patient_id like '%"+patientRegisterRecord.getPatientId()+"%' ";        
            } 
            if (patientRegisterRecord.getDeptId()!=null && !"".equals(patientRegisterRecord.getDeptId())){        
                sql+=" and dept_id like '%"+patientRegisterRecord.getDeptId()+"%' ";        
            } 
            if (patientRegisterRecord.getRegisterDate()!=null && !"".equals(patientRegisterRecord.getRegisterDate())){        
                sql+=" and register_date like '%"+patientRegisterRecord.getRegisterDate()+"%' ";        
            } 
            if (patientRegisterRecord.getRecordUser()!=null && !"".equals(patientRegisterRecord.getRecordUser())){        
                sql+=" and record_user like '%"+patientRegisterRecord.getRecordUser()+"%' ";        
            } 
            if (patientRegisterRecord.getDoctorId()!=null && !"".equals(patientRegisterRecord.getDoctorId())){        
                sql+=" and doctor_id like '%"+patientRegisterRecord.getDoctorId()+"%' ";        
            } 
            if (patientRegisterRecord.getStatus()!=null && !"".equals(patientRegisterRecord.getStatus())){        
                sql+=" and status like '%"+patientRegisterRecord.getStatus()+"%' ";        
            } 
            List<PatientRegisterRecord> list = dao.freeFind(sql);        
            return list;        
    }

    @Override
    public List findAlls() {
        String sql = "SELECT * from base_patient_info ORDER BY patient_name";
        List list = dao.freeFind(sql);
        return list;
    }

    @Override
    public List<PatientRegisterRecord> findByUserid(Integer userId) {
        return dao.findByDoctorId(userId);
    }

    @Override
    public List<PatientRegisterRecord> findByUserids(BaseUser bucct) {
        String sql = "SELECT pr.*,CAST(bp.patient_name AS CHAR) AS pname\n" +
                " from patient_register_record pr JOIN base_patient_info bp ON pr.patient_id=bp.patient_id\n" +
                " WHERE pr.status = '1' AND pr.dept_id = '"+bucct.getDeptId()+"' OR pr.doctor_id = '"+bucct.getUserId()+"' AND pr.status='1'";
        return dao.freeFind(sql);
    }


}