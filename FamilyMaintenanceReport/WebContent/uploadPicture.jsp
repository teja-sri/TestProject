

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html>
 
 <head>
	<title>Family Maintenance Report - Upload Picture</title>
</head>
 
 <body>
        <html:form action="/uploadPicture" enctype="multipart/form-data" >
            <html:errors/><br/>
           Select File : <html:file name="UploadPictureForm" property="file"/><br/>
            <html:submit value="Upload"/>
        </html:form>
    </body>
</html>







