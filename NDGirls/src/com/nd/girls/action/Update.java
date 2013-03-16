package com.nd.girls.action;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Update {
	private String uploadPath = "D:\\temp"; // �ϴ��ļ���Ŀ¼
    private String tempPath = "d:\\temp\\buffer\\"; // ��ʱ�ļ�Ŀ¼
    File tempPathFile;
 
    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest request, HttpServletResponse response)
           throws IOException, ServletException {
       try {
           // Create a factory for disk-based file items
           DiskFileItemFactory factory = new DiskFileItemFactory();
 
           // Set factory constraints
           factory.setSizeThreshold(4096); // ���û�������С��������4kb
           factory.setRepository(tempPathFile);// ���û�����Ŀ¼
 
           // Create a new file upload handler
           ServletFileUpload upload = new ServletFileUpload(factory);
 
           // Set overall request size constraint
           upload.setSizeMax(4194304); // ��������ļ��ߴ磬������4MB
 
           List<FileItem> items = upload.parseRequest(request);// �õ����е��ļ�
           Iterator<FileItem> i = items.iterator();
           while (i.hasNext()) {
              FileItem fi = (FileItem) i.next();
              String fileName = fi.getName();
              if (fileName != null) {
                  File fullFile = new File(fi.getName());
                  File savedFile = new File(uploadPath, fullFile.getName());
                  fi.write(savedFile);
              }
           }
           System.out.print("upload succeed");
       } catch (Exception e) {
           // ������ת����ҳ��
           e.printStackTrace();
       }
    }
 
    public void init() throws ServletException {
       File uploadFile = new File(uploadPath);
       if (!uploadFile.exists()) {
           uploadFile.mkdirs();
       }
       File tempPathFile = new File(tempPath);
        if (!tempPathFile.exists()) {
           tempPathFile.mkdirs();
       }
    }
}
