package com.training.bundles.calculate.utils;

import com.training.bundles.registrar.bean.OperationBean;
import com.training.bundles.registrar.service.OperationsStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReversePolishNotationParser {
    private ReversePolishNotationParser(){
    }

    /**
     * This function parse from normal notation to reverse polish notation
     *
     * @param execution - normal notation
     * @return - list with reverse polish notation
     */
    public static List<String> parseToRPN(String execution) {
        List<String> result = new ArrayList<String>();
        Stack<String> stack = new Stack<String>();

        String[] temp = execution.split(" ");

        for (String symbol : temp) {
            if (OperationHelper.isOperand(symbol)) {
                result.add(symbol);
            } else if (OperationHelper.isOperator(symbol)) {
                if (")".equals(symbol)) {
                    while (stack.peek().equals("(")) {
                        result.add(stack.pop());
                    }
                    stack.pop();
                }
                if (compareOperationPriority(symbol, stack.peek()) < 0) {
                    while (!stack.isEmpty()) {
                        result.add(stack.pop());
                    }
                } else {
                    stack.push(symbol);
                }
            }
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    private static int compareOperationPriority(String operator1, String operator2) {
        return ((Integer) getOperationPriority(operator1)).compareTo(getOperationPriority(operator2));
    }

    private static int getOperationPriority(String operator) {
        int priority  = -1;

        OperationBean operationBean = OperationsStorage.getOperation(operator);
        if (operationBean != null) {
            priority = operationBean.getOperationRank();
        }

        return priority;
    }
}
