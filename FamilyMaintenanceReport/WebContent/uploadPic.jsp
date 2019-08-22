<%-- <input type="file" name="file" size="50" /> <br /> 
<input type="button" value="Upload File" onclick="submitForm('UploadPicturePost.jsp?id=<%=id %>', 'Y')" />
 --%><%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>


<head>
	<title>Family Maintenance Report - Upload Picture</title>
</head>

<!-- <img src="../Struts_/upload/Lighthouse.jpg" alt="Smiley face" height="42" width="42"> -->

<script>
					function submitForm(action) {
						var form = document.UploadPictureForm;
						form.action = action;
						form.submit();
					}
					
					function submitForm(action, uploadFlag) {
						var form = document.UploadPictureForm;
						form.enctype = "multipart/form-data";
						form.action = action;
						form.submit();
					}
</script>

<body>
	<html:form action="/upload.do?ssss=uploadPicture"
		enctype="multipart/form-data" method="post">
		<html:errors />
		<br />
           Select File : <html:file name="UploadPictureForm"
			property="file" />
		<br />
		<html:submit value="Upload" />
	</html:form>
</body>
</html>
