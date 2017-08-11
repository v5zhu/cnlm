package me.cnlm.utils;

import me.cnlm.api.gateway.aliyun.AliyunSmsGateway;
import me.cnlm.core.exception.InnerException;

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
 * 2017/2/24  	         zhuxl@paxsz.com        Create/Add/Modify/Delete
 * ============================================================================		
 */
public class SmsUtil {

    public static final int SMS_CODE_EXPIRED = 15;

    public static void sendSmsCode(String phone, String code) throws InnerException {
        boolean res = AliyunSmsGateway.batchSend(phone, code);
        if (!res) {
            throw new InnerException("发送短信失败");
        }
    }
}
