package cn.com.dyj.repository;

import cn.com.dyj.entity.DemoModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * duanyuanjun 2017/10/23 18:40
 */
@Repository
public interface DemoModelRepository extends JpaRepository<DemoModel, Long>, JpaSpecificationExecutor<DemoModel> {

  //查询
  List<DemoModel> findById(final Long id);

  //可以直接透过name 查询
  //还有很多用法  https://docs.spring.io/spring-data/jpa/docs/2.0.0.M1/reference/html/  ctrl+f findByAgeGreaterThanEqual
  List<DemoModel> findByName(String name);

  //表示原生态的sql  nativeQuery = true
  @Modifying  //表示修改
  @Query(value = "update demo_model set id=:id , name=:name where id=:planId", nativeQuery = true)
  int updateForeignKeyById(@Param("id") final Long id, @Param("planId") final Long planId, @Param("name") final String name);

  @Query("select s from DemoModel s left join FETCH s.demoModelListList p where p.id=:listId and s.id=:id")
  DemoModel find(@Param("id") final Long id, @Param("listId") final Long listId);

  /**
   * 根据ID获取任务.
   *
   * @param pageable 分页参数
   * @return 分页后数据
   */
  @Query("SELECT t FROM DemoModel t WHERE t.name=:name ORDER BY createTime DESC")
  Page<DemoModel> findTaskResultByName(@Param("name") final String name, final Pageable pageable);
}
