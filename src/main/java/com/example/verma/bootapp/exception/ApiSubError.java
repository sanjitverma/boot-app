package com.example.verma.bootapp.exception;

/**
 * Created by SANJIT on 15/11/17.
 */

public class ApiSubError {
    private String object;
    private String field;
    private Object rejectedValue;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }
}
