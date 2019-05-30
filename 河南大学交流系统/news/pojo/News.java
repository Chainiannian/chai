package com.qhit.news.pojo;




public class News { 
    private Integer news;    //主键 
    private String newsTime;    //发布时间 
    private String newsTheme;    //新闻主题
    private String newsContent;    //新闻主体
    private String newsImage;    //图片 

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public Integer getNews() {
        return news;
    }

    public void setNews(Integer news) { 
        this.news = news;
    } 

    public String getNewsTime() { 
        return newsTime;
    }

    public void setNewsTime(String newsTime) { 
        this.newsTime = newsTime;
    }
    public String getNewsTheme() { 
        return newsTheme;
    }

    public void setNewsTheme(String newsTheme) { 
        this.newsTheme = newsTheme;
    }
    public String getNewsImage() { 
        return newsImage;
    }

    public void setNewsImage(String newsImage) { 
        this.newsImage = newsImage;
    }

 }