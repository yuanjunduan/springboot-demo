package cn.com.dyj.api;

import cn.com.dyj.contants.ApiDocs;
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
   * @param demoReq 请求数据
   * @return 返回保存策略实体
   */
  @PostMapping
  @ApiOperation(value = ApiDocs.API_DEMO_ADD, notes = ApiDocs.API_DEMO_ADD)
  public DemoModel save(@RequestBody final DemoModel demoReq) {

    return service.save(demoReq);
  }

  @GetMapping("/{reqId}")
  @ApiOperation(value = ApiDocs.API_DEMO_FIND_ONE, notes = ApiDocs.API_DEMO_FIND_ONE)
  public DemoModel find(@PathVariable final Long reqId) {

    return service.find(reqId);
  }

  @DeleteMapping("/{reqId}")
  @ApiOperation(value = ApiDocs.API_DEMO_DELETE, notes = ApiDocs.API_DEMO_DELETE)
  public void delete(@PathVariable final Long reqId) {
    service.delete(reqId);
  }

  @PutMapping("/{reqId}")
  @ApiOperation(value = ApiDocs.API_DEMO_UPDATE, notes = ApiDocs.API_DEMO_UPDATE)
  public DemoModel update(@PathVariable final Long reqId, @RequestBody final DemoModel reqModel) {

    reqModel.setId(reqId);
    return service.update(reqModel);
  }

  @GetMapping
  @ApiOperation(value = ApiDocs.API_DEMO_LIST, notes = ApiDocs.API_DEMO_LIST)
  public PageInfo<DemoModel> list(
      @RequestParam(value = "page", defaultValue = UrlsConstants.PAGE) final Integer page,
      @RequestParam(value = "size", defaultValue = UrlsConstants.SIZE) final Integer size,
      @ModelAttribute final DemoModel model) {

    return service.findList(model, page, size);
  }


}
