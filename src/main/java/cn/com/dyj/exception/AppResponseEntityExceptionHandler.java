package cn.com.dyj.exception;

import cn.com.dyj.contants.AppError;
import cn.com.dyj.utils.StackTraceUtil;

import com.jcabi.aspects.Loggable;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Slf4j
@Loggable
@ControllerAdvice
public class AppResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private Messages messages;

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, //
      final HttpHeaders headers, //
      final HttpStatus status, //
      final WebRequest request) {

    log.error("MethodArgumentNotValidException:{}", StackTraceUtil.getStackTrace(ex));
    final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
    final ErrorMessage errorMessage =
        new ErrorMessage(AppError.OTHER_METHOD_ARGS_NOT_VALID.getErrorCode(), fieldErrors.get(0).getDefaultMessage());
    return new ResponseEntity<>(errorMessage, headers, status);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex, //
      final HttpHeaders headers, //
      final HttpStatus status, //
      final WebRequest request) {

    log.error("HttpMediaTypeNotSupportedException:{}", StackTraceUtil.getStackTrace(ex));
    final String message = messages.get(AppError.OTHER_HTTP_MEDIA_TYPE_NOT_SUPPORT.getMessageKey(), //
        ex.getContentType().getType(), //
        MediaType.toString(ex.getSupportedMediaTypes()));

    final ErrorMessage errorMessage = //
        new ErrorMessage(AppError.OTHER_HTTP_MEDIA_TYPE_NOT_SUPPORT.getErrorCode(), message);
    return new ResponseEntity<>(errorMessage, headers, status);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, //
      final HttpHeaders headers, //
      final HttpStatus status, //
      final WebRequest request) {
    log.error("HttpMessageNotReadableException:{}", StackTraceUtil.getStackTrace(ex));

    final AppError appError = AppError.OTHER_HTTP_MESSAGE_NOT_READABLE;
    final ErrorMessage errorMessage = new ErrorMessage(appError.getErrorCode(), appError.getMessageKey());
    return new ResponseEntity<>(errorMessage, headers, status);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, //
      final HttpHeaders headers, //
      final HttpStatus status, //
      final WebRequest request) {
    log.error("handleTypeMismatch:{}", StackTraceUtil.getStackTrace(ex));

    final ErrorMessage errorMessage =  //
        new ErrorMessage(AppError.OTHER_HTTP_TYPE_MISMATCH.getErrorCode(), "params.type.mismatch");
    return new ResponseEntity<>(errorMessage, headers, status);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(final Exception ex, //
      final Object body, //
      final HttpHeaders headers, //
      final HttpStatus status, //
      final WebRequest request) {

    log.error("{}:{}", ex.getClass(), StackTraceUtil.getStackTrace(ex));
    final ErrorMessage errorMessage = //
        new ErrorMessage(AppError.OTHER_SERVER_INTERNAL_EXCEPTION.getErrorCode(), ex.getMessage());
    return new ResponseEntity<>(errorMessage, headers, status);

  }

  @ExceptionHandler(value = {BadRequestException.class})
  protected ResponseEntity<Object> handleBadRequestException(final BadRequestException ex, //
      final WebRequest request) {

    log.error("{}", StackTraceUtil.getStackTrace(ex));
    final HttpStatus status = HttpStatus.BAD_REQUEST;
    final ErrorMessage errorMessage = new ErrorMessage(ex.getErrorCode(), ex.getMessage(), ex.getData());
    return new ResponseEntity<>(errorMessage, status);
  }

  @ExceptionHandler(value = {NotFoundException.class})
  protected ResponseEntity<Object> handleNotFoundException(final NotFoundException ex, final WebRequest request) {

    log.error("{}", StackTraceUtil.getStackTrace(ex));
    final HttpStatus status = HttpStatus.NOT_FOUND;
    final ErrorMessage errorMessage = new ErrorMessage(ex.getErrorCode(), ex.getMessage());
    return new ResponseEntity<>(errorMessage, status);
  }

  @ExceptionHandler(value = {InternalServerErrorException.class})
  protected ResponseEntity<Object> handleInternalServerException(final Exception ex, final WebRequest request) {

    log.error("handleInternalServerException {}:{}", ex.getClass(), StackTraceUtil.getStackTrace(ex));
    final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    final ErrorMessage errorMessage = //
        new ErrorMessage(AppError.OTHER_SERVER_INTERNAL_EXCEPTION.getErrorCode(), ex.getMessage());
    return new ResponseEntity<>(errorMessage, status);
  }

  @ExceptionHandler(value = {Exception.class})
  protected ResponseEntity<Object> handleGenericException(final Exception ex, final WebRequest request) {

    log.error("handleGenericException {}:{}", ex.getClass(), StackTraceUtil.getStackTrace(ex));
    final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    final ErrorMessage errorMessage = //
        new ErrorMessage(AppError.OTHER_SERVER_INTERNAL_EXCEPTION.getErrorCode(), ex.getMessage());
    return new ResponseEntity<>(errorMessage, status);
  }

  @ExceptionHandler(value = {UnauthorizedException.class})
  protected ResponseEntity<Object> handleUnauthorizedException(
      final UnauthorizedException ex, final WebRequest request) {

    log.error("{}", StackTraceUtil.getStackTrace(ex));
    final HttpStatus status = HttpStatus.UNAUTHORIZED;
    final ErrorMessage errorMessage = new ErrorMessage(ex.getErrorCode(), ex.getMessage());
    return new ResponseEntity<>(errorMessage, status);
  }

  @ExceptionHandler(value = {ForbiddenException.class})
  protected ResponseEntity<Object> handleForbiddenException(
      final ForbiddenException ex, final WebRequest request) {

    log.error("{}", StackTraceUtil.getStackTrace(ex));
    final HttpStatus status = HttpStatus.FORBIDDEN;
    final ErrorMessage errorMessage = new ErrorMessage(ex.getErrorCode(), ex.getMessage());
    return new ResponseEntity<>(errorMessage, status);
  }
}
