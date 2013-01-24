package eu.cloudtm.wpm.logService.remote.listeners;

import java.rmi.RemoteException;

import eu.cloudtm.wpm.connector.WPMConnector;
import eu.cloudtm.wpm.logService.remote.events.PublishViewChangeEvent;
import eu.cloudtm.wpm.logService.remote.events.SubscribeEvent;

public class TestWPMViewChangeRemoteListenerImpl implements
WPMViewChangeRemoteListener{

	private WPMConnector connector;
	
	public TestWPMViewChangeRemoteListenerImpl(WPMConnector connector){
		
		this.connector = connector;
	}
	
	@Override
	public void onViewChange(PublishViewChangeEvent event)
			throws RemoteException {
		
		
		WPMStatisticsRemoteListener listener = new TestWPMStatisticsRemoteListenerImpl();
		
		String[] VMs = event.getCurrentVMs();
				
				
				
		connector.registerStatisticsRemoteListener(new SubscribeEvent(VMs), listener);
		
		
	}

}
