package com.training.bundles.registrar;

import com.training.bundles.registrar.listeners.OperationRegistrar;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleListener;

public class Activator implements BundleActivator {
    private BundleListener operationRegistrar;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Starting to listen and register \"Operation\" bundles");

        operationRegistrar = new OperationRegistrar();
        bundleContext.addBundleListener(operationRegistrar);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        bundleContext.removeBundleListener(operationRegistrar);
        System.out.println("Stopped listening and registering \"Operation\" bundles");
    }
}
