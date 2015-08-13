package com.rjrudin.marklogic.appdeployer.command.databases;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.rjrudin.marklogic.appdeployer.AppConfig;
import com.rjrudin.marklogic.appdeployer.command.AbstractCommand;
import com.rjrudin.marklogic.appdeployer.command.CommandContext;
import com.rjrudin.marklogic.appdeployer.command.SortOrderConstants;
import com.rjrudin.marklogic.mgmt.databases.DatabaseManager;
import com.rjrudin.marklogic.rest.util.JsonNodeUtil;

public class UpdateContentDatabasesCommand extends AbstractCommand {

    public UpdateContentDatabasesCommand() {
        setExecuteSortOrder(SortOrderConstants.CREATE_CONTENT_DATABASES);
    }

    @Override
    public void execute(CommandContext context) {
        AppConfig appConfig = context.getAppConfig();

        List<File> files = appConfig.getConfigDir().getContentDatabaseFiles();
        logger.info("Merging JSON files at locations: " + files);
        JsonNode node = JsonNodeUtil.mergeJsonFiles(files);

        if (node == null) {
            logger.info(format("No content database files found in directory %s, so no updating content databases",
                    appConfig.getConfigDir().getDatabasesDir().getAbsolutePath()));
            return;
        }

        String payload = node.toString();
        String json = tokenReplacer.replaceTokens(payload, appConfig, false);

        DatabaseManager dbMgr = new DatabaseManager(context.getManageClient());
        dbMgr.save(json);

        if (appConfig.isTestPortSet()) {
            json = tokenReplacer.replaceTokens(payload, appConfig, true);
            dbMgr.save(json);
        }
    }
}
