package com.bootcamp.service;

import com.bootcamp.dao.VoteTicketDao;
import com.bootcamp.entity.VotePeople;
import com.bootcamp.entity.VoteTicket;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yaobin on 2017/3/24.
 */
@Service("voteTicketService")
@Transactional
public class VoteTicketService {
    @Resource
    private VoteTicketDao voteTicketDao;

    public void save(VoteTicket voteTicket){
        voteTicketDao.save(voteTicket);
    }

    public List<VoteTicket> findByVotePeopleIdOpenIdAndMatchProjectId(String votePeopleIdOpenId , int matchProjectId){
        DetachedCriteria dc = voteTicketDao.createDetachedCriteria();
        dc.add(Restrictions.eq("votePeopleIdOpenId",votePeopleIdOpenId)).add(Restrictions.eq("matchProjectId",matchProjectId));
        return  voteTicketDao.find(dc);
    }

}
