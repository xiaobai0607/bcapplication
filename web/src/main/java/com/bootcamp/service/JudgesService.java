package com.bootcamp.service;

import com.bootcamp.dao.JudgesDao;
import com.bootcamp.entity.Judges;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yaobin on 2017/3/10.
 */
@Service("judgesService")
public class JudgesService{
    @Autowired
    private JudgesDao judgesDao;

    public List<Judges> findByJudgesOpenidAndMatchIdAndIsDelete(String judgesOpenid ,int matchId , int isDelete){
        DetachedCriteria dc = judgesDao.createDetachedCriteria();
        dc.add(Restrictions.eq("judgesOpenid",judgesOpenid)).add(Restrictions.eq("matchId",matchId)).add(Restrictions.eq("isDelete",isDelete));
        return  judgesDao.find(dc);
    }

    public List<Judges> findByMatchIdAndIsDelete(int matchId ,int isDelete){
        DetachedCriteria dc = judgesDao.createDetachedCriteria();
        dc.add(Restrictions.eq("matchId",matchId)).add(Restrictions.eq("isDelete",isDelete));
        return  judgesDao.find(dc);
    }
}
