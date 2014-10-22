<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%--<c:redirect url="/magazyn" />--%>
<html>
    <head>
        <!--<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">-->
        <title>Insert title here</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/mycss.css" />"/>
    </head>
    <body>
        <menu>
            <a href="<c:url value="/magazyn" />">Pokaż magazyn</a>
            <a href="<c:url value="/dodaj" />">Dodaj towar do magazynu</a>
        </menu>
    </body>