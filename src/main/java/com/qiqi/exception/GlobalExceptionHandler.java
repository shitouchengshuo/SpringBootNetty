package com.qiqi.exception;

import com.qiqi.utils.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局异常捕获处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 登录未授权
     */
    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseBody
    //@ResponseStatus(value = HttpStatus.UNAUTHORIZED)//可以将某种异常映射为HTTP状态码，未授权
    public String authenticationExceptionHandler(AuthenticationException e){
        logger.error("登录未授权:【"+e.getMessage()+"】");// 记录简单的出错信息
        return ExceptionUtil.resultOf(ResultStatusCode.AuthorizationCodeError);
    }

    /**
     * token无效或者过期
     */
    @ExceptionHandler(TokenInvalidOrOverdueException.class)
    @ResponseBody
    public String tokenInvalidOrOverdueExceptionHandler(TokenInvalidOrOverdueException e) {
        logger.error("token无效或者过期:【"+e.getMessage()+"】");
        return ExceptionUtil.resultOf(ResultStatusCode.TokenInvalidOrOverdueException);
    }

    /**
     * 签名错误
     */
    @ExceptionHandler(WrongSignatureException.class)
    @ResponseBody
    public String wrongSignatureExceptionHandler(WrongSignatureException e) {
        logger.error("签名错误:【"+e.getMessage()+"】");
        return ExceptionUtil.resultOf(ResultStatusCode.WrongSignatureException);
    }

    /**
     * http请求的方法不正确
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public String httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        logger.error("http请求的方法不正确:【"+e.getMessage()+"】");
        return ExceptionUtil.resultOf(ResultStatusCode.RequestMethodNotAllowed);
    }

    /**
     * 请求参数不全
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public String missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        logger.error("请求参数不全:【"+e.getMessage()+"】");
        return ExceptionUtil.resultOf(ResultStatusCode.MissingServletRequestParameter);
    }

    /**
     * 请求参数类型不正确
     */
    @ExceptionHandler(TypeMismatchException.class)
    @ResponseBody
    public String typeMismatchExceptionHandler(TypeMismatchException e) {
        logger.error("请求参数类型不正确:【"+e.getMessage()+"】");
        return ExceptionUtil.resultOf(ResultStatusCode.TypeMismatchException);
    }

    /**
     * 数据格式不正确
     */
    @ExceptionHandler(DataFormatException.class)
    @ResponseBody
    public String dataFormatExceptionHandler(DataFormatException e) {
        logger.error("数据格式不正确:【"+e.getMessage()+"】");
        return ExceptionUtil.resultOf(ResultStatusCode.DataFormatException);
    }

    /**
     * 用户没找到
     */
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public String userNotFoundExceptionHandler(UserNotFoundException e) {
        logger.error("用户没找到:【"+e.getMessage()+"】");
        return ExceptionUtil.resultOf(ResultStatusCode.UserNotExist);
    }

    /**
     * 数据不存在
     */
    @ExceptionHandler(DataNotExistException.class)
    @ResponseBody
    public String dataNotExistExceptionHandler(DataNotExistException e) {
        logger.error("数据不存在:【"+e.getMessage()+"】");
        return ExceptionUtil.resultOf(ResultStatusCode.DataNotExistException);
    }

    /**
     * 非法输入
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public String illegalArgumentExceptionHandler(IllegalArgumentException e) {
        logger.error("非法输入:【"+e.getMessage()+"】");
        return ExceptionUtil.resultOf(ResultStatusCode.IllegalArgumentException);
    }

    /**
     * 时间格式异常
     */
    @ExceptionHandler(TimeFormatException.class)
    @ResponseBody
    public String timeFormatExceptionHandler(TimeFormatException e) {
        logger.error("时间格式异常:【"+e.getMessage()+"】");
        return ExceptionUtil.resultOf(ResultStatusCode.TimeFormatException);
    }

    /**
     * 图片格式异常
     */
    @ExceptionHandler(PictureFormatException.class)
    @ResponseBody
    public String pictureFormatExceptionHandler(PictureFormatException e) {
        logger.error("图片格式异常:【"+e.getMessage()+"】");
        return ExceptionUtil.resultOf(ResultStatusCode.PictureFormatException);
    }

    @ExceptionHandler  //处理其他异常
    @ResponseBody
    public String allExceptionHandler(Exception e){
        logger.error("具体错误信息:【"+ExceptionUtil.getErrorMessage(e)+"】"); //会记录出错的代码行等具体信息
        int count = 0; //只打印15行的错误堆栈
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            logger.error(stackTraceElement.toString());
            if(count++ > 13) break;
        }
        return ExceptionUtil.resultOf(ResultStatusCode.SystemException);
    }

}
