package com.fer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class UploadPicAction extends DispatchAction{
	
	public ActionForward load(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("landingSuccess");
         
		}

	public ActionForward uploadPicture(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		return mapping.findForward("uploadProcess");
		 /*File file ;
		   int maxFileSize = 5000 * 1024;
		   int maxMemSize = 5000 * 1024;
		   //ServletRequest pageContext = null;
		   //String pageContext = request.getContextPath();
		   PageContext pageContext = file.
		ServletContext context = ((ServletRequest) pageContext).getServletContext();
		   String filePath = context.getInitParameter("file-upload");

		   // Verify the content type
		   String contentType = request.getContentType();
		   String picturePath = "";
		   if ((contentType.indexOf("multipart/form-data") >= 0)) {

		      DiskFileItemFactory factory = new DiskFileItemFactory();
		      // maximum size that will be stored in memory
		      factory.setSizeThreshold(maxMemSize);
		      // Location to save data that is larger than maxMemSize.
		      factory.setRepository(new File("c:\\temp"));

		      // Create a new file upload handler
		      ServletFileUpload upload = new ServletFileUpload(factory);
		      // maximum file size to be uploaded.
		      upload.setSizeMax( maxFileSize );
		      try{ 
		         // Parse the request to get file items.
		         List fileItems = upload.parseRequest(request);

		         // Process the uploaded file items
		         Iterator i = fileItems.iterator();

		         while ( i.hasNext () ) 
		         {
		            FileItem fi = (FileItem)i.next();
		            if ( !fi.isFormField () )	
		            {
			            // Get the uploaded file parameters
			            String fieldName = fi.getFieldName();
			            String fileName = fi.getName();
			            boolean isInMemory = fi.isInMemory();
			            long sizeInBytes = fi.getSize();
			            // Write the file
			            if( fileName.lastIndexOf("\\") >= 0 ){
				            file = new File( filePath + 
				            fileName.substring( fileName.lastIndexOf("\\"))) ;
			            }else{
				            file = new File( filePath + 
				            fileName.substring(fileName.lastIndexOf("\\")+1)) ;
			            }
			            fi.write( file ) ;
			            
			            picturePath = fileName;
							 User userSession = new User();
							userSession.setImagePath(fileName);
							long id = System.currentTimeMillis();			
							session.setAttribute("id", id); 
							//String id = request.getParameter("id");
							//session
			            HttpSession session = request.getSession();
			            session.setAttribute("imagePath", fileName);
		            }
		         }
		      }catch(Exception ex) {
		         System.out.println(ex);
		      }
		   }
		 
		   if(!"".equals(picturePath)) {
			
			   FamilyExpenseReportServiceImpl familyExpenseReportServiceImpl = new FamilyExpenseReportServiceImpl();
			   String  id= request.getParameter("id");
			   HttpSession session = request.getSession();
			   UserSession userSession = (UserSession)session.getAttribute(id);

			   //String userName = userSession.getUsername();
			   //String userName = session.getAttribute("userName").toString();
			   boolean uploadFlag = familyExpenseReportServiceImpl.uploadPicture(userSession.getUsername(), picturePath);
			   String forwardPage = "";
			   if(uploadFlag){
				   forwardPage = "uploadSuccess";
				   //out.println("Picture has been uploaded successfully");
				   //session.setAttribute("imagePath", picturePath);
			   }
			   else{
				   //out.println("Failed to upload picture");
				   forwardPage = "uploadFail";
			   }
			   return mapping.findForward(forwardPage); 
		   }
		   
		return null;*/
         
		}
}
