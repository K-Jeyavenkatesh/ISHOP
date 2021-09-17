package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

//import frontpage.Error;

public class Seller_profile extends JFrame implements ActionListener{

	public static void main(String[] args){
		//new demo.Seller_profile();

	}
	
	JFrame j1;
	JPanel headerpanel,midpanel;
	JLabel heading,heading2;
	JLabel Username,Password,Shopname,Email,Mobile_number,Address;
	JComboBox state,district;
	JTextField username,password,shopname,email,mobile_number;
	JTextArea address;
	HashMap<String,ArrayList<String>> map=new HashMap<>();
	JButton Submit,Back,Edit;
	Seller_bean seller;
	
	
	Seller_profile(Seller_bean seller){
		
		setTitle("ISHOP");
		ImageIcon icon = new ImageIcon("IShop.png");
		setIconImage(icon.getImage());

		this.seller=seller;
		this.j1 = this;
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
		
		heading2=new JLabel("SELLER - PROFILE");
		heading2.setFont(new Font("TimesRoman",Font.BOLD,19));
		heading2.setForeground(Color.black);
		heading2.setBounds(390,100,500,50);
		add(heading2);
		
		midpanel=new JPanel();
		
		
		Username=new JLabel("USERNAME");
		Username.setBounds(100,20,100,40);
		Username.setFont(new Font("TimesRoman",Font.BOLD,15));
		midpanel.add(Username);
		
		username=new JTextField(seller.getUsername());
		username.setBounds(300,20,200,40);
		username.setBorder(new LineBorder(Color.BLACK));
		username.setEditable(false);
		midpanel.add(username);
		
		Password=new JLabel("PASSWORD");
		Password.setBounds(100,70,100,40);
		Password.setFont(new Font("TimesRoman",Font.BOLD,15));
		midpanel.add(Password);
		
		password=new JTextField(seller.getPassword());
		password.setBounds(300,70,200,40);
		password.setEditable(false);
		password.setBorder(new LineBorder(Color.BLACK));
		midpanel.add(password);
		
		Email=new JLabel("EMAIL");
		Email.setBounds(100,120,100,40);
		Email.setFont(new Font("TimesRoman",Font.BOLD,15));
		midpanel.add(Email);
		
		email=new JTextField(seller.getEmail());
		email.setBounds(300,120,200,40);
		email.setEditable(false);
		email.setBorder(new LineBorder(Color.BLACK));
		midpanel.add(email);
		
		Mobile_number=new JLabel("MOBILE");
		Mobile_number.setBounds(100,170,200,40);
		Mobile_number.setFont(new Font("TimesRoman",Font.BOLD,15));
		midpanel.add(Mobile_number);
		
		mobile_number=new JTextField(seller.getMobile_no());
		mobile_number.setBounds(300,170,200,40);
		mobile_number.setEditable(false);
		mobile_number.setBorder(new LineBorder(Color.BLACK));
		midpanel.add(mobile_number);
		
		Shopname=new JLabel("SHOP-NAME");
		Shopname.setBounds(100,220,100,40);
		Shopname.setFont(new Font("TimesRoman",Font.BOLD,15));
		midpanel.add(Shopname);
		
		shopname=new JTextField(seller.getShop_name());
		shopname.setEditable(false);
		shopname.setBorder(new LineBorder(Color.BLACK));
		shopname.setBounds(300,220,200,40);
		midpanel.add(shopname);
		
		
		
		JLabel State=new JLabel("STATE");
		State.setBounds(100,270,180,40);
		State.setFont(new Font("TimesRoman",Font.BOLD,15));
		midpanel.add(State);
		
		String a[]={"SELECT STATE","Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chattisgarh","Karnataka","Kerala","Tamil Nadu","Telangana","Uttar Pradesh","West Bengal"};
		a[0] = seller.getState(); 
		state=new JComboBox(a);    
		state.setBounds(300,270,200,40);
		state.setEnabled(false);
		state.setBorder(new LineBorder(Color.BLACK));
		midpanel.add(state);
		state.addActionListener(this);
		
		JLabel District=new JLabel("DISTRICT");
		District.setBounds(100,320,200,40);
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
		String[] ao= {seller.getDistrict()};
		
        district=new JComboBox(ao);
		district.setBounds(300,320,160,40);
		district.setBorder(new LineBorder(Color.BLACK));
		district.setEnabled(false);
		midpanel.add(district);
		
		Address=new JLabel("ADDRESS");
		Address.setBounds(100,380,200,40);
		Address.setFont(new Font("TimesRoman",Font.BOLD,15));
		midpanel.add(Address);
		
		address=new JTextArea(seller.getAddress());
		address.setBounds(300,380,300,80);
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
				shopname.setEditable(true);
				state.setEnabled(true);
				email.setEditable(true);
				district.setEnabled(true);
				address.setEditable(true);
				Submit.setBounds(800,680,100,30);
				add(Submit);
				j1.validate();
				j1.repaint();
				Submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(new JFrame(), "CREDENTIALS SAVED");
						dispose();
					}
				});
				
			}
		});
		
		
		midpanel.setBounds(150, 150, 710, 500);
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

		Submit.addActionListener(this);
		Back.addActionListener(this);
		
		ImageIcon image1=new ImageIcon("history.jpg");
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
		
		    

	}
	
	boolean errorfound=false;
	
	public void actionPerformed(ActionEvent e) {
		errorfound=false;
		
		if(e.getSource()==Back) {
			dispose();
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
					int n=new Sql().is_UpdateSellerRegistered(o);
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
					obj.setSeller_id(seller.getSeller_id());
					obj.setUsername(username.getText());
					obj.setPassword(password.getText());
					obj.setEmail(email.getText());
					obj.setShop_name(shopname.getText());
					obj.setMobile_no(mobile_number.getText());
					obj.setAddress(address.getText());
					obj.setState(state.getSelectedItem().toString());
					obj.setDistrict(district.getSelectedItem().toString());
					new Sql().update_Seller(obj);
					seller=obj;
				} catch (Exception ex) {
					ex.printStackTrace();
					errorfound=true;
				}
			}

			if(!errorfound) {
				JOptionPane.showMessageDialog(new JFrame(), "ADDED SUCCESSFULLY");
				errorfound=true;
				dispose();
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


