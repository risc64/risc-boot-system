package com.risc.boot.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @description: SpringBean工具类
 * @author: 李良发
 * @since: 2023/8/21
 */
@Component
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }
    
    /**
     * applicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    
    /**
     * 通过name获取 Bean.
     * @param name beanName
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }
    
    /**
     * 通过class获取Bean.
     * @param clazz beanClass
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }
    
    /**
     * 通过name,以及Clazz返回指定的Bean
     * 【shioy】
     * @param name beanName
     * @param clazz beanClass
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
    
    /**
     * 手动注册Bean
     * @param requiredType 待注册的BeanClass文件
     * @param <T> bean类型
     * @return 返回Bean对象
     */
    public static  <T> T registerBean(Class<T> requiredType){
        return registerBean(requiredType,requiredType.getSimpleName());
    }
    
    /**
     *  动态注入bean
     * @param requiredType 注入类
     * @param beanName bean名称
     */
    public static <T> T registerBean(Class<T> requiredType,String beanName){
        //将applicationContext转换为ConfigurableApplicationContext
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        //获取BeanFactory
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getAutowireCapableBeanFactory();
        //创建bean信息.
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(requiredType);
        //动态注册bean.
        defaultListableBeanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        //获取动态注册的bean.
        return configurableApplicationContext.getBean(requiredType);
    }
    
    /**
     * 读取当前配置环境
     * @return
     */
    public static String getActiveProfile() {
        return applicationContext.getEnvironment().getActiveProfiles()[0];
    }
    
    
    /**
     * 读取环境配置文件
     * @param propertyName
     * @return
     */
    public static String getProperty(String propertyName) {
        return applicationContext.getEnvironment().getProperty(propertyName);
    }
}
