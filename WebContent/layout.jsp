<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="/semi/boot/css/bootstrap.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js" ></script>
<script src="/semi/boot/js/bootstrap.js"></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title"/> </title>


</head>
<body>

<tiles:insertAttribute name="navbar"/>
<tiles:insertAttribute name="chat"/>
<tiles:insertAttribute name="body"/>
<tiles:insertAttribute name="footer"/>

</body>
</html>