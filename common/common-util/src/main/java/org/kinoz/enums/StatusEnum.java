package org.kinoz.enums;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 *
 */
@Getter
public enum StatusEnum {

    AUTH_NORMAL("权限正常"),
    AUTH_ERROR("权限停用"),
    ;

    private String message;

    private StatusEnum(String message) {
        this.message = message;
    }
}