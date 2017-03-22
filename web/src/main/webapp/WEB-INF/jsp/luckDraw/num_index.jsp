<%--
  Created by IntelliJ IDEA.
  User: yaobin
  Date: 2017/3/14
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="bc" value="${pageContext.request.contextPath}"/>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>BootCamp抽奖页面</title>
    <script type="text/javascript" src="${bc}/js/jquery-2.1.1.min.js"></script>
    <link rel="stylesheet" href="${bc}/css/num_index.css">
</head>
<body style="background: url(${luckDraw.luckDrawImg}) no-repeat  ;background-size: cover;">
    <p style="font-size: ${luckDraw.luckDrawTitleSize};color: ${luckDraw.luckDrawTitleColor};font-family: 'Calibri Light';font-weight : bold;">${luckDraw.luckDrawTitle}</p><br>
    <div id="resultDiv" style="margin:0 auto;color: #${prize.prizeNumColor};font-size: ${prize.prizeNumSize}px;width: ${prize.prizeNumLeftRightSpace*prize.prizeNumColumn}px;height: 100px">
    </div>
    <c:forEach var="luckyDrawPeople" items="${luckyDrawPeopleList}" varStatus="status">
        <input type="hidden" name="luckyDrawPeopleNums"  value="${luckyDrawPeople.luckyDrawPeopleNum}">
    </c:forEach>
    <!--<input type="hidden" id="prize" name="prize"  value="${prize}"> -->
    <input type="hidden" id="prizeId" name="prizeId"  value="${prize.prizeId}">
    <input type="hidden" id="prizeNum" name="prizeNum"  value="${prize.prizeNum}">
    <input type="hidden" id="prizeNumColumn" name="prizeNumColumn"  value="${prize.prizeNumColumn}">
    <input type="hidden" id="prizeNumLeftRightSpace" name="prizeNumLeftRightSpace"  value="${prize.prizeNumLeftRightSpace}">
    <input type="hidden" id="prizeNumUpLowSpace" name="prizeNumUpLowSpace"  value="${prize.prizeNumUpLowSpace}">
    <input type="hidden" id="prizeNumSize" name="prizeNumSize"  value="${prize.prizeNumSize}">
    <input type="hidden" id="prizeNumColor" name="prizeNumColor"  value="${prize.prizeNumColor}">

</body>
<script>
    var luckyDrawPeopleNumList =new Array();
    var timeoutProcess = null;
    var resultArr = new Array();
//    var prize = $("#prize").val();
    var prizeNum = $("#prizeNum").val();
    var prizeNumColumn = $("#prizeNumColumn").val();
    var prizeId = $("#prizeId").val();
    var prizeNumLeftRightSpace = $("#prizeNumLeftRightSpace").val();
    var prizeNumUpLowSpace = $("#prizeNumUpLowSpace").val();
    var prizeNumSize = $("#prizeNumSize").val();
    var prizeNumColor = "#"+$("#prizeNumColor").val();
    var flag = false;
    var downFlag = false;
    $("input[name='luckyDrawPeopleNums']").each(function(){
        luckyDrawPeopleNumList.push($(this).val());
    });
    function myRandom(max){
        var ranNum = parseInt(Math.random()*(max+1),10);
        return ranNum;
    }
    function getRandomArr(randomSize,max) {
        var arr =   new Array();
        for(var i=0;i<randomSize;i++){
            do{
                var randowNum = myRandom(max);
            }while( $.inArray(randowNum, arr)!=-1);
            arr.push(randowNum);
        }
        return arr;
    }
    function showResultY(arr) {
        var i=0;
        var str = " <table width=\"100%\" style=\"text-align: center;font-size:"+prizeNumSize+";\">";
        for(var k=0;k<(prizeNum % prizeNumColumn == 0 ? (prizeNum/prizeNumColumn) :(prizeNum/prizeNumColumn-1));k++){
            str += "<tr >";
            for(var j=0;j<prizeNumColumn;j++){
                str += " <td height=\""+prizeNumUpLowSpace+"\" width=\""+prizeNumLeftRightSpace+"\" style=\"color: "+prizeNumColor+ ";color:;text-align: center;line-height: "+prizeNumUpLowSpace+"px;vertical-align: middle\">";
                str += luckyDrawPeopleNumList[arr[i]];
                str += "</td>";
                i++;
            }
            str += "</tr>";
        }
        str += "</table>";
        return str;
    }
    function showResultN(arr,num) {
        var str = " <table width=\"100%\" style=\"text-align: center;\">";
        str += "<tr>";
        for(var k=num;k>0;k--){
            str += " <td height=\""+prizeNumUpLowSpace+"\" width=\""+prizeNumLeftRightSpace*prizeNumColumn/num+"\" style=\"color: "+prizeNumColor+ ";font-size:"+prizeNumSize+";text-align: center;line-height: "+prizeNumUpLowSpace+"px;vertical-align: middle\">";
            str += luckyDrawPeopleNumList[arr[arr.length-k]];
            str += "</td>";
        }
        str += "</tr>";
        str += "</table>";
        return str;
    }
    function changeDiv() {
        var arr = getRandomArr(prizeNum,luckyDrawPeopleNumList.length-1);
        resultArr = arr;
        var str = showResultY(arr);
        if(prizeNum%prizeNumColumn !=0 ){
            str += showResultN(arr,prizeNum%prizeNumColumn);
        }
        $("#resultDiv").html(str);
    }
    $(document).keypress(function(e) {

        // 回车键事件
        if(e.keyCode == 115) {
            if(flag){
                return;
            }
            //changeDiv();
            flag = true;
            timeoutProcess = setInterval(changeDiv,${luckDraw.luckDrawRandomFrequency});

        }
        if(e.keyCode == 101) {
            if(!flag){
                return;
            }
            flag = false;
            downFlag = true;
            clearInterval(timeoutProcess);
        }
//下轮次抽奖
        if(e.keyCode == 110) {
            if(!downFlag){
                return;
            }
            downFlag = false;
            $("#resultDiv").html("");
            var str = "";
            for(var i = 0 ; i < resultArr.length ; i++){
                if(i != resultArr.length - 1){
                    str += (luckyDrawPeopleNumList[resultArr[i]]+",");
                }
                else{
                    str += luckyDrawPeopleNumList[resultArr[i]];
                }
            }
            var url = "/web/luckDraw/next";
            $.post(url, {
                'resultArr': str,
                'prizeId' : ""+prizeId,
            }, function (data) {
                var LuckDrawModel = jQuery.parseJSON(data);
                prizeNum = LuckDrawModel.prize.prizeNum;
                prizeNumColumn = LuckDrawModel.prize.prizeNumColumn;
                prizeId = LuckDrawModel.prize.prizeId;
                prizeNumLeftRightSpace = LuckDrawModel.prize.prizeNumLeftRightSpace;
                prizeNumUpLowSpace = LuckDrawModel.prize.prizeNumUpLowSpace;
                prizeNumSize = LuckDrawModel.prize.prizeNumSize;
                prizeNumColor = LuckDrawModel.prize.prizeNumColor;
                var luckyDrawPeopleNumListNew = new Array();
                for(var i = 0 ; i < LuckDrawModel.luckyDrawPeoples.length ; i++){
                    luckyDrawPeopleNumListNew.push(LuckDrawModel.luckyDrawPeoples[i].luckyDrawPeopleNum);
                }
                luckyDrawPeopleNumList = luckyDrawPeopleNumListNew;
                $("#resultDiv").css("width",prizeNumLeftRightSpace*prizeNumColumn+"px");
                $("#resultDiv").css("height",prizeNumLeftRightSpace*prizeNum/prizeNumColumn+"px");
            });
        }
    });
</script>
</html>
