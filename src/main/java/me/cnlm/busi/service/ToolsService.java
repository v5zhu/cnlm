package me.cnlm.busi.service;

import com.alibaba.fastjson.JSONObject;
import me.cnlm.busi.params.DencryptParam;

/**
 * Created by PAX on 2017/4/1.
 */
public interface ToolsService {

    String dateFormat(String src, String format, String type);

    String codec(String src, String type);

    JSONObject dencrypt(DencryptParam dencryptParam);
}
