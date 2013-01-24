package eu.cloudtm.wpm.connector;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import eu.cloudtm.wpm.logService.remote.events.SubscribeEvent;
import eu.cloudtm.wpm.logService.remote.listeners.WPMStatisticsRemoteListener;
import eu.cloudtm.wpm.logService.remote.listeners.WPMViewChangeRemoteListener;
import eu.cloudtm.wpm.logService.remote.observables.WPMObservable;

public class WPMConnector {
	
	private static final int PORT = 1099;
	
	private WPMObservable observable;
	
	public WPMConnector() throws RemoteException, UnknownHostException, NotBoundException{
		
		Registry registry;
		
		
		registry = LocateRegistry.getRegistry(InetAddress.getLocalHost().getHostName(),PORT);
		
		this.observable=(WPMObservable) registry.lookup("WPMObservable");
		
		
	}
	
	
	public void registerStatisticsRemoteListener(SubscribeEvent event, WPMStatisticsRemoteListener listener) throws RemoteException{
		
		WPMStatisticsRemoteListener stub_listener = (WPMStatisticsRemoteListener) UnicastRemoteObject.exportObject(listener, 0);
		
		this.observable.registerWPMStatisticsRemoteListener(event, listener);
		
	}
	
	public void registerViewChangeRemoteListener(WPMViewChangeRemoteListener listener) throws RemoteException{
		
		WPMViewChangeRemoteListener stub_listener = (WPMViewChangeRemoteListener) UnicastRemoteObject.exportObject(listener, 0);
		
		this.observable.registerWPMViewChangeRemoteListener(listener);
		
	}

}
