import cn.com.dyj.contants.UrlsConstants;
import cn.com.dyj.entity.DemoModel;
import cn.com.dyj.enum1.EntityStatus;

import com.fasterxml.jackson.core.type.TypeReference;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

/**
 * duanyuanjun 2017/10/26 10:22
 */
public class DemoTest extends BaseTest {

  private static final String URL = UrlsConstants.PROJECT_API_ROOT + "/demo";

  //新增
  @Test
  public void add() throws Exception {

    //创建实体
    final DemoModel plan = DemoModel.builder()
        .name("新增demo")
        .status(EntityStatus.NORMAL)
        .build();

    final String content = mockMvc.perform(
        MockMvcRequestBuilders.post(URL)
            //.headers(getHttpHeader()) 这里可以放token认证信息 keycloak ..
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(plan)))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    final DemoModel saveDemoModel = objectMapper.readValue(content, DemoModel.class);
    assertNotNull("============不为空  则保存成功===", saveDemoModel.getId());
  }

  @Test
  public void find() throws Exception {

    final String content = mockMvc.perform(
        MockMvcRequestBuilders.get(URL + "/2")
            // .headers(getHttpHeader())
            .contentType(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    final DemoModel findDemoModel = objectMapper.readValue(content, DemoModel.class);
    assertNotNull("===========查询成功===", findDemoModel);
  }


  @Test
  public void update() throws Exception {

    String name = "demo 测试修改";
    final DemoModel planReq = DemoModel.builder()
        .name(name)
        .build();

    final String content = mockMvc.perform(
        MockMvcRequestBuilders.put(URL + "/2")
            // .headers(getHttpHeader())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(planReq)))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();


    //这里修改需要返回修改的信息 才这里写的
    final DemoModel updateDemoModel = objectMapper.readValue(content, DemoModel.class);
    //fixme 修改不返回值 去数据库查询
    assertTrue("================修改成功", name.equals(updateDemoModel.getName()));
  }


  //这里没有真实的删除 只是修改状态
  @Test
  public void delete() throws Exception {

    mockMvc.perform(
        MockMvcRequestBuilders.delete(URL + "/2")
        //        .headers(getHttpHeader())
    )
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn()
        .getResponse().getContentAsString();

    //todo 这里没有添加断言  自己补全
  }

  //查询所有带分页
  @Test
  public void findList() throws Exception {

    final String content = mockMvc.perform(
        MockMvcRequestBuilders.get(URL)
            // .headers(getHttpHeader())
            .contentType(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    //json 字符转转集合
    final List<DemoModel> beanList = objectMapper.readValue(content, new TypeReference<List<DemoModel>>() {
    });

    assertTrue("===========查询成功===", CollectionUtils.isNotEmpty(beanList));
  }
}
