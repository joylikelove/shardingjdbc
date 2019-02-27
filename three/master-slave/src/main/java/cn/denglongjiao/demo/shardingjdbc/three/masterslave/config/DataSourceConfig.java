package cn.denglongjiao.demo.shardingjdbc.three.masterslave.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.shardingsphere.api.config.rule.MasterSlaveRuleConfiguration;
import io.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * DataSourceConfig
 *
 * @author joylikelove
 * @date 2019/2/25 12:17 AM
 */
//@Configuration
public class DataSourceConfig {

    /**
     * 配置分库分表策略
     *
     * @return
     * @throws SQLException
     */
//    @Bean(name = "shardingDataSource")
    DataSource getMasterSlaveDataSource() throws SQLException {
        MasterSlaveRuleConfiguration masterSlaveRuleConfig = new MasterSlaveRuleConfiguration();
        masterSlaveRuleConfig.setName("ds_master_slave");
        masterSlaveRuleConfig.setMasterDataSourceName("ds_0");
        masterSlaveRuleConfig.setSlaveDataSourceNames(Arrays.asList("ds_1"));
        return MasterSlaveDataSourceFactory.createDataSource(createDataSourceMap(), masterSlaveRuleConfig, new LinkedHashMap<>(), new Properties());
    }

    private static Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>(2);
        result.put("ds_0", createDataSource("ds_0"));
        result.put("ds_1", createDataSource("ds_1"));
        return result;
    }

    /**
     * 设置表的node
     *
     * @return
     */
    private static DataSource createDataSource(String dataSourceName) {
        // 配置真实数据源
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(String.format("jdbc:mysql://localhost:3306/%s?characterEncoding=utf-8", dataSourceName));
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }

}
