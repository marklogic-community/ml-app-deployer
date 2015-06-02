package com.marklogic.appdeployer.app;

import java.io.File;
import java.io.IOException;

import org.springframework.util.FileCopyUtils;

import com.marklogic.appdeployer.AppConfig;
import com.marklogic.clientutil.LoggingObject;

/**
 * Abstract base class that provides some convenience methods for implementing a plugin. Requires that the Spring
 * Ordered interface be implemented so that the implementor takes into account when this particular plugin should be
 * executed relative to other plugins.
 */
public abstract class AbstractPlugin extends LoggingObject implements AppPlugin {

    /**
     * By default, assumes that the sort order on delete should be the same as on create. Subclasses can override this
     * to provide an alternate approach.
     */
    @Override
    public Integer getSortOrderOnDelete() {
        return getSortOrderOnCreate();
    }

    protected String format(String s, Object... args) {
        return String.format(s, args);
    }

    protected String copyFileToString(File f) {
        try {
            return new String(FileCopyUtils.copyToByteArray(f));
        } catch (IOException ie) {
            throw new RuntimeException("Unable to copy file to string from path: " + f.getAbsolutePath() + "; cause: "
                    + ie.getMessage(), ie);
        }
    }

    /**
     * TODO Would be nice to extract this into a separate class - e.g. TokenReplacer - so that it's easier to customize
     * the tokens that are replaced.
     */
    protected String replaceConfigTokens(String payload, AppConfig appConfig, boolean isTestResource) {
        payload = payload.replace("%%NAME%%",
                isTestResource ? appConfig.getTestRestServerName() : appConfig.getRestServerName());
        payload = payload.replace("%%GROUP%%", appConfig.getGroupName());
        payload = payload.replace("%%DATABASE%%",
                isTestResource ? appConfig.getTestContentDatabaseName() : appConfig.getContentDatabaseName());
        payload = payload.replace("%%MODULES-DATABASE%%", appConfig.getModulesDatabaseName());
        payload = payload.replace("%%TRIGGERS_DATABASE%%", appConfig.getTriggersDatabaseName());
        payload = payload.replace("%%PORT%%", isTestResource ? appConfig.getTestRestPort().toString() : appConfig
                .getRestPort().toString());
        return payload;
    }

}