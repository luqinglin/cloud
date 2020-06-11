package me.sta.configuration;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.SQLException;

/**
 * Multiple DataSource Aspect
 *
 * @author HelloWood
 * @date 2017-08-15 11:37
 * @email hellowoodes@gmail.com
 */
@Aspect
@Component
@Order(100)
public class DynamicDataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);


    /**
     * Dao aspect.
     */
    @Pointcut("execution( * me.sta.service.impl.*.*(..))")
    public void daoAspect() {
    }

    /**
     * Switch DataSource
     * 如调用数据库存在主库操作 请优先开启事物
     * @param point the point
     */
    @Before("daoAspect()")
    public void switchDataSource(JoinPoint point) throws SQLException {

        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        TradingDataSource tds = method.getAnnotation(TradingDataSource.class);

        if(null == tds && DynamicDataSourceContextHolder.getDataSourceKey() == null){
            logger.error("==【未指定数据源,请检测方法 ,methodSignature:{}】== ",methodSignature.getMethod().getName());
            throw new ApplicationException(ApplicationCode.DATA_SOURCE_SET_ERROR);
        }

        if(null == tds && DynamicDataSourceContextHolder.getDataSourceKey() != null){
            return;
        }

        String dataSources = tds.name();

        if(StringUtils.startsWith(dataSources, MasterSlaveDataSourceKey.MASTER)){
            logger.debug("==【switchDataSource,切换主库，slave:{}】==",dataSources);
            DynamicDataSourceContextHolder.setDataSourceKey(DataSourceKey.masterSource.name());
            return;
        }

        // 读库负载获取数据源切换
        if(StringUtils.startsWith(dataSources,MasterSlaveDataSourceKey.READ)){
            logger.debug("==【switchDataSource,切换从库，slave:{}】==",dataSources);
            DynamicDataSourceContextHolder.setDataSourceKey(DataSourceKey.readSource.name());
            return;
        }

        throw new ApplicationException(ApplicationCode.DATA_SOURCE_SET_ERROR);
    }

    /**
     * Restore DataSource
     *
     * @param point the point
     */
    @After("daoAspect())")
    public void restoreDataSource(JoinPoint point) {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        TradingDataSource tds = method.getAnnotation(TradingDataSource.class);

        if(null == tds && DynamicDataSourceContextHolder.getDataSourceKey() == null){
            logger.error("==【未指定数据源,请检测方法 ,methodSignature:{}】== ",methodSignature.getMethod().getName());
            throw new ApplicationException(ApplicationCode.DATA_SOURCE_SET_ERROR);
        }

        if(null == tds && DynamicDataSourceContextHolder.getDataSourceKey() != null){
            return;
        }

        DynamicDataSourceContextHolder.clearDataSourceKey();
    }


}
