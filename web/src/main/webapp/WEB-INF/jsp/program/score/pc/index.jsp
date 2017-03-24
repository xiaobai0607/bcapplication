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
<body style="background: url(${match.matchLocation}) no-repeat  ;background-size: cover;">
<div>
${match.matchName}
</div>
<div style="margin: 0 auto;width: 1000px;height: 100px;text-align: center">
    <span style="line-height: 100px;vertical-align: middle;color: white;font-size: 50px;">${matchProject.PName}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: white;line-height: 100px;vertical-align: bottom;">项目负责人：${matchProject.PDirector}</span>
</div>
<div style="margin: 0 auto;width: 1000px;text-align: center;">
    <c:forEach var="judges" items="${judgess}">
        <div class="side" style="width: 200px;height: 300px;float: left;">
            <div id="point${judges.judgesId}" style="width: 200px;height: 200px;background: url(${judges.judgesLocation}) no-repeat  ;background-size: cover;line-height: 200px;vertical-align: middle;color: brown;font-size: 30px;">
                打分中。。。
            </div>
            <div style="width: 200px;height: 100px;font-size: 50px;color: white;">
                    ${judges.judgesName}
            </div>
        </div>
    </c:forEach>
</div>
<div style="margin: 0 auto;width: 1000px;text-align: center;height: 100%;position: absolute;z-index: 2;display: none;" id="showDiv">
    <div style="width: 1000px;height: 100px;margin-top: 100px;">
        <div style="width: 50%;height: 100px;text-align: center;" id="juScore">
            <p style="vertical-align: middle;line-height: 100px;"></p>
        </div>
        <div style="width: 50%;height: 100px;text-align: center;" id="totalVote">
            <p style="vertical-align: middle;line-height: 100px;"></p>
        </div>
    </div>
    <div style="width: 1000px;height: 100px;text-align: center" id="totalScore">
        <p style="vertical-align: middle;line-height: 100px;"></p>
    </div>
    <div style="width: 1000px;height: 50px;text-align: center" id="scoreOrder">
        <p style="vertical-align: middle;line-height: 50px;"></p>
    </div>
</div>
<input type="hidden" name="matchProjectId" id="matchProjectId" value="${matchProject.matchProjectId}">
<input type="hidden" name="perScore" id="perScore" value="${matchProject.perScore}">
<input type="hidden" name="matchId" id="matchId" value="${match.matchId}">
</body>
<script>
    var matchProjectId = 0;
    $(document).ready(function(){
        setInterval(function () {
            matchProjectId = $("#matchProjectId").val();
            matchId = $("#matchId").val();
            var url = "/web/program/judges/mobile/projectScoreAjax";
               $.post(url, {
                   'matchProjectId': matchProjectId,
                   'matchId':matchId
               }, function (data) {
                   if(data == 0){
                        return;
                   }
                   var matchProjectScores = jQuery.parseJSON(data);
                   for(var i = 0 ;i < matchProjectScores.length ; i++){
                       $("#point"+matchProjectScores[i].jugesId).css("font-size","100px");
                   }

                   $(".side").css("transform","rotateY(360deg)");

                  for(var i = 0 ;i < matchProjectScores.length ; i++){
                      $("#point"+matchProjectScores[i].jugesId).html(matchProjectScores[i].score);
                  }
                   $.post("/web/program/judges/mobile/voteAjax", {
                       'matchProjectId': matchProjectId,
                       'matchId':matchId
                   }, function (data) {
                       var projectScoreModel = jQuery.parseJSON(data);
                       var matchProject = projectScoreModel.matchProject;
                       var matchProjectModels = projectScoreModel.matchProjectModels;
                       if(matchProject.voteNum != null && matchProject.voteNum != ""){
                           perScore = $("#perScore").val();
                           if(${isVote eq 1}){
                               $("#juScore").css("display","block")
                               $("#totalVote").css("display","block")
                               $("#juScore").html("<p style=\"vertical-align: middle;line-height: 100px;\">评委总分："+matchProject.totalScore+"</p>");
                               $("#totalVote").html("<p style=\"vertical-align: middle;line-height: 100px;\">观众票数："+matchProject.voteNum+"</p>");
                               $("#totalScore").html("<p style=\"vertical-align: middle;line-height: 100px;\">总分："+(matchProject.totalScore*perScore+matchProject.voteNum*(10-perScore))/10+"</p>");
                           }
                           else{
                               $("#juScore").css("display","none")
                               $("#totalVote").css("display","none")
                               $("#totalScore").html("<p style=\"vertical-align: middle;line-height: 100px;\">总分："+matchProject.totalScore+"</p>");
                           }
                           var str = "";
                           for(var i = 0 ;i < matchProjectModels.length ; i++){
                               if(${isVote eq 1}) {
                                   str += "<p style=\"vertical-align: middle;line-height: 50px;\">项目名称："+matchProjectModels[i].matchProject.PName+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;得分："+(matchProjectModels[i].matchProject.totalScore*perScore+matchProjectModels[i].matchProject.voteNum*(10-perScore))/10+"</p>";
                               }
                               else{
                                   str += "<p style=\"vertical-align: middle;line-height: 50px;\">项目名称："+matchProjectModels[i].matchProject.PName+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;得分："+matchProjectModels[i].matchProject.totalScore+"</p>";
                               }
                           }
                           $("#scoreOrder").html(str);
                       }
                       $("#showDiv").css("display","block")
                       window.clearInterval;
                   });
            });
        },3000);
    });

</script>
</html>
