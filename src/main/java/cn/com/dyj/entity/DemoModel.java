package cn.com.dyj.entity;

import cn.com.dyj.enum1.EntityStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * duanyuanjun 2017/10/23 18:24
 */
@Data //set get 方法
@NoArgsConstructor //无参构造
@AllArgsConstructor //全参构造
@Builder //链式编程
@Entity //创建表
@EntityListeners(AuditingEntityListener.class) //自动注入创建人、时间
public class DemoModel {

  @Id
  @Column(unique = true, nullable = false, columnDefinition = "bigint(20)")
  @GeneratedValue(strategy = GenerationType.AUTO)
  @ApiModelProperty(name = "id", value = "页面提示信息", dataType = "Long")
  private long id;

  private String name;

  @Column(columnDefinition = "int(1) default 0 COMMENT '策略状态 0：正常，1：删除'")
  private EntityStatus status;

  @JsonIgnore //json 序列化不显示
  @CreatedBy   //创建人
  @ManyToOne(fetch = FetchType.LAZY)   //多对一 懒加载生效
  @JoinColumn(updatable = false, columnDefinition = "int(20)) COMMENT '创建人'") //updatable = false 一定要有
  private UserModel createUser;

  @LastModifiedBy
  @ManyToOne(fetch = FetchType.LAZY)   //多对一 懒加载生效
  @JoinColumn(columnDefinition = "int(20) COMMENT '修改人'")
  private UserModel updateUser;

  @Column(updatable = false, columnDefinition = "datetime COMMENT '创建时间'")//updatable = false 只有新增是一次有效
  @JsonIgnore //页面是否显示 添加JsonIgnore 不显示
  @CreatedDate
  private DateTime createTime;

  @Column(columnDefinition = "datetime COMMENT '修改时间'")
  @JsonIgnore
  @LastModifiedDate
  private DateTime updateTime;


  @ApiModelProperty(name = "demo1", value = "第二个demo")
  @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)//TODO 不知道懒加载不生效
  @JoinColumn(name = "demo_model_id") //会在多的一方创建少的一方的主键
  @JsonIgnoreProperties(value = {"name", "email"}) //不显示该类的name、email 字段
  private Set<DemoModelList> demoModelListList = new HashSet<>();
}


