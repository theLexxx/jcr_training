package com.training.bundles.registrar.bean;

import org.apache.commons.lang3.StringUtils;
import org.osgi.framework.Bundle;

import java.util.Comparator;

public class OperationBean implements Comparator<OperationBean> {
    private final String operationSymbol;
    private final String operationClassPath;

    private final int operationRank;
    private final Bundle operationBundle;

    public OperationBean(String operationSymbol,
                         int operationRank,
                         String operationClassPath,
                         Bundle operationBundle) throws IllegalArgumentException {

        if (StringUtils.isBlank(operationSymbol)) {
            throw new IllegalArgumentException("operationSymbol can not be empty");
        }
        if (operationRank <= 0) {
            throw new IllegalArgumentException("operationRank can be more that 0");
        }
        if (StringUtils.isBlank(operationClassPath)) {
            throw new IllegalArgumentException("operationClassPath can not be \"null\"");
        }

        if (operationBundle == null) {
            throw new IllegalArgumentException("operationRank can not be \"null\"");
        }

        this.operationClassPath = operationClassPath;
        this.operationSymbol = operationSymbol.trim();
        this.operationRank = operationRank;
        this.operationBundle = operationBundle;
    }

    public String getOperationSymbol() {
        return operationSymbol;
    }

    public int getOperationRank() {
        return operationRank;
    }

    public Bundle getOperationBundle() {
        return operationBundle;
    }

    public String getOperationClassPath() {
        return operationClassPath;
    }

    @Override
    public int compare(OperationBean o1, OperationBean o2) {
        return ((Long) (o1.getOperationBundle().getBundleId())).compareTo(o2.getOperationBundle().getBundleId());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OperationBean) {
            Long objBundleId = ((OperationBean) obj).getOperationBundle().getBundleId();
            return objBundleId.equals(operationBundle.getBundleId());
        } else {
            return super.equals(obj);
        }
    }

    @Override
    public int hashCode() {
        return ((Long) (this.operationBundle.getBundleId())).hashCode();
    }
}
