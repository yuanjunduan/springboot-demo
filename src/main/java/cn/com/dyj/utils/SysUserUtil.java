package cn.com.dyj.utils;

import cn.com.dyj.entity.UserModel;

public final class SysUserUtil {

  private SysUserUtil() {
  }

  public static UserModel getSysUser() {

    //获取淞沪
    return UserModel.builder().build();

  }
}
