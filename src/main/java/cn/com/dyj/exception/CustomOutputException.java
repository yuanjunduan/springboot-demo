package cn.com.dyj.exception;

import cn.com.dyj.contants.AppError;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class CustomOutputException {

  public static void outputErrorResponse(final HttpServletResponse httpResponse, final HttpStatus status)
      throws IOException {
    AppError appError = AppError.OTHER_SERVER_INTERNAL_EXCEPTION;
    switch (status) {
      case UNAUTHORIZED:
        appError = AppError.UNAUTHORIZED_NO_LOGIN;
        break;
      case FORBIDDEN:
        appError = AppError.UNAUTHORIZED_ACCESSED;
        break;
      default:
        break;
    }
    outputErrorResponse(appError.getErrorCode(), appError.getMessageKey(), httpResponse, status);
  }

  public static void outputErrorResponse(final int errorCode, //
      final String message, //
      final HttpServletResponse httpResponse, //
      final HttpStatus status) throws IOException {
    final ErrorMessage errorMessage = new ErrorMessage(errorCode, message);
    final String tokenJsonResponse = new ObjectMapper().writeValueAsString(errorMessage);
    httpResponse.addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    httpResponse.setStatus(status.value());
    httpResponse.getWriter().print(tokenJsonResponse);
  }
}
