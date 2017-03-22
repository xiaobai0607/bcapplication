package com.bootcamp.service;

import com.bootcamp.dao.ScoreItemDao;
import com.bootcamp.entity.ScoreItem;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yaobin on 2017/3/10.
 */
@Service("scoreItemService")
public class ScoreItemService {

    @Autowired
    private ScoreItemDao scoreItemDao;

    public List<ScoreItem> findByIsEnabled(int isEnabled){
        DetachedCriteria dc = scoreItemDao.createDetachedCriteria();
        dc.add(Restrictions.eq("isEnabled",isEnabled));
        return scoreItemDao.find(dc);
    }

}
