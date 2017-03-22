package com.bootcamp.controller;

import com.bootcamp.entity.LuckDraw;
import com.bootcamp.entity.LuckyDrawPeople;
import com.bootcamp.entity.Prize;
import com.bootcamp.model.LuckDrawModel;
import com.bootcamp.service.LuckDrawService;
import com.bootcamp.service.LuckyDrawPeopleService;
import com.bootcamp.service.PrizeService;
import com.bootcamp.utils.JsonConvertor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * **********************************************************************************************************************************************************
 * **********************************************************************抽奖系统****************************************************************************
 * **********************************************************************************************************************************************************
 * Created by yaobin on 2017/3/14.
 */
@Controller
@RequestMapping("/luckDraw")
@Transactional
public class LuckDrawController {

    private static final Logger LOGGER = Logger.getLogger(LuckDrawController.class);

    @Autowired
    private LuckDrawService luckDrawService;

    @Autowired
    private LuckyDrawPeopleService luckyDrawPeopleService;

    @Autowired
    private PrizeService prizeService;


    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String intoIndex(HttpServletRequest request){
        LuckDraw luckDraw =  luckDrawService.findByLuckDrawIsStart(1).get(0);//已开始的抽奖
        List<LuckyDrawPeople> luckyDrawPeopleList = luckyDrawPeopleService.findByLuckDrawIdAndNotDeleted(luckDraw.getLuckDrawId());//本次抽奖的参与人员
        Prize prize = prizeService.findByLuckDrawIdAndPrizeIsEndAndNotDeleted(0,luckDraw.getLuckDrawId()).get(0);
        request.setAttribute("luckDraw",luckDraw);
        request.setAttribute("luckyDrawPeopleList",luckyDrawPeopleList);
        request.setAttribute("prize",prize);
        return "/luckDraw/num_index";
    }

    @RequestMapping(value = "next",method = RequestMethod.POST)
    @ResponseBody
    public String indexAjax(HttpServletRequest request){
        int prizeId = Integer.parseInt(request.getParameter("prizeId"));
        System.out.println("prizeId:"+prizeId);
        String resultArr =  request.getParameter("resultArr");
        Prize prize = prizeService.findByPrizeId(prizeId);
        String[] luckyDrawPeopleIds = resultArr.split(",");
        LuckyDrawPeople luckyDrawPeople = null;
        for(int i = 0 ;i <luckyDrawPeopleIds.length ; i++){
            luckyDrawPeople = luckyDrawPeopleService.findByLuckDrawIdAndluckyDrawPeopleNum(prize.getLuckDrawId(),luckyDrawPeopleIds[i]);
            luckyDrawPeople.setLuckyDrawPeopleIsDrawn(prize.getPrizeId());
            luckyDrawPeopleService.update(luckyDrawPeople);
        }
        List<LuckyDrawPeople> luckyDrawPeopleList = luckyDrawPeopleService.findByLuckDrawIdAndLuckyDrawPeopleIsDrawnAndNotDeleted(prize.getLuckDrawId(),0);//本次抽奖的参与人员
        prize.setPrizeIsEnd(1);
        prizeService.update(prize);
        Prize prizeNew = prizeService.findByLuckDrawIdAndPrizeIsEndAndNotDeleted(0,prize.getLuckDrawId()).get(0);
        LuckDrawModel luckDrawModel = new LuckDrawModel();
        luckDrawModel.setLuckyDrawPeoples(luckyDrawPeopleList);
        luckDrawModel.setPrize(prizeNew);
        return JsonConvertor.getInstance().conver2JsonStr(luckDrawModel);
    }
}
