package com.vvboot.end.utils;

import com.alibaba.fastjson.JSONObject;
import com.vvboot.end.core.exception.InnerException;
import com.vvboot.end.thirdparty.api.aliyun.AliyunSmsGateway;

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

    //阿里云短信服务
    public static final String SMS_GATEWAY_ALIYUN_URL = "http://sms.market.alicloudapi.com";
    public static final String SMS_GATEWAY_ALIYUN_PATH = "/singleSendSms";
    public static final String SMS_GATEWAY_ALIYUN_METHOD = "GET";
    public static final String SMS_GATEWAY_ALIYUN_AUTH = "APPCODE 0b4cc9f202234a81b22507209358c05f";
    public static final String SMS_GATEWAY_ALIYUN_SIGNNAME = "微微布特";
    public static final String SMS_GATEWAY_ALIYUN_SMSTEMPLATE = "SMS_49260144";


    public static void sendSmsCode(String phone, String code) throws InnerException {
        JSONObject vars = new JSONObject();
        vars.put("code", code);
        vars.put("time", SMS_CODE_EXPIRED);
        boolean res = AliyunSmsGateway.batchSend(SMS_GATEWAY_ALIYUN_URL, SMS_GATEWAY_ALIYUN_PATH, SMS_GATEWAY_ALIYUN_METHOD, SMS_GATEWAY_ALIYUN_AUTH, vars.toJSONString(), phone, SMS_GATEWAY_ALIYUN_SIGNNAME, SMS_GATEWAY_ALIYUN_SMSTEMPLATE);
        if (!res) {
            throw new InnerException("发送短信失败");
        }
    }
}
