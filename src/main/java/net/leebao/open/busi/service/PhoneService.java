package net.leebao.open.busi.service;


import net.leebao.open.core.exception.LeeBaoException;

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
public interface PhoneService {

    /**
     * 检测手机号码是否被注册
     *
     * @param phone
     */
    void checkPhone(String phone) throws LeeBaoException;

    /**
     * 生成手机验证码
     *
     * @param phone 手机号
     */
    void generatePhoneCode(String phone) throws LeeBaoException;

    /**
     * 验证手机验证码是否正确
     *
     * @param phone
     * @param code
     */
    void verifyPhoneCode(String phone, String code) throws LeeBaoException;
}
