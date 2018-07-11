package com.marklogic.appdeployer.command.flexrep;

import com.marklogic.appdeployer.AppConfig;
import com.marklogic.appdeployer.ConfigDir;
import com.marklogic.appdeployer.command.AbstractResourceCommand;
import com.marklogic.appdeployer.command.CommandContext;
import com.marklogic.appdeployer.command.SortOrderConstants;
import com.marklogic.mgmt.resource.ResourceManager;
import com.marklogic.mgmt.resource.flexrep.PullsManager;

import java.io.File;

public class DeployPullsCommand extends AbstractResourceCommand {

	ResourceManager pullsManager;

	public DeployPullsCommand() {
		setExecuteSortOrder(SortOrderConstants.DEPLOY_FLEXREP_PULLS);
	}

	@Override
	protected File[] getResourceDirs(CommandContext context) {
		return findResourceDirs(context, configDir -> configDir.getFlexrepPullsDir());
	}

	@Override
	protected ResourceManager getResourceManager(CommandContext context) {
		return pullsManager;
	}

	@Override
	public void execute(CommandContext context) {
		AppConfig appConfig = context.getAppConfig();
		for (ConfigDir configDir : appConfig.getConfigDirs()) {
			deployFlexRepPulls(context, configDir, appConfig.getContentDatabaseName());
			for (File dir : configDir.getDatabaseResourceDirectories()) {
				deployFlexRepPulls(context, new ConfigDir(dir), dir.getName());
			}
		}
	}

	protected void deployFlexRepPulls(CommandContext context, ConfigDir configDir, String databaseIdOrName) {
		pullsManager = new PullsManager(context.getManageClient(), databaseIdOrName);
		processExecuteOnResourceDir(context, configDir.getFlexrepPullsDir());
	}


}
