package com.bootcamp.service;

import com.bootcamp.dao.VotePeopleDao;
import com.bootcamp.entity.Admin;
import com.bootcamp.entity.VotePeople;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yaobin on 2017/3/24.
 */
@Service("votePeopleService")
@Transactional
public class VotePeopleService {
    @Resource
    private VotePeopleDao votePeopleDao;

    public List<VotePeople> findByOpenIdAndMatchId(String votePeopleIdOpenId , int matchId){
        DetachedCriteria dc = votePeopleDao.createDetachedCriteria();
        dc.add(Restrictions.eq("votePeopleIdOpenId",votePeopleIdOpenId)).add(Restrictions.eq("matchId",matchId));
        return  votePeopleDao.find(dc);
    }

    public void save(VotePeople votePeople){
        votePeopleDao.save(votePeople);
    }
}
