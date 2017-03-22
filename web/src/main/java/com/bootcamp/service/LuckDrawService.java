package com.bootcamp.service;

import com.bootcamp.dao.LuckDrawDao;
import com.bootcamp.entity.LuckDraw;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yaobin on 2017/3/14.
 */
@Service("luckDrawService")
public class LuckDrawService{

    @Autowired
    private LuckDrawDao luckDrawDao;

    @Transactional
    public List<LuckDraw> findByLuckDrawIsStart(int luckDrawIsStart){
        DetachedCriteria dc = luckDrawDao.createDetachedCriteria();
        dc.add(Restrictions.eq("luckDrawIsStart",luckDrawIsStart));
        return luckDrawDao.find(dc);
    }
}
