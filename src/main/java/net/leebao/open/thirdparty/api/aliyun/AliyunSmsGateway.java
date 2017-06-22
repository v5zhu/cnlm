package net.leebao.open.thirdparty.api.aliyun;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;
import net.leebao.open.core.exception.InnerException;
import net.leebao.open.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final String fileName = "application.sms.properties";
    private static String $AccessId;
    private static String $AccessKey;
    private static String $MNSEndpoint;
    private static String $Topic;
    private static String $SignName;
    private static String $SMSTemplateCode;

    static {
        $AccessId = PropertiesUtil.getValue(fileName, "$AccessId");
        $AccessKey = PropertiesUtil.getValue(fileName, "$AccessKey");
        $MNSEndpoint = PropertiesUtil.getValue(fileName, "$MNSEndpoint");
        $Topic = PropertiesUtil.getValue(fileName, "$Topic");
        $SignName = PropertiesUtil.getValue(fileName, "$SignName");
        $SMSTemplateCode = PropertiesUtil.getValue(fileName, "$SMSTemplateCode");
    }

    private static final Logger logger = LoggerFactory.getLogger(AliyunSmsGateway.class);

    public static final boolean batchSend(String phone, String code) {
        /**
         * Step 1. 获取主题引用
         */
        CloudAccount account = new CloudAccount($AccessId, $AccessKey, $MNSEndpoint);
        MNSClient client = account.getMNSClient();
        CloudTopic topic = client.getTopicRef($Topic);
        /**
         * Step 2. 设置SMS消息体（必须）
         *
         * 注：目前暂时不支持消息内容为空，需要指定消息内容，不为空即可。
         */
        RawTopicMessage msg = new RawTopicMessage();
        msg.setMessageBody("sms-message");
        /**
         * Step 3. 生成SMS消息属性
         */
        MessageAttributes messageAttributes = new MessageAttributes();
        BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
        // 3.1 设置发送短信的签名（SMSSignName）
        batchSmsAttributes.setFreeSignName($SignName);
        // 3.2 设置发送短信使用的模板（SMSTempateCode）
        batchSmsAttributes.setTemplateCode($SMSTemplateCode);
        // 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
        BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
        smsReceiverParams.setParam("code", code);
        smsReceiverParams.setParam("time", "15");
        // 3.4 增加接收短信的号码
        batchSmsAttributes.addSmsReceiver(phone, smsReceiverParams);
//        batchSmsAttributes.addSmsReceiver("13880298929", smsReceiverParams);
        messageAttributes.setBatchSmsAttributes(batchSmsAttributes);
        try {
            /**
             * Step 4. 发布SMS消息
             */
            TopicMessage ret = topic.publishMessage(msg, messageAttributes);
            logger.info("网关发送短信返回:[{}]", JSONObject.toJSONString(ret));
        } catch (ServiceException se) {
            System.out.println(se.getErrorCode() + se.getRequestId());
            System.out.println(se.getMessage());
            se.printStackTrace();
            throw new InnerException("发送短信异常");
        } catch (Exception e) {
            e.printStackTrace();
            throw new InnerException("发送短信异常");
        } finally {
            client.close();
        }
        return true;
    }

    public static void main(String[] args) {
        batchSend("13880298929", "123456");
    }
}
