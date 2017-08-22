package org.opensingular.samples.studio.init;

import org.jetbrains.annotations.NotNull;
import org.opensingular.samples.studio.cfg.SampleAppConfig;
import org.opensingular.studio.app.StudioAppConfig;
import org.opensingular.studio.app.init.StudioWebAppInitializer;

public class SampleWebAppInitializer extends StudioWebAppInitializer {
    @NotNull
    @Override
    protected Class<? extends StudioAppConfig> getStudioAppConfig() {
        return SampleAppConfig.class;
    }
}