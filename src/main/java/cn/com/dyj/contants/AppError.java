package cn.com.dyj.contants;

/**
 * 错误信息定义.
 *
 * @author zeche
 */
public enum AppError {

  UNAUTHORIZED_ACCESSED(4001, "unauthorized"),
  UNAUTHORIZED_NO_LOGIN(4002, "user.no.login"),

  REQUEST_PARAM_ERROR(4003, "请求参数有误"),


  DB_OPERATE_ERROR(8999, "database.operate.error"),
  OTHER_METHOD_ARGS_NOT_VALID(9000, ""),
  OTHER_HTTP_MEDIA_TYPE_NOT_SUPPORT(9001, "other.contenttype.unsupport"),
  OTHER_HTTP_MESSAGE_NOT_READABLE(9002, "other.message.not.readable"),
  OTHER_HTTP_TYPE_MISMATCH(9003, "other.type.mismatch"),
  OTHER_SERVER_INTERNAL_EXCEPTION(9999, "other.server.internal.error");
  private int errorCode;
  private String messageKey;

  AppError(final int code, final String messageKey) {
    this.errorCode = code;
    this.messageKey = messageKey;
  }

  public int getErrorCode() {
    return this.errorCode;
  }

  public String getMessageKey() {
    return this.messageKey;
  }

  public static AppError valueOf(final int errorCode) {
    AppError appError = null;
    for (final AppError error : values()) {
      if (error.getErrorCode() == errorCode) {
        appError = error;
        break;
      }
    }

    return appError;
  }
}
