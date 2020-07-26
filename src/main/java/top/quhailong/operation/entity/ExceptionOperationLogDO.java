package top.quhailong.operation.entity;

import java.util.Date;

public class ExceptionOperationLogDO {
    private Integer id;

    private String exceptionId;

    private String exceptionName;

    private String exceptionMessage;

    private String exceptionRequestParam;

    private String operationUri;

    private String operationMethod;

    private String operationIp;

    private Date operationCreateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(String exceptionId) {
        this.exceptionId = exceptionId == null ? null : exceptionId.trim();
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName == null ? null : exceptionName.trim();
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage == null ? null : exceptionMessage.trim();
    }

    public String getExceptionRequestParam() {
        return exceptionRequestParam;
    }

    public void setExceptionRequestParam(String exceptionRequestParam) {
        this.exceptionRequestParam = exceptionRequestParam == null ? null : exceptionRequestParam.trim();
    }

    public String getOperationUri() {
        return operationUri;
    }

    public void setOperationUri(String operationUri) {
        this.operationUri = operationUri == null ? null : operationUri.trim();
    }

    public String getOperationMethod() {
        return operationMethod;
    }

    public void setOperationMethod(String operationMethod) {
        this.operationMethod = operationMethod == null ? null : operationMethod.trim();
    }

    public String getOperationIp() {
        return operationIp;
    }

    public void setOperationIp(String operationIp) {
        this.operationIp = operationIp == null ? null : operationIp.trim();
    }

    public Date getOperationCreateTime() {
        return operationCreateTime;
    }

    public void setOperationCreateTime(Date operationCreateTime) {
        this.operationCreateTime = operationCreateTime;
    }
}