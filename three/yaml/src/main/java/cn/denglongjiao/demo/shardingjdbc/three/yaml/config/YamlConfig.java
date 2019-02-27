package cn.denglongjiao.demo.shardingjdbc.three.yaml.config;

import io.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class YamlConfig {

    @Bean(name = "shardingDataSource")
    DataSource getShardingDataSource() throws SQLException {
//        DataSource dataSource = MasterSlaveDataSourceFactory.createDataSource("");
        return null;
    }

}
