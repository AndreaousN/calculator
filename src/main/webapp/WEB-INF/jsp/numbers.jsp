<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Skaičiai</title>
    <jsp:include page="header.jsp"/>
</head>
<body class="container">
    <div class="table-responsive">
        <table class="table table-striped">
            <tr>
                <th>Pirmas skaičius</th>
                <th>Ženklas</th>
                <th>Antras skaičius</th>
                <th>Rezultatas</th>
                <th>Veiksmas</th>
            </tr>

            <!-- iteruoja per visą skaičių sarašą -->
            <c:forEach var="number" items="${numbers}">

                <!-- konstruoja įrašo atnaujinimo adresą su skaičiaus id -->
                <c:url var="refresh" value="/refresh">
                    <c:param name="id" value="${number.id}"/>
                </c:url>

                <!-- konstruoja įrašo trynimo adresą su skaičiaus id -->
                <c:url var="delete" value="/delete">
                    <c:param name="id" value="${number.id}"/>
                </c:url>

                <!-- konstruoja irašo peržiūros adresą su skaičiaus id -->
                <c:url var="view" value="/view">
                    <c:param name="id" value="${number.id}"/>
                </c:url>

            <tr>
                <td>${number.num1}</td>
                <td>${number.operation}</td>
                <td>${number.num2}</td>
                <td>${number.result}</td>

                <td>

                    <!-- atvaizduoti atnaujinimo adresą --> <a href="${refresh}">Keisti</a>
                    | <a href="${delete}"
                        onclick="if (!(confirm('Ar tikrai norite ištrinti šį įrašą?'))) return false">Trinti</a>
                    |  <!-- atvaizduoti rodymo adresą --> <a href="${view}">Rodyti</a>
                </td>

            </tr>

            </c:forEach>
        </table>
    </div>
</body>
</html>