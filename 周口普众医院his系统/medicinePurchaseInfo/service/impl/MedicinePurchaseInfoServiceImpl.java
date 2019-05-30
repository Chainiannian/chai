package com.qhit.medicinePurchaseInfo.service.impl;

import com.qhit.medicinePurchaseInfo.service.IMedicinePurchaseInfoService;
import java.util.List;
import com.qhit.medicinePurchaseInfo.dao.IMedicinePurchaseInfoDao;
import com.qhit.medicinePurchaseInfo.dao.impl.MedicinePurchaseInfoDaoImpl;
import com.qhit.medicinePurchaseInfo.pojo.MedicinePurchaseInfo;

/**
* Created by GeneratorCode on 2018/12/04
*/

public class MedicinePurchaseInfoServiceImpl  implements IMedicinePurchaseInfoService {

    IMedicinePurchaseInfoDao dao = new MedicinePurchaseInfoDaoImpl();

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
        MedicinePurchaseInfo medicinePurchaseInfo = findById(id); 
        return dao.delete(medicinePurchaseInfo); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public MedicinePurchaseInfo findById(Object id) { 
        List<MedicinePurchaseInfo> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public boolean freeUpdate(String sql) {
        return dao.freeUpdate(sql);
    }


    @Override 
    public List<MedicinePurchaseInfo> search(MedicinePurchaseInfo medicinePurchaseInfo) {
            String sql = "select * from medicine_purchase_info where 1=1 "; 
            if (medicinePurchaseInfo.getMedicineCodeid()!=null && !"".equals(medicinePurchaseInfo.getMedicineCodeid())){        
                sql+=" and MEDICINE_CODEID like '%"+medicinePurchaseInfo.getMedicineCodeid()+"%' ";        
            } 
            if (medicinePurchaseInfo.getManCode()!=null && !"".equals(medicinePurchaseInfo.getManCode())){        
                sql+=" and MAN_CODE like '%"+medicinePurchaseInfo.getManCode()+"%' ";        
            } 
            if (medicinePurchaseInfo.getPchAmt()!=null && !"".equals(medicinePurchaseInfo.getPchAmt())){        
                sql+=" and PCH_AMT like '%"+medicinePurchaseInfo.getPchAmt()+"%' ";        
            } 
            if (medicinePurchaseInfo.getPchPrice()!=null && !"".equals(medicinePurchaseInfo.getPchPrice())){        
                sql+=" and PCH_PRICE like '%"+medicinePurchaseInfo.getPchPrice()+"%' ";        
            } 
            if (medicinePurchaseInfo.getPchTotal()!=null && !"".equals(medicinePurchaseInfo.getPchTotal())){        
                sql+=" and PCH_TOTAL like '%"+medicinePurchaseInfo.getPchTotal()+"%' ";        
            } 
            if (medicinePurchaseInfo.getStatus()!=null && !"".equals(medicinePurchaseInfo.getStatus())){        
                sql+=" and STATUS like '%"+medicinePurchaseInfo.getStatus()+"%' ";        
            } 
            if (medicinePurchaseInfo.getPchUserid()!=null && !"".equals(medicinePurchaseInfo.getPchUserid())){        
                sql+=" and PCH_USERID like '%"+medicinePurchaseInfo.getPchUserid()+"%' ";        
            } 
            if (medicinePurchaseInfo.getPchDate()!=null && !"".equals(medicinePurchaseInfo.getPchDate())){        
                sql+=" and PCH_DATE like '%"+medicinePurchaseInfo.getPchDate()+"%' ";        
            } 
            if (medicinePurchaseInfo.getApprvUserid()!=null && !"".equals(medicinePurchaseInfo.getApprvUserid())){        
                sql+=" and APPRV_USERID like '%"+medicinePurchaseInfo.getApprvUserid()+"%' ";        
            } 
            if (medicinePurchaseInfo.getApprvDate()!=null && !"".equals(medicinePurchaseInfo.getApprvDate())){        
                sql+=" and APPRV_DATE like '%"+medicinePurchaseInfo.getApprvDate()+"%' ";        
            } 
            List<MedicinePurchaseInfo> list = dao.freeFind(sql);        
            return list;        
    }

    @Override
    public List<MedicinePurchaseInfo> findAllByStatus() {
        String sql = "select * from medicine_purchase_info where status=2";
        return dao.freeFind(sql);
    }


}