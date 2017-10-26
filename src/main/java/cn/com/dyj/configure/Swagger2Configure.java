package cn.com.dyj.configure;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "operation", name = "swagger-open", havingValue = "true")
public class Swagger2Configure {

  @Value("${operation.swagger-open}")
  private boolean swaggerOpen;

  @Autowired
  private RestTemplate restTemplate;


  @Bean
  public Docket createRestApi() {
    final List<Parameter> parameters = new ArrayList<>();
//    if (swaggerOpen) {
//      // 添加header参数
//      final ParameterBuilder parameterBuilder = new ParameterBuilder();
//      final Parameter parameter = parameterBuilder.name("Authorization")
//          .defaultValue("Bearer " + getKeyCloakToken())
//          .description("token")
//          .modelRef(new ModelRef("string"))
//          .parameterType("header")
//          .required(false)
//          .build();
//
//
//      parameters.add(parameter);
//
//    }
    return new Docket(DocumentationType.SWAGGER_2)
        .globalOperationParameters(parameters)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("cn.com.dyj.api"))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Spring Boot中使用Swagger2构建RESTful APIs")
        .description("描述")
        .version("1.0")
        .build();
  }

  /**
   * 获取KeyCloak的Token
   */
  /*private String getKeyCloakToken() {
    final String url = authServerUrl + "/realms/" + realm + "/protocol/openid-connect/token";

    final MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
    multiValueMap.add("grant_type", grantType);
    multiValueMap.add("client_id", resource);
    multiValueMap.add("client_secret", clientSecret);
    // 这里的用户名和密码都与resource相同，如果不同，需要单独配置
    multiValueMap.add("username", username);
    multiValueMap.add("password", password);

    final String json = restTemplate.postForObject(url, multiValueMap, String.class);

    if (StringUtils.isNotEmpty(json)) {
      try {
        final Object token = new ObjectMapper().readValue(json, Map.class).get("access_token");

        if (token != null) {
          return token.toString();
        }
      } catch (IOException e) {
        log.error("解析KeyCloakToken异常", e);
      }
    }

    return "";
  }*/

}
