package me.cnlm.utils;

import me.cnlm.core.exception.InnerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

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
public class PropertiesUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    public static final String getValue(String fileName, String key) throws InnerException {
        try {
            Properties prop = new Properties();
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            InputStreamReader isr=new InputStreamReader(in,"UTF-8");
            prop.load(isr);
            return prop.getProperty(key);
        } catch (Exception e) {
            logger.error("读取配置文件异常:", e);
            throw new InnerException("读取配置文件异常");
        }
    }
}
