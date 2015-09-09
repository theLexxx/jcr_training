package com.training.bundles.registrar;

import com.training.bundles.registrar.listeners.OperationRegistrar;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {
    private OperationRegistrar operationRegistrar;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Starting to listen and register \"Operation\" bundles");

        operationRegistrar = new OperationRegistrar();
        bundleContext.addServiceListener(operationRegistrar);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        bundleContext.addServiceListener(operationRegistrar);
        System.out.println("Stopped listening and registering \"Operation\" bundles");
    }
}
