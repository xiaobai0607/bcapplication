package com.bootcamp.service;

import com.bootcamp.dao.ScoreItemDetailDao;
import com.bootcamp.entity.ScoreItemDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by yaobin on 2017/3/10.
 */
@Service("scoreItemDetailService")
public class ScoreItemDetailService {

    @Autowired
    private ScoreItemDetailDao scoreItemDetailDao;

    public void save(ScoreItemDetail scoreItemDetail){
        Date date = new Date();
        scoreItemDetail.setCreatedBy("admin");
        scoreItemDetail.setCreatedTime(date);
        scoreItemDetail.setUpdatedBy("admin");
        scoreItemDetail.setUpdatedTime(date);
        scoreItemDetailDao.save(scoreItemDetail);
    }
}
