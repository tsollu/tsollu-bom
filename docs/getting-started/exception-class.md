# 异常类

制定了 Exception 的相关规范，一是为了复用；二是全局异常处理。

| 异常类 | 说明 |
| ---- | ---- | 
| **BaseException** | 异常基类（抽象类），自定义异常继承该类，便于全局异常处理。 | 
| **BusinessException** | 业务处理异常，有明确的业务语义，不需要记录错误（Error） 日志，不需要重试（Retry）。 | 
| **SystemException** | 已知的系统异常，需要记录错误（Error）日志，可以重试（Retry）。 | 
| **ValidationException** | 请求参数校验异常，有明确的错误语义，不需要记录错误（Error）日志，不需要重试（Retry）。 | 
| **AssertUtil** | 异常工具类。 | 
| __Exception__ | 未知的系统异常，需要记录完整的错误（Error Stack）日志，可以重试（Retry）。 | 

