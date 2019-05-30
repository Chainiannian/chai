package com.qhit.news.controller; 

import com.qhit.news.pojo.News; 
import com.qhit.news.service.INewsService; 
import org.springframework.web.bind.annotation.RequestMapping; 
import javax.annotation.Resource; 
import java.util.List; 
import org.springframework.web.bind.annotation.RestController; 



@RestController 
@RequestMapping("/news") 
public class NewsController { 

    @Resource 
    INewsService newsService; 

    @RequestMapping("/insert") 
    public void insert(News news) { 
        newsService.insert(news); 
    } 

    @RequestMapping("/delete") 
    public void delete(Integer news) { 
        newsService.delete(news); 
    } 

    @RequestMapping("/update") 
    public void update(News news) { 
        newsService.update(news); 
    } 

    @RequestMapping("/updateSelective") 
    public void updateSelective(News news) { 
        newsService.updateSelective(news); 
    } 

    @RequestMapping("/load") 
    public News load(Integer news) { 
        News news1 = newsService.findById(news);
        return news1;
    } 

    @RequestMapping("/list") 
    public List<News> list()  { 
        List<News> list = newsService.findAll(); 
        return list; 
    } 

    @RequestMapping("/search") 
    public List<News> search(News news) { 
        List<News> list = newsService.search(news); 
        return list; 
    } 

} 
