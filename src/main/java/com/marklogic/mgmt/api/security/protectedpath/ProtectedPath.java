package com.marklogic.mgmt.api.security.protectedpath;

import com.marklogic.mgmt.api.Resource;
import com.marklogic.mgmt.resource.ResourceManager;
import com.marklogic.mgmt.resource.security.ProtectedPathManager;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "protected-path-properties")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProtectedPath extends Resource {

	@XmlElement(name = "path-id")
	private String pathId;

	@XmlElement(name = "path-expression")
	private String pathExpression;

	@XmlElementWrapper(name = "path-namespaces")
	@XmlElement(name = "path-namespace")
	private List<PathNamespace> pathNamespace;

	@XmlElementWrapper(name = "permissions")
	@XmlElement(name = "permission")
	private List<Permission> permission;

	@XmlElement(name = "path-set")
	private String pathSet;

	public ProtectedPath() {
	}

	@Override
	protected ResourceManager getResourceManager() {
		return new ProtectedPathManager(super.getClient());
	}

	@Override
	protected String getResourceId() {
		return pathExpression;
	}

	public ProtectedPath(String pathExpression) {
		this.pathExpression = pathExpression;
	}

	public String getPathId() {
		return pathId;
	}

	public void setPathId(String pathId) {
		this.pathId = pathId;
	}

	public String getPathExpression() {
		return pathExpression;
	}

	public void setPathExpression(String pathExpression) {
		this.pathExpression = pathExpression;
	}

	public List<PathNamespace> getPathNamespace() {
		return pathNamespace;
	}

	public void setPathNamespace(List<PathNamespace> pathNamespace) {
		this.pathNamespace = pathNamespace;
	}

	public List<Permission> getPermission() {
		return permission;
	}

	public void setPermission(List<Permission> permission) {
		this.permission = permission;
	}

	public String getPathSet() {
		return pathSet;
	}

	public void setPathSet(String pathSet) {
		this.pathSet = pathSet;
	}
}
