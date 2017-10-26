package cn.com.dyj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * duanyuanjun 2017/10/23 18:18
 */
@SpringBootApplication //启动主注解
@EnableJpaAuditing//开启创建人 创建时间注解
@EnableTransactionManagement //开始事物管理
//@EnableJpaRepositories
public class DyjApplication {

  public static void main(final String... args) {
    SpringApplication.run(DyjApplication.class, args);

  }
}