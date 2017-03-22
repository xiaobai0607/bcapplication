package com.bootcamp.service;

import com.bootcamp.dao.PrizeDao;
import com.bootcamp.entity.Prize;
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
@Transactional
@Service("prizeService")
public class PrizeService {

    @Autowired
    private PrizeDao prizeDao;


    public List<Prize> findByLuckDrawIdAndNotDeleted(int luckDrawId){
        DetachedCriteria dc = prizeDao.createDetachedCriteria();
        dc.add(Restrictions.eq("luckDrawId",luckDrawId)).add(Restrictions.eq("isDelete",0));
        return prizeDao.find(dc);
    }

    public Prize findByPrizeId(int prizeId){
        DetachedCriteria dc = prizeDao.createDetachedCriteria();
        dc.add(Restrictions.eq("prizeId",prizeId));
        return prizeDao.find(dc).get(0);
    }

    public List<Prize> findByLuckDrawIdAndPrizeIsEndAndNotDeleted(int prizeIsEnd , int luckDrawId){
        DetachedCriteria dc = prizeDao.createDetachedCriteria();
        dc.add(Restrictions.eq("luckDrawId",luckDrawId)).add(Restrictions.eq("isDelete",0)).add(Restrictions.eq("prizeIsEnd",prizeIsEnd));
        return prizeDao.find(dc);
    }

    public void update(Prize prize){
        prize.setUpdatedTime(new Date());
        prizeDao.save(prize);
    }
}
