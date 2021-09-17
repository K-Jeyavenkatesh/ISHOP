package payment_gateway;

import java.awt.*;
import javax.swing.*;

import demo.Customer_bean;
import demo.Product_bean;


public class Loading {
	
	JFrame frame;
	JPanel main_panel;
	JLabel wait, line_1, line_2, line_3;
	JProgressBar bar;
	int i = 0;
	private double billAmount;
	private ItemBean[] item;
	private boolean paid;
	private Product_bean Product;
	private Customer_bean Customer;
	
	public Loading(double billAmount, ItemBean[] item, boolean paid, Product_bean Product,Customer_bean Customer) {
		
		this.item = item;
		this.paid = paid;
		this.Product = Product;
		this.Customer = Customer;
		frame = new JFrame("IShop");
		frame.setBounds(500, 50, 800, 800);
		frame.getContentPane().setBackground(Color.getHSBColor(255, 216, 17));
		frame.setVisible(true);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		main_panel = new JPanel();
		main_panel.setBounds(50, 50, 680, 650);
		main_panel.setLayout(null);
		main_panel.setBackground(Color.WHITE);
		frame.add(main_panel);
		
		wait = new JLabel("PLEASE WAIT...");
		wait.setBounds(250, 30, 250, 100);
		wait.setFont(new Font("Helvetica", Font.BOLD, 20));
		main_panel.add(wait);
		
		line_1 = new JLabel("Your Payment/Order process is Processing");
		line_1.setBounds(125, 75, 500, 100);
		line_1.setFont(new Font("Helvetica", Font.BOLD, 20));
		main_panel.add(line_1);
		
		line_2 = new JLabel("Don\'t close the window till process yet to complete");
		line_2.setBounds(100, 130, 550, 100);
		line_2.setFont(new Font("Helvetica", Font.BOLD, 20));
		main_panel.add(line_2);
		
		line_3 = new JLabel("Processing...");
		line_3.setBounds(270, 325, 550, 100);
		line_3.setFont(new Font("Helvetica", Font.BOLD, 20));
		main_panel.add(line_3);
		
		bar = new JProgressBar(0,5000);
		bar.setBounds(85, 400, 500, 50);
		bar.setFont(new Font("Helvetica", Font.BOLD, 20));
		bar.setBackground(Color.BLACK);
		bar.setForeground(Color.GREEN);
		bar.setValue(0);
		bar.setStringPainted(true);
		main_panel.add(bar);
		
		LoadingThread l1 = new LoadingThread(this,billAmount, item, paid, Product, Customer);
		l1.start();
		
		/*System.out.println(l1.returning());
		if(i == 5050) {
			
			JOptionPane.showMessageDialog(frame, "Successfully Paid", "Process Completed", JOptionPane.PLAIN_MESSAGE);
			new FinalPage();
			frame.dispose();
		}*/
	}  
	
	/*public static void main(String[] args) {
		new Loading(6000.00, null, false);
	}*/
}
