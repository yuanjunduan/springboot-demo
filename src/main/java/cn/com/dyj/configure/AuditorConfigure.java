package cn.com.dyj.configure;

import cn.com.dyj.entity.UserModel;
import cn.com.dyj.utils.SysUserUtil;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class AuditorConfigure implements AuditorAware<UserModel> {

  @Override
  public UserModel getCurrentAuditor() {
    final UserModel user = SysUserUtil.getSysUser();
    if (user != null) {
      return user;
    }
    //todo 抛一个自定义异常
    throw new RuntimeException();
  }
}
