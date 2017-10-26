import cn.com.dyj.DyjApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

/**
 * Created by liufei on 2017/8/18.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DyjApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@WebAppConfiguration
@ActiveProfiles("test.env")
public class BaseTest extends Assert {

  protected MockMvc mockMvc;
  @Resource
  protected WebApplicationContext wac;
  @Resource
  protected FilterChainProxy filterChainProxy;

  protected ObjectMapper objectMapper = new ObjectMapper();


  @Before()
  public void initMock() {

    objectMapper.registerModule(new JodaModule());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilters(filterChainProxy).build();
  }


  /*
  之前项目使用的keycloak  实例
  public HttpHeaders getHttpHeader() {
    final BasicAuthConfiguration config = new BasicAuthConfiguration();
    config.setAuthServerUrl(authServerUrl);
    config.setCredentialSecret(clientSecret);
    config.setResource(resource);
    config.setRealm(realm);
    config.setUsername(username);
    config.setPassword(password);

    final BasicAuthToken basicToken = new BasicAuthToken(config);

    final String token = basicToken.getToken();
    final HttpHeaders headers = new HttpHeaders();

    headers.add("authorization", "Bearer " + token);
    return headers;
  }*/
}