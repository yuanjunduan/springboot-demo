package cn.com.dyj.api;

import cn.com.dyj.contants.UrlsConstants;
import cn.com.dyj.entity.DemoModel;
import cn.com.dyj.service.DemoModelService;
import cn.com.dyj.utils.PageInfo;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * duanyuanjun 2017/10/23 18:23
 */
@RestController
@RequestMapping(UrlsConstants.PROJECT_API_ROOT + "/demo")
public class DemoApi {

  @Autowired
  private DemoModelService service;

  /**
   * 策略保存
   *
   * @param strategyReq 请求数据
   * @return 返回保存策略实体
   */
  @PostMapping
  @ApiOperation(value = "接口描述", notes = "接口描述")
  public DemoModel saveStrategy(@RequestBody final DemoModel strategyReq) {

    return null;
  }

  @GetMapping("/{strategyId}")
  public DemoModel findStrategy(@PathVariable final Long strategyId) {

    return null;
  }

  @DeleteMapping("/{strategyId}")
  public void deleteStrategy(@PathVariable final Long strategyId) {

  }

  @PutMapping("/{strategyId}")
  public DemoModel updateStrategy(@PathVariable final Long strategyId, @RequestBody final DemoModel strategyReq) {

    return null;
  }

  @GetMapping
  public PageInfo<DemoModel> listStrategies(
      @RequestParam(value = "page", defaultValue = "0") final Integer page,
      @RequestParam(value = "size", defaultValue = "10") final Integer size,
      @ModelAttribute final DemoModel model) {

    return service.findList(model, page, size);
  }


}
