package tutorial.core.banking.infrastructure;

import java.util.Calendar;

public class TimeManager {
	
	public int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

}
