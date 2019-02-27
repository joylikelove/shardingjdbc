package cn.denglongjiao.demo.shardingjdbc.three.javacode.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 2YSP on 2018/9/23.
 */
@Configuration
public class DataSourceConfig {

    /**
     * 配置分库分表策略
     *
     * @return
     * @throws SQLException
     */
    @Bean(name = "shardingDataSource")
    DataSource getShardingDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getUserTableRuleConfiguration());
        shardingRuleConfig.getBindingTableGroups().add("t_order");
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", new ModuloDatabaseShardingAlgorithm()));
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id", new ModuloTableShardingAlgorithm()));
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, new ConcurrentHashMap(1), new Properties());
    }

    /**
     * 设置表的node
     *
     * @return
     */
    @Bean
    TableRuleConfiguration getUserTableRuleConfiguration() {
        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
        orderTableRuleConfig.setLogicTable("t_order");
        orderTableRuleConfig.setActualDataNodes("ds${0..1}.t_order${0..1}");
        orderTableRuleConfig.setKeyGeneratorColumnName("user_id");
        return orderTableRuleConfig;
    }

    private Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>(8);
        result.put("ds0", createDataSource("ds0"));
        result.put("ds1", createDataSource("ds1"));
        return result;
    }

    private static DataSource createDataSource(String dataSourceName) {
        //使用druid连接数据库
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl(String.format("jdbc:mysql://localhost:3306/%s?characterEncoding=utf-8", dataSourceName));
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("");
        return druidDataSource;
    }

}
