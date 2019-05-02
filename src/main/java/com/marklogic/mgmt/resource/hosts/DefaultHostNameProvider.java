package com.marklogic.mgmt.resource.hosts;

import com.marklogic.mgmt.ManageClient;
import com.marklogic.mgmt.resource.groups.GroupManager;

import java.util.List;

public class DefaultHostNameProvider implements HostNameProvider {

	private ManageClient manageClient;
	private List<String> hostNames;

	public DefaultHostNameProvider(ManageClient manageClient) {
		this.manageClient = manageClient;
	}

	public DefaultHostNameProvider(ManageClient manageClient, List<String> hostNames) {
		this.manageClient = manageClient;
		this.hostNames = hostNames;
	}

	@Override
	public List<String> getHostNames() {
		if (this.hostNames != null) {
			return this.hostNames;
		}
		return new HostManager(manageClient).getHostNames();
	}

	@Override
	public List<String> getGroupHostNames(String groupName) {
		return new GroupManager(manageClient).getHostNames(groupName);
	}
}
