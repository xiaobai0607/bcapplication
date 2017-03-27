package com.bootcamp.service;

import com.bootcamp.dao.AdminDao;
import com.bootcamp.entity.Admin;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yaobin on 2017/3/24.
 */
@Service("adminService")
@Transactional
public class AdminService {
    @Resource
    private AdminDao adminDao;

    public List<Admin> findByOpenIdAndIsDelete(String openId , String isDelete){
        DetachedCriteria dc = adminDao.createDetachedCriteria();
        dc.add(Restrictions.eq("openId",openId)).add(Restrictions.eq("isDelete",isDelete));
        return  adminDao.find(dc);
    }
}
