<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='<c:url value="/css/style.css" />' rel="stylesheet" />
<title>CPB Error</title>
</head>
<body>
	<h1>CPB Error Page</h1>
	<hr/>
	<p>Application has encountered an error. Please contact support on
		...</p>
	<p>Message: ${exception.message}</p>
	<!--
    	Failed URL: ${url}
    	Exception:  ${exception.message}
    	<c:forEach items="${exception.stackTrace}" var="ste">    ${ste} </c:forEach>
  	-->
</body>
</html>