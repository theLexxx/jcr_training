package com.training.bundles.calculate.utils;

import com.training.bundles.registrar.service.OperationsStorage;
import org.apache.commons.lang3.StringUtils;

public class OperationHelper {
    private OperationHelper() {
    }

    public static boolean isOperand(String symbol) {
        return StringUtils.isNumeric(symbol);
    }

    public static boolean isOperator(String symbol) {
        return ("(".equals(symbol))
                || (")".equals(symbol))
                || OperationsStorage.getOperation(symbol) != null;
    }
}
