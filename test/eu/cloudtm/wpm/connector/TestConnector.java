package eu.cloudtm.wpm.connector;

import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import eu.cloudtm.wpm.logService.remote.listeners.TestWPMViewChangeRemoteListenerImpl;
import eu.cloudtm.wpm.logService.remote.listeners.WPMViewChangeRemoteListener;

public class TestConnector {
	
	
	
	public static void main(String[] args) throws RemoteException, UnknownHostException, NotBoundException{
		
		WPMConnector connector = new WPMConnector();
		
		WPMViewChangeRemoteListener viewChange = new TestWPMViewChangeRemoteListenerImpl(connector);
		
		connector.registerViewChangeRemoteListener(viewChange);
		
		
		//WPMStatisticsRemoteListener listener = new TestWPMStatisticsRemoteListenerImpl();
		
		//String[] VMs = new String[1];
		
		//VMs[0] = "192.168.0.221";
		
		//connector.registerStatisticsRemoteListener(new SubscribeEvent(VMs), listener);
		
		
		while(true){}
		
	}

}
