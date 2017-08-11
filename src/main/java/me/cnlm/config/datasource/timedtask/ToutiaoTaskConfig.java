package me.cnlm.config.datasource.timedtask;

import me.cnlm.core.constant.LBTimedTaskConstant;
import me.cnlm.core.factory.InvokingJobDetailDetailFactory;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LONG on 2017/7/8.
 */
@Configuration
public class ToutiaoTaskConfig {

    @Value("${quartz.cron.toutiao.update}")
    private String cronToutiaoUpdate;
    @Value("${quartz.cron.toutiao.delete}")
    private String cronToutiaoDelete;

    /**
     * 更新头条
     *
     * @return
     */
    @Bean
    public JobDetailFactoryBean toutiaoUpdateJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(InvokingJobDetailDetailFactory.class);
        factoryBean.setDurability(true);
        factoryBean.setRequestsRecovery(true);
        factoryBean.setGroup(LBTimedTaskConstant.CommonConstant.GROUP);
        Map<String, String> map = new HashMap<>();
        map.put("targetObject", LBTimedTaskConstant.ToutiaoConstant.targetObject);
        map.put("targetMethod", LBTimedTaskConstant.ToutiaoConstant.targetMethod_updateToutiao);
        factoryBean.setJobDataAsMap(map);
        return factoryBean;
    }
    @Bean
    public CronTriggerFactoryBean toutiaoUpdateTrigger(@Qualifier("toutiaoUpdateJobDetail") JobDetail jobDetail) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setCronExpression(cronToutiaoUpdate);
        return factoryBean;
    }

    /**
     * 删除头条
     *
     * @return
     */
    @Bean
    public JobDetailFactoryBean toutiaoDeleteJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(InvokingJobDetailDetailFactory.class);
        factoryBean.setDurability(true);
        factoryBean.setRequestsRecovery(true);
        factoryBean.setGroup(LBTimedTaskConstant.CommonConstant.GROUP);
        Map<String, String> map = new HashMap<>();
        map.put("targetObject", LBTimedTaskConstant.ToutiaoConstant.targetObject);
        map.put("targetMethod", LBTimedTaskConstant.ToutiaoConstant.targetMethod_deleteToutiao);
        factoryBean.setJobDataAsMap(map);
        return factoryBean;
    }
    @Bean
    public CronTriggerFactoryBean toutiaoDeleteTrigger(@Qualifier("toutiaoDeleteJobDetail") JobDetail jobDetail) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setCronExpression(cronToutiaoDelete);
        return factoryBean;
    }
}
