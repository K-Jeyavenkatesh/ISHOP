package payment_gateway;

import demo.Customer_bean;

public class OTPGenerator {

	public int OTPGenerator(Customer_bean Customer) {
		int min = 100000;
		int max = 999999;
		int otp_final = (int)Math.floor(Math.random()*(max-min+1)+min);
		
		String from = "19euit065@skcet.ac.in";     
		String to = Customer.getEmail();   
		String password = "19euit065@KJV9";	   
		String title = "OTP Verification mail from I-Shop";
		String message = "We have send an OTP for Payment Process."
				+ "Your OTP is valid for one minutes only. OTP is "+otp_final;
		new Gmail().Gmail(from, to, password, title, message);
		return otp_final;
	}

}
