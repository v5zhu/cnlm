package com.pax.cms.busi.platform;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :   zhuxl@paxsz.com
 * @time :   2017/6/22
 * @description:
 */
public class LB extends JSONObject{
    public LB(){}
    public static LB ok(){
        LB lb=new LB();
        lb.put("code",200);
        return lb;
    }

    public static LB ok(Object object){
        LB lb=new LB();
        lb.put("code",200);
        lb.put("data",object);
        return lb;
    }

    public static LB error(){
        LB lb=new LB();
        lb.put("code",500);
        lb.put("msg","未知异常，请联系管理员");
        return lb;
    }

    public static LB error(String msg){
        LB lb=new LB();
        lb.put("code",500);
        lb.put("msg",msg);
        return lb;
    }

    public static void main(String[] args) {
        System.out.println(LB.ok().toString());
        System.out.println(LB.ok(LB.ok()).toString());
        System.out.println(LB.error().toString());
        System.out.println(LB.error("系统异常").toString());
    }
}
