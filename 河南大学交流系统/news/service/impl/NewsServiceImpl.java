package com.qhit.news.service.impl;

import com.qhit.news.service.INewsService;
import java.util.List;
import com.qhit.news.dao.INewsDao;
import com.qhit.news.pojo.News;
import org.springframework.stereotype.Service;
import javax.annotation.Resource; 



@Service 
public class NewsServiceImpl  implements INewsService {

    @Resource 
    INewsDao dao;

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
        News news = findById(id); 
        return dao.delete(news); 
    } 

    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 

    @Override 
    public News findById(Object id) { 
        List<News> list = dao.findById(id); 
        return  list.get(0); 
    } 

    @Override 
    public List<News> search(News news) { 
        return dao.search(news); 
    } 

}