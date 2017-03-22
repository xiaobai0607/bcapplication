package com.bootcamp.service;

import com.bootcamp.dao.LuckyDrawPeopleDao;
import com.bootcamp.entity.LuckyDrawPeople;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by yaobin on 2017/3/14.
 */
@Service("luckyDrawPeopleService")
@Transactional
public class LuckyDrawPeopleService{

    @Autowired
    private LuckyDrawPeopleDao luckyDrawPeopleDao;

    public List<LuckyDrawPeople> findByLuckDrawIdAndNotDeleted(int luckDrawId){
        DetachedCriteria dc = luckyDrawPeopleDao.createDetachedCriteria();
        dc.add(Restrictions.eq("luckDrawId",luckDrawId)).add(Restrictions.eq("isDelete",0));
        return luckyDrawPeopleDao.find(dc);
    }

    public List<LuckyDrawPeople> findByLuckDrawIdAndLuckyDrawPeopleIsDrawnAndNotDeleted(int luckDrawId,int luckyDrawPeopleIsDrawn){
        DetachedCriteria dc = luckyDrawPeopleDao.createDetachedCriteria();
        dc.add(Restrictions.eq("luckDrawId",luckDrawId)).add(Restrictions.eq("isDelete",0)).add(Restrictions.eq("luckyDrawPeopleIsDrawn",luckyDrawPeopleIsDrawn));
        return luckyDrawPeopleDao.find(dc);
    }

    public LuckyDrawPeople findByLuckDrawIdAndluckyDrawPeopleNum(int luckDrawId,String luckyDrawPeopleNum){
        DetachedCriteria dc = luckyDrawPeopleDao.createDetachedCriteria();
        dc.add(Restrictions.eq("luckDrawId",luckDrawId)).add(Restrictions.eq("luckyDrawPeopleNum",luckyDrawPeopleNum));
        return luckyDrawPeopleDao.find(dc).get(0);
    }

    public void update(LuckyDrawPeople luckyDrawPeople){
        luckyDrawPeople.setUpdatedTime(new Date());
        luckyDrawPeopleDao.save(luckyDrawPeople);
    }
}
