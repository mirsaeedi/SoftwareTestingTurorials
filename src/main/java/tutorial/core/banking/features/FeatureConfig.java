package tutorial.core.banking.features;
import java.io.FileNotFoundException;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class FeatureConfig {

	private Feature[] features;
	private final String filePath="./src/main/java/tutorial/core/banking/features/features.json";
	
	public FeatureConfig() {
		features = loadFeatures();
	}

	private Feature[] loadFeatures() {
		
		Gson gson = new Gson();
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(filePath));
			Feature[] features = gson.fromJson(reader, Feature[].class);
			return features;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean isOn(String featureName) {
		
		for(Feature feature:features) {
			if(feature.name.equals(featureName) && feature.status.equals("On"))
			{
				return true;
			}
		}
		
		return false;
		
	}
	
}
