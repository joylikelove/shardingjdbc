package cn.denglongjiao.demo.shardingjdbc.three.masterslave.repository;

import cn.denglongjiao.demo.shardingjdbc.three.masterslave.bean.Order;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by 2YSP on 2018/9/23.
 */
public interface OrderRepository extends CrudRepository<Order, Long> {

}
