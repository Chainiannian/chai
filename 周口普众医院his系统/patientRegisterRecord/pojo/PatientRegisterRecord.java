package com.qhit.patientRegisterRecord.pojo;


import com.sun.org.glassfish.gmbal.Description;

/**
* Created by GeneratorCode on 2018/12/21
*/

public class PatientRegisterRecord {

    private Integer registerId;    //主键
    private Integer patientId;    //病人
    private Integer deptId;    //部门
    private String registerDate;    //挂号时间
    private Integer recordUser;    //挂号人
    private Integer doctorId;    //医生
    private Integer status;    //状态
    @Description("nu")
    private String pname;    //病人姓名

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getRegisterId() {
        return registerId;
    }
 
    public void setRegisterId(Integer registerId) { 
        this.registerId = registerId;
    }
 
    public Integer getPatientId() { 
        return patientId;
    }
 
    public void setPatientId(Integer patientId) { 
        this.patientId = patientId;
    }
 
    public Integer getDeptId() { 
        return deptId;
    }
 
    public void setDeptId(Integer deptId) { 
        this.deptId = deptId;
    }
 
    public String getRegisterDate() { 
        return registerDate;
    }
 
    public void setRegisterDate(String registerDate) { 
        this.registerDate = registerDate;
    }
 
    public Integer getRecordUser() { 
        return recordUser;
    }
 
    public void setRecordUser(Integer recordUser) { 
        this.recordUser = recordUser;
    }
 
    public Integer getDoctorId() { 
        return doctorId;
    }
 
    public void setDoctorId(Integer doctorId) { 
        this.doctorId = doctorId;
    }
 
    public Integer getStatus() { 
        return status;
    }
 
    public void setStatus(Integer status) { 
        this.status = status;
    }
 

 }