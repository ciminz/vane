package com.vane.endpoint.tcp.listener;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vane.base.LocalBeanFactory;
import com.vane.endpoint.base.ProcessThread;
import com.vane.endpoint.tcp.config.TcpConfig;


public class SocketListener implements ServletContextListener {
	private static Logger logger = LoggerFactory.getLogger(SocketListener.class);

	private List<ListenThread> listenThreadList;
	
	public void contextDestroyed(ServletContextEvent arg0) {
		for(ListenThread listenThread : listenThreadList) {
			if (listenThread != null && listenThread.isInterrupted()) {
				listenThread.interrupt();
			}
		}
		

	}

	public void contextInitialized(ServletContextEvent arg0) {
		listenThreadList = new ArrayList<SocketListener.ListenThread>();
		TcpConfig tcpConfig = (TcpConfig)LocalBeanFactory.get(TcpConfig.class);
		Map<String,Object> serverConfigMap = tcpConfig.getServerConfig();
		Set<String> configSet = serverConfigMap.keySet();
		for(String s : configSet) {
			logger.info("监听端口:" + s);
			ListenThread listenThread = new ListenThread(Integer.valueOf(s));
			listenThread.start();// servlet上下文初始化时启动socket
			listenThreadList.add(listenThread);
		}
			
	}

	/*
	 * 自定义一个Class线程类继承自线程类，重写run()方法，用于从后台获取处理数据
	 * 
	 */
	class ListenThread extends Thread {
		private Logger log = LoggerFactory.getLogger(ListenThread.class);
		private int threadPort;
		public ListenThread(int port) {
			this.threadPort = port;
		}
		
		public void run() {
			
			ServerSocket ss = null;
			try {
				ss = new ServerSocket(threadPort);
				log.info("端口:"  +threadPort+ "线程启动");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(ss == null) {
				throw new RuntimeException("监听失败");
			}
			
			while (!this.isInterrupted()) {// 线程未中断执行循环
				try {
					Socket socket = ss.accept();
					InputStream is = socket.getInputStream();
					try {
						TcpConfig tcpConfig = (TcpConfig)LocalBeanFactory.get(TcpConfig.class);
						Map<String,Object> serverConfigMap = tcpConfig.getServerConfig();
						String enpointId = (String)serverConfigMap.get("" + threadPort);
						ProcessThread processThread = new ProcessThread(socket, enpointId);
						log.info("收到请求信息,enpointId:" + enpointId);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
				}
			}
		}
	}
}
