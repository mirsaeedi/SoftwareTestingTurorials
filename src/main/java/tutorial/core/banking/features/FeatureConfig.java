package tutorial.core.banking.features;
import java.io.FileNotFoundException;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class FeatureConfig {

private final String filePath="./src/main/java/tutorial/core/banking/features/features.json";
	
	public Feature[] loadFeatures() {
		
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
	
}
