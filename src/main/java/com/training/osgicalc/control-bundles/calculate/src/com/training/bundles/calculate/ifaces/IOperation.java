package com.training.bundles.calculate.ifaces;

public interface IOperation<T> {
    T execute(T[] arguments) throws IllegalArgumentException;
}
