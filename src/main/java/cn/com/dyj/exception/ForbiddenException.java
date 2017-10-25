package cn.com.dyj.exception;


import cn.com.dyj.contants.AppError;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ForbiddenException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private int errorCode;
  private String message;
  private String data;

  public ForbiddenException(final AppError appError) {
    super();
    this.errorCode = appError.getErrorCode();
    this.message = appError.getMessageKey();
  }

  public ForbiddenException(final AppError appError, final String data) {
    super();
    this.errorCode = appError.getErrorCode();
    this.message = appError.getMessageKey();
    this.data = data;
  }
}
