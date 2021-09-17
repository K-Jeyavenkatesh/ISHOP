package payment_gateway;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PaymentProcess {
	
	private double billAmount;
	
	public PaymentProcess(double billAmount) {
		
		this.billAmount = billAmount;
	}
	public boolean creditPayment(String name, long number, int month, int year, int cvv) throws ClassNotFoundException, SQLException {
		
		Connection con = new DatabaseBank().DatabaseBank();
		Statement st = con.createStatement();
		ResultSet r = st.executeQuery("select * from creditcard");
		while(r.next()) {
			if(number == r.getLong(2)) {
				if(r.getString(1).equals(name) && month == r.getInt(3) && year == r.getInt(4) && cvv == r.getInt(5)) {
					double amountFrom = r.getDouble(6);
					if(amountFrom > billAmount) {
						String query = "update creditcard set amount = "+(amountFrom-billAmount)+" where cardNumber = "+number;
						int r1 = st.executeUpdate(query);
						System.out.println("Upadated ->"+r1);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean debitPayment(String name, long number, int month, int year, int cvv) throws ClassNotFoundException, SQLException {
		
		Connection con = new DatabaseBank().DatabaseBank();
		Statement st = con.createStatement();
		ResultSet r = st.executeQuery("select * from debitcard");
		while(r.next()) {
			if(number == r.getLong(2)) {
				if(r.getString(1).equals(name) && month == r.getInt(3) && year == r.getInt(4) && cvv == r.getInt(5)) {
					double amountFrom = r.getDouble(6);
					if(amountFrom > billAmount) {
						String query = "update debitcard set amount = "+(amountFrom-billAmount)+" where cardNumber = "+number;
						int r1 = st.executeUpdate(query);
						System.out.println("Upadated ->"+r1);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean upi(String upiId, String emailId, int pin) throws ClassNotFoundException, SQLException {
		
		Connection con = new DatabaseBank().DatabaseBank();
		Statement st = con.createStatement();
		ResultSet r = st.executeQuery("select * from upi");
		while(r.next()) {
			if(r.getString(1).equals(upiId)) {
				if(r.getString(2).equals(emailId) && pin == r.getInt(3)) {
					double amountFrom = r.getDouble(4);
					if(amountFrom > billAmount) {
						String query = "update upi set amount = "+(amountFrom-billAmount)+" where upiID = "+upiId;
						int r1 = st.executeUpdate(query);
						System.out.println("Upadated ->"+r1);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean netbanking(String bankname, String userId, String password) throws ClassNotFoundException, SQLException {
		
		Connection con = new DatabaseBank().DatabaseBank();
		Statement st = con.createStatement();
		ResultSet r = st.executeQuery("select * from netbanking");
		while(r.next()) {
			if(r.getString(2).equals(userId)) {
				if(r.getString(1).equals(bankname) && r.getString(3).equals(password)) {
					double amountFrom = r.getDouble(4);
					if(amountFrom > billAmount) {
						String query = "update netbanking set amount = "+(amountFrom-billAmount)+" where UserID = "+userId;
						int r1 = st.executeUpdate(query);
						System.out.println("Upadated ->"+r1);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		//System.out.println(new PaymentProcess().netbanking("Indian Bank","jeyavenkateshk@ib","kjvindianbank100"));
	}

}
