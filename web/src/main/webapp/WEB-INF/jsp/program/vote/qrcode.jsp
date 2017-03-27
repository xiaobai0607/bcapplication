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
    <script type="text/javascript" src="${bc}/js/jquery.qrcode.min.js"></script>
    <link rel="stylesheet" href="${bc}/css/program_index.css">
</head>
<body style="margin: 0 auto;">
    <div id="code" style="width: 300px;height: 300px;margin: 0 auto;margin-top: 50px;">

    </div>

</body>
<script type="text/javascript">
    $(function(){
        var str = "http://www.bootcamp.org.cn/web/program/qrcode/sure?myOpen=${myOpen}";
            $("#code").empty();
           // var str = toUtf8($("#mytxt").val());

            $("#code").qrcode({
                correctLevel:0,
                width: 300,
                height:300,
                text: str
            });

    })
    function toUtf8(str) {
        var out, i, len, c;
        out = "";
        len = str.length;
        for(i = 0; i < len; i++) {
            c = str.charCodeAt(i);
            if ((c >= 0x0001) && (c <= 0x007F)) {
                out += str.charAt(i);
            } else if (c > 0x07FF) {
                out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
                out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));
                out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
            } else {
                out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));
                out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
            }
        }
        return out;
    }
</script>
</html>
