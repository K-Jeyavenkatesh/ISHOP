import demo.Buyer_Login;
import server.Server;

public class Driver {

	public static void main(String[] args) {
		
		//start of application
		new Buyer_Login();
		Server.start(6050);
	}
}
