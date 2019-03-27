package tutorial.core.banking.features;

import java.util.ArrayList;

public class FeatureAwareFactory {

	private FeatureConfig featureConfig;
	
	public FeatureAwareFactory() {
		
		featureConfig = new FeatureConfig();
		
	}
	
	public ArrayList<BaseMessagingFeature> getMessagingFeature() {
		
		ArrayList<BaseMessagingFeature> list= new ArrayList<BaseMessagingFeature>();
		
		if(featureConfig.isOn("SendingEmail")) {
			list.add(new EmailSendingFeature());
		}
		if(featureConfig.isOn("SendingText")) {
			list.add( new TextSendingFeature());
		}
		
		if(list.size()==0) {
			list.add(new EmptySendingFeature());
		}
		
		return list;
	}
}
