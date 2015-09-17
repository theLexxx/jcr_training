package com.training.bundles.minus;

import com.training.bundles.calculate.ifaces.IOperation;

public class Operation<T> implements IOperation<T> {

    public T execute(T[] arguments) throws IllegalArgumentException {
        T result = null;

        if (arguments.length > 0) {
            if (arguments[0] instanceof Integer) {
                result = arguments[0];
                for (int i = 1; i < arguments.length; i++) {
                    result = (T) (Integer) ((Integer) result - (Integer) arguments[i]);
                }
            }
            if (arguments[0].getClass() == Double.class) {
                result = arguments[0];
                for (int i = 1; i < arguments.length; i++) {
                    result = (T) (Double) ((Double) result - (Double) arguments[i]);
                }
            }
            if (result == null) {
                throw new IllegalArgumentException("Parameters can be only Integer or Double");
            }
        }

        return result;
    }
}
