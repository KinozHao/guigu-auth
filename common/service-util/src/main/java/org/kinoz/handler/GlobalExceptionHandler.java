package org.kinoz.handler;

import org.kinoz.exception.LabException;
import org.kinoz.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author haogu
 * @date 2023/5/7 13:10
 * @DESCRIPTION
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail();
    }

    //we make a mistake with int 9/0 it should have the ArithmeticException We changed the message with our onw
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        e.printStackTrace();
        return Result.fail().message("这是一个经典算数错误,请结合异常信息去问题");
    }

    //This is our exception
    @ExceptionHandler(LabException.class)
    @ResponseBody
    public Result error(LabException e){
        e.printStackTrace();
        return Result.fail().message(e.getMessage()).code(e.getCode());
    }
}
