package com.bootcamp.service;

import com.bootcamp.dao.MatchProjectDao;
import com.bootcamp.entity.MatchProject;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by yaobin on 2017/3/10.
 */
@Service("matchProjectService")
public class MatchProjectService  {

    @Autowired
    private MatchProjectDao matchProjectDao;

    public List<MatchProject> findByMatchIdAndIsDelete(int matchId , int isDelete){
        DetachedCriteria dc = matchProjectDao.createDetachedCriteria();
        dc.add(Restrictions.eq("matchId",matchId)).add(Restrictions.eq("isDelete",isDelete));
        return matchProjectDao.find(dc);
    }

    public MatchProject  findByMatchProjectId(int matchProjectId){
        return matchProjectDao.get(matchProjectId);
    }

    public void update(MatchProject matchProject){
        Date date = new Date();
        matchProject.setUpdatedTime(date);
        matchProjectDao.save(matchProject);
    }

    public List<MatchProject> findByMatchIdOrderByTotalScoreDesc(int matchId){
        DetachedCriteria dc = matchProjectDao.createDetachedCriteria();
        dc.addOrder(Order.desc("totalScore"));
        dc.add(Restrictions.eq("matchId",matchId));
        return matchProjectDao.find(dc);
    }

}
