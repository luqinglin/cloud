package me.sta.configuration;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@Primary
@ConditionalOnProperty(name = "spring.datasource.mode", havingValue = "sharding")
public class DataSourceShardingConfiguration {


    @Resource
    private DataSource dataSource;

    /**
     * Sql session factory bean.
     * Here to config datasource for SqlSessionFactory
     * <p>
     * You need to add @{@code @ConfigurationProperties(prefix = "mybatis")}, if you are using *.xml file,
     * the {@code 'mybatis.type-aliases-package'} and {@code 'mybatis.mapper-locations'} should be set in
     * {@code 'application.properties'} file, or there will appear invalid bond statement common
     *
     * @return the sql session factory bean
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // Here is very important, if don't config this, will can't switch datasource
        // put all datasource into SqlSessionFactoryBean, then will autoconfig SqlSessionFactory
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:sqlMapper/*.xml"));
        return sqlSessionFactoryBean;
    }



    /**
     * Transaction manager platform transaction manager.
     *
     * @return the platform transaction manager
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}