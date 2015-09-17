package com.training.bundles.calculate;

import com.training.bundles.calculate.utils.OperationHelper;
import com.training.bundles.calculate.utils.ReversePolishNotationParser;
import com.training.bundles.registrar.bean.OperationBean;
import com.training.bundles.registrar.service.*;
import org.osgi.framework.Bundle;

import java.util.List;
import java.util.Stack;

public class Calculate {

    public Calculate() {
    }

    public Number execute(String execution) {
        List<String> executionRPN = ReversePolishNotationParser.parseToRPN(execution);
        Stack<Double> stack = new Stack<Double>();

        for (String symbol : executionRPN) {
            if (OperationHelper.isOperand(symbol)) {
                stack.push(Double.parseDouble(symbol));
            } else if (OperationHelper.isOperator(symbol)) {
                stack.push(executeOperation(stack.pop(), stack.pop(), symbol));
            }
        }

        return stack.pop();
    }

    private Double executeOperation(Double operand1, Double operand2, String operator) {
        OperationBean operationBean = OperationsStorage.getOperation(operator);

        if (operationBean == null) {
            //TODO need to trow magic exception
            return null;
        }

        Bundle operationBundle;
        operationBundle = operationBean.getOpeartionBundle();

        return null;
    }

}
