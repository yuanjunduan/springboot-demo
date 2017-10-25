package cn.com.dyj.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * duanyuanjun 2017/10/24 8:42
 */
public final class StackTraceUtil {

  public StackTraceUtil(){
  }

  /**
   * 获取异常的堆栈信息.
   */
  public static String getStackTrace(final Throwable tw) {
    final StringWriter sw = new StringWriter();
    final PrintWriter pw = new PrintWriter(sw);

    try {
      tw.printStackTrace(pw);
      return sw.toString();
    } finally {
      pw.close();
    }
  }
}
