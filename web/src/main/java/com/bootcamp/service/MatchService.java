package com.bootcamp.service;

import com.bootcamp.dao.MatchDao;
import com.bootcamp.entity.Match;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yaobin on 2017/3/10.
 */
@Service("matchService")
@Transactional
public class MatchService {

    @Autowired
    private MatchDao matchDao;

    public List<Match> findByIsStart(int isStart){
        DetachedCriteria dc = matchDao.createDetachedCriteria();
        dc.add(Restrictions.eq("isStart",isStart));
        return matchDao.find(dc);
    }

}
