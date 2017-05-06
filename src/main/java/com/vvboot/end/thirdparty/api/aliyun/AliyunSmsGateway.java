package com.vvboot.end.thirdparty.api.aliyun;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

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
 * 2017/2/25  	         zhuxl@paxsz.com        Create/Add/Modify/Delete
 * ============================================================================		
 */
public class AliyunSmsGateway {
    private static final Logger logger = LoggerFactory.getLogger(AliyunSmsGateway.class);

    public static final boolean batchSend(String url, String path, String method, String auth, String vars, String phones, String signName, String smsTemplate) {
        Map<String, String> headers = new HashMap<>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", auth);
        Map<String, String> querys = new HashMap<>();
        querys.put("ParamString", vars);
        querys.put("RecNum", phones);
        querys.put("SignName", signName);
        querys.put("TemplateCode", smsTemplate);
        String result = null;
        try {
            HttpResponse response = HttpUtils.doGet(url, path, method, headers, querys);
            result = EntityUtils.toString(response.getEntity());
            //获取response的body
            logger.info("<<======发送短信验证码响应:[{}]",result);
            JSONObject obj = JSONObject.parseObject(result);
            if("true".equals(obj.getString("success"))){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
