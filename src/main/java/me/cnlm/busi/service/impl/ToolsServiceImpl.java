package me.cnlm.busi.service.impl;

import com.alibaba.fastjson.JSONObject;
import me.cnlm.busi.params.DencryptParam;
import me.cnlm.busi.service.ToolsService;
import me.cnlm.core.exception.InnerException;
import me.cnlm.core.exception.LeeBaoException;
import me.cnlm.utils.CodecUtils;
import me.cnlm.utils.DateUtils;
import me.cnlm.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.validation.Validator;
import java.security.MessageDigest;

/**
 * Created by PAX on 2017/4/1.
 */
@Service
public class ToolsServiceImpl implements ToolsService {

    @Autowired
    private Validator validator;

    @Override
    public String dateFormat(String src, String format, String type) {
        if ("1".equals(type)) {
            return DateUtils.dateFormat(src, format);
        } else if ("-1".equals(type)) {
            return DateUtils.dateParse(src, format).toString();
        }
        return null;
    }

    @Override
    public String codec(String src, String type) {
        switch (type) {
            case "1":
                //URL编码(UTF-8)
                return CodecUtils.URLEncode(src, "UTF-8");
            case "2":
                //URL解码(UTF-8)
                return CodecUtils.URLDecode(src, "UTF-8");
            case "3":
                //URL编码(GBK)
                return CodecUtils.URLEncode(src, "GBK");
            case "4":
                //URL解码(GBK)
                return CodecUtils.URLDecode(src, "GBK");
            default:
                return null;
        }
    }

    @Override
    public JSONObject dencrypt(DencryptParam dencryptParam) {
        try {
            String error = ValidatorUtils.validate(validator, dencryptParam);
            if (error != null) {
                throw new LeeBaoException(error);
            }
            JSONObject output = new JSONObject();
            switch (dencryptParam.getArithmetic()) {
                case "MD5BASE64":
                    if ("encrypt".equals(dencryptParam.getMethod())) {
                        BASE64Encoder encoder = new BASE64Encoder();

                        MessageDigest md = MessageDigest.getInstance("MD5", "SUN");
                        md.reset();
                        md.update(dencryptParam.getContent().getBytes("UTF-8"));
                        byte[] bytes = md.digest();
                        output.put("output", encoder.encode(bytes));
                    } else if ("decrypt".equals(dencryptParam.getMethod())) {
                        throw new LeeBaoException("非法操作");
                    }
                    break;
                case "MD5":
                    break;
                case "SHA":
                    break;
                case "HMAC":
                    break;
                case "DES":
                    break;
                case "SALT":
//                if ("encrypt".equals(dencryptParam.getMethod())) {
//                    //加密
//                } else if ("decrypt".equals(dencryptParam.getMethod())) {
//                    //解密
//                    String password = PasswordEncryptionUtil.getEncryptedPassword(dencryptParam.getContent(), dencryptParam.getSalt());
//                    output.setOutput(password);
//                }
                    break;
                default:
                    throw new LeeBaoException("非法操作");
            }
            return output;
        } catch (LeeBaoException e) {
            throw e;
        } catch (Exception e) {
            throw new InnerException("内部错误");
        }
    }
}
