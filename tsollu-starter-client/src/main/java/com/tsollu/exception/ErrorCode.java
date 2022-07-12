package com.tsollu.exception;

/**
 * 错误码接口 - 所有自定义错误码均需要实现该接口
 *
 * @author larry.qi
 * @date 2022-07-02
 */
public interface ErrorCode {

    /**
     * 接口返回码字段，请求成功时返回[00000]，请求失败时返回错误码。
     *
     * @return 错误代码
     */
    String getCode();

    /**
     * 接口返回信息字段，请求成功返回[成功]，请求失败返回错误原因。
     *
     * @return 错误信息
     */
    String getMessage();

}
