package com.kaddo.exception;

import org.hibernate.validator.HibernateValidator;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * 异常工具类 - 支持参数校验异常（ValidationException）、业务处理异常（BusinessException）和已知的系统异常（SystemException）。
 *
 * @author larry.qi
 * @date 2022-07-02
 */
public final class AssertUtil {

    /**
     * 业务异常
     *
     * @param errorCode 错误码
     * @return BusinessException 业务异常
     */
    public static BusinessException business(@NonNull ErrorCode errorCode) {
        return new BusinessException(errorCode);
    }

    /**
     * 业务异常
     *
     * @param errorCode 错误码
     * @param reason    错误原因
     * @return BusinessException 业务异常
     */
    public static BusinessException business(@NonNull ErrorCode errorCode, String reason) {
        return new BusinessException(errorCode, reason);
    }

    /**
     * 业务异常
     *
     * @param errorCode 错误码
     * @param cause     异常信息
     * @return BusinessException 业务异常
     */
    public static BusinessException business(@NonNull ErrorCode errorCode, Throwable cause) {
        return new BusinessException(errorCode, cause);
    }

    /**
     * 业务异常
     *
     * @param errorCode 错误码
     * @param reason    错误原因
     * @param cause     异常信息
     * @return BusinessException 业务异常
     */
    public static BusinessException business(@NonNull ErrorCode errorCode, String reason, Throwable cause) {
        return new BusinessException(errorCode, reason, cause);
    }

    /**
     * 已知的系统异常
     *
     * @param errorCode 错误码
     * @return SystemException 已知的系统异常
     */
    public static SystemException system(@NonNull ErrorCode errorCode) {
        return new SystemException(errorCode);
    }

    /**
     * 已知的系统异常
     *
     * @param errorCode 错误码
     * @param reason    错误原因
     * @return SystemException 已知的系统异常
     */
    public static SystemException system(@NonNull ErrorCode errorCode, String reason) {
        return new SystemException(errorCode, reason);
    }

    /**
     * 已知的系统异常
     *
     * @param errorCode 错误码
     * @param cause     异常信息
     * @return SystemException 已知的系统异常
     */
    public static SystemException system(@NonNull ErrorCode errorCode, Throwable cause) {
        return new SystemException(errorCode, cause);
    }

    /**
     * 已知的系统异常
     *
     * @param errorCode 错误码
     * @param reason    错误原因
     * @param cause     异常信息
     * @return SystemException 已知的系统异常
     */
    public static SystemException system(@NonNull ErrorCode errorCode, String reason, Throwable cause) {
        return new SystemException(errorCode, reason, cause);
    }

    /**
     * 请求参数校验异常
     *
     * @return ValidationException 请求参数校验异常
     */
    public static ValidationException validate() {
        return new ValidationException();
    }

    /**
     * 请求参数校验异常
     *
     * @param reason 错误原因
     * @return ValidationException 请求参数校验异常
     */
    public static ValidationException validate(String reason) {
        return new ValidationException(reason);
    }

    /**
     * 请求参数校验异常
     *
     * @param cause 异常信息
     * @return ValidationException 请求参数校验异常
     */
    public static ValidationException validate(Throwable cause) {
        return new ValidationException(cause);
    }

    /**
     * 请求参数校验异常
     *
     * @param reason 错误原因
     * @param cause  异常信息
     * @return ValidationException 请求参数校验异常
     */
    public static ValidationException validate(String reason, Throwable cause) {
        return new ValidationException(reason, cause);
    }

    /**
     * 基于注解的对象校验，抛出业务异常
     *
     * @param errorCode 错误码
     * @param object    校验对象
     * @param groups    分组校验
     */
    public static void validateObject(@NonNull ErrorCode errorCode, @Nullable Object object, Class<?>... groups) {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure()
            .failFast(true).buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> sets = validator.validate(object, groups);
        for (ConstraintViolation<Object> o : sets) {
            AssertUtil.business(errorCode, o.getMessage()).assertThrow();
        }
    }

    /**
     * 基于注解的对象校验，抛出业务异常
     *
     * @param errorCode 错误码
     * @param object    校验对象
     */
    public static void validateObject(@NonNull ErrorCode errorCode, @Nullable Object object) {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure()
            .failFast(true).buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> sets = validator.validate(object);
        for (ConstraintViolation<Object> o : sets) {
            AssertUtil.business(errorCode, o.getMessage()).assertThrow();
        }
    }

    /**
     * 基于注解的对象校验，抛出请求参数异常
     *
     * @param object 校验对象
     * @param groups 分组校验
     */
    public static void validateObject(@Nullable Object object, Class<?>... groups) {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure()
            .failFast(true).buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> sets = validator.validate(object, groups);
        for (ConstraintViolation<Object> o : sets) {
            AssertUtil.validate(o.getMessage()).assertThrow();
        }
    }

    /**
     * 基于注解的对象校验，抛出请求参数异常
     *
     * @param object 校验对象
     */
    public static void validateObject(@Nullable Object object) {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure()
            .failFast(true).buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> sets = validator.validate(object);
        for (ConstraintViolation<Object> o : sets) {
            AssertUtil.validate(o.getMessage()).assertThrow();
        }
    }

}
