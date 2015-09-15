package com.training.bundles.registrar.service;

import com.training.bundles.registrar.bean.OperationBean;

import java.util.*;

public class OperationsStorage {
    static private Map<String, Set<OperationBean>> storage = new HashMap<String, Set<OperationBean>>();

    private OperationsStorage() {
    }

    static public OperationBean addOperation(OperationBean operationBean) {
        String operationSymbol = operationBean.getOperationSymbol();

        if (storage.containsKey(operationSymbol)) {
            if (!storage.get(operationSymbol).contains(operationBean)) {
                storage.get(operationSymbol).add(operationBean);
            }
        } else {
            Set<OperationBean> operationMap = new HashSet<OperationBean>();
            operationMap.add(operationBean);
            storage.put(operationSymbol, operationMap);
        }

        System.out.println("Operation \"" + operationSymbol + "\" was registered.");

        return operationBean;
    }

    static public OperationBean getOperation(String operationSymbol) {
        OperationBean result = null;

        if (storage.containsKey(operationSymbol) && !storage.get(operationSymbol).isEmpty()) {
            result = storage.get(operationSymbol).iterator().next();
        }

        return result;
    }

    static public boolean removeOperation(OperationBean operationBean) {
        boolean isSuccess = false;
        String operationSymbol = operationBean.getOperationSymbol();

        if (storage.containsKey(operationSymbol)) {
            if (storage.get(operationSymbol).contains(operationBean)) {
                isSuccess = storage.get(operationSymbol).remove(operationBean);

                if (storage.get(operationSymbol).isEmpty()) {
                    storage.remove(operationSymbol);
                }
            }
        }

        if (isSuccess) {
            System.out.println("Operation \"" + operationSymbol + "\" was registered.");
        }

        return isSuccess;
    }

}
