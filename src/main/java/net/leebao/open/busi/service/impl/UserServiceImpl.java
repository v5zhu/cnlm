package net.leebao.open.busi.service.impl;

import net.leebao.auth.dao.UserDao;
import net.leebao.auth.entity.User;
import net.leebao.open.busi.dao.*;
import net.leebao.open.busi.dto.UserDto;
import net.leebao.open.busi.entity.AuthCenter;
import net.leebao.open.busi.entity.PhoneCode;
import net.leebao.open.busi.enums.UserInfo;
import net.leebao.open.busi.params.LoginParam;
import net.leebao.open.busi.params.PerfectInfoParam;
import net.leebao.open.busi.params.RegisterParam;
import net.leebao.open.busi.service.UserService;
import net.leebao.open.core.exception.InnerException;
import net.leebao.open.core.exception.LeeBaoException;
import net.leebao.open.utils.PasswordEncryptionUtil;
import net.leebao.open.utils.StringUtils;
import net.leebao.open.utils.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.mapper.BeanMapper;

import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by zhuxl@paxsz.com on 2016/7/25.
 */
@SuppressWarnings("ALL")
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserDao userDao;
    @Autowired
    CertificateMybatisDao certificateMybatisDao;
    @Autowired
    ImageMybatisDao imageMybatisDao;
    @Autowired
    AuthCenterMybatisDao authCenterMybatisDao;
    @Autowired
    PhoneCodeMybatisDao phoneCodeMybatisDao;
    @Autowired
    private Validator validator;

    @Override
    @Transactional
    public void register(RegisterParam registerParam) throws LeeBaoException {
        String error = ValidatorUtils.validate(validator, registerParam);
        if (error != null) {
            throw new LeeBaoException(error);
        }
        //判定密码和确认密码是否一样
        if (!registerParam.getPassword().equals(registerParam.getConfirmPassword())) {
            throw new LeeBaoException("两次密码不一致");
        }
        //判定手机号是否已注册
        int count1 = userDao.checkIsRegisteredByPhone(registerParam.getPhone());
        if (count1 > 0) {
            throw new LeeBaoException("手机号已注册");
        }
        //判定验证码是否正确
        PhoneCode phoneCode = phoneCodeMybatisDao.findByPhone(registerParam.getPhone());
        if (phoneCode == null) {
            throw new LeeBaoException("未生成验证码");
        } else {
            if (!registerParam.getCode().equals(phoneCode.getPresCode())) {
                //验证码不同
                throw new LeeBaoException("验证码错误");
            }
        }

        User user = new User();
        String uid = StringUtils.generate32uuid();
        user.setToken(uid);
        user.setPhone(registerParam.getPhone());
        //insert user
        try {
            userDao.register(user);
        } catch (Exception e) {
            logger.info("插入用户信息异常，堆栈:", e);
            throw new InnerException("插入数据异常");
        }
        AuthCenter authCenter = new AuthCenter();
        String authId = StringUtils.generate32uuid();
        authCenter.setId(authId);
        authCenter.setUserId(uid);
        authCenter.setLoginName(registerParam.getPhone());
        String salt = StringUtils.generate32uuid();
        authCenter.setSalt(salt);
        authCenter.setPassword(PasswordEncryptionUtil.getEncryptedPassword(registerParam.getPassword(), salt));
        //insert authCenter
        try {
            authCenterMybatisDao.insertAuth(authCenter);
        } catch (Exception e) {
            logger.info("插入登录信息异常，堆栈:", e);
            throw new InnerException("插入数据异常");
        }
    }

    @Override
    public UserDto login(LoginParam loginParam) throws LeeBaoException {
        //todo 加入登录日志
        String loginName = loginParam.getLoginName();
        String password = loginParam.getPassword();
        AuthCenter authCenter = authCenterMybatisDao.findAuthByLoginName(loginName);
        if (authCenter == null) {
            logger.info("通过登录名[{}]查询不到登录信息", loginName);
            throw new LeeBaoException("非法登录");
        }
        boolean success = PasswordEncryptionUtil.authenticate(password, authCenter.getPassword(), authCenter.getSalt());
        if (!success) {
            logger.info("登录账号[{}]密码[{}]错误", loginName, password);
            throw new LeeBaoException("密码错误");
        }
        User user = userDao.findByToken(authCenter.getUserId());
        return BeanMapper.map(user, UserDto.class);
    }

    @Override
    @Transactional
    public void perfectUserInfo(PerfectInfoParam perfectInfoParam) throws LeeBaoException {
        try {
            String error = ValidatorUtils.validate(validator, perfectInfoParam);
            if (error != null) {
                throw new LeeBaoException(error);
            }
            UserInfo infoType = UserInfo.valueOf(perfectInfoParam.getType());
            Map params = new HashMap();
            String column = infoType.name().toLowerCase();
            params.put("uid", "'" + perfectInfoParam.getUid() + "'");
            params.put("column", column);
            if (org.apache.commons.lang.StringUtils.isBlank(perfectInfoParam.getValue())) {
                params.put("value", "null");
            } else {
                params.put("value", "'" + perfectInfoParam.getValue() + "'");
            }
            logger.info("即将完善的信息参数:[{}]", params.toString());
            userDao.perfectUserInfo(params);
        } catch (Exception e) {
            logger.info("完善信息异常，堆栈:", e);
            throw new InnerException("更新记录异常");
        }
    }

    @Override
    public UserDto getUserInfo(String uid) throws LeeBaoException {
        User user = userDao.findByToken(uid);
        if (user == null) {
            throw new LeeBaoException("非法操作");
        }
        UserDto userDto = BeanMapper.map(user, UserDto.class);
        return userDto;
    }
}
