<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src='<c:url value="/js/app.js" />'></script>
<link href='<c:url value="/css/style.css" />' rel="stylesheet" />

<title>Welcome</title>
</head>
<body>
  <h1>Welcome to CPB Demo App</h1>
  <hr>

  <h2>Your name is ${name}</h2>

</body>
</html>