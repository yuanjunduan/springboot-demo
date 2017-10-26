package cn.com.dyj.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * duanyuanjun 2017/10/23 18:31
 */
@Data //set get 方法
@NoArgsConstructor //无参构造
@AllArgsConstructor //全参构造
@Builder //链式编程
@Entity //创建表
@EntityListeners(AuditingEntityListener.class) //自动注入创建人、时间
public class DemoModelList {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String userName;
  private String email;
}
