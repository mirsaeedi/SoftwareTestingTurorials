import tutorial.web.AccountController;
import tutorial.web.HttpSession;

public class Startup {

	public static void main(String[] args) {
	    
		HttpSession session = getSession();
		AccountController controller = new AccountController(session);
		controller.TransferMoney();
	    
	}

	private static HttpSession getSession() {
		
		HttpSession session = new HttpSession();
		session.IpAddress="201.210.2.8";
		session.RequestedUrl="/bank/account/transfer";
		session.UserLocation="Canada, QC, Montreal";
		session.Username="mtl@test.ca";
		session.SessionId="631276387126387123123";
		
		return session;
		
	}

}
