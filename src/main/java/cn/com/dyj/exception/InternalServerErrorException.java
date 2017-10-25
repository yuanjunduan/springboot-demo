package cn.com.dyj.exception;


import cn.com.dyj.contants.AppError;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class InternalServerErrorException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private int errorCode;
  private String message;

  public InternalServerErrorException(final AppError appError) {
    super();
    this.errorCode = appError.getErrorCode();
    this.message = appError.getMessageKey();
  }
}
