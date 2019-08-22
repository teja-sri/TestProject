package com.fer.action;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.fer.bean.UserSession;
import com.fer.form.UploadPictureForm;
import com.fer.service.FamilyExpenseReportServiceImpl;
import com.fer.service.FamilyExpenseReportServiceInterface;


public class SavePicAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		UploadPictureForm uploadPictureForm = (UploadPictureForm) form;
		
		HttpSession session = request.getSession();
		
		UserSession userSession = (UserSession) session.getAttribute("userSession");
		
	    FormFile file = uploadPictureForm.getFile();
	    //Get the servers upload directory real path name
	   // String filePath = getServlet().getServletContext().getContextPath()+"/"+"upload";
	    String filePath =getServlet().getServletContext().getRealPath("/") +"upload";
	    //create the upload folder if not exists
	    File folder = new File(filePath);
	    if(!folder.exists()){
	    	folder.mkdir();
	    }
	    
 String fileName = file.getFileName();
	    if(!("").equals(fileName)){  
	        System.out.println("Server path:" +filePath);
	        File newFile = new File(filePath, fileName);
	        if(!newFile.exists()){
	          FileOutputStream fos = new FileOutputStream(newFile);
	          fos.write(file.getFileData());
	          fos.flush();
	          fos.close();
	        }  
	    
	    
	   /* String fileName = file.getFileName();
	    System.out.println("Server path:" +filePath+"/"+file.getFileName());
	    String imagePath=filePath+"/"+file.getFileName();
	    if(!("").equals(fileName)){  
	    	File newFile = new File(filePath, fileName);
              
	        if(!newFile.exists()){
	          FileOutputStream fos = new FileOutputStream(newFile);
	          fos.write(file.getFileData());
	          fos.flush();
	          fos.close();
	        }  */
	        String imagePath = filePath+"/"+fileName;
	        FamilyExpenseReportServiceInterface familyExpenseReportService = new FamilyExpenseReportServiceImpl();
              session.setAttribute("image", uploadPictureForm.getFile()); 
	          session.setAttribute("imagePath", imagePath);
	        boolean uploadPictureFlag = familyExpenseReportService.uploadPicture(session.getAttribute("userName").toString(), fileName);
	        
	        if(uploadPictureFlag)	{
	        	userSession.setImagePath(fileName);
	        }
	   }
	
		return mapping.findForward("picStatus");
		
}
	
}

