package com.bootcamp.controller;

import com.bootcamp.entity.*;
import com.bootcamp.model.MatchProjectModel;
import com.bootcamp.model.ProjectScoreModel;
import com.bootcamp.model.ScoreModel;
import com.bootcamp.service.*;
import com.bootcamp.utils.JsonConvertor;
import com.bootcamp.utils.tenpay.JSAPI.OpenIdUtil;
import com.bootcamp.utils.tenpay.JSAPI.WechatPay;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yaobin on 2017/3/9.
 */
@Controller
@RequestMapping("/program")
@Transactional
public class ProgramController {

    private static final Logger LOGGER = Logger.getLogger(ProgramController.class);

    @Resource
    private JudgesService judgesService;
    @Resource
    private MatchProjectScoreService matchProjectScoreService;
    @Resource
    private MatchProjectService matchProjectService;
    @Resource
    private MatchService matchService;
    @Resource
    private ScoreItemDetailService scoreItemDetailService;
    @Resource
    private ScoreItemService scoreItemService;
    @Resource
    private VotePeopleService votePeopleService;
    @Resource
    private VoteTicketService voteTicketService;
    @Resource
    private AdminService adminService;

    @RequestMapping(value = "/test",method= RequestMethod.GET)
    public String test(HttpServletRequest request , HttpServletResponse response){
        LOGGER.info("测试" );
        return "/program/index";
    }

    /**
     * 进入评分系统PC端主页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/scoring/system/pc/index",method= RequestMethod.GET)
    public String scoringSystemIndex(HttpServletRequest request , HttpServletResponse response){
        LOGGER.info("进入评分系统PC端主页" );
        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("1");
        a.add("1");
        a.add("1");
        a.add("1");
        request.setAttribute("a",a);
        return "/program/score/pc/index";
    }

    /**
     * 评分
     * @param request
     * @return
     */
    @RequestMapping(value="/judges/mobile/index/{falg}",method=RequestMethod.GET)
    public String judges(HttpServletRequest request,@PathVariable("falg") Integer falg) {
        String path = "http%3a%2f%2fwww.bootcamp.org.cn%2fweb%2fprogram%2fjudges%2fmobile%2fmatch%2f"+falg;
        return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WechatPay.appid+"&redirect_uri="+path+"&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect";
    }

    @RequestMapping(value="/judges/mobile/match/{falg}",method=RequestMethod.GET)
    public String judgesMatch(HttpServletRequest request,@PathVariable("falg") Integer falg) {
        String code = request.getParameter("code");
        JSONObject jsonObject = OpenIdUtil.httpsRequestToJsonObject("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+WechatPay.appid+"&secret="+WechatPay.appsecret+"&code="+code+"&grant_type=authorization_code", "POST", null);
        Object errorCode = jsonObject.get("errcode");
        String openid = "";
        if(errorCode != null) {
            request.setAttribute("errorCode", errorCode.toString());
            return "/judges/mobile/msg";
        }else{
            openid = jsonObject.getString("openid");
        }
        Match match = matchService.findByIsStart(1).get(0);
        if(match == null){
            request.setAttribute("msg", "大赛还未开始！！！！");
            return "/judges/mobile/msg";
        }
        Judges judges = judgesService.findByJudgesOpenidAndMatchIdAndIsDelete(openid, match.getMatchId(),0).get(0);
        if(judges != null){
            MatchProject matchProject = matchProjectService.findByMatchIdAndIsDelete(match.getMatchId(), 0).get(0);
            List<ScoreItem> scoreItems = scoreItemService.findByIsEnabled(1);
            request.setAttribute("judgesId", judges.getJudgesId());
            request.setAttribute("matchProject", matchProject);
            request.setAttribute("scoreItems", scoreItems);
            request.setAttribute("falg", falg);
            return "/judges/mobile/detail";
        }
        else{
            request.setAttribute("msg", "您不是本次大赛的评委，如有疑问，请与本次大赛组委会联系");
            return "/judges/mobile/msg";
        }
    }

    @RequestMapping(value="/judges/mobile/sub",method=RequestMethod.POST)
    public String judgesSub(HttpServletRequest request) {
        String judgesId = request.getParameter("judgesId");
        String matchProjectId = request.getParameter("matchProjectId");
        MatchProject matchProject = matchProjectService.findByMatchProjectId(Integer.parseInt(matchProjectId));
        MatchProjectScore matchProjectScoreOld =  matchProjectScoreService.findByMatchProjectIdAndJugesId(Integer.parseInt(matchProjectId), Integer.parseInt(judgesId)).get(0);
        if(matchProjectScoreOld!=null){
            return "redirect:/judges/mobile/index";
        }
        List<ScoreItem> scoreItems = scoreItemService.findByIsEnabled(1);
        int tatal = 0;
        for(ScoreItem s :scoreItems){
            int score = Integer.parseInt(request.getParameter(s.getScoreItemId()+""));
            tatal += score;
            ScoreItemDetail scoreItemDetail = new ScoreItemDetail(s.getScoreItemName(), s.getScoreItemId(), matchProject.getMatchProjectId(), Integer.parseInt(judgesId), score);
            scoreItemDetailService.save(scoreItemDetail);
        }
        MatchProjectScore matchProjectScore = new MatchProjectScore(Integer.parseInt(matchProjectId), Integer.parseInt(judgesId), tatal);
        matchProjectScoreService.save(matchProjectScore);
        List<Judges> judges = judgesService.findByMatchIdAndIsDelete(matchProject.getMatchId(),0);
        List<MatchProjectScore> matchProjectScores = matchProjectScoreService.findByMatchProjectId(matchProject.getMatchProjectId());
        if(judges.size()==matchProjectScores.size()){
            List<Integer> scoreTolList = new ArrayList<Integer>();
            for(MatchProjectScore m:matchProjectScores){
                scoreTolList.add(m.getScore());
            }
            int scoreTol = 0;
//				Collections.sort(scoreTolList);
//				for(int i=1;i<scoreTolList.size()-1;i++){
//					scoreTol += scoreTolList.get(i);
//				}
            for(int i=0;i<scoreTolList.size();i++){
                scoreTol += scoreTolList.get(i);
            }
            double avg = scoreTol;
            avg = avg/judges.size();
            DecimalFormat df   = new DecimalFormat("######0.00");
            matchProject.setTotalScore(Double.parseDouble(df.format(avg)));
            matchProjectService.update(matchProject);
        }
        request.setAttribute("msg", "打分成功！！！");
        return "redirect:/judges/mobile/index/2";

    }

    @RequestMapping(value="/judges/mobile/findMatchProject",method=RequestMethod.POST)
    @ResponseBody
    public String findMatchProject(HttpServletRequest request) {
        String matchId = request.getParameter("matchId");
        MatchProject matchProject = matchProjectService.findByMatchIdAndIsDelete(Integer.parseInt(matchId), 0).get(0);
        return JsonConvertor.getInstance().conver2JsonStr(matchProject) ;
    }

    @RequestMapping(value="/judges/mobile/scoreAjax",method=RequestMethod.POST)
    @ResponseBody
    public String judgesScore(HttpServletRequest request) {
        int matchId = Integer.parseInt(request.getParameter("matchId"));
        List<MatchProject> matchProjects = matchProjectService.findByMatchIdOrderByTotalScoreDesc(matchId,0);
        List<Judges> judges = judgesService.findByMatchIdAndIsDelete(matchId,0);
        List<ProjectScoreModel> projectScoreModels = new ArrayList<ProjectScoreModel>();
        ScoreModel scoreModel = new ScoreModel();
        scoreModel.setJudges(judges);
        for(MatchProject m : matchProjects){
            ProjectScoreModel projectScoreModel = new ProjectScoreModel();
            List<String> strs = new ArrayList<String>();
            strs.add(m.getPName());
            strs.add(m.getPDirector());
            for(Judges j : judges){
                MatchProjectScore matchProjectScore = matchProjectScoreService.findByMatchProjectIdAndJugesId(m.getMatchProjectId(), j.getJudgesId()).get(0);
                if(matchProjectScore  == null )
                    strs.add("还未评分") ;
                else
                    strs.add(matchProjectScore.getScore()+"");
            }
            strs.add(m.getTotalScore()+"");
            projectScoreModel.setTd(strs);
            projectScoreModels.add(projectScoreModel);
            scoreModel.setProjectScoreModels(projectScoreModels);
        }
        return JsonConvertor.getInstance().conver2JsonStr(scoreModel);

    }

    @RequestMapping(value="/judges/mobile/projectScoreAjax",method=RequestMethod.POST)
    @ResponseBody
    public String projectScoreAjax(HttpServletRequest request) {
        int matchId = Integer.parseInt(request.getParameter("matchId"));
        int matchProjectId = Integer.parseInt(request.getParameter("matchProjectId"));
        List<MatchProjectScore> matchProjectScores = matchProjectScoreService.findByMatchProjectId(matchProjectId);
        List<Judges> judges = judgesService.findByMatchIdAndIsDelete(matchId,0);
        if(matchProjectScores.size() != judges.size()){
            return "0";
        }
        return JsonConvertor.getInstance().conver2JsonStr(matchProjectScores);

    }

    @RequestMapping(value="/judges/mobile/voteAjax",method=RequestMethod.POST)
    @ResponseBody
    public String voteAjax(HttpServletRequest request) {
        Match match = matchService.findByIsStart(1).get(0);
        ProjectScoreModel projectScoreModel = new ProjectScoreModel();
        int matchId = Integer.parseInt(request.getParameter("matchId"));
        int matchProjectId = Integer.parseInt(request.getParameter("matchProjectId"));
        MatchProject matchProjectNew = matchProjectService.findByMatchIdAndIsDelete(match.getMatchId(), 0).get(0);
        List<MatchProject> matchProjects = matchProjectService.findByMatchIdOrderByTotalScoreDesc(matchId,0);
        List<MatchProjectModel> matchProjectModels = new ArrayList<MatchProjectModel>();
        for(MatchProject m : matchProjects){
            MatchProjectModel matchProjectModel = new MatchProjectModel();
            matchProjectModel.setMatchProject(m);
            if(m.getVoteNum()==null || "".equals(m.getVoteNum())){
                matchProjectModel.setTotalScorePre(m.getTotalScore());
            }
            else{
                matchProjectModel.setTotalScorePre((m.getTotalScore()*m.getPerScore()+ Integer.parseInt(m.getVoteNum())*(10-m.getPerScore()))/10);
            }
            matchProjectModels.add(matchProjectModel);
        }
        MatchProject matchProject = matchProjectService.findByMatchProjectId(matchProjectId);
        //sort by date
        Collections.sort(matchProjectModels,new Comparator<MatchProjectModel>(){
            public int compare(MatchProjectModel arg0, MatchProjectModel arg1) {
                return arg1.getTotalScorePre().compareTo(arg0.getTotalScorePre());
            }
        });
        projectScoreModel.setMatchProject(matchProject);
        projectScoreModel.setMatchProjectModels(matchProjectModels);
        projectScoreModel.setMatchProjectNew(matchProjectNew);
        return JsonConvertor.getInstance().conver2JsonStr(projectScoreModel);

    }

    @RequestMapping(value="/judges/mobile/score",method=RequestMethod.GET)
    public String judgesScoreIndex(HttpServletRequest request) {
        Match match = matchService.findByIsStart(1).get(0);
        List<Judges> judgess = judgesService.findByMatchIdAndIsDelete(match.getMatchId(),0);
        MatchProject matchProject = matchProjectService.findByMatchIdAndIsDelete(match.getMatchId(),0).get(0);
        request.setAttribute("match", match);
        request.setAttribute("judgess", judgess);
        request.setAttribute("matchProject", matchProject);
        return "/program/score/pc/index";
    }

    /**
     * 评分
     * @param request
     * @return
     */
    @RequestMapping(value="/vote/mobile/openId",method=RequestMethod.GET)
    public String openId(HttpServletRequest request) {
        String path = "http%3a%2f%2fwww.bootcamp.org.cn%2fweb%2fprogram%2fvote%2fshow%2fmobile%2fqrcode";
        return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ WechatPay.appid+"&redirect_uri="+path+"&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect";
    }

    @RequestMapping(value="/vote/show/mobile/qrcode",method=RequestMethod.GET)
    public String ShowOpenId(HttpServletRequest request){
        String code = request.getParameter("code");
        JSONObject jsonObject = OpenIdUtil.httpsRequestToJsonObject("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+WechatPay.appid+"&secret="+WechatPay.appsecret+"&code="+code+"&grant_type=authorization_code", "POST", null);
        Object errorCode = jsonObject.get("errcode");
        String openid = "";
        if(errorCode != null) {
            request.setAttribute("errorCode", errorCode.toString());
            return "/judges/mobile/msg";
        }else{
            openid = jsonObject.getString("openid");
        }
        request.setAttribute("myOpen", openid);
        return "/program/vote/qrcode";
    }

    @RequestMapping(value="/qrcode/sure",method=RequestMethod.GET)
    public String qrcodeSure(HttpServletRequest request) {
        String myOpen = request.getParameter("myOpen");
        String path = "http%3a%2f%2fwww.bootcamp.org.cn%2fweb%2fprogram%2fvote%2fsure%2fopenId%2f"+myOpen;
        return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ WechatPay.appid+"&redirect_uri="+path+"&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect";
    }

    @RequestMapping(value="/vote/sure/openId/{myOpen}",method=RequestMethod.GET)
    public String sureOpenId(HttpServletRequest request,@PathVariable("myOpen") String myOpen){
        String code = request.getParameter("code");
        JSONObject jsonObject = OpenIdUtil.httpsRequestToJsonObject("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+WechatPay.appid+"&secret="+WechatPay.appsecret+"&code="+code+"&grant_type=authorization_code", "POST", null);
        Object errorCode = jsonObject.get("errcode");
        String openid = "";
        if(errorCode != null) {
            return "/program/vote/error";
        }else{
            openid = jsonObject.getString("openid");
        }
        if(adminService.findByOpenIdAndIsDelete(openid,"0").size()<=0){
            return "";
        }

        Match match = matchService.findByIsStart(1).get(0);
        if(votePeopleService.findByOpenIdAndMatchId(myOpen,match.getMatchId()).size()>0){
            return "";
        }
        VotePeople votePeople = new VotePeople();
        votePeople.setMatchId(match.getMatchId());
        votePeople.setVotePeopleIdOpenId(myOpen);
        votePeopleService.save(votePeople);
        return "/program/vote/success";
    }

    @RequestMapping(value="/into/votes",method=RequestMethod.GET)
    public String intoVote(HttpServletRequest request){

        return "/program/vote/index";
    }

    @RequestMapping(value="/vote/mobile/sure",method=RequestMethod.POST)
    public String voteSure(HttpServletRequest request) {
        String path = "http%3a%2f%2fwww.bootcamp.org.cn%2fweb%2fprogram%2fpeople%2fvote";
        return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ WechatPay.appid+"&redirect_uri="+path+"&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect";
    }

    @RequestMapping(value="/people/vote",method=RequestMethod.GET)
    public String peopleVote(HttpServletRequest request){
        String code = request.getParameter("code");
        JSONObject jsonObject = OpenIdUtil.httpsRequestToJsonObject("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+WechatPay.appid+"&secret="+WechatPay.appsecret+"&code="+code+"&grant_type=authorization_code", "POST", null);
        Object errorCode = jsonObject.get("errcode");
        String openid = "";
        if(errorCode != null) {
            return "/program/vote/error";
        }else{
            openid = jsonObject.getString("openid");
        }
        Match match = matchService.findByIsStart(1).get(0);
        if(votePeopleService.findByOpenIdAndMatchId(openid,match.getMatchId()).size()<=0){
            return "/program/vote/error";
        }
        MatchProject matchProject = matchProjectService.findByMatchIdAndIsDelete(match.getMatchId(),0).get(0);
        if(voteTicketService.findByVotePeopleIdOpenIdAndMatchProjectId(openid,matchProject.getMatchProjectId()).size()>0){
            return "/program/vote/error";
        }
        VoteTicket voteTicket = new VoteTicket();

        voteTicket.setMatchProjectId(matchProject.getMatchProjectId());
        voteTicket.setVotePeopleIdOpenId(openid);
        voteTicketService.save(voteTicket);
        return "/program/vote/success";
    }

}
