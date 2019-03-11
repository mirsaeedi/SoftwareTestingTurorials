package tutorial.core.banking.data;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import tutorial.core.banking.models.User;

/*
 *  This is an external Dependency
 */
public class UserRepository implements IRepository<User>{

	private final String filePath="./src/main/java/tutorial/core/banking/infrastructure/users.json";
	
	public User[] getAll() {
		
		
		Gson gson = new Gson();
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(filePath));
			User[] users = gson.fromJson(reader, User[].class); // contains the whole reviews list
			return users;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	

}
