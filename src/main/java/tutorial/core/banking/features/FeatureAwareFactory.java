package tutorial.core.banking.features;

public class FeatureAwareFactory {

	public BaseMessagingFeature getMessagingFeature(Boolean isEmailEnabled, Boolean isTextEnabled) {
		
		if(isEmailEnabled) {
			return new EmailSendingFeature();
		}
		else if(isTextEnabled) {
			return  new TextSendingFeature();
		}
		
		return new EmptySendingFeature();
	}
}
