package demo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.border.Border;
import javax.swing.*;

public class Buyer_registeration extends JFrame implements ActionListener{
	
	public static void main(String[] args) {
		new Buyer_registeration();
	}
	
	JLabel lname,lmobile,lemailId,laddress,title,lpassword,lDOB;
	JTextField name,mobile,emailid;
	JPasswordField password;
	JTextArea address;
	JButton submit;
	JFrame frame;
	JPanel main_panel;
	JCheckBox show;
	
	public Buyer_registeration() {
		
		frame = new JFrame("ISHOP ");
		frame.setBounds(300, 30, 1200, 800);
		frame.getContentPane().setBackground(Color.getHSBColor(255, 216, 17));
		frame.setVisible(true);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		title = new JLabel("I-SHOP- SHOP THE FUTURE HERE");
		title.setBounds(100, 30, 1000, 75);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("Verdana",Font.BOLD,20));
		title.setForeground(Color.white);
		title.setBorder(BorderFactory.createLineBorder(Color.white, 1, true));
		frame.add(title);
		
		JLabel heading2=new JLabel("CUSTOMER REGISTER FORM");
		heading2.setFont(new Font("TimesRoman",Font.BOLD,18));
		heading2.setForeground(Color.white);
		heading2.setBounds(450,140,500,50);
		frame.add(heading2);
		
		main_panel = new JPanel();
		main_panel.setBounds(225, 220, 750, 450);
		main_panel.setVisible(true);
		main_panel.setBackground(new Color(220,230,240,210));
		main_panel.setLayout(null);
		frame.add(main_panel);
		
		lname=new JLabel("Name : ");
		lmobile=new JLabel("Mobile Number : ");
		lemailId=new JLabel("Email ID : ");
		laddress=new JLabel("Address : ");
		lDOB = new JLabel("Date of Birth : ");
		lpassword = new JLabel("Password : ");
		lname.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lmobile.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lemailId.setFont(new Font("Times New Roman", Font.BOLD, 18));
		laddress.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lDOB.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lpassword.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lname.setBounds(200, 50, 100, 30);
		lmobile.setBounds(200, 100, 150, 30);
		lemailId.setBounds(200, 150, 100, 30);
		laddress.setBounds(200, 200, 100, 30);
		//lDOB.setBounds(100, 250, 200, 30);
		lpassword.setBounds(200, 300, 200, 30);


		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		name=new JTextField();
		mobile=new JTextField();
		emailid=new JTextField();	
		address = new JTextArea();
		address.setLineWrap(true);
		address.setWrapStyleWord(true);
		password = new JPasswordField();
		show = new JCheckBox("show");
		show.setForeground(Color.white);
		show.setOpaque(false);
		submit = new JButton("Submit");
		name.setBounds(350, 50, 150, 30);
		mobile.setBounds(350, 100, 150, 30);		
		emailid.setBounds(350, 150, 150, 30);
		address.setBounds(350, 200, 150, 80);

		name.setBorder(border);
		mobile.setBorder(border);
		emailid.setBorder(border);
		address.setBorder(border);
		password.setBorder(border);
		show.setBounds(550,300,100,30);
		show.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(show.isSelected()) {
					password.setEchoChar((char)0);
				} else {
					password.setEchoChar('\u26ab');
				}
			}
		});
		

		password.setBounds(350,300,150,30);
		submit.setBounds(290, 390, 150, 30);
		
		main_panel.add(lname);
		main_panel.add(lmobile);
		main_panel.add(lemailId);
		main_panel.add(laddress);
		main_panel.add(lDOB);
		main_panel.add(lpassword);
		main_panel.add(name);
		main_panel.add(mobile);
		main_panel.add(emailid);
		main_panel.add(address);
		main_panel.add(password);
		main_panel.add(submit);

		submit.addActionListener(this);
		
		ImageIcon image1=new ImageIcon("registration_bg.jpg");
		Image image2=image1.getImage();
		Image image=image2.getScaledInstance(1200, 870, Image.SCALE_SMOOTH); // LAST is used for the quality of image (highest quality)
		JLabel background=new JLabel();
		background.setIcon(new ImageIcon(image));
		background.setBounds(0, 0, 1570, 800);
		frame.getContentPane().add(background);
		frame.validate();
		frame.repaint();
		frame.setTitle("ISHOP");
		ImageIcon icon = new ImageIcon("IShop.png");
		frame.setIconImage(icon.getImage());
	}
	

	@Override	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==submit) {
			boolean errorfound=false;

			if(!(name.getText().matches("^[a-zA-z]+$"))) {
				JOptionPane.showMessageDialog(this, "Enter valid name");
				errorfound=true;
			}
			else if(!mobile.getText().matches("[0-9]{10}")) {
				JOptionPane.showMessageDialog(this, "ID should contains 10 digits");
				errorfound=true;
			}
			else if(!emailid.getText().matches("^[\\w!#$%&'+/=?`{|}~^-]+(?:\\.[\\w!#$%&'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
				JOptionPane.showMessageDialog(this, "Enter valid Mail Id");
				errorfound=true;
			}
			
			if(!errorfound){
				try {
					Customer_bean o=new Customer_bean();
					o.setEmail(emailid.getText());
					o.setMobile_no(mobile.getText());
					int n=new Sql().is_customerRegistered(o);
					if(n==1){
						errorfound=true;
						JOptionPane.showMessageDialog(this, "THIS EMAIL IS ALREADY REGISTERED");
					}
					if(n==2){
						errorfound=true;
						JOptionPane.showMessageDialog(this, "THIS MOBILE NUMBER IS ALREADY REGISTERED");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			if(!errorfound){
				try {
					Customer_bean obj=new Customer_bean();
					String cid="cid"+new Sql().get_CustomerId();
					obj.setCustomer_id(cid);
					obj.setUsername(name.getText());
					obj.setPassword(password.getText());
					obj.setEmail(emailid.getText());
					obj.setMobile_no(mobile.getText());
					obj.setAddress(address.getText());
					JOptionPane.showMessageDialog(new JFrame(), "ADDED SUCCESSFULLY");
					new Sql().registerCustomer(obj);
					frame.dispose();
					new Buyer_Login();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
				
		}
	}
}

