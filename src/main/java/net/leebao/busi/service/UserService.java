package net.leebao.busi.service;

import net.leebao.busi.params.RegisterParam;
import net.leebao.busi.dto.UserDto;
import net.leebao.busi.params.LoginParam;
import net.leebao.core.exception.LeeBaoException;
import net.leebao.busi.params.PerfectInfoParam;

/**
 * Created by zhuxl on 2015/5/20.
 */
public interface UserService {

    /**
     * 用户注册，最少包含手机号，登录账号，登录密码
     *
     * @param registerParam
     * @throws Exception
     */
    void register(RegisterParam registerParam) throws LeeBaoException;

    /**
     * 用户登录，登录成功返回uid
     *
     * @param loginParam
     * @return
     * @throws LeeBaoException
     */
    UserDto login(LoginParam loginParam) throws LeeBaoException;

    /**
     * 完善用户信息，单项更新
     *
     * @param perfectInfoParam
     * @throws Exception
     */
    void perfectUserInfo(PerfectInfoParam perfectInfoParam) throws LeeBaoException;

    /**获取用户详情
     * @param uid
     * @return
     * @throws LeeBaoException
     */
    UserDto getUserInfo(String uid) throws LeeBaoException;
}
