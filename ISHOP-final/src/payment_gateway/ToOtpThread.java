package payment_gateway;

import demo.Customer_bean;
import demo.Product_bean;

public class ToOtpThread extends Thread{

	private double billAmount;
	private ItemBean[] item;
	private boolean paid;
	private Product_bean Product;
	private Customer_bean Customer;
	
	public ToOtpThread(double billAmount, ItemBean[] item, boolean paid,Product_bean Product,Customer_bean Customer) {
		this.billAmount = billAmount;
		this.item = item;
		this.paid = paid;
		this.Product = Product;
		this.Customer = Customer;
	}
	public void run() {
		new OTPverify(billAmount, item, paid, Product, Customer);
	}
}
