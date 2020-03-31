package me.metafysik.utils.logs.intelligent.entities;

import me.metafysik.utils.logs.intelligent.logs.LogModule;

/**
 * @author metafysik.
 */
public class LogEntry {

    public LogModule getModule() {
        return module;
    }

    public void setModule(LogModule module) {
        this.module = module;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getInputParameters() {
        return inputParameters;
    }

    public void setInputParameters(Object[] inputParameters) {
        this.inputParameters = inputParameters;
    }

    public Object getOutputParameter() {
        return outputParameter;
    }

    public void setOutputParameter(Object outputParameter) {
        this.outputParameter = outputParameter;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public Long getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(Long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getThrowableMessage() {
        return throwableMessage;
    }

    public void setThrowableMessage(String throwableMessage) {
        this.throwableMessage = throwableMessage;
    }

    public String getThrowableStacktrace() {
        return throwableStacktrace;
    }

    public void setThrowableStacktrace(String throwableStacktrace) {
        this.throwableStacktrace = throwableStacktrace;
    }

    private LogModule module;

    private String category;

    private String subCategory;

    private boolean isSuccess;

    private String methodName;

    private Object[] inputParameters;

    private Object outputParameter;

    private long now;

    private Long timeElapsed;

    private String className;

    private String throwableMessage;

    private String throwableStacktrace;
}
