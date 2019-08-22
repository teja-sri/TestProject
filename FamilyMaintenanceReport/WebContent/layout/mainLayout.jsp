<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<link rel="stylesheet" href="/resources/demos/style.css">
		  
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	    <script>
	    	$( function() {
	        	$( "#datepicker" ).datepicker();
	    	} );
	    </script>
	    <style type="text/css">
	    	body	{
	    		font-family: Georgia,"Times New Roman",serif;
				font-size: 10px;
				font-weight: bold;
				//color: #600;
				line-height: 22px;
				margin: 0;
 				//text-transform: uppercase;
 				//letter-spacing: .2px;
	    	}
	    </style>
	</head>
	<body>
		<table width='800px' height='600px' align='center'
			style='text-align: center;' border="2">

			<tr>
				<td colspan='2'>
					<tiles:insert attribute="header"></tiles:insert>
				</td>
			</tr>
			<tr height='400px'>
				<td width="150px">
					<tiles:insert attribute="leftFrame"></tiles:insert>
				</td>
				<td>
					<tiles:insert attribute="body"></tiles:insert>
				</td>
			</tr>
			<tr>
				<td colspan='2'>
					<tiles:insert attribute="footer"></tiles:insert>
				</td>
			</tr>
		</table>
	</body>
</html>