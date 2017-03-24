package com.bootcamp.service;

import com.bootcamp.dao.VoteTicketDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yaobin on 2017/3/24.
 */
@Service("voteTicketService")
@Transactional
public class VoteTicketService {
    private VoteTicketDao voteTicketDao;

}
