package com.marklogic.appdeployer.impl;

import com.marklogic.appdeployer.AppConfig;

import java.util.List;

public class AddHostNameTokensDeployerListener extends DeployerListenerSupport {

	@Override
	public void beforeCommandsExecuted(DeploymentContext context) {
		AppConfig appConfig = context.getAppConfig();
		if (appConfig.isAddHostNameTokens()) {
			List<String> hostNames = context.getCommandContext().getHostNames();
			int size = hostNames.size();
			for (int i = 1; i <= size; i++) {
				appConfig.getCustomTokens().put("mlHostName" + i, hostNames.get(i - 1));
			}
		}
	}

}
