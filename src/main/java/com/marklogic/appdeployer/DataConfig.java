package com.marklogic.appdeployer;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class DataConfig {

	public final static String DEFAULT_DATA_PATH = "src/main/ml-data";

	private List<String> dataPaths;
	private boolean dataLoadingEnabled = true;
	private String databaseName;
	private Integer batchSize;
	private boolean replaceTokensInData = true;
	private FileFilter fileFilter;
	private boolean logUris = true;

	private String[] collections;

	// Comma-delimited list of role,capability,role,capability
	private String permissions;

	private File projectDir;

	public DataConfig(File projectDir) {
		this.projectDir = projectDir;

		dataPaths = new ArrayList<>();
		String path = projectDir != null ? new File(projectDir, DEFAULT_DATA_PATH).getAbsolutePath() : DEFAULT_DATA_PATH;
		dataPaths.add(path);
	}

	public List<String> getDataPaths() {
		return dataPaths;
	}

	public void setDataPaths(List<String> dataPaths) {
		this.dataPaths = dataPaths;
	}

	public boolean isDataLoadingEnabled() {
		return dataLoadingEnabled;
	}

	public void setDataLoadingEnabled(boolean dataLoadingEnabled) {
		this.dataLoadingEnabled = dataLoadingEnabled;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public Integer getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(Integer batchSize) {
		this.batchSize = batchSize;
	}

	public boolean isReplaceTokensInData() {
		return replaceTokensInData;
	}

	public void setReplaceTokensInData(boolean replaceTokensInData) {
		this.replaceTokensInData = replaceTokensInData;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public FileFilter getFileFilter() {
		return fileFilter;
	}

	public void setFileFilter(FileFilter fileFilter) {
		this.fileFilter = fileFilter;
	}

	public String[] getCollections() {
		return collections;
	}

	public void setCollections(String[] collections) {
		this.collections = collections;
	}

	public boolean isLogUris() {
		return logUris;
	}

	public void setLogUris(boolean logUris) {
		this.logUris = logUris;
	}

	public File getProjectDir() {
		return projectDir;
	}
}