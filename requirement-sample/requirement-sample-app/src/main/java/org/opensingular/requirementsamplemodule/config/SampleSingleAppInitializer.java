package org.opensingular.requirementsamplemodule.config;

public class SampleSingleAppInitializer implements SingleAppInitializer {
    @Override
    public String moduleCod() {
        return "REQUIREMENTSAMPLE";
    }

    @Override
    public String[] springPackagesToScan() {
        return new String[]{"org.opensingular"};
    }
}