<%@include file="./includes/common.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE>
<html>
    <head>
        <!--        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">-->
        <title>Insert title here</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/mycss.css" />"/>
    </head>
    <body>
        <menu>
            <a href="<c:url value="/" />">Strona główna</a>
            <a href="<c:url value="/magazyn" />">Powrót do magazynu</a>
        </menu>
        <form:form action="dodaj" method="post" commandName="towar">
            <table class="novisible">
                <tr>
                    <td>Kategoria: </td>
                    <td><form:input path="kategoria" /></td>
                </tr>
                <tr>
                    <td>Nazwa: </td>
                    <td><form:input path="nazwa" /></td>
                </tr>
                <tr>
                    <td>Opis: </td>
                    <td><form:input path="opis" /></td>
                </tr>
                <tr>
                    <td>Cena: </td>
                    <td><form:input path="cena" /></td>
                    <td>
                        <form:errors path="cena" cssClass="err"/>
                    </td>
                </tr>
                <tr>
                    <td>Ilosc: </td>
                    <td><form:input path="ilosc" /></td>
                    <td>
                        <form:errors path="ilosc" cssClass="err"/>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Dodaj" /></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>