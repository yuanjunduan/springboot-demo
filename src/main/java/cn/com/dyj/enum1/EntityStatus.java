package cn.com.dyj.enum1;

/**
 * duanyuanjun 2017/8/17 20:05
 */
public enum EntityStatus {

  NORMAL("正常", 0),
  DELETE("删除", 1);

  // 成员变量
  private String name;
  private Integer index;

  // 构造方法
  EntityStatus(final String name, final int index) {
    this.name = name;
    this.index = index;
  }

  public String getName() {
    return name;
  }

  public static String getName(final int index) {
    for (final EntityStatus status : EntityStatus.values()) {
      if (status.getIndex() == index) {
        return status.name;
      }
    }
    return null;
  }

  public Integer getIndex() {
    return index;
  }

  public static Integer getIndex(final String name) {
    for (final EntityStatus status : EntityStatus.values()) {
      if (status.getName().equals(name)) {
        return status.getIndex();
      }
    }
    return null;
  }

}
