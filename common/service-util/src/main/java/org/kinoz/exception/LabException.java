package org.kinoz.exception;

import lombok.Data;
import org.kinoz.enums.ResultCodeEnum;

/**
 * @author haogu
 * @date 2023/5/7 13:17
 * @DESCRIPTION this is the customer exception Lab doesn't have some mean
 * this is just exercise
 */
@Data
public class LabException extends RuntimeException {
    private Integer code;

    private String message;

    /**
     * 通过状态码和错误消息创建异常对象
     * @param code
     * @param message
     */
    public LabException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * 接收枚举类型对象
     * @param resultCodeEnum
     */
    public LabException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "LabException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
