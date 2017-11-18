package com.example.verma.bootapp.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SANJIT on 15/11/17.
 */

@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM, property = "error", visible = true)
@JsonTypeIdResolver(LowerCaseClassNameResolver.class)
public class ApiError implements Serializable{

    private String errorMessage;
    private HttpStatus status;
    private List<ApiSubError> subErrorList;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-YYYY hh:mm:ss")
    private LocalDateTime localDateTime;
    private String debugMessage;

    public ApiError() {
        this.localDateTime = LocalDateTime.now();
    }

    public ApiError(String errorMessage, HttpStatus status) {
        this();
        this.errorMessage = errorMessage;
        this.status = status;
    }

    public ApiError(String errorMessage, HttpStatus status, Throwable throwable) {
        this();
        this.errorMessage = errorMessage;
        this.status = status;
        this.debugMessage = throwable.getLocalizedMessage();
    }

    void addValidationErrors(List<FieldError> fieldErrors){
        fieldErrors.forEach(this::addValidationError);
    }

    private void addValidationError(FieldError fieldError){
        ApiSubError subError = new ApiSubError();
        subError.setField(fieldError.getField());
        subError.setObject(fieldError.getObjectName());
        subError.setRejectedValue(fieldError.getRejectedValue());
        this.getSubErrorList().add(subError);
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<ApiSubError> getSubErrorList() {
        if(this.subErrorList==null)
            this.subErrorList = new ArrayList<>();

        return subErrorList;
    }
}


class LowerCaseClassNameResolver extends TypeIdResolverBase {

    @Override
    public String idFromValue(Object value) {
        return value.getClass().getSimpleName().toLowerCase();
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        return idFromValue(value);
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }
}
