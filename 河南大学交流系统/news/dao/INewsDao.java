package com.qhit.news.dao;

import org.apache.ibatis.annotations.Mapper;
import com.qhit.news.pojo.News;
import java.util.List;



@Mapper  
public interface INewsDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    boolean freeUpdate(String sql);

    List<News> search(News news);

    List findByNewsTime(Object newsTime);

    List findByNewsTheme(Object newsTheme);

    List findByNewsContent(Object newsContent);

    List findByNewsImage(Object newsImage);

}