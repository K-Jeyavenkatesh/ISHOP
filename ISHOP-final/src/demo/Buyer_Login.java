package demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import payment_gateway.Gmail;
import server.*;

public class Buyer_Login extends JFrame implements ActionListener{
	JFrame frame;
	JLabel title,l1,l2;
	JTextField t1;
	JPasswordField t2;
	JButton login,register,seller,click_here;
	JPanel main_panel;
	JCheckBox show;
	Customer_bean customer;

	public Buyer_Login() {	
		
		
		frame = new JFrame("IShop");
		frame.setBounds(300, 50, 1200, 800);
		//frame.getContentPane().setBackground(Color.getHSBColor(255, 216, 17));
		frame.setVisible(true);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		title = new JLabel("I-SHOP");
		title.setBounds(100, 50, 1000, 75);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("TimesRoman", Font.BOLD, 25));
		title.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1, true));
		frame.add(title);
		
		main_panel = new JPanel();
		main_panel.setBounds(225, 200, 750, 400);
		main_panel.setBackground(new Color(220,222,220,210)); //new Color(220,220,220)
		main_panel.setBorder(new LineBorder(Color.black,1,true));
		main_panel.setVisible(true);
		//main_panel.setBackground(Color.WHITE);
		main_panel.setLayout(null);
		frame.add(main_panel);
		
		
		JLabel forgot=new JLabel("Forgot Password??  ");
		forgot.setFont(new Font("Helvetica", Font.BOLD, 11));
		forgot.setBounds(250,240,150,30);
		main_panel.add(forgot);
		
		click_here=new JButton("Click here");
		click_here.setBackground(new Color(220,222,220,210));
		click_here.setOpaque(false);
		click_here.setBounds(380,245,100,18);
		main_panel.add(click_here);
		
		click_here.addActionListener(this);

		l1=new JLabel("Email ID : ",JLabel.CENTER);
		l1.setFont(new Font("Helvetica", Font.BOLD, 20));
		l2=new JLabel("Password : ",JLabel.CENTER);
		l2.setFont(new Font("Helvetica", Font.BOLD, 20));
		JLabel Register=new JLabel("IF YOU ARE NEW, ");
		Register.setFont(new Font("Helvetica", Font.BOLD, 15));
		JLabel Seller=new JLabel("IF SELLER");
		Seller.setFont(new Font("Helvetica", Font.BOLD, 15));
		t1=new JTextField();
		t2=new JPasswordField();
		show = new JCheckBox("show");
		show.setOpaque(false);
		register=new JButton("REGISTER");
		register.setFont(new Font("Helvetica", Font.BOLD, 14));
		seller=new JButton("SELLER");
		seller.setFont(new Font("Helvetica", Font.BOLD, 14));
		t2.setEchoChar('\u26ab');
		login=new JButton("Login");
		login.setBackground(Color.gray);
		login.setForeground(Color.white);
		register.setBackground(Color.gray);
		register.setForeground(Color.white);
		seller.setBackground(Color.gray);
		seller.setForeground(Color.white);
		l1.setBounds(100,100,150,25);
		show.setBounds(470,150,100,25);
		l2.setBounds(100,150,150,25);
		t1.setBounds(250,100,200,25);
		t2.setBounds(250,150,200,25);
		login.setBounds(250,200, 200, 35);
		Register.setBounds(105,300,250,30);
		register.setBounds(240,302,120,25);
		Seller.setBounds(370,300,150,30);
		seller.setBounds(460,302,150,25);
		seller.addActionListener(this);
		register.addActionListener(this);
		
		login.addActionListener(this); // invokes 45
		main_panel.add(show);
		main_panel.add(l1);
		main_panel.add(l2);
		main_panel.add(t1);
		main_panel.add(t2);
		main_panel.add(login);
		main_panel.add(Register);
		main_panel.add(register);
		main_panel.add(Seller);
		main_panel.add(seller);
		
		ImageIcon image1=new ImageIcon("oldbackground.jpg");
		Image image2=image1.getImage();
		Image image=image2.getScaledInstance(1200, 870, Image.SCALE_SMOOTH); // LAST is used for the quality of image (highest quality)
		JLabel background=new JLabel();
		background.setIcon(new ImageIcon(image));
		background.setBounds(0, 0, 1570, 800);
		frame.add(background);
		//frame.getContentPane().setBackground(new Color(94,173,189));
		
		show.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(show.isSelected()) {
					t2.setEchoChar((char)0);
				} else {
					t2.setEchoChar('\u26ab');
				}
				
			}
		});
		
		frame.validate();
		frame.repaint();
		frame.setTitle("ISHOP");
		ImageIcon icon = new ImageIcon("IShop.png");
		frame.setIconImage(icon.getImage());
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==click_here) {
			String em = JOptionPane.showInputDialog("Enter email ID (registered emailID)");
			try {
		        String flag = new Sql().forget(em);
		        if(flag != null){
		        new Gmail().Gmail("19euit065@skcet.ac.in", em, "19euit065@KJV9", "Forgot Password", "We have "
		        				+ "sent your Forgotten Password is - "+ flag);
		        		JOptionPane.showMessageDialog(frame, "Mail sent");
		        	} else {
		        	JOptionPane.showMessageDialog(frame, "Registered Email ID is Not Found");
		        }
			} catch (Exception ep) {
				ep.printStackTrace();
			}
		}
		
		if(e.getSource()==seller) {
			new Seller_login();
			frame.dispose();
		}
		
		boolean errorfound=false;
		if(e.getSource()==login) {
			String regex = "^[\\w!#$%&'+/=?`{|}~^-]+(?:\\.[\\w!#$%&'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(t1.getText());
			
			if(!matcher.matches()) {
				JOptionPane.showMessageDialog(new JFrame(), "EMAIL MUST BE PROPERLY GIVEN");
				errorfound=true;
				return;
			}
			
			else if(t2.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Fill all the details");
				errorfound=true;
			}

			if(!errorfound){
				Customer_bean obj= new Customer_bean();
				obj.setEmail(t1.getText());
				try {
					customer=new Sql().get_customer_information(obj);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				if(obj.getPassword().equals("Not Found")){
					JOptionPane.showMessageDialog(this, "EMAIL NOT REGISTERED");
					errorfound=true;
				}
				else if(!obj.getPassword().equals(t2.getText())){
					JOptionPane.showMessageDialog(this, "ENTER VALID PASSWORD");
					errorfound=true;
				}

			}

			if(!errorfound){
				new Catalogue_buyer(customer);
				frame.dispose();
			}

		}
		
		if(e.getSource()==register) {
			new Buyer_registeration();
			frame.dispose();
		}
	}
	public static void main(String[] args) {
		new Buyer_Login();
		Server.start(6050);
	}

}
