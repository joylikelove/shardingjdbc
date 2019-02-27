package cn.denglongjiao.demo.shardingjdbc.three.javacode;

import cn.denglongjiao.demo.shardingjdbc.three.javacode.bean.Order;
import cn.denglongjiao.demo.shardingjdbc.three.javacode.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavacodeApplicationTests {

    @Autowired
    private OrderRepository repository;

    @Test
    public void add() throws InterruptedException {
//        for (int i = 0; i < 10; i++) {
//            Order order = new Order();
//            order.setOrderId(System.currentTimeMillis());
//            order.setUserId((long) i);
//            repository.save(order);
//            TimeUnit.SECONDS.sleep(1);
//        }
        Order order = new Order();
        order.setUserId(1L);
        order.setOrderId(System.currentTimeMillis());
        repository.save(order);
    }

    @Test
    public void queryAll() {
        List<Order> orders = (List<Order>) repository.findAll();
        orders.forEach(order -> System.out.println(order));
    }

}
