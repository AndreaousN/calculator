<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Skaičiaus atnaujinimas</title>
        <jsp:include page="header.jsp"/>
        <style>
            form{margin-left: 20px}
        </style>
    </head>
    <body>
        <form:form name="number" action="/refreshNumber" method="post">
                <!-- id būtina pateikti formoje, kitaip į back-end nueis null. Todėl darome hidden, kad neredaguotų -->
                 <input type="hidden" name="id" value="${number.id}"><p>
                 Pirmas skaičius:<br>
                 <input type="number" name="num1" value="${number.num1}"><p>
                 Ženklas:<br>
                 <input type="text" name="operation" value="${number.operation}"><p>
                 Antras skaičius:<br>
                 <input type="number" name="num2" value="${number.num2}"><p>
                 Rezultatas:<br>
                 <input type="number" name="result" value="${number.result}"><p>
                 <input type="submit" value="Atnaujinti">
        </form:form>
    </body>
</html>