package top.quhailong.operation.entity;

import java.util.Date;

public class ControllerOperationLogDO {
    private Integer id;

    private String operationId;

    private String operationModule;

    private String operationType;

    private String operationDesc;

    private String operationRequestParam;

    private String operationResponseParam;

    private String operationContent;

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

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId == null ? null : operationId.trim();
    }

    public String getOperationModule() {
        return operationModule;
    }

    public void setOperationModule(String operationModule) {
        this.operationModule = operationModule == null ? null : operationModule.trim();
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType == null ? null : operationType.trim();
    }

    public String getOperationDesc() {
        return operationDesc;
    }

    public void setOperationDesc(String operationDesc) {
        this.operationDesc = operationDesc == null ? null : operationDesc.trim();
    }

    public String getOperationRequestParam() {
        return operationRequestParam;
    }

    public void setOperationRequestParam(String operationRequestParam) {
        this.operationRequestParam = operationRequestParam == null ? null : operationRequestParam.trim();
    }

    public String getOperationResponseParam() {
        return operationResponseParam;
    }

    public void setOperationResponseParam(String operationResponseParam) {
        this.operationResponseParam = operationResponseParam == null ? null : operationResponseParam.trim();
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent == null ? null : operationContent.trim();
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