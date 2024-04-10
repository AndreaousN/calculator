<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
    <head>
        <title>Skaičiuotuvas</title>
        <jsp:include page="header.jsp"/>
        <style>
            .error{color:red}
        </style>
    </head>
    <body>
        <h2>Internetinis skaičiuotuvas. Galimos operacijos: sudėti, atimti, dauginti, dalinti </h2>
            <h3>Skaiciuotuvas jautrus neigiamiems skaiciams.</h3>
            <form:form method="post" action="calculate" modelAttribute="number">
                Pirmas skaičius: <form:input type="number" path="num1"/>
                                 <form:errors path="num1" cssClass="error"/><br><br>
                Antras skaičius: <form:input type="number" path="num2"/>
                                 <form:errors path="num2" cssClass="error"/><br><br>
                Operacijos ženklas:
                <select name="operation">
                    <option selected="selected" value="+">Sudėtis</option>
                    <option value="-">Atimtis</option>
                    <option value="*">Daugyba</option>
                    <option value="/">Dalyba</option>
                </select><p>
                <input type="submit" value="skaiciuoti">
        </form>
        </form:form>
    </body>
</html>