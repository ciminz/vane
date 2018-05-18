package com.vane.endpoint.tcp.config;

import java.util.HashMap;

public class TcpConfig {
	public HashMap<String, Object> serverConfig;

	public HashMap<String, Object> getServerConfig() {
		return serverConfig;
	}

	public void setServerConfig(HashMap<String, Object> serverConfig) {
		this.serverConfig = serverConfig;
	}
}
