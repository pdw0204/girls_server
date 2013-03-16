package com.nd.girls.action;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/**
 * �����ϴ�
 * @author pdw
 */
public class NDCheckInAction extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private File tempFile ;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (!isMultipart) {
			System.out.print(req.getParameter("a"));
			getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
		} else {
			req.setCharacterEncoding("UTF-8");
			String no = req.getParameter("nd_No");
			
			initDir(no);
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			// Set factory constraints
			factory.setSizeThreshold(4096); // 设置缓存大小
			factory.setRepository(tempFile);// 设置缓存目录
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			// Set overall request size constraint
			upload.setSizeMax(4194304); 
			
			try {
				List<FileItem> files = upload.parseRequest(req);
				Iterator<FileItem> iterator = files.iterator();
				final String saveFilePath = getDirFile(no);
				while(iterator.hasNext()) {
					FileItem item = (FileItem) iterator.next();
					if (item.isFormField()) {
						String fieldName = item.getFieldName();
						String fieldValue = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
						System.out.println("name:"+fieldName+";value:"+fieldValue);
					} else {
						String fileName = item.getName();
						if (fileName != null) {
							File savedFile = new File(saveFilePath, fileName);
							item.write(savedFile);
						}
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private String getDirFile(String no) {
		String file = String.format(NDGirlsActionConstant.DIR_IMAGE, no);
		return file ;
	}
	
	private void initDir(String no) {
		File file = new File(String.format(NDGirlsActionConstant.DIR_IMAGE, no));
		if (!file.exists()) {
			file.mkdirs();
		}
	}
	
	@Override
	public void init() throws ServletException {
	       File uploadFile = new File(NDGirlsActionConstant.DIR_IMAGE);
	       if (!uploadFile.exists()) {
	           uploadFile.mkdirs();
	       }
	       tempFile = new File(NDGirlsActionConstant.DIR_IMAGE_TEMP);
	        if (!tempFile.exists()) {
	        	tempFile.mkdirs();
	       }
	    }

}
