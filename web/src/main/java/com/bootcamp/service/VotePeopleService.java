package com.bootcamp.service;

import com.bootcamp.dao.VotePeopleDao;
import com.bootcamp.entity.VotePeople;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yaobin on 2017/3/24.
 */
@Service("votePeopleService")
@Transactional
public class VotePeopleService {
    private VotePeopleDao votePeopleDao;
}
