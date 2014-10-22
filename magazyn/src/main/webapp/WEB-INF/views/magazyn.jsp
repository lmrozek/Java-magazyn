<%@include file="./includes/common.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE>
<html>
    <head>
        <!--<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">-->
        <title>Insert title here</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/mycss.css" />"/>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.1.js" />"></script>
        <script type="text/javascript" src="http://code.jquery.com/jquery-2.1.1.js"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/searchName.js" />"></script>
    </head>
    <body>
        <menu>
            <a href="<c:url value="/" />">Strona główna</a>
            <a href="<c:url value="/dodaj" />">Dodaj towar do magazynu</a>
        </menu>
        <p><b>Filtruj wyniki po:</b></p>
        <form>
            <p><b>kategorii:</b>
                <c:forEach var="kat" items="${kategorie}">
                    <c:set var="kati" value=""/>
                    <c:forEach var="checked" items="${checkeds}">
                        <c:if test="${kat==checked}">
                            <c:set var="kati" value="checked=\"checked\""/>
                        </c:if>
                    </c:forEach>
                    <input type="checkbox" name="kategoria" value="${kat}" <c:out value="${kati}"/> />${kat}
                </c:forEach></p>
            <p><b>cenie:</b>
                od<input type="text" name="cena1" value="${cena1}" >do<input type="text" name="cena2" value="${cena2}" >
                <span style="color: red"><c:out value="${filtrByCenaValid}"/></span>
            </p>
            <!--<button  name="filtruj" value="filtruj" formaction="magazyn" formmethod="POST">dfd</button>-->
            <input type="submit" name="filtruj" value="filtruj" formaction="magazyn" formmethod="POST"/>
        </form>
            <p><b>Szukaj</b> towaru po nazwie:
            <input type="text" name="nazwa" value="" id="search" autocomplete="off"/>
        </p>
        <table>
            <tr>
                <td><a href="magazyn?sortBy=kat" class="sort"><b>Kategoria</b></a></td>
                <td><a href="magazyn?sortBy=nazwa" class="sort"><b>Nazwa</b></a></td>
                <td><b>Opis</b></td>
                <td><a href="magazyn?sortBy=cena" class="sort"><b>Cena</b></a></td>
                <td><b>Ilość</b></td>
            </tr>
            <c:forEach var="towar" items="${magazyn}">
                <tr class="results">
                    <td><c:out value="${towar.kategoria}" /></td>
                    <td class="resultsName"><c:out value="${towar.nazwa}" /></td>
                    <td><c:out value="${towar.opis}" /></td>
                    <td><c:out value="${towar.cena}" /></td>
                    <td><c:out value="${towar.ilosc}" /></td>
                    <td><a href="magazyn?id=${towar.id}&action=delete">Usuń</a></td>
                    <td><a href="magazynzmien?id=${towar.id}&action=update">Zmień</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>