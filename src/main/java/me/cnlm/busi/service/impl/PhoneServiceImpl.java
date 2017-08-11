package me.cnlm.busi.service.impl;

import me.cnlm.busi.dao.PhoneCodeMybatisDao;
import me.cnlm.busi.entity.PhoneCode;
import me.cnlm.busi.enums.PhoneVerifyResult;
import me.cnlm.busi.service.PhoneService;
import me.cnlm.core.exception.LeeBaoException;
import me.cnlm.auth.dao.UserDao;
import me.cnlm.utils.DateUtils;
import me.cnlm.utils.SmsUtil;
import me.cnlm.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/*
 * ============================================================================		
 * = COPYRIGHT		
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION		
 *   This software is supplied under the terms of a license agreement or		
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied		
 *   or disclosed except in accordance with the terms in that agreement.		
 *      Copyright (C) 2017-? PAX Technology, Inc. All rights reserved.		
 * Description: // Detail description about the function of this module,		
 *             // interfaces with the other modules, and dependencies. 		
 * Revision History:		
 * Date	                 Author	                  Action
 * 2017/2/23  	         zhuxl@paxsz.com        Create/Add/Modify/Delete
 * ============================================================================		
 */
@SuppressWarnings("ALL")
@Service
public class PhoneServiceImpl implements PhoneService {
    private static final Logger logger = LoggerFactory.getLogger(PhoneServiceImpl.class);

    @Autowired
    UserDao userDao;
    @Autowired
    PhoneCodeMybatisDao phoneCodeMybatisDao;

    @Override
    public void checkPhone(String phone) throws LeeBaoException {
        String mbl = userDao.checkPhone(phone);
        if (org.apache.commons.lang.StringUtils.isNotBlank(mbl)) {
            throw new LeeBaoException("手机号已注册");
        }
    }

    @Override
    @Transactional
    public void generatePhoneCode(String phone) throws LeeBaoException {
        String code = StringUtils.generate6PhoneCode();
        Date now = DateUtils.nowTime();

        PhoneCode phoneCode = phoneCodeMybatisDao.findByPhone(phone);
        if (phoneCode == null) {
            //该手机号未生成过验证码
            phoneCode = new PhoneCode();
            phoneCode.setId(StringUtils.generate32uuid());
            phoneCode.setPhone(phone);
            phoneCode.setPresCode(code);
            phoneCode.setTimes(1);
            phoneCode.setVerifyTimes(0);
            Date time = DateUtils.nowTime();
            phoneCode.setPresTime(time);
            //insert phoneCode
            phoneCodeMybatisDao.insertPhoneCode(phoneCode);
        } else {
            //该手机号生成过验证码，再次生成
            if (1 * 60 * 1000 > (now.getTime() - phoneCode.getPresTime().getTime())) {
                //发送验证码1分钟只能点击发送1次；
                throw new LeeBaoException("发送验证过于频繁");
            }
            //累加次数
            phoneCode.setTimes(phoneCode.getTimes() + 1);

            phoneCode.setPrevTime(phoneCode.getPresTime());
            phoneCode.setPresTime(now);
            phoneCode.setPrevCode(phoneCode.getPresCode());
            phoneCode.setPresCode(code);
            phoneCode.setVerifyTimes(0);
            phoneCode.setPrevVerifyResult(phoneCode.getPresVerifyResult());
            //update phoneCode
            phoneCodeMybatisDao.updatePhoneCode(phoneCode);
        }
        logger.info("向手机号：[{}]发送验证码：[{}]", phone, code);
//        SmsUtil.sendSmsCode(phone, code);
    }

    @Override
    @Transactional
    public void verifyPhoneCode(String phone, String code) throws LeeBaoException {
        PhoneCode phoneCode = phoneCodeMybatisDao.findByPhone(phone);
        if (phoneCode == null) {
            //手机号错误
            throw new LeeBaoException("非法操作");
        }
        //判定时间是否在expired分钟内
        Date now = DateUtils.nowTime();
        long expired = SmsUtil.SMS_CODE_EXPIRED;
        if (expired * 60 * 1000 < (now.getTime() - phoneCode.getPresTime().getTime())) {
            throw new LeeBaoException("验证码已过期,请重新获取");
        }
        if (phoneCode.getPresCode().equals(code)) {
            //equals
            //只允许验证1次,验证1次后失效
            if (phoneCode.getVerifyTimes() == 1) {
                throw new LeeBaoException("");
            }
            phoneCode.setVerifyTimes(1);
            phoneCode.setPrevVerifyResult(phoneCode.getPresVerifyResult());
            phoneCode.setPresVerifyResult(PhoneVerifyResult.SUCCESS);
            //update phoneCode
            phoneCodeMybatisDao.updatePhoneCode(phoneCode);
        } else {
            throw new LeeBaoException("验证码错误");
        }
    }
}
