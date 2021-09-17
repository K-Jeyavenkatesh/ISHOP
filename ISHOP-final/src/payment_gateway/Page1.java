package payment_gateway;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import demo.*;

public class Page1 implements ActionListener{
	
	JFrame frame;
	JLabel title, selectTile;
	JPanel side_panel, main_panel;
	JButton profile, cart, history, logOut, back, proceed;
	JRadioButton onsite, cod, onlinePay;
	ButtonGroup group;
	private double billAmount;
	private boolean paid;
	Customer_bean customer;
	Product_bean product;
	
	
	public Page1(double billAmount, ItemBean[] item, boolean paid,Product_bean Product,Customer_bean Customer) {
		
		product=Product;
		customer=Customer;
		
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
		
		proceed = new JButton("PROCEED NOW");
		proceed.setBounds(650, 650, 150, 50);
		proceed.setFont(new Font("Helvetica", Font.BOLD, 15));
		proceed.setBackground(Color.WHITE);
		frame.add(proceed);
		
		selectTile = new JLabel("Select Your Payment Option");
		selectTile.setBounds(10, 10, 725, 50);
		selectTile.setHorizontalAlignment(JLabel.CENTER);
		selectTile.setFont(new Font("Helvetica", Font.BOLD, 20));
		main_panel.add(selectTile);
		
		onsite = new JRadioButton(" ONSITE PAYMENT ");
		onsite.setBounds(50, 75, 650, 75);
		onsite.setFont(new Font("Helvetica", Font.BOLD, 20));
		main_panel.add(onsite);
		
		cod = new JRadioButton(" CASH ON DELIVERY ");
		cod.setBounds(50, 175, 650, 75);
		cod.setFont(new Font("Helvetica", Font.BOLD, 20));
		main_panel.add(cod);
		
		onlinePay = new JRadioButton(" ONLINE PAYMENT ");
		onlinePay.setBounds(50, 275, 650, 75);
		onlinePay.setFont(new Font("Helvetica", Font.BOLD, 20));
		main_panel.add(onlinePay);
		
		group = new ButtonGroup();
		group.add(onsite);
		group.add(cod);
		group.add(onlinePay);
		
		JOptionPane.showMessageDialog(frame, "Your TOATL AMOUNT "+billAmount, "Your Bill Amount", JOptionPane.PLAIN_MESSAGE);
		frame.validate();
		frame.repaint();
		
		System.out.println(item[0].getProductName());
		
		
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.dispose();
				
			}
		});
		
		proceed.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(onsite.isSelected()) {
					new OnsitePayment(billAmount, item, paid,product,customer);
					frame.dispose();
				} else if (cod.isSelected()) {
					new CodPayment(billAmount, item, paid,product,customer);
					frame.dispose();
				} else {
					new OnlinePayment(billAmount, item, paid,product,customer);
					frame.dispose();
				}
				
			}
		});
		
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
	


//	public static void main(String[] args) {
//		demo.ItemBean[] b = beandummy.Itembean();
//		//new page1(1000.00, b, true);
//	}
}
