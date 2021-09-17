package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

//import frontpage.Error;

public class Buyer_Profile extends JFrame implements ActionListener{

	public static void main(String[] args){
		//new demo.Buyer_Profile();

	}
	
	JPanel headerpanel,midpanel;
	JLabel heading,heading2;
	JLabel Username,Password,Email,Mobile_number,Address;
	JTextField username,password,mobile_number;
	JTextField email;
	JTextArea address;
	JButton Submit,Back,Edit;
	Customer_bean customer;
	
	public Buyer_Profile(Customer_bean customer){

		this.customer=customer;

		headerpanel=new JPanel();
		
		heading=new JLabel("ISHOP - SHOP THE FUTURE HERE");
		heading.setFont(new Font("Verdana",Font.BOLD,17));
		heading.setForeground(Color.black);
		heading.setBounds(150,10,500,50);
		headerpanel.add(heading);
		
		headerpanel.setBounds(150, 30, 710, 70);
		headerpanel.setBackground(new Color(220,222,220,210));//headerpanel.setBackground(new Color(220,230,240,210)); //new Color(220,220,220)
		headerpanel.setBorder(new LineBorder(Color.black,1,true));
		headerpanel.setLayout(null);
		//headerpanel.setOpaque(false);
		add(headerpanel);
		
		heading2=new JLabel("BUYER - PROFILE");
		heading2.setFont(new Font("TimesRoman",Font.BOLD,19));
		heading2.setForeground(Color.black);
		heading2.setBounds(390,130,500,50);
		add(heading2);
		
		midpanel=new JPanel();
		
		
		Username=new JLabel("USERNAME");
		Username.setBounds(100,20,100,40);
		Username.setFont(new Font("TimesRoman",Font.BOLD,15));
		midpanel.add(Username);
		
		username=new JTextField(customer.getUsername());
		username.setBounds(300,20,200,40);
		username.setBorder(new LineBorder(Color.BLACK));
		username.setEditable(false);
		midpanel.add(username);
		
		Password=new JLabel("PASSWORD");
		Password.setBounds(100,80,100,40);
		Password.setFont(new Font("TimesRoman",Font.BOLD,15));
		midpanel.add(Password);
		
		password=new JTextField(customer.getPassword());
		password.setBounds(300,80,200,40);
		password.setBorder(new LineBorder(Color.BLACK));
		password.setEditable(false);
		midpanel.add(password);
		
		
		Mobile_number=new JLabel("MOBILE");
		Mobile_number.setBounds(100,140,200,40);
		Mobile_number.setFont(new Font("TimesRoman",Font.BOLD,15));
		midpanel.add(Mobile_number);
		
		mobile_number=new JTextField(customer.getMobile_no());
		mobile_number.setBounds(300,140,200,40);
		mobile_number.setBorder(new LineBorder(Color.BLACK));
		mobile_number.setEditable(false);
		midpanel.add(mobile_number);
		
		Email=new JLabel("EMAIL");
		Email.setBounds(100,200,200,40);
		Email.setFont(new Font("TimesRoman",Font.BOLD,15));
		midpanel.add(Email);
		
		email=new JTextField(customer.getEmail());
		email.setBounds(300,200,200,40);
		email.setBorder(new LineBorder(Color.BLACK));
		email.setEditable(false);
		midpanel.add(email);
		
		Address=new JLabel("ADDRESS");
		Address.setBounds(100,260,200,40);
		Address.setFont(new Font("TimesRoman",Font.BOLD,15));
		midpanel.add(Address);
		
		address=new JTextArea(customer.getAddress());
		address.setBounds(300,260,300,130);
		address.setLineWrap(true);
		address.setWrapStyleWord(true);
		address.setBorder(new LineBorder(Color.BLACK));
		address.setEditable(false);
		midpanel.add(address);


		Submit=new JButton("SAVE");
		Edit=new JButton("EDIT");
		Edit.setBounds(430,680,100,30);
		add(Edit);
		Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username.setEditable(true);
				password.setEditable(true);
				email.setEditable(true);
				mobile_number.setEditable(true);
				address.setEditable(true);
				Submit.setBounds(800,680,100,30);
				add(Submit);
				Submit.addActionListener(this);
				validate();
				repaint();
			}
		});
		
		
		midpanel.setBounds(150, 200, 710, 450);
		midpanel.setBackground(new Color(220,222,220,210)); //new Color(220,220,220)
		midpanel.setBorder(new LineBorder(Color.black,1,true));
		midpanel.setPreferredSize(new Dimension(5000, 900));
		//midpanel.setOpaque(false);
		midpanel.setLayout(null);
		add(midpanel);
		
		Back=new JButton("BACK");
		Back.setBounds(60, 680, 100, 30);
		Back.setForeground(Color.blue);
		Back.setOpaque(false);
		add(Back);
		
		Back.addActionListener(this);


		ImageIcon image1=new ImageIcon("history.jpg");
		Image image2=image1.getImage();
		Image image=image2.getScaledInstance(1000, 870, Image.SCALE_SMOOTH); // LAST is used for the quality of image (highest quality)
		JLabel background=new JLabel();
		background.setIcon(new ImageIcon(image));
		background.setBounds(0, 0, 1570, 800);
		getContentPane().add(background);

		Submit.addActionListener(this);
		setBounds(300,10,1000,800);
		setLayout(null);
		setVisible(true);
		//getContentPane().setBackground(Color.black);
		
		setTitle("ISHOP");
		ImageIcon icon = new ImageIcon("IShop.png");
		setIconImage(icon.getImage());

	}
	
	boolean errorfound=false;
	
	public void actionPerformed(ActionEvent e) {
		errorfound=false;

		if(e.getSource()==Back) {
			//new demo.Catalogue_buyer(customer);
			//this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			dispose();
		}
	
		if(e.getSource()==Submit) {
			//username unique
			
			String Username=username.getText();
			String Password=password.getText();
			String Email=email.getText();
			String Mobile_number=mobile_number.getText();
			String Address=address.getText();
			
			
			if(Username.length()==0 || Password.length()==0 ||  Email.length()==0 || Mobile_number.length()==0 ||  Address.length()==0) {
				JOptionPane.showMessageDialog(new JFrame(), "EVERYTHING MUST BE FILLED");
				errorfound=true;
				return;
			}
			String regex = "^[\\w!#$%&'+/=?`{|}~^-]+(?:\\.[\\w!#$%&'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(Email);
			
			if(!matcher.matches()) {
				JOptionPane.showMessageDialog(new JFrame(), "EMAIL MUST BE PROPERLY GIVEN");
				errorfound=true;
				return;
			}

			if(!errorfound){
				Customer_bean o=new Customer_bean();
				o.setEmail(Email);
				o.setMobile_no(Mobile_number);
				try {
					int n=new Sql().is_UpdateCustomerRegistered(o);
					if(n==1){
						errorfound=true;
						JOptionPane.showMessageDialog(new JFrame(), "THIS EMAIL IS ALREADY REGISTERED");
					}
					if(n==2){
						errorfound=true;
						JOptionPane.showMessageDialog(new JFrame(), "THIS MOBILE NUMBER IS ALREADY REGISTERED");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			if(!errorfound){
				Customer_bean obj=new Customer_bean();
				try {
					obj.setCustomer_id(customer.getCustomer_id());
					obj.setPassword(password.getText());
					obj.setUsername(username.getText());
					obj.setEmail(email.getText());
					obj.setMobile_no(mobile_number.getText());
					obj.setAddress(address.getText());
					new Sql().update_Customer(obj);
					customer=obj;
				} catch (Exception ex) {
					ex.printStackTrace();
					errorfound=true;
				}
			}

			if(!errorfound) {
				errorfound=true;
				JOptionPane.showMessageDialog(new JFrame(), "CREDENTIALS SAVED");
				dispose();
			}
			
		}

		
	}


	}


