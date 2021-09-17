package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

//import frontpage.Error;

public class Seller_registeration extends JFrame implements ActionListener{

	public static void main(String[] args){
		new Seller_registeration();

	}
	
	JPanel headerpanel,midpanel;
	JLabel heading,heading2;
	JLabel Username,Password,Shopname,Email,Mobile_number,Address;
	JComboBox state,district;
	JTextField username,shopname,email,mobile_number;
	JPasswordField password;
	JTextArea address;
	HashMap<String,ArrayList<String>> map=new HashMap<>();
	JButton Submit,Back;
	JCheckBox show;
	
	
	Seller_registeration(){
		setTitle("ISHOP");
		ImageIcon icon = new ImageIcon("IShop.png");
		setIconImage(icon.getImage());
		
		headerpanel=new JPanel();
		
		heading=new JLabel("ISHOP - SHOP THE FUTURE HERE");
		heading.setFont(new Font("Verdana",Font.BOLD,17));
		heading.setForeground(Color.black);
		heading.setBounds(150,10,500,50);
		headerpanel.add(heading);
		
		headerpanel.setBounds(150, 30, 710, 70);
		headerpanel.setBackground(new Color(220,230,240,210));//headerpanel.setBackground(new Color(220,230,240,210)); //new Color(220,220,220)
		headerpanel.setBorder(new LineBorder(Color.white,1,true));
		headerpanel.setLayout(null);
		//headerpanel.setOpaque(false);
		add(headerpanel);
		
		heading2=new JLabel("SELLER REGISTER FORM");
		heading2.setFont(new Font("TimesRoman",Font.BOLD,18));
		heading2.setForeground(Color.white);
		heading2.setBounds(350,110,500,50);
		add(heading2);
		
		midpanel=new JPanel();
		final JScrollPane scrollPanel = new JScrollPane(
			    midpanel,
			    ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
			);
		
		scrollPanel.setBounds(150, 200, 710, 400);
		
		Username=new JLabel("USERNAME");
		Username.setBounds(100,20,100,40);
		Username.setFont(new Font("TimesRoman",Font.BOLD,15));
		Username.setForeground(Color.white);
		midpanel.add(Username);
		
		username=new JTextField();
		username.setBounds(300,20,200,40);
		username.setBackground(Color.white);
		midpanel.add(username);
		
		Password=new JLabel("PASSWORD");
		Password.setBounds(100,100,100,40);
		Password.setForeground(Color.white);
		Password.setFont(new Font("TimesRoman",Font.BOLD,15));
		midpanel.add(Password);
		
		password=new JPasswordField();
		password.setBounds(300,100,200,40);
		password.setBackground(Color.white);
		midpanel.add(password);
		
		
		show = new JCheckBox("show");
		show.setBounds(550,100,100,40);
		show.setOpaque(false);
		show.setForeground(Color.white);
		midpanel.add(show);
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
		
		Shopname=new JLabel("SHOP-NAME");
		Shopname.setForeground(Color.white);
		Shopname.setBounds(100,180,100,40);
		Shopname.setFont(new Font("TimesRoman",Font.BOLD,15));
		midpanel.add(Shopname);
		
		shopname=new JTextField();
		shopname.setBackground(Color.white);
		shopname.setBounds(300,180,200,40);
		midpanel.add(shopname);
		
		Email=new JLabel("EMAIL");
		Email.setForeground(Color.white);
		Email.setBounds(100,260,100,40);
		Email.setFont(new Font("TimesRoman",Font.BOLD,15));
		midpanel.add(Email);
		
		email=new JTextField();
		email.setBounds(300,260,200,40);
		email.setBackground(Color.white);
		midpanel.add(email);
		
		Mobile_number=new JLabel("MOBILE_NUMBER");
		Mobile_number.setForeground(Color.white);
		Mobile_number.setBounds(100,340,200,40);
		Mobile_number.setFont(new Font("TimesRoman",Font.BOLD,15));
		midpanel.add(Mobile_number);
		
		mobile_number=new JTextField();
		mobile_number.setBackground(Color.white);
		mobile_number.setBounds(300,340,200,40);
		midpanel.add(mobile_number);
		
		mobile_number.addKeyListener(new KeyListener() {
		

			@Override
			public void keyTyped(KeyEvent ae) {
				// TODO Auto-generated method stub
				char s=ae.getKeyChar();
				
				//System.out.println(op);
				
				if(Character.isDigit(s)) {
					op=mobile_number.getText();
				//	System.out.println(op);
					
					
				} 
				else {
					ae.consume();
				}
				if(op.length()>12 ) {
					//System.out.println("hii");
					ae.consume();			
				}
				
			}
			String op="";

			@Override
			public void keyPressed(KeyEvent ae) {
				
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		JLabel State=new JLabel("STATE");
		State.setForeground(Color.white);
		State.setBounds(100,420,200,40);
		State.setFont(new Font("TimesRoman",Font.BOLD,15));
		midpanel.add(State);
		
		String a[]={"SELECT STATE","Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chattisgarh","Karnataka","Kerala","Tamil Nadu","Telangana","Uttar Pradesh","West Bengal"};
		 state=new JComboBox(a);    
		 state.setBackground(Color.white);
		state.setBounds(300,420,200,40);
		midpanel.add(state);
		state.addActionListener(this);
		
		JLabel District=new JLabel("DISTRICT");
		District.setBounds(100,500,200,40);
		District.setForeground(Color.white);
		District.setFont(new Font("TimesRoman",Font.BOLD,15));
		midpanel.add(District);
		
		map.put("Andhra Pradesh",new ArrayList<>(Arrays.asList("Anantapur","Chittoor","East Godavari","Guntur","Krishna","Kurnool","Prakasam")));
        map.put("Arunachal Pradesh",new ArrayList<>(Arrays.asList("Anjaw","Changlang","Dibang Valley","East Kameng","East Siang","Itanagar","Kra Daadi","Kurung Kumey")));
        map.put("Assam",new ArrayList<>(Arrays.asList("Sivasagar", "Dibrugarh", "Kokrajhar", "Barpeta", "Nalbari", "Baksa", "Bajali", "Kamrup", "Darang", "Nagaon", "Hojai", "Tinsukia", "Dhemaji", "Cachar", "Karimganj and Karbi" ,"Anglong")));
        map.put("Bihar",new ArrayList<>(Arrays.asList("Araria","Arwal","Aurangabad","Banka","Begusarai","Bhagalpur","Bhojpur","Buxar"))); 
        map.put("Chattisgarh",new ArrayList<>(Arrays.asList("Ahmedabad","Amreli","Anand","Aravalli","Banaskantha","Bharuch","Bhavnagar","Botad","Mehsana","Morbi","Narmada","Navsari","Panchmahal","Patan","Porbandar","Rajkot")));
        map.put("Karnataka",new ArrayList<>(Arrays.asList("Dakshina","Kannada","Davangere","Dharwad","Gadag","Hassan","Haveri","Kalaburagi","Kodagu","Kolar","Koppal","Mandya","Mysuru","Raichur","Ramanagara")));
        map.put("Kerala",new ArrayList<>(Arrays.asList("Alappuzha","Ernakulam","Idukki","Kannur","Kasaragod","Kollam","Kottayam","Kozhikode","Malappuram","Palakkad","Pathanamthitta","Thiruvananthapuram","Thrissur","Wayanad")));
        map.put("Tamil Nadu",new ArrayList<>(Arrays.asList("Ariyalur","Chengalpatt","Chennai","Coimbatore","Cuddalore","Dharmapuri","Dindigul","Erode","Kallakurichi","Kanchipuram","Kanyakumari","Karur","Krishnagiri","Madurai","Nagapattinam","Namakkal","Nilgiris","Perambalur","Pudukkottai","Ramanathapuram","Ranipet","Salem","Sivaganga","Tenkasi","Thanjavur","Theni","Thoothukudi","Tiruchirappalli","Tirunelveli","Tirupathur","Tiruppur","Tiruvallur","Tiruvannamalai","Tiruvarur","Vellore","Viluppuram","Virudhunagar")));
        map.put("Telangana",new ArrayList<>(Arrays.asList("Adilabad","Bhadradri","Kothagudem","Hyderabad","Jagtial","Jangaon","Sangareddy","Siddipet","Suryapet","Vikarabad","Wanaparthy")));
        map.put("Uttar Pradesh",new ArrayList<>(Arrays.asList("Auraiya","Azamgarh","Baghpat","Bahraich","Ballia","Balrampur","Banda","Barabanki","Bareilly","Basti","Bhadohi","Bijnor","Budaun","Bulandshahr","Chandauli","Chitrakoot","Deoria","Etah","Etawah","Faizabad","Farrukhabad","Fatehpur","Firozabad")));    
        map.put("West Bengal",new ArrayList<>(Arrays.asList("Alipurduar","Bankura","Birbhum","Cooch Behar","Dakshin Dinajpur","Darjeeling","Hooghly","Howrah","Jalpaiguri","Jhargram","Kalimpong","Kolkata","Malda","Murshidabad","Nadia","North 24 Parganas","Paschim Medinipur","Paschim","Burdwan","Purba Burdwan","Purba Medinipur","Purulia","South 24 Parganas","Uttar Dinajpur")));
		String[] ao= {"SELECT STATE FIRST"};
        district=new JComboBox(ao);
        district.setBackground(Color.white);
		district.setBounds(300,500,160,40);
		midpanel.add(district);
		
		Address=new JLabel("ADDRESS");
		Address.setBounds(100,580,200,40);
		Address.setForeground(Color.white);
		Address.setFont(new Font("TimesRoman",Font.BOLD,15));
		midpanel.add(Address);
		
		address=new JTextArea();
		address.setBounds(300,580,300,100);
		address.setBackground(Color.white);
		midpanel.add(address);
		
		Submit=new JButton("SUBMIT");
		Submit.setBounds(230,760,100,30);
		midpanel.add(Submit);
		Submit.addActionListener(this);
		validate();
		repaint();
		
		midpanel.setBounds(150, 200, 710, 400);
		midpanel.setBackground(new Color(220,230,240,210)); //new Color(220,220,220)
		midpanel.setBorder(new LineBorder(Color.white,1,true));
		midpanel.setPreferredSize(new Dimension(5000, 900));
		midpanel.setOpaque(false);
		scrollPanel.setOpaque(false);
		scrollPanel.getViewport().setOpaque(false);
		midpanel.setLayout(null);
		
		
		
		
		add(scrollPanel);
		
		Back=new JButton("BACK");
		Back.setBounds(60, 680, 140, 40);
		Back.setForeground(Color.blue);
		Back.setOpaque(false);
		add(Back);
		
		Back.addActionListener(this);
		
		ImageIcon image1=new ImageIcon("registration_bg.jpg");
		Image image2=image1.getImage();
		Image image=image2.getScaledInstance(1000, 870, Image.SCALE_SMOOTH); // LAST is used for the quality of image (highest quality)
		JLabel background=new JLabel();
		background.setIcon(new ImageIcon(image));
		background.setBounds(0, 0, 1570, 800);
		getContentPane().add(background);
		
		setBounds(300,10,1000,800);
		setLayout(null);
		setVisible(true);
		//getContentPane().setBackground(Color.black);
		
		addMouseMotionListener(new MouseMotionListener() {

            public void mouseDragged(MouseEvent e) {
                
            }
            public void mouseMoved(MouseEvent e) {
            	 int x=e.getX();
                 int y=e.getY();

                 String msg=x+"  "+y;
              //   setTitle(msg);
            }
		});    

	}
	
	public void actionPerformed(ActionEvent e) {
		boolean errorfound=false;
		
		if(e.getSource()==Back) {
			new Seller_login();
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		}
	
		if(e.getSource()==Submit) {
			//username unique
			
			String Username=username.getText();
			String Password=password.getText();
			String Shopname=shopname.getText();
			String Email=email.getText();
			String Mobile_number=mobile_number.getText();
			String State=(String) state.getSelectedItem();
			String District=(String) state.getSelectedItem();
			String Address=address.getText();
			
			
			if(Username.length()==0 || Password.length()==0 || Shopname.length()==0 || Email.length()==0 || Mobile_number.length()==0 || State.length()==0 || District.length()==0 || Address.length()==0) {
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
				Seller_bean o=new Seller_bean();
				o.setEmail(Email);
				o.setMobile_no(Mobile_number);
				try {
					int n=new Sql().check_SellerRegistered(o);
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
				Seller_bean obj=new Seller_bean();
				try {
					String str=new Sql().get_SellerId();
					obj.setSeller_id("sid"+str);
					obj.setUsername(username.getText());
					obj.setPassword(password.getText());
					obj.setEmail(email.getText());
					obj.setShop_name(shopname.getText());
					obj.setMobile_no(mobile_number.getText());
					obj.setAddress(address.getText());
					obj.setState(state.getSelectedItem().toString());
					obj.setDistrict(district.getSelectedItem().toString());
					new Sql().registerSeller(obj);
				} catch (Exception ex) {
					ex.printStackTrace();
					errorfound=true;
				}
			}

			if(!errorfound) {
				JOptionPane.showMessageDialog(new JFrame(), "ADDED SUCCESSFULLY");
				errorfound=true;
				new Seller_login();
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			}
			
		}
		
		
		if(e.getSource()==state) {
			 String str=state.getSelectedItem().toString();
	            district.removeAllItems();
	            switch (str){
	                case "Andhra Pradesh":
	                    ArrayList<String> dis=map.get(str);
	                    for(String i:dis)
	                    district.addItem(i);
	                    break;

	                case "Arunachal Pradesh":
	                    ArrayList<String> dis2=map.get(str);
	                    for(String i:dis2)
	                    district.addItem(i);
	                    break;
	                    
	                case "Assam":
	                    ArrayList<String> dis21=map.get(str);
	                    for(String i:dis21)
	                    district.addItem(i);
	                    break;

	                case "Bihar":
	                    ArrayList<String> dis3=map.get(str);
	                    for(String i:dis3)
	                    district.addItem(i);
	                    break;
	                    
	                case "Chattisgarh":
	                    ArrayList<String> dis4=map.get(str);
	                    for(String i:dis4)
	                    district.addItem(i);
	                    break;
	                    
	                case "Karnataka":
	                    ArrayList<String> dis5=map.get(str);
	                    for(String i:dis5)
	                    district.addItem(i);
	                    break;

	                case "Kerala":
	                    ArrayList<String> dis6=map.get(str);
	                    for(String i:dis6)
	                    district.addItem(i);
	                    break;
	                    
	                case "Tamil Nadu":
	                    ArrayList<String> dis7=map.get(str);
	                    for(String i:dis7)
	                    district.addItem(i);
	                    break;

	                case "Telangana":
	                    ArrayList<String> dis8=map.get(str);
	                    for(String i:dis8)
	                    district.addItem(i);
	                    break;
	                    
	                case "Uttar Pradesh":
	                    ArrayList<String> dis9=map.get(str);
	                    for(String i:dis9)
	                    district.addItem(i);
	                    break;

	                case "West Bengal":
	                    ArrayList<String> dis0=map.get(str);
	                    for(String i:dis0)
	                    district.addItem(i);
	                    break;
	                    
	            }
		}
		
	}


	}


