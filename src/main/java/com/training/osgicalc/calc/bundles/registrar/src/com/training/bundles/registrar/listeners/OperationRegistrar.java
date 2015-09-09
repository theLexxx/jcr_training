package com.training.bundles.registrar.listeners;

import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;

public class OperationRegistrar implements BundleListener {

    @Override
    public void bundleChanged(BundleEvent bundleEvent) {
        System.out.println("Q!" + bundleEvent.toString());
        System.out.println("1\t" + bundleEvent.getBundle().getHeaders().toString());
    }
}
