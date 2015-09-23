package com.training.bundles.registrar.service;

import com.training.bundles.registrar.bean.OperationBean;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

import java.util.*;

@Component (immediate = true)
@Service
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

        System.out.println("STORAGE: Operation \"" + operationSymbol + "\" was registered.");

        return operationBean;
    }

    /**
     *
     * @param operationSymbol;
     * @return operation bean or null (if operation not found)
     */
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
            System.out.println("STORAGE: Operation \"" + operationSymbol + "\" was unregistered.");
        } else {
            System.out.println("STORAGE WARNING: Operation \"" + operationSymbol + "\" was NOT unregistered.");
        }

        return isSuccess;
    }

}
