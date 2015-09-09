package com.training.bundles.registrar.bean;

import org.apache.commons.lang3.StringUtils;
import org.osgi.framework.Bundle;

public class OperationBean {
    private final String operationSymbol;
    private final int operationRank;
    private final Bundle opeartionBundle;

    public OperationBean(String operationSymbol, int operationRank, Bundle opeartionBundle) throws IllegalArgumentException{

        if (StringUtils.isBlank(operationSymbol)){
            throw new IllegalArgumentException("operationSymbol can not be empty");
        }
        if (operationRank <= 0) {
            throw new IllegalArgumentException("operationRank can be more that 0");
        }
        if (opeartionBundle == null) {
            throw new IllegalArgumentException("operationRank can not \"null\"");
        }

        this.operationSymbol = operationSymbol.trim();
        this.operationRank = operationRank;
        this.opeartionBundle = opeartionBundle;
    }

    public String getOperationSymbol() {
        return operationSymbol;
    }

    public int getOperationRank() {
        return operationRank;
    }

    public Bundle getOpeartionBundle() {
        return opeartionBundle;
    }
}
