<%@page import="com.fer.service.FamilyExpenseReportServiceImpl"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>
<%@page import="com.fer.bean.User"%>
<%@page import="com.fer.bean.UserSession"%>

<head>
	<title>Family Maintenance Report - Upload Picture Post</title>
</head>

<%
   File file ;
   int maxFileSize = 5000 * 1024;
   int maxMemSize = 5000 * 1024;
   ServletContext context = pageContext.getServletContext();
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
					/* User userSession = new User();
					userSession.setImagePath(fileName);
					long id = System.currentTimeMillis();			
					session.setAttribute("id", id); */
					//String id = request.getParameter("id");
					//session
	            session.setAttribute("imagePath", fileName);
            }
         }
      }catch(Exception ex) {
         System.out.println(ex);
      }
   }
%>
 
 <%
   if(!"".equals(picturePath)) {
	
	   FamilyExpenseReportServiceImpl familyExpenseReportServiceImpl = new FamilyExpenseReportServiceImpl();
	   String  id= request.getParameter("id");
	   UserSession userSession = (UserSession)session.getAttribute(id);

	   //String userName = userSession.getUsername();
	   //String userName = session.getAttribute("userName").toString();
	   boolean uploadFlag = familyExpenseReportServiceImpl.uploadPicture(userSession.getUsername(), picturePath);
%>
<%	   
	   if(uploadFlag){
		   out.println("Picture has been uploaded successfully");
		   //session.setAttribute("imagePath", picturePath);
	   }
	   else{
		   out.println("Failed to upload picture");
	   }
	   
%>
	<%
   }
   
%>
	
