package com.example.verma.bootapp.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by SANJIT on 05/11/17.
 */

@Component
@ConfigurationProperties("amazon")
public class AmazonProperties {
    private String associateId;

    public String getAssociateId() {
        return associateId;
    }

    public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }
}
