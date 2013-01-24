package eu.cloudtm.wpm.logService.remote.listeners;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import eu.cloudtm.wpm.logService.remote.events.PublishAttribute;
import eu.cloudtm.wpm.logService.remote.events.PublishMeasurement;
import eu.cloudtm.wpm.logService.remote.events.PublishStatisticsEvent;
import eu.cloudtm.wpm.parser.ResourceType;

public class TestWPMStatisticsRemoteListenerImpl implements
		WPMStatisticsRemoteListener {

	@Override
	public void onNewPerVMStatistics(PublishStatisticsEvent event)
			throws RemoteException {
		
		System.out.println("Called onNewPerVMStatistics");

	}

	@Override
	public void onNewPerSubscriptionStatistics(PublishStatisticsEvent event)
			throws RemoteException {
	
		Set<String> ips = event.getIps();
		
		for(String ip: ips){
			
			System.out.println("Printing Statistics for machine "+ip);
			
			int numResources = event.getNumResources(ResourceType.JMX, ip);
			
			if(numResources > 0){
				for (int i= 0; i< numResources; i++){
					PublishMeasurement pm = event.getPublishMeasurement(ResourceType.JMX, i, ip);
					HashMap<String, PublishAttribute> values = pm.getValues();
					if(values != null && !values.isEmpty()){
						
						Set<Entry<String,PublishAttribute>> entries = values.entrySet();
						
						for(Entry<String,PublishAttribute> entry: entries){
							
							System.out.println(""+entry.getKey()+" - "+entry.getValue().getValue());
							
						}
					}
				}
				
			}
			
		}
		

	}

}
