package com.training.bundles.registrar.listeners;

import com.training.bundles.registrar.bean.OperationBean;
import com.training.bundles.registrar.service.OperationsStorage;
import org.apache.commons.lang3.StringUtils;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;

import java.util.Dictionary;

public class OperationRegistrar implements BundleListener {
    public static final String META_IS_OPERATIONS = "Operation-Bundle";
    public static final String META_OPERATION_SYMBOL = "Operation-Symbol";
    public static final String META_OPERATION_RANK = "Operation-Rank";

    @Override
    public void bundleChanged(BundleEvent bundleEvent) {
        OperationBean operationBean = createOperationBean(bundleEvent.getBundle());

        if (operationBean != null) {
            try {
                switch (bundleEvent.getType()) {
                    case BundleEvent.STARTED:
                        OperationsStorage.addOperation(operationBean);
                        break;
                    case BundleEvent.STOPPING:
                    case BundleEvent.STOPPED:
                    case BundleEvent.UNINSTALLED:
                    case BundleEvent.STARTING:
                        OperationsStorage.removeOperation(operationBean);
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public static OperationBean createOperationBean(Bundle bundle) {
        OperationBean operationBean = null;

        Dictionary bundleHeaders = bundle.getHeaders();

        boolean isOperation = false;
        if (bundleHeaders.get(META_IS_OPERATIONS) != null) {
            isOperation = "true".equals(bundleHeaders.get(META_IS_OPERATIONS));
        }

        String operationSymbol = StringUtils.EMPTY;
        if (bundleHeaders.get(META_OPERATION_SYMBOL) != null) {
            operationSymbol = (String) bundleHeaders.get(META_OPERATION_SYMBOL);
            operationSymbol = operationSymbol.trim();
        }

        String operationRankStr;
        int operationRank = 0;
        if (bundleHeaders.get(META_OPERATION_RANK) != null) {
            operationRankStr = (String) bundleHeaders.get(META_OPERATION_RANK);
            operationRankStr = operationRankStr.trim();
            operationRank = Integer.parseInt(operationRankStr);
        }


        if (isOperation) {
            operationBean = new OperationBean(operationSymbol, operationRank, bundle);
        }

        return operationBean;
    }
}
