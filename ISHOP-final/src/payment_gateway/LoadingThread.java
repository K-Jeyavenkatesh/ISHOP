package payment_gateway;

import java.awt.Color;

import javax.swing.JOptionPane;

import demo.Customer_bean;
import demo.Product_bean;

public class LoadingThread extends Thread{

	Loading l;
	private double billAmount;
	private ItemBean[] item;
	private boolean paid;
	private Product_bean Product;
	private Customer_bean Customer;
	
	public LoadingThread(Loading l, double billAmount, ItemBean[] item, boolean paid,Product_bean Product,Customer_bean Customer) {
		this.l = l;
		this.billAmount = billAmount;
		this.item = item;
		this.paid = paid;
		this.Product = Product;
		this.Customer = Customer;
		
	}
	
	public void run() {
		
		
		while(l.i<=5000){    
			 l.bar.setValue(l.i);    
			 l.i=l.i+50;
			 if(l.i == 4000) {
				 if(!paid) {
					 l.bar.setForeground(Color.RED);
					 break;
				 }    
			}
			 try{
				  Thread.sleep(50);
			 } catch (Exception e) {}
		}
		if(paid) {
			JOptionPane.showMessageDialog(l.frame, "Successfully Paid", "Process Completed", JOptionPane.PLAIN_MESSAGE);
			try {
				new FinalPage(billAmount, this.item);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(l.frame, "Payment Failed", "Process Failed", JOptionPane.ERROR_MESSAGE);
			int x1 = JOptionPane.showConfirmDialog(l.frame, "Due you want to Continue Payment Again", "Payment Failed", JOptionPane.OK_CANCEL_OPTION);
			if(x1 == 0) {
				new Page1(billAmount, item, true, Product, Customer);
			} else {
				// **************** TO HOME PAGE OR PRODUCTPAGE
			}
		}
		l.frame.dispose();
	}
	
	public int returning() {
		return l.i;
	}
}
