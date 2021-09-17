package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class Seller_login extends JFrame implements ActionListener {

	public static void main(String[] args) {
		new Seller_login();
	}

	JPanel headerpanel, midpanel;
	JLabel heading;
	JTextField email, password;
	JButton submit, register,forgot_password;
	JCheckBox show;
	JFrame j;

	Seller_login() {
		j=this;
		
		setTitle("ISHOP");
		ImageIcon icon = new ImageIcon("IShop.png");
		setIconImage(icon.getImage());
		
		headerpanel = new JPanel();

		heading = new JLabel("ISHOP - SHOP THE FUTURE HERE");
		heading.setFont(new Font("Verdana", Font.BOLD, 17));
		heading.setForeground(Color.black);
		heading.setBounds(320, 10, 500, 50);
		headerpanel.add(heading);

		headerpanel.setBounds(110, 20, 960, 70);
		headerpanel.setBackground(new Color(220, 222, 220, 210));//headerpanel.setBackground(new Color(220,230,240,210)); //new Color(220,220,220)
		headerpanel.setBorder(new LineBorder(Color.black, 1, true));
		headerpanel.setLayout(null);
		//headerpanel.setOpaque(false);
		add(headerpanel);


		midpanel = new JPanel();

		JLabel heading2 = new JLabel("SELLER LOGIN");
		heading2.setBounds(380, 15, 150, 50);
		heading2.setFont(new Font("TimesRoman", Font.ITALIC + Font.BOLD + Font.HANGING_BASELINE, 20));
		midpanel.add(heading2);

		JLabel Email = new JLabel("EMAIL");
		Email.setBounds(240, 125, 150, 50);
		Email.setFont(new Font("TimesRoman", Font.ITALIC + Font.BOLD, 17));
		midpanel.add(Email);

		email = new JTextField();
		email.setBounds(440, 125, 180, 40);
		midpanel.add(email);

		JLabel Password = new JLabel("PASSWORD");
		Password.setBounds(240, 195, 150, 50);
		Password.setFont(new Font("TimesRoman", Font.ITALIC + Font.BOLD, 17));
		midpanel.add(Password);
		
		password = new JPasswordField();
		password.setBounds(440, 205, 180, 40);
		((JPasswordField) password).setEchoChar('\u26ab');
		midpanel.add(password);
		
		show = new JCheckBox("show");
		show.setBounds(640, 205, 100, 40);
		show.setOpaque(false);
		midpanel.add(show);
		
		show.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(show.isSelected()) {
					((JPasswordField) password).setEchoChar((char)0);
				} else {
					((JPasswordField) password).setEchoChar('\u26ab');
				}
				
			}
		});
		
		forgot_password=new JButton("Forgot Password");
		forgot_password.setBounds(440, 255, 150, 18);
		forgot_password.setFont(new Font("TimesRoman", Font.ITALIC + Font.BOLD, 13));
		forgot_password.setBackground(new Color(220,222,220,210));
		forgot_password.setOpaque(false);
		midpanel.add(forgot_password);
		
		forgot_password.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String em = JOptionPane.showInputDialog("Enter email ID (registered emailID)");
				try {
			        String flag = new Sql().forgets(em);
			        if(flag != null){
			        new Gmail().Gmail("19euit065@skcet.ac.in", em, "19euit065@KJV9", "Forgot Password", "We have "
			        				+ "sent your Forgotten Password is - "+ flag);
			        		JOptionPane.showMessageDialog(j, "Mail sent");
			        	} else {
			        	JOptionPane.showMessageDialog(j, "Registere Email ID is Not Found");
			        }
				} catch (Exception e) {
					e.printStackTrace();
				}
		        
			}
		});
		

		register = new JButton("REGISTER");
		register.setFont(new Font("TimesRoman", Font.ITALIC + Font.BOLD, 9));
		register.setBounds(575, 313, 100, 15);
		register.setBackground(new Color(220,222,220,210));
		register.setOpaque(false);
		midpanel.add(register);

		register.addActionListener(this);

		JLabel registering = new JLabel("IF YOU ARE NEW HERE,");
		registering.setBounds(440, 300, 180, 40);
		registering.setFont(new Font("TimesRoman", Font.ITALIC + Font.BOLD, 11));
		midpanel.add(registering);
		

		submit = new JButton("SUBMIT");
		submit.setBounds(380, 355, 140, 40);
		submit.setForeground(Color.blue);
		submit.setOpaque(false);
		midpanel.add(submit);

		submit.addActionListener(this);

		midpanel.setBounds(120, 170, 950, 490);
		midpanel.setBackground(new Color(220, 222, 220, 210)); //new Color(220,220,220)
		midpanel.setBorder(new LineBorder(Color.black, 1, true));
		//midpanel.setOpaque(false);
		midpanel.setPreferredSize(new Dimension(5000, 900));
		midpanel.setLayout(null);
		add(midpanel);


		setBounds(300, 10, 1200, 800);
		setLayout(null);
		setVisible(true);
		//getContentPane().setBackground(Color.black);
		ImageIcon image1=new ImageIcon("oldbackground.jpg");
		Image image2=image1.getImage();
		Image image=image2.getScaledInstance(1200, 870, Image.SCALE_SMOOTH); // LAST is used for the quality of image (highest quality)
		JLabel background=new JLabel();
		background.setIcon(new ImageIcon(image));
		background.setBounds(0, 0, 1570, 800);
		getContentPane().add(background);
		validate();
		repaint();
		
		addMouseMotionListener(new MouseMotionListener() {

			public void mouseDragged(MouseEvent e) {

			}

			public void mouseMoved(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();

				String msg = x + "  " + y;
				//setTitle(msg);
			}
		});
		
		
	}


	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == register) {
			new Seller_registeration();
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}

		if (e.getSource() == submit) {

			boolean errorfound = false;
			String Email = email.getText();
			String Password = password.getText();

			String regex = "^[\\w!#$%&'+/=?`{|}~^-]+(?:\\.[\\w!#$%&'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(Email);

			if (!matcher.matches()) {
				JOptionPane.showMessageDialog(new JFrame(), "EMAIL MUST BE PROPERLY GIVEN");
				errorfound = true;
				return;
			}

			if(!errorfound){
				Seller_bean o=new Seller_bean();
				o.setEmail(Email);
				try {
					Seller_bean obj=new Sql().is_SellerRegistered(o);
					if(obj.getPassword().equals("Not Found")){
						JOptionPane.showMessageDialog(new JFrame(), "EMAIL IS NOT REGISTERED");
						errorfound = true;
						return;
					}
					else{
						if(!obj.getPassword().equals(Password)) {
							JOptionPane.showMessageDialog(new JFrame(), "ENTER VALID PASSWORD");
							errorfound = true;
							return;
						}
						else {
							ArrayList<Product_bean> li=new Sql().get_products_inshop(obj.getSeller_id());
							new Seller_shop(li,obj);
							this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}

	}

}
