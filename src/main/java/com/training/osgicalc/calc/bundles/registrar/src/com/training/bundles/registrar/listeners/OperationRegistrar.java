package com.training.bundles.registrar.listeners;

import com.training.bundles.registrar.bean.OperationBean;
import org.apache.commons.lang3.StringUtils;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;

import java.util.Dictionary;

public class OperationRegistrar implements BundleListener {
    public static final String META_IS_OPERATIONS = "Operation-Bundle";
    public static final String META_OPERATION_SYMBOL = "Operation-Symbol";
    public static final String META_OPERATION_RANK = "Operation-Rank";

    @Override
    public void bundleChanged(BundleEvent bundleEvent) {
        Dictionary bundleHeaders = bundleEvent.getBundle().getHeaders();

        boolean isOperation = false;
        if (bundleHeaders.get(META_IS_OPERATIONS) != null) {
            isOperation = "true".equals(bundleHeaders.get(META_IS_OPERATIONS));
        }

        String operationSymbol = StringUtils.EMPTY;
        if (bundleHeaders.get(META_OPERATION_SYMBOL) != null) {
            operationSymbol = (String) bundleHeaders.get(META_IS_OPERATIONS);
            operationSymbol = operationSymbol.trim();
        }

        String operationRankStr;
        int operationRank = 0;
        if (bundleHeaders.get(META_OPERATION_RANK) != null) {
            operationRankStr = (String) bundleHeaders.get(META_OPERATION_RANK);
            operationRankStr = operationRankStr.trim();
            operationRank = Integer.getInteger(operationRankStr);
        }

        if (isOperation) {
            try {
                OperationBean operationBean = new OperationBean(operationSymbol, operationRank, bundleEvent.getBundle());
                //TODO registrar operation
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }

    }
}
