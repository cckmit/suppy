package com.zjjzfy.exchange.exception;

/**
 *
 * @author      jackshenonly
 * @description 类说明,rsa 加解密异常
 * @date        2019-12-15 10:03
 */
public class RsaCheckException extends Exception {
    public RsaCheckException() {
    }

    public RsaCheckException(String message) {
        super(message);
    }

    public RsaCheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public RsaCheckException(Throwable cause) {
        super(cause);
    }
}
