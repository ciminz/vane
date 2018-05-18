package com.vane.endpoint.base;

import java.io.IOException;
import java.net.Socket;

import com.vane.base.LocalBeanFactory;
import com.vane.process.BaseProcess;



public class ProcessThread extends Thread {
	private Socket socket;  
    private String enpointId;  
    
    public ProcessThread(Object object, String enpointId) throws IOException {
    	 this.socket = (Socket)object;  
    	 this.enpointId = enpointId;
         start(); 
    }
    
	@Override
	public void run() {
		BaseProcess process = (BaseProcess)LocalBeanFactory.get("process_" + enpointId);
		process.execute(socket);
	}

}
