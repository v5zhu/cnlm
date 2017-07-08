package net.leebao.auth.datasource.timedtask;

import net.leebao.open.inner.timertask.InvokingJobDetailDetailFactory;
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

    /**
     * 加载job
     * @return
     */
    @Bean
    public JobDetailFactoryBean toutiaoJob() {
        return createJobDetail(InvokingJobDetailDetailFactory.class, "updateDialogStatusGroup", "toutiaoTimerTask");
    }

    /**
     * 加载触发器
     * @param jobDetail
     * @return
     */
    @Bean(name = "toutiaoJobTrigger")
    public CronTriggerFactoryBean toutiaoJobTrigger(@Qualifier("toutiaoJob") JobDetail jobDetail) {
        return dialogStatusTrigger(jobDetail, cronToutiaoUpdate);
    }

    /**
     * 创建job工厂
     * @param jobClass
     * @param groupName
     * @param targetObject
     * @return
     */
    private static JobDetailFactoryBean createJobDetail(Class<?> jobClass, String groupName, String targetObject) {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(jobClass);
        factoryBean.setDurability(true);
        factoryBean.setRequestsRecovery(true);
        factoryBean.setGroup(groupName);
        Map<String, String> map = new HashMap<>();
        map.put("targetObject", targetObject);
        map.put("targetMethod", "updateToutiao");
        factoryBean.setJobDataAsMap(map);
        return factoryBean;
    }

    /**
     * 创建触发器工厂
     * @param jobDetail
     * @param cronExpression
     * @return
     */
    private static CronTriggerFactoryBean dialogStatusTrigger(JobDetail jobDetail, String cronExpression) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setCronExpression (cronExpression);
        return factoryBean;
    }
}
