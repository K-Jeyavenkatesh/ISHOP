package payment_gateway;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import demo.Buyer_Login;
import demo.Buyer_Profile;
import demo.Cart;
import demo.CustomerHistory;
import demo.Customer_bean;
import demo.Product_bean;

public class CodPayment implements ActionListener{

	JFrame frame;
	JLabel title, line_11, line_12, line_2, line_3;
	JPanel side_panel, main_panel;
	JButton profile, cart, history, logOut, back, proceed;
	JRadioButton cash, card;
	ButtonGroup group;
	JCheckBox agree;
	private double billAmount;
	private ItemBean[] item;
	private boolean paid;
	private Customer_bean customer;
	private Product_bean product;
	
	public CodPayment(double billAmount, ItemBean[] item, boolean paid,Product_bean Product,Customer_bean Customer) {
		
		product=Product;
		customer=Customer;
		
		this.billAmount = billAmount;
		frame = new JFrame("IShop");
		frame.setBounds(300, 50, 1200, 800);
		frame.getContentPane().setBackground(Color.getHSBColor(255, 216, 17));
		frame.setVisible(true);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		title = new JLabel("I-SHOP");
		title.setBounds(100, 50, 1000, 75);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("Helvetica", Font.BOLD, 20));
		title.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5, true));
		frame.add(title);
		
		side_panel = new JPanel();
		side_panel.setBounds(100, 200, 200, 400);
		side_panel.setVisible(true);
		side_panel.setBackground(Color.LIGHT_GRAY);
		side_panel.setLayout(null);
		frame.add(side_panel);
		
		profile = new JButton("PROFILE");
		profile.setBounds(25, 25, 150, 50);
		profile.setFont(new Font("Helvetica", Font.BOLD, 15));
		profile.setBackground(Color.WHITE);
		side_panel.add(profile);
		
		cart = new JButton("CART");
		cart.setBounds(25, 125, 150, 50);
		cart.setFont(new Font("Helvetica", Font.BOLD, 15));
		cart.setBackground(Color.WHITE);
		side_panel.add(cart);
		
		history = new JButton("HISTORY");
		history.setBounds(25, 225, 150, 50);
		history.setFont(new Font("Helvetica", Font.BOLD, 15));
		history.setBackground(Color.WHITE);
		side_panel.add(history);
		
		logOut = new JButton("LOG OUT");
		logOut.setBounds(25, 325, 150, 50);
		logOut.setFont(new Font("Helvetica", Font.BOLD, 15));
		logOut.setBackground(Color.WHITE);
		side_panel.add(logOut);
		
		back = new JButton("BACK");
		back.setBounds(125, 650, 150, 50);
		back.setFont(new Font("Helvetica", Font.BOLD, 15));
		back.setBackground(Color.WHITE);
		frame.add(back);
		
		main_panel = new JPanel();
		main_panel.setBounds(350, 200, 750, 400);
		main_panel.setVisible(true);
		main_panel.setBackground(Color.WHITE);
		main_panel.setLayout(null);
		frame.add(main_panel);
		
		line_11 = new JLabel();
		line_11.setText("YOU HAVE SELECTED AN OPTION :");
		line_11.setBounds(25, 20, 350, 50);
		line_11.setFont(new Font("Helvetica", Font.BOLD, 20));
		main_panel.add(line_11);
		
		line_12 = new JLabel();
		line_12.setText("CASH ON PAYMENT");
		line_12.setBounds(400, 20, 400, 50);
		line_12.setForeground(Color.GREEN);
		line_12.setFont(new Font("Helvetica", Font.BOLD, 20));
		main_panel.add(line_12);
		
		line_2 = new JLabel("Mode of Payment at Cash On Delivery");
		line_2.setBounds(25, 100, 400, 50);
		line_2.setFont(new Font("Helvetica", Font.BOLD, 20));
		main_panel.add(line_2);
		
		cash = new JRadioButton("CASH");
		cash.setBounds(75, 175, 100, 50);
		cash.setFont(new Font("Helvetica", Font.BOLD, 20));
		main_panel.add(cash);
		
		card = new JRadioButton("CARD");
		card.setBounds(275, 175, 100, 50);
		card.setFont(new Font("Helvetica", Font.BOLD, 20));
		main_panel.add(card);
		
		group = new ButtonGroup();
		group.add(cash);
		group.add(card);
		
		agree = new JCheckBox(" I agree to all terms and conditions ");
		agree.setBounds(250, 325, 500, 50);
		agree.setFont(new Font("Helvetica", Font.BOLD, 15));
		agree.setBackground(Color.WHITE);
		main_panel.add(agree);
		
		proceed = new JButton("CONFIRM MY ORDER");
		proceed.setBounds(625, 650, 200, 50);
		proceed.setFont(new Font("Helvetica", Font.BOLD, 15));
		proceed.setBackground(Color.WHITE);
		frame.add(proceed);
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Page1(billAmount, item, paid,product,customer);
				frame.dispose();
			}
		});
		
		proceed.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(!(cash.isSelected() || card.isSelected())) {
					JOptionPane.showMessageDialog(frame, "Mode of Payment is not selected", "Not Selected", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!agree.isSelected()) {
					JOptionPane.showMessageDialog(frame, "All Terms and Conditions is not Accpted" , "Not Accepted", JOptionPane.WARNING_MESSAGE);
				} else {
					for(int i = 0; i < item.length; i++) {
						item[i].setPaymentMode("COD");
					}
					new ToOtpThread(billAmount, item, paid,product,customer).start();
					frame.dispose();
				}
			}
		});

		frame.validate();
		frame.repaint();
		
		profile.addActionListener(this);
		history.addActionListener(this);
		cart.addActionListener(this);
		logOut.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==profile){
			
			new Buyer_Profile(customer);
		}

		if(e.getSource()==history){
			try {
				new CustomerHistory(customer);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		if(e.getSource()==cart){
			try {
				new Cart(customer);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		if(e.getSource()==logOut) {
			new Buyer_Login();
			frame.dispose();
		}
	}

	
	
	public static void main(String[] args) {
		//new codPayment();
	}

}
