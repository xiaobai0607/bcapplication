package com.bootcamp.service;

import com.bootcamp.dao.MatchProjectScoreDao;
import com.bootcamp.entity.MatchProjectScore;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by yaobin on 2017/3/10.
 */
@Service("matchProjectScoreService")
@Transactional
public class MatchProjectScoreService {

    @Autowired
    private MatchProjectScoreDao matchProjectScoreDao;

    public List<MatchProjectScore> findByMatchProjectIdAndJugesId(int matchProjectId , int jugesId){
        DetachedCriteria dc = matchProjectScoreDao.createDetachedCriteria();
        dc.add(Restrictions.eq("matchProjectId",matchProjectId)).add(Restrictions.eq("jugesId",jugesId));
        return matchProjectScoreDao.find(dc);
    }

    public void save(MatchProjectScore matchProjectScore){
        matchProjectScore.setUpdatedBy("admin");
        matchProjectScore.setCreatedBy("admin");
        Date date = new Date();
        matchProjectScore.setCreatedTime(date);
        matchProjectScore.setUpdatedTime(date);
        matchProjectScoreDao.save(matchProjectScore);
    }

    public List<MatchProjectScore> findByMatchProjectId(int matchProjectId ){
        DetachedCriteria dc = matchProjectScoreDao.createDetachedCriteria();
        dc.add(Restrictions.eq("matchProjectId",matchProjectId));
        return matchProjectScoreDao.find(dc);
    }

}
