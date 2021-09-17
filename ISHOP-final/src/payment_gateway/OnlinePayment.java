package payment_gateway;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import demo.Buyer_Login;
import demo.Buyer_Profile;
import demo.Cart;
import demo.CustomerHistory;
import demo.Customer_bean;
import demo.Product_bean;

public class OnlinePayment implements ActionListener{
	
	JFrame frame;
	JLabel title, line_11, line_12, card;
	JPanel side_panel, main_panel, down_panel;
	JButton profile, cart, history, logOut, back, proceed;
	JRadioButton credit, debit, upi, netbank;
	ButtonGroup group;
	JCheckBox agree;
	JPasswordField card_no_CVV;
	BufferedImage image_card;
	private double billAmount;
	private ItemBean[] item;
	private boolean paid;
	private Customer_bean customer;
	private Product_bean product;
	
	public OnlinePayment(double billAmount, ItemBean[] item, boolean paid,Product_bean Product,Customer_bean Customer) {
		
		this.product=Product;
		this.customer=Customer;
		
		this.paid = paid;
		this.item = item;
		this.billAmount = billAmount;
		
		frame = new JFrame("IShop");
		frame.setBounds(300, 50, 1200, 800);
		frame.getContentPane().setBackground(Color.getHSBColor(255, 216, 17));
		frame.setVisible(true);
		frame.setLayout(null);
		//frame.setResizable(false);
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
		main_panel.setBounds(350, 200, 750, 175);
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
		line_12.setText("ONLINE PAYMENT");
		line_12.setBounds(400, 20, 400, 50);
		line_12.setForeground(Color.GREEN);
		line_12.setFont(new Font("Helvetica", Font.BOLD, 20));
		main_panel.add(line_12);
		
		credit = new JRadioButton("CREDIT CARD");
		credit.setBounds(25, 70, 135, 75);
		credit.setFont(new Font("Helvetica", Font.BOLD, 15));
		credit.setSelected(true);
		main_panel.add(credit);
		
		debit = new JRadioButton("DEBIT CARD");
		debit.setBounds(200, 70, 125, 75);
		debit.setFont(new Font("Helvetica", Font.BOLD, 15));
		main_panel.add(debit);
		
		upi = new JRadioButton("UPI");
		upi.setBounds(370, 70, 100, 75);
		upi.setFont(new Font("Helvetica", Font.BOLD, 15));
		main_panel.add(upi);
		
		netbank = new JRadioButton("NET BANKING");
		netbank.setBounds(500, 70, 200, 75);
		netbank.setFont(new Font("Helvetica", Font.BOLD, 15));
		main_panel.add(netbank);
		
		group = new ButtonGroup();
		group.add(credit);
		group.add(debit);
		group.add(upi);
		group.add(netbank);
		
		downPanelCreate();
		creditAdd();
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Page1(billAmount, item, paid,product,customer);
				frame.dispose();
			}
		});
		
		credit.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(credit.isSelected()) {
					down_panel.removeAll();
					frame.remove(down_panel);
					downPanelCreate();
					creditAdd();
				}
			}
		});
		
		debit.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(debit.isSelected()) {
					down_panel.removeAll();
					frame.remove(down_panel);
					downPanelCreate();
					debitAdd();
				}
			}
		});
		
		upi.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(upi.isSelected()) {
					down_panel.removeAll();
					frame.remove(down_panel);
					downPanelCreate();
					upiAdd();
				}
			}
		});
		
		netbank.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if(netbank.isSelected()) {
					down_panel.removeAll();
					frame.remove(down_panel);
					downPanelCreate();
					netbankAdd();
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
		Customer_bean customer=new Customer_bean();
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

	
	
	public void downPanelCreate() {
		down_panel = new JPanel();
		down_panel.setBounds(350, 400, 750, 300);
		down_panel.setVisible(true);
		down_panel.setBackground(Color.WHITE);
		down_panel.setLayout(null);
		frame.add(down_panel);
		
		agree = new JCheckBox(" I agree to all terms and conditions ");
		agree.setBounds(200, 250, 300, 50);
		agree.setFont(new Font("Helvetica", Font.BOLD, 15));
		agree.setBackground(Color.WHITE);
		down_panel.add(agree);
	}
	
	public void creditAdd() {
		
		JLabel mode = new JLabel("Credit Card");
		mode.setBounds(20, 10, 200, 50);
		mode.setFont(new Font("Helvetica", Font.BOLD, 20));
		down_panel.add(mode);
		
		JLabel card_name_label = new JLabel("CARD HOLDER NAME : ");
		card_name_label.setBounds(20, 55, 300, 40);
		card_name_label.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(card_name_label);
		
		JTextField card_name = new JTextField();
		card_name.setBounds(220, 55, 250, 40);
		card_name.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(card_name);
		
		JLabel card_no_label = new JLabel("CARD NUMBER : ");
		card_no_label.setBounds(20, 110, 200, 40);
		card_no_label.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(card_no_label);
		
		JTextField card_no = new JTextField();
		card_no.setBounds(220, 110, 250, 40);
		card_no.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(card_no);
		
		JLabel card_expiry = new JLabel("VALID UPTO (MM/YY) : ");
		card_expiry.setBounds(20, 160, 200, 40);
		card_expiry.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(card_expiry);
		
		JTextField card_exp = new JTextField();
		card_exp.setBounds(220, 160, 100, 40);
		card_exp.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(card_exp);
		
		JLabel card_CVV = new JLabel("ENTER CVV/CVC : ");
		card_CVV.setBounds(20, 210, 200, 40);
		card_CVV.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(card_CVV);
		
		JPasswordField card_no_CVV = new JPasswordField();
		card_no_CVV.setBounds(220, 210, 100, 40);
		card_no_CVV.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(card_no_CVV);
		
		proceed = new JButton("PROCEED");
		proceed.setBounds(500, 210, 150, 40);
		proceed.setFont(new Font("Helvetica", Font.BOLD, 15));
		proceed.setBackground(Color.LIGHT_GRAY);
		down_panel.add(proceed);
		
		card_name.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent arg0) {
				
				String name = card_name.getText();
				Pattern p = Pattern.compile("[[^a-zA-Z\\s]]");
				Matcher m = p.matcher(name);
				while(m.find()) {
					JOptionPane.showMessageDialog(frame,"Only Alphabetic Characters are Allowed", "Invalid Character", 0);
					card_name.setText("");
					return;
				}
			}
		});
		
		card_no.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent arg0) {
				String no = card_no.getText();
				int n = no.length();
				Pattern p = Pattern.compile("([a-zA-Z!@#$%^&])|([-][-])|[/]|[+]|[*]");
				Matcher m = p.matcher(no);
				while(m.find()) {
					JOptionPane.showMessageDialog(frame, "Invalid Character", "Invalid", 0);
					card_no.setText("");
					return;
				}
				if(card != null) {
					down_panel.remove(card);
					frame.validate();
					frame.repaint();
				}
				if(n == 4 || n == 9 || n == 14) {
					no += "-";
				}
				if(n < 19) {
					card_no.setText(no);
				} else {
					card_no.setText(no.substring(0,19));
					String number = no.substring(0,4)+no.substring(5,9)+no.substring(10,14)+no.substring(15,19);
					Pattern p1 = Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$");
					Matcher m1 = p1.matcher(number);
					if(m1.find()) {
						try {
							image_card = ImageIO.read(new File("visa.jpeg"));
							card = new JLabel(new ImageIcon(image_card));
							card.setBounds(475, 100, 200, 70);
							down_panel.add(card);
							frame.validate();
							frame.repaint();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					Pattern p2 = Pattern.compile("^^(5[1-5][0-9]{14}|2(22[1-9][0-9]{12}|2[3-9][0-9]{13}|"
							+ "[3-6][0-9]{14}|7[0-1][0-9]{13}|720[0-9]{12}))$");
					Matcher m2 = p2.matcher(number);
					if(m2.find()) {
						try {
							image_card = ImageIO.read(new File("mastercard.jpeg"));
							card = new JLabel(new ImageIcon(image_card));
							card.setBounds(475, 100, 200, 70);
							down_panel.add(card);
							frame.validate();
							frame.repaint();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					Pattern p3 = Pattern.compile("^(5018|5020|5038|6304|6759|6761|6763)[0-9]{8,15}$");
					Matcher m3 = p3.matcher(number);
					if(m3.find()) {
						try {
							image_card = ImageIO.read(new File("maestro.jpeg"));
							card = new JLabel(new ImageIcon(image_card));
							card.setBounds(475, 100, 200, 70);
							down_panel.add(card);
							frame.validate();
							frame.repaint();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					Pattern p4 = Pattern.compile("^(60|6521|6522)[0-9]{6,15}$");
					Matcher m4 = p4.matcher(number);
					if(m4.find()) {
						try {
							image_card = ImageIO.read(new File("rupay.jpeg"));
							card = new JLabel(new ImageIcon(image_card));
							card.setBounds(475, 100, 200, 70);
							down_panel.add(card);
							frame.validate();
							frame.repaint();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		
		card_exp.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent arg0) {
				String exp_date = card_exp.getText();
				int n = exp_date.length();
				Pattern p = Pattern.compile("([a-zA-Z!@#$%^&])|([/][/])");
				Matcher m = p.matcher(exp_date);
				while(m.find()) {
					JOptionPane.showMessageDialog(frame, "Invalid Character", "Invalid", 0);
					card_exp.setText("");
					return;
				}
				if(n == 2) {
					exp_date += "/";
				}
				if(n <= 5) {
					card_exp.setText(exp_date);
				} else {
					card_exp.setText(exp_date.substring(0,5));
				}
				try{
					if(exp_date.length() > 1) {
						int month = Integer.valueOf(exp_date.substring(0,2));
						if(!(month > 0 && month < 13)) {
							JOptionPane.showMessageDialog(frame, "Invalid Month", "Invalid", 0);
							card_exp.setText("");
						}
						if(exp_date.length() >= 5) {
							int year = Integer.valueOf(exp_date.substring(3,5));
							if(!(year >= 21 && year <= 45)) {
								JOptionPane.showMessageDialog(frame, "Invalid Year", "Invalid", 0);
								card_exp.setText(exp_date.substring(0,3));
							}
						}
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(frame, "Invalid Character", "Invalid", 0);
					card_exp.setText("");
					return;
				}
			}
		});
		
		card_no_CVV.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent arg0){
				String cvv = card_no_CVV.getText();
				card_no_CVV.setEchoChar((char)0);
				Pattern p = Pattern.compile("([a-zA-Z!@#$%^&-+*/])");//([-_=,\\{}()<>?:;/])
				Matcher m = p.matcher(cvv);
				while(m.find()) {
					JOptionPane.showMessageDialog(frame, "Invalid Character", "Invalid", 0);
					card_no_CVV.setText("");
					return;
				}
				if(cvv.length() >= 3) {
					card_no_CVV.setText(cvv.substring(0,3));
					card_no_CVV.setEchoChar('\u26ab');
				}
			}
		});
		
		proceed.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String cardHolderName = card_name.getText();
				String cardNumber = card_no.getText();
				String expiry = card_exp.getText();
				String cvv = card_no_CVV.getText();
				//System.out.println(cardHolderName+" "+cardNumber+" "+expiry+" "+cvv);
				if (cardHolderName.equals("")){
					JOptionPane.showMessageDialog(frame, "Card Holder Name is Mandatory" , "Mandatory", JOptionPane.WARNING_MESSAGE);
				} else if (cardNumber.equals("")){
					JOptionPane.showMessageDialog(frame, "Card Number is Mandatory" , "Mandatory", JOptionPane.WARNING_MESSAGE);
				} else if (expiry.equals("")){
					JOptionPane.showMessageDialog(frame, "Validity is Mandatory" , "Mandatory", JOptionPane.WARNING_MESSAGE);
				} else if (cvv.equals("")){
					JOptionPane.showMessageDialog(frame, "CVV/CVC is Mandatory" , "Mandatory", JOptionPane.WARNING_MESSAGE);
				} else if(!agree.isSelected()) {
					JOptionPane.showMessageDialog(frame, "All Terms and Conditions is not Accpted" , "Not Accepted", JOptionPane.WARNING_MESSAGE);
				} else {
					Pattern p = Pattern.compile("[0-9]");
					Matcher m = p.matcher(cardNumber);
					String number = "";
					while(m.find()) {
						number += m.group();
					}
					int month = Integer.valueOf(expiry.substring(0,2));
					int year = Integer.valueOf(expiry.substring(3,5));
					System.out.println(number);
					boolean paidcredit = true;
					try {
						paidcredit = new PaymentProcess(billAmount).creditPayment(cardHolderName, Long.valueOf(number), month, year, Integer.valueOf(cvv));
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("on online page "+paidcredit);
					for(int i = 0; i < item.length; i++) {
						item[i].setPaymentMode("CreditCard");
					}
					new ToOtpThread(billAmount, item,paidcredit,product,customer).start();
					frame.dispose();
				}
			}
		});
		
		frame.validate();
		frame.repaint();
	}
	
	public void debitAdd() {
		
		JLabel mode = new JLabel("Debit Card");
		mode.setBounds(20, 10, 200, 50);
		mode.setFont(new Font("Helvetica", Font.BOLD, 20));
		down_panel.add(mode);
		
		JLabel card_name_label = new JLabel("CARD HOLDER NAME : ");
		card_name_label.setBounds(20, 55, 300, 40);
		card_name_label.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(card_name_label);
		
		JTextField card_name = new JTextField();
		card_name.setBounds(220, 55, 250, 40);
		card_name.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(card_name);
		
		JLabel card_no_label = new JLabel("CARD NUMBER : ");
		card_no_label.setBounds(20, 110, 200, 40);
		card_no_label.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(card_no_label);
		
		JTextField card_no = new JTextField();
		card_no.setBounds(220, 110, 250, 40);
		card_no.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(card_no);
		
		JLabel card_expiry = new JLabel("VALID UPTO (MM/YY) : ");
		card_expiry.setBounds(20, 160, 200, 40);
		card_expiry.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(card_expiry);
		
		JTextField card_exp = new JTextField();
		card_exp.setBounds(220, 160, 100, 40);
		card_exp.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(card_exp);
		
		JLabel card_CVV = new JLabel("ENTER CVV/CVC : ");
		card_CVV.setBounds(20, 210, 200, 40);
		card_CVV.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(card_CVV);
		
		JPasswordField card_no_CVV = new JPasswordField();
		card_no_CVV.setBounds(220, 210, 100, 40);
		card_no_CVV.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(card_no_CVV);
		
		proceed = new JButton("PROCEED");
		proceed.setBounds(500, 210, 150, 40);
		proceed.setFont(new Font("Helvetica", Font.BOLD, 15));
		proceed.setBackground(Color.LIGHT_GRAY);
		down_panel.add(proceed);
		
		card_name.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent arg0) {
				
				String name = card_name.getText();
				Pattern p = Pattern.compile("[[^a-zA-Z\\s]]");
				Matcher m = p.matcher(name);
				while(m.find()) {
					JOptionPane.showMessageDialog(frame,"Only Alphabetic Characters are Allowed", "Invalid Character", 0);
					card_name.setText("");
					return;
				}
			}
		});
		
		card_no.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent arg0) {
				String no = card_no.getText();
				Pattern p = Pattern.compile("([a-zA-Z!@#$%^&])|([-][-])|[/]|[+]|[*]");
				Matcher m = p.matcher(no);
				while(m.find()) {
					JOptionPane.showMessageDialog(frame, "Invalid Character", "Invalid", 0);
					card_no.setText("");
					return;
				}
				if(card != null) {
					down_panel.remove(card);
					frame.validate();
					frame.repaint();
				}
				int n = no.length();
				if(n == 4 || n == 9 || n == 14) {
					no += "-";
				}
				if(n < 19) {
					card_no.setText(no);
				} else {
					card_no.setText(no.substring(0,19));
					String number = no.substring(0,4)+no.substring(5,9)+no.substring(10,14)+no.substring(15,19);
					Pattern p1 = Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$");
					Matcher m1 = p1.matcher(number);
					if(m1.find()) {
						try {
							image_card = ImageIO.read(new File("visa.jpeg"));
							card = new JLabel(new ImageIcon(image_card));
							card.setBounds(475, 100, 200, 70);
							down_panel.add(card);
							frame.validate();
							frame.repaint();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					Pattern p2 = Pattern.compile("^^(5[1-5][0-9]{14}|2(22[1-9][0-9]{12}|2[3-9][0-9]{13}|"
							+ "[3-6][0-9]{14}|7[0-1][0-9]{13}|720[0-9]{12}))$");
					Matcher m2 = p2.matcher(number);
					if(m2.find()) {
						try {
							image_card = ImageIO.read(new File("mastercard.jpeg"));
							card = new JLabel(new ImageIcon(image_card));
							card.setBounds(475, 100, 200, 70);
							down_panel.add(card);
							frame.validate();
							frame.repaint();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					Pattern p3 = Pattern.compile("^(5018|5020|5038|6304|6759|6761|6763)[0-9]{8,15}$");
					Matcher m3 = p3.matcher(number);
					if(m3.find()) {
						try {
							image_card = ImageIO.read(new File("maestro.jpeg"));
							card = new JLabel(new ImageIcon(image_card));
							card.setBounds(475, 100, 200, 70);
							down_panel.add(card);
							frame.validate();
							frame.repaint();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					Pattern p4 = Pattern.compile("^(60|6521|6522)[0-9]{6,15}$");
					Matcher m4 = p4.matcher(number);
					if(m4.find()) {
						try {
							image_card = ImageIO.read(new File("rupay.jpeg"));
							card = new JLabel(new ImageIcon(image_card));
							card.setBounds(475, 100, 200, 70);
							down_panel.add(card);
							frame.validate();
							frame.repaint();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		
		card_exp.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent arg0) {
				String exp_date = card_exp.getText();
				int n = exp_date.length();
				Pattern p = Pattern.compile("([a-zA-Z!@#$%^&])|([/][/])");
				Matcher m = p.matcher(exp_date);
				while(m.find()) {
					JOptionPane.showMessageDialog(frame, "Invalid Character", "Invalid", 0);
					card_exp.setText("");
					return;
				}
				if(n == 2) {
					exp_date += "/";
				}
				if(n <= 5) {
					card_exp.setText(exp_date);
				} else {
					card_exp.setText(exp_date.substring(0,5));
				}
				try{
					if(exp_date.length() > 1) {
						int month = Integer.valueOf(exp_date.substring(0,2));
						if(!(month > 0 && month < 13)) {
							JOptionPane.showMessageDialog(frame, "Invalid Month", "Invalid", 0);
							card_exp.setText("");
						}
						if(exp_date.length() >= 5) {
							int year = Integer.valueOf(exp_date.substring(3,5));
							System.out.println(year);
							if(!(year >= 21 && year <= 45)) {
								JOptionPane.showMessageDialog(frame, "Invalid Year", "Invalid", 0);
								card_exp.setText(exp_date.substring(0,3));
							}
						}
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(frame, "Invalid Character", "Invalid", 0);
					card_exp.setText("");
					return;
				}
			}
		});
		
		card_no_CVV.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent arg0){
				String cvv = card_no_CVV.getText();
				card_no_CVV.setEchoChar((char)0);
				Pattern p = Pattern.compile("([a-zA-Z!@#$%^&-+*/])");//([-_=,\\{}()<>?:;/])
				Matcher m = p.matcher(cvv);
				while(m.find()) {
					JOptionPane.showMessageDialog(frame, "Invalid Character", "Invalid", 0);
					card_no_CVV.setText("");
					return;
				}
				if(cvv.length() >= 3) {
					card_no_CVV.setText(cvv.substring(0,3));
					card_no_CVV.setEchoChar('\u26ab');
				}
			}
		});
		
		proceed.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String cardHolderName = card_name.getText();
				String cardNumber = card_no.getText();
				String expiry = card_exp.getText();
				String cvv = card_no_CVV.getText();
				//System.out.println(cardHolderName+" "+cardNumber+" "+expiry+" "+cvv);
				if (cardHolderName.equals("")){
					JOptionPane.showMessageDialog(frame, "Card Holder Name is Mandatory" , "Mandatory", JOptionPane.WARNING_MESSAGE);
				} else if (cardNumber.equals("")){
					JOptionPane.showMessageDialog(frame, "Card Number is Mandatory" , "Mandatory", JOptionPane.WARNING_MESSAGE);
				} else if (expiry.equals("")){
					JOptionPane.showMessageDialog(frame, "Validity is Mandatory" , "Mandatory", JOptionPane.WARNING_MESSAGE);
				} else if (cvv.equals("")){
					JOptionPane.showMessageDialog(frame, "CVV/CVC is Mandatory" , "Mandatory", JOptionPane.WARNING_MESSAGE);
				} else if(!agree.isSelected()) {
					JOptionPane.showMessageDialog(frame, "All Terms and Conditions is not Accpted" , "Not Accepted", JOptionPane.WARNING_MESSAGE);
				} else {
					Pattern p = Pattern.compile("[0-9]");
					Matcher m = p.matcher(cardNumber);
					String number = "";
					while(m.find()) {
						number += m.group();
					}
					int month = Integer.valueOf(expiry.substring(0,2));
					int year = Integer.valueOf(expiry.substring(3,5));
					System.out.println(number);
					boolean paiddebit = true;
					try {
						paiddebit = new PaymentProcess(billAmount).debitPayment(cardHolderName, Long.valueOf(number), month, year, Integer.valueOf(cvv));
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("on online page "+paiddebit);
					for(int i = 0; i < item.length; i++) {
						item[i].setPaymentMode("DebitCard");
					}
					new ToOtpThread(billAmount, item,paiddebit,product,customer).start();
					frame.dispose();
				}
			}
		});
		
		frame.validate();
		frame.repaint();
	}
	
	public void upiAdd() {
		
		JLabel mode = new JLabel("UPI - (Unified Payments Interface)");
		mode.setBounds(20, 10, 400, 50);
		mode.setFont(new Font("Helvetica", Font.BOLD, 20));
		down_panel.add(mode);
		
		JLabel name_label = new JLabel("ENTER UPI ID : ");
		name_label.setBounds(20, 70, 300, 40);
		name_label.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(name_label);
		
		JTextField name_text = new JTextField();
		name_text.setBounds(220, 70, 250, 40);
		name_text.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(name_text);
		
		JLabel email_label = new JLabel("E-MAIL ID : ");
		email_label.setBounds(20, 130, 200, 40);
		email_label.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(email_label);
		
		JTextField email_text = new JTextField();
		email_text.setBounds(220, 130, 250, 40);
		email_text.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(email_text);
		
		JLabel password_label = new JLabel("ENTER UPI PIN : ");
		password_label.setBounds(20, 190, 200, 40);
		password_label.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(password_label);
		
		JPasswordField password = new JPasswordField();
		password.setBounds(220, 190, 150, 40);
		password.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(password);
		
		proceed = new JButton("PROCEED");
		proceed.setBounds(500, 240, 150, 40);
		proceed.setFont(new Font("Helvetica", Font.BOLD, 15));
		proceed.setBackground(Color.LIGHT_GRAY);
		down_panel.add(proceed);
		
		JButton show = new JButton();
		show.setText("SHOW");
		show.setBounds(500, 195, 75, 30);
		down_panel.add(show);
		
		JButton hide = new JButton();
		hide.setText("HIDE");
		hide.setBounds(500, 195, 75, 30);
		
		
		show.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				down_panel.remove(show);
				down_panel.add(hide);
				password.setEchoChar((char)0);
				frame.validate();
				frame.repaint();
			}
		});
		
		hide.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				down_panel.remove(hide);
				down_panel.add(show);
				password.setEchoChar('\u26ab');
				frame.validate();
				frame.repaint();
			}
		});
		
		name_text.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent arg0) {
				
				Pattern p = Pattern.compile("[^a-zA-Z0-9@]");
				Matcher m = p.matcher(name_text.getText());
				while(m.find()) { 
					JOptionPane.showMessageDialog(frame,"Invalid UPI ID", "Invalid Character", 0);
					name_text.setText("");
					return;
				}
			}
		});
		
		
		proceed.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String userName$ = name_text.getText();
				String  email$ = email_text.getText();
				String password$ = password.getText();
				//System.out.println(cardHolderName+" "+cardNumber+" "+expiry+" "+cvv);
				if (userName$.equals("")){
					JOptionPane.showMessageDialog(frame, "UPI ID is Mandatory" , "Mandatory", JOptionPane.WARNING_MESSAGE);
				} else if (email$.equals("")){
					JOptionPane.showMessageDialog(frame, "E-Mail ID is Mandatory" , "Mandatory", JOptionPane.WARNING_MESSAGE);
				} else if (password$.equals("")){
					JOptionPane.showMessageDialog(frame, "Password is Mandatory" , "Mandatory", JOptionPane.WARNING_MESSAGE);
				} else if(!agree.isSelected()) {
					JOptionPane.showMessageDialog(frame, "All Terms and Conditions is not Accpted" , "Not Accepted", JOptionPane.WARNING_MESSAGE);
				} else {
					
					Pattern p = Pattern.compile("[a-zA-Z0-9]+@[a-zA-Z0-9]+");
					Matcher m = p.matcher(name_text.getText());
					boolean flag = false;
					while(m.find()) { 
						flag = true;
					}
					if(!flag) {
						JOptionPane.showMessageDialog(frame,"Invalid UPI ID", "Invalid Character", 0);
						name_text.setText("");
						return;
					}
					
					p = Pattern.compile( "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
					m = p.matcher(email_text.getText());
					if(!m.matches()) { 
						JOptionPane.showMessageDialog(frame,"Invalid EmailID-", "Invalid Character", 0);
						email_text.setText("");
						return;
					}
					
					p = Pattern.compile("[0-9]{4}");
					m = p.matcher(password.getText());
					if(!m.matches()) { 
						JOptionPane.showMessageDialog(frame,"Invalid UPI pin", "Invalid Character", 0);
						password.setText("");
						return;
					}
					
					boolean paidupi = true;
					try {
						paidupi = new PaymentProcess(billAmount).upi(userName$, email$, Integer.valueOf(password$));
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("on online page "+paidupi);
					for(int i = 0; i < item.length; i++) {
						item[i].setPaymentMode("UPI");
					}
					new ToOtpThread(billAmount, item,paidupi,product,customer).start();
					frame.dispose();
				}
			}
		});
		
		frame.validate();
		frame.repaint();
	}
	
	public void netbankAdd() {
		
		JLabel mode = new JLabel("Net Banking");
		mode.setBounds(20, 10, 400, 50);
		mode.setFont(new Font("Helvetica", Font.BOLD, 20));
		down_panel.add(mode);
		
		JLabel name_label = new JLabel("SELECT YOUR BANK : ");
		name_label.setBounds(20, 70, 300, 40);
		name_label.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(name_label);
		
		String[] bank_name = {"select","Andhra Bank","AXIS","Bank of Bahrain and Kuwait","Bank of Baroda Corporate Banking","Bank of India"
				,"Bank of Maharashtra","Canara Bank","Catholic Syrian Bank","Central Bank of India","City Union Bank"
				,"Corporation Bank","Cosmos Bank","DBS Bank","Dena Bank","Deutsche Bank","Development Credit Bank","Dhanalakshmi Bank"
				,"Federal Bank","HDFC","ICICI","Indian Bank","Indian Overseas Bank","IndusInd Bank","Industrial Development Bank of India"
				,"Jammu and Kashmir Bank","Janata Sahakari Bank","Karnataka Bank","Karur Vysya Bank","Karur Vysya - Corporate Netbanking"
				,"KOTAK","Laxmi Vilas Bank","Laxmi Vilas Bank (Corporate Netbanking)","Oriental Bank of Commerce",
				"Punjab And Maharashtra Co-operative Bank","Punjab National Bank (Corporate)","Punjab & Sind Bank",
				"Royal Bank Of Scotland","Saraswat Bank","SBI","Shamrao Vitthal Cooperative Bank","South Indian Bank"
				,"Standard Chartered Bank","State Bank of Bikaner and Jaipur","State Bank of Hyderabad","State Bank of Mysore"
				,"State Bank of Patiala","State Bank of Travancore","Syndicate Bank","Tamilnad Mercantile Bank","The Nainital Bank"
				,"UCO Bank","Union Bank of India","Union Bank of India- Corporate Netbanking","United Bank of India"
				,"Vijaya Bank","Yes Bank"};
		
		JComboBox<String> name_text = new JComboBox<String>(bank_name);
		name_text.setBounds(220, 70, 300, 40);
		name_text.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(name_text);
		
		JLabel email_label = new JLabel("ENTER USER ID : ");
		email_label.setBounds(20, 130, 200, 40);
		email_label.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(email_label);
		
		JTextField email_text = new JTextField();
		email_text.setBounds(220, 130, 250, 40);
		email_text.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(email_text);
		
		JLabel password_label = new JLabel("ENTER PASSWORD : ");
		password_label.setBounds(20, 190, 200, 40);
		password_label.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(password_label);
		
		JPasswordField password = new JPasswordField();
		password.setBounds(220, 190, 250, 40);
		password.setFont(new Font("Helvetica", Font.BOLD, 15));
		down_panel.add(password);
		
		proceed = new JButton("PROCEED");
		proceed.setBounds(500, 240, 150, 40);
		proceed.setFont(new Font("Helvetica", Font.BOLD, 15));
		proceed.setBackground(Color.LIGHT_GRAY);
		down_panel.add(proceed);
		
		JButton show = new JButton();
		show.setText("SHOW");
		show.setBounds(500, 195, 75, 30);
		down_panel.add(show);
		
		JButton hide = new JButton();
		hide.setText("HIDE");
		hide.setBounds(500, 195, 75, 30);
		
		
		show.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				down_panel.remove(show);
				down_panel.add(hide);
				password.setEchoChar((char)0);
				frame.validate();
				frame.repaint();
			}
		});
		
		hide.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				down_panel.remove(hide);
				down_panel.add(show);
				password.setEchoChar('\u26ab');
				frame.validate();
				frame.repaint();
			}
		});
		
		proceed.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String bankName$ = (String)name_text.getSelectedItem();
				String  user$ = email_text.getText();
				String password$ = password.getText();
				//System.out.println(bankName$+" "+user$+" "+password$);
				if (bankName$.equals("select")){
					JOptionPane.showMessageDialog(frame, "Bank Name is Mandatory" , "Mandatory", JOptionPane.WARNING_MESSAGE);
				} else if (user$.equals("")){
					JOptionPane.showMessageDialog(frame, "User ID is Mandatory" , "Mandatory", JOptionPane.WARNING_MESSAGE);
				} else if (password$.equals("")){
					JOptionPane.showMessageDialog(frame, "Password is Mandatory" , "Mandatory", JOptionPane.WARNING_MESSAGE);
				} else if(!agree.isSelected()) {
					JOptionPane.showMessageDialog(frame, "All Terms and Conditions is not Accpted" , "Not Accepted", JOptionPane.WARNING_MESSAGE);
				} else {
					
					boolean paidnet = true;
					try {
						paidnet = new PaymentProcess(billAmount).netbanking(bankName$, user$, password$);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("on online page "+paidnet);
					for(int i = 0; i < item.length; i++) {
						item[i].setPaymentMode("UPI");
					}
					new ToOtpThread(billAmount, item,paidnet,product,customer).start();
					frame.dispose();
				}
			}
		});
		
		frame.validate();
		frame.repaint();
	}
	
	/*public static void main(String[] args) {
		new OnlinePayment(6000.00, null,true);
	}*/

}
