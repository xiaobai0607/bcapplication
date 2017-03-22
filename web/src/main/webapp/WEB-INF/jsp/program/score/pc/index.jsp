<%--
  Created by IntelliJ IDEA.
  User: yaobin
  Date: 2017/3/9
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="bc" value="${pageContext.request.contextPath}"/>
<%@ page isELIgnored="false" %>
<meta name="viewport" content="width=device-width" />
<c:set var="ctxStatic" value="/static"/>
<html>
<head>
    <title>Boot Camp评分系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Dreativity Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
		Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <script type="text/javascript" src="${bc}/js/jquery-2.1.1.min.js"></script>
    <link rel="stylesheet" href="${bc}/css/program_index.css">
</head>
<body>
<div style="margin: 0 auto;width: 1000px;text-align: center;">
    <c:forEach var="a" items="${a}">
        <div class="container">
            <div class="side">
                <div class="front">
                    <!-- 正面 -->
                </div>
                <div class="back">
                    正在评分
                </div>
            </div>
        </div>
    </c:forEach>
</div>

</body>
<script>
    $(function(){
        setInterval(function(){
            $(".side").css("transform","rotateY(180deg)");
            setTimeout(function () {
                $(".side").css("transform","rotateY(0deg)");;
            }, 2000);
        },4000);
    });
</script>
</html>
