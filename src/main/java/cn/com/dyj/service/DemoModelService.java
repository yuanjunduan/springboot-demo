package cn.com.dyj.service;

import cn.com.dyj.contants.AppError;
import cn.com.dyj.entity.DemoModel;
import cn.com.dyj.entity.DemoModelList;
import cn.com.dyj.enum1.EntityStatus;
import cn.com.dyj.exception.BadRequestException;
import cn.com.dyj.repository.DemoModelRepository;
import cn.com.dyj.utils.PageInfo;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.Predicate;

/**
 * duanyuanjun 2017/10/23 18:55
 */
@Service
@Transactional
public class DemoModelService {

  @Autowired
  private DemoModelRepository repo;

  /**
   * 查询渠道分页列表
   */
  public PageInfo<DemoModel> findList(final DemoModel model, final Integer page, final Integer size) {

    final Pageable pageable = new PageRequest(page - 1, size);

    // 过滤查询条件
    final Page<DemoModel> pageList = repo.findAll((root, criteriaQuery, criteriaBuilder) -> {

      final List<Predicate> predicates = new ArrayList<>();
      //状态不等于删除的
      predicates.add(criteriaBuilder.notEqual(root.get("status"), EntityStatus.DELETE));

      //like 查询
      if (StringUtils.isNotEmpty(model.getName())) {
        predicates.add(criteriaBuilder.like(root.get("name"), model.getName()));
      }

      //in 查询
      final Set<DemoModelList> demoModelListList = model.getDemoModelListList();
      if (CollectionUtils.isNotEmpty(demoModelListList)) {
        Set<Long> ids = new HashSet<>();
        demoModelListList.forEach(demo1 -> ids.add(demo1.getId()));
        // authors.replaceAll("；", ";").split(";");
        predicates.add(root.get("createUser").get("username").in(ids));
      }

      //通过时间倒叙
      criteriaQuery.orderBy(criteriaBuilder.desc(root.get("updateTime")));
      return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }, pageable);

    final List<DemoModel> listResponses = new ArrayList<>();
    if (CollectionUtils.isNotEmpty(pageList.getContent())) {

      for (DemoModel model1 : pageList.getContent()) {//获取所有的集合

      }
    }
    // pageList.getTotalElements() 获取的总记录数
    return new PageInfo<>(listResponses, pageList.getTotalElements());
  }

  public DemoModel save(final DemoModel demoReq) {
    return repo.save(demoReq);
  }

  public DemoModel find(final Long reqId) {
    return repo.findOne(reqId);
  }

  public void delete(final Long reqId) {

    final DemoModel demo = repo.findOne(reqId);
    if (null == demo) {
      throw new BadRequestException(AppError.REQUEST_PARAM_ERROR, "具体的错误信息");
    }
    demo.setStatus(EntityStatus.DELETE);
    repo.save(demo);
  }

  public DemoModel update(final DemoModel reqModel) {

    final DemoModel demo = repo.findOne(reqModel.getId());
    if (null == demo) {
      throw new BadRequestException(AppError.REQUEST_PARAM_ERROR, "具体的错误信息");
    }
    return repo.save(demo);
  }
}
