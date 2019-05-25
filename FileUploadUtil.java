package com.qhit.utils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;


@WebServlet(name = "FileUploadUtil",urlPatterns = "/FileUploadUtil")
public class FileUploadUtil extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取输入流
        String path = request.getSession().getServletContext().getRealPath("upload");
        ServletInputStream inputStream = request.getInputStream();
        FileOutputStream fos = new FileOutputStream(path+"\\temp.txt");
        int b;
        while((b=inputStream.read())!=-1){
            fos.write(b);
        }
        inputStream.close();
        fos.close();
        RandomAccessFile randomAccessFile = new RandomAccessFile(path+"\\temp.txt","r");
//        去掉前4行
        String firstLine = randomAccessFile.readLine();
        String secondLine = randomAccessFile.readLine();
        secondLine =new String( secondLine.getBytes("iso-8859-1"),"utf-8");
        String[] arr = secondLine.split("; ");
        String filename =  UUID.randomUUID().toString()+arr[2].substring(10,arr[2].length()-1);
        String name = arr[1].substring(6,arr[1].length()-1);
        String thirdLine = randomAccessFile.readLine();
        randomAccessFile.readLine();
//        获取输入流中内容的长度
        int length = request.getContentLength();
        int realLength = length - firstLine.getBytes("utf-8").length
                -secondLine.getBytes("utf-8").length
                -thirdLine.getBytes("utf-8").length
                - firstLine.getBytes("utf-8").length-2-12;

        FileOutputStream fos2 = new FileOutputStream(path+"\\"+filename);
        int count=0;
        while((b=randomAccessFile.read())!=-1){
            fos2.write(b);
            count++;
            if(count==realLength){
                break;
            }
        }
        randomAccessFile.close();
        fos2.close();
        request.setAttribute("filename",filename);
        request.setAttribute("name",name);
        request.getRequestDispatcher("files/message.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
