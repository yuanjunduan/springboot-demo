package cn.com.dyj.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfigure {

  @Value("${rest.read.timeout}")
  private int readTimeout;

  @Value("${rest.connect.timeout}")
  private int connectTimeout;

  @Bean
  public RestTemplate restTemplate(final ClientHttpRequestFactory factory) {
    return new RestTemplate(factory);
  }

  @Bean
  public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
    final SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    factory.setReadTimeout(readTimeout);
    factory.setConnectTimeout(connectTimeout);
    return factory;
  }
}
