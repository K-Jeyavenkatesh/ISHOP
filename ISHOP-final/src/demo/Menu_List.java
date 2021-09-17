package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.*;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

//import sun.jvm.hotspot.runtime.ThreadLocalAllocBuffer;

public class Menu_List extends JFrame implements ActionListener{

	public static void main(String[] args) {
		
	}
	
	JPanel headerpanel,leftpanel,midpanel;
	JLabel heading,genrename,shopname;
	JButton history,cart,logout,profile,back;
	JTextArea pr_description;
	JComboBox state,district;
	JLabel pr_id,pr_name,pr_price,pr_offer;
	JButton pr_image;
	String genre;
	ArrayList<Product_bean> li;
	HashMap<String,ArrayList<String>> map=new HashMap<>();
	Customer_bean customer;
	
	Menu_List(ArrayList<Product_bean> products,String Genre_name,Customer_bean customer){
		
		setTitle("ISHOP");
		ImageIcon icon = new ImageIcon("IShop.png");
		setIconImage(icon.getImage());

		this.customer=customer;

		li=products;
		genre=Genre_name;
		headerpanel=new JPanel();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		heading=new JLabel("ISHOP - SHOP THE FUTURE HERE");
		heading.setFont(new Font("Verdana",Font.BOLD,17));
		heading.setForeground(Color.black);
		heading.setBounds(320,10,500,50);
		headerpanel.add(heading);
		
		headerpanel.setBounds(110, 20, 960, 70);
		headerpanel.setBackground(new Color(220,222,220,210));//headerpanel.setBackground(new Color(220,230,240,210)); //new Color(220,220,220)
		headerpanel.setBorder(new LineBorder(Color.black,1,true));
		headerpanel.setLayout(null);
		//headerpanel.setOpaque(false);
		add(headerpanel);
		
		
		leftpanel=new JPanel();
		
		profile=new JButton("PROFILE");
		profile.setFont(new Font("Verdana",Font.BOLD,12));
		profile.setBackground(Color.BLACK);
		profile.setForeground(Color.WHITE);
		profile.setBounds(35,30,100,45);
		leftpanel.add(profile);
		
		
		history=new JButton("HISTORY");
		history.setFont(new Font("Verdana",Font.BOLD,12));
		history.setBackground(Color.BLACK);
		history.setForeground(Color.WHITE);
		history.setBounds(35,130,100,45);
		leftpanel.add(history);
		
		cart=new JButton("CART");
		cart.setFont(new Font("Verdana",Font.BOLD,12));
		cart.setBackground(Color.BLACK);
		cart.setForeground(Color.WHITE);
		cart.setBounds(35,230,100,45);
		leftpanel.add(cart);
		
		logout=new JButton("LOGOUT");
		logout.setFont(new Font("Verdana",Font.BOLD,12));
		logout.setBackground(Color.BLACK);
		logout.setForeground(Color.WHITE);
		logout.setBounds(35,330,100,45);
		leftpanel.add(logout);
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Buyer_Login();
				dispose();
			}
			
		});
		
		leftpanel.setBounds(50, 203, 170, 400);
		leftpanel.setBackground(new Color(220,222,220,210)); //new Color(220,220,220)
		leftpanel.setBorder(new LineBorder(Color.black,1,true));
		leftpanel.setLayout(null);
		//leftpanel.setOpaque(false);
		add(leftpanel);
		
		
		String a[]={"ALL STATES","Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chattisgarh","Karnataka","Kerala","Tamil Nadu","Telangana","Uttar Pradesh","West Bengal"};
		state=new JComboBox(a);
		state.setBounds(765,110,150,30);
		add(state);
		state.addActionListener(this);
		
		
		map.put("Andhra Pradesh",new ArrayList<>(Arrays.asList("ALL DISTRICTS","Anantapur","Chittoor","East Godavari","Guntur","Krishna","Kurnool","Prakasam")));
        map.put("Arunachal Pradesh",new ArrayList<>(Arrays.asList("ALL DISTRICTS","Anjaw","Changlang","Dibang Valley","East Kameng","East Siang","Itanagar","Kra Daadi","Kurung Kumey")));
        map.put("Assam",new ArrayList<>(Arrays.asList("ALL DISTRICTS","Sivasagar", "Dibrugarh", "Kokrajhar", "Barpeta", "Nalbari", "Baksa", "Bajali", "Kamrup", "Darang", "Nagaon", "Hojai", "Tinsukia", "Dhemaji", "Cachar", "Karimganj and Karbi" ,"Anglong")));
        map.put("Bihar",new ArrayList<>(Arrays.asList("ALL DISTRICTS","Araria","Arwal","Aurangabad","Banka","Begusarai","Bhagalpur","Bhojpur","Buxar")));
        map.put("Chattisgarh",new ArrayList<>(Arrays.asList("ALL DISTRICTS","Ahmedabad","Amreli","Anand","Aravalli","Banaskantha","Bharuch","Bhavnagar","Botad","Mehsana","Morbi","Narmada","Navsari","Panchmahal","Patan","Porbandar","Rajkot")));
        map.put("Karnataka",new ArrayList<>(Arrays.asList("ALL DISTRICTS","Dakshina","Kannada","Davangere","Dharwad","Gadag","Hassan","Haveri","Kalaburagi","Kodagu","Kolar","Koppal","Mandya","Mysuru","Raichur","Ramanagara")));
        map.put("Kerala",new ArrayList<>(Arrays.asList("ALL DISTRICTS","Alappuzha","Ernakulam","Idukki","Kannur","Kasaragod","Kollam","Kottayam","Kozhikode","Malappuram","Palakkad","Pathanamthitta","Thiruvananthapuram","Thrissur","Wayanad")));
        map.put("Tamil Nadu",new ArrayList<>(Arrays.asList("ALL DISTRICTS","Ariyalur","Chengalpatt","Chennai","Coimbatore","Cuddalore","Dharmapuri","Dindigul","Erode","Kallakurichi","Kanchipuram","Kanyakumari","Karur","Krishnagiri","Madurai","Nagapattinam","Namakkal","Nilgiris","Perambalur","Pudukkottai","Ramanathapuram","Ranipet","Salem","Sivaganga","Tenkasi","Thanjavur","Theni","Thoothukudi","Tiruchirappalli","Tirunelveli","Tirupathur","Tiruppur","Tiruvallur","Tiruvannamalai","Tiruvarur","Vellore","Viluppuram","Virudhunagar")));
        map.put("Telangana",new ArrayList<>(Arrays.asList("ALL DISTRICTS","Adilabad","Bhadradri","Kothagudem","Hyderabad","Jagtial","Jangaon","Sangareddy","Siddipet","Suryapet","Vikarabad","Wanaparthy")));
        map.put("Uttar Pradesh",new ArrayList<>(Arrays.asList("ALL DISTRICTS","Auraiya","Azamgarh","Baghpat","Bahraich","Ballia","Balrampur","Banda","Barabanki","Bareilly","Basti","Bhadohi","Bijnor","Budaun","Bulandshahr","Chandauli","Chitrakoot","Deoria","Etah","Etawah","Faizabad","Farrukhabad","Fatehpur","Firozabad")));
        map.put("West Bengal",new ArrayList<>(Arrays.asList("ALL DISTRICTS","Alipurduar","Bankura","Birbhum","Cooch Behar","Dakshin Dinajpur","Darjeeling","Hooghly","Howrah","Jalpaiguri","Jhargram","Kalimpong","Kolkata","Malda","Murshidabad","Nadia","North 24 Parganas","Paschim Medinipur","Paschim","Burdwan","Purba Burdwan","Purba Medinipur","Purulia","South 24 Parganas","Uttar Dinajpur")));
		String[] ao= {"ALL DISTRICTS"};
        district=new JComboBox(ao);
		district.setBounds(965,110,160,30);
		add(district);
		district.addActionListener(this);
		
		
		midpanel=new JPanel();

		midpanel.removeAll();
		try {
			fill(products);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		genrename=new JLabel(Genre_name);
		genrename.setFont(new Font("TimesRoman",Font.BOLD,21));
		genrename.setForeground(Color.blue);
		genrename.setBorder(new CompoundBorder());
		genrename.setBounds(410,100,250,50);
		add(genrename);
		
		
		final JScrollPane scrollPanel = new JScrollPane(
			    midpanel,
			    ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
			);
		
		scrollPanel.setBounds(280, 170, 850, 490);
		
		
		
		midpanel.setBounds(280, 170, 850, 490);
		midpanel.setBackground(new Color(220,222,220,210)); //new Color(220,220,220)
		midpanel.setBorder(new LineBorder(Color.black,1,true));
		//midpanel.setOpaque(false);
		midpanel.setPreferredSize(new Dimension(5000, 5450));
		midpanel.setLayout(null);
		midpanel.setOpaque(false);
		scrollPanel.setOpaque(false);
		scrollPanel.getViewport().setOpaque(false);
		getContentPane().add(scrollPanel);
		
		
		
		back=new JButton("BACK");
		back.setBounds(60, 680, 140, 40);
		back.setForeground(Color.blue);
		back.setOpaque(false);
		add(back);
		
		back.addActionListener(this);
		
		
		ImageIcon image1=new ImageIcon("menu_list&&_seller_shop.jpg");
		Image image2=image1.getImage();
		Image image=image2.getScaledInstance(1200, 870, Image.SCALE_SMOOTH); // LAST is used for the quality of image (highest quality)
		JLabel background=new JLabel();
		background.setIcon(new ImageIcon(image));
		background.setBounds(0, 0, 1570, 800);
		getContentPane().add(background);

		profile.addActionListener(this);
		history.addActionListener(this);
		cart.addActionListener(this);
		
		setBounds(300,10,1200,800);
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
                 //setTitle(msg);
            }
		});    
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==back || e.getSource()==logout) {
			new Catalogue_buyer(customer);
			//this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			dispose();
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

					default:
						district.addItem(new String("ALL DISTRICTS"));
						break;
	                    
	            }

	            midpanel.removeAll();
			try {
				ArrayList<Product_bean> list=new ArrayList<>();
				for(Product_bean p:li){
					boolean flag=true;
					if(!state.getSelectedItem().toString().equals("ALL State"))flag=new Sql().is_crtlocation(p.getSeller_id(),state.getSelectedItem().toString(),district.getSelectedItem().toString());
					if(flag){
						list.add(p);
					}
				}
				fill(list);
				validate();
				repaint();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		if(e.getSource()==district){
			midpanel.removeAll();
			try {
				ArrayList<Product_bean> list=new ArrayList<>();
				for(Product_bean p:li){
					boolean flag=true;
					if(!state.getSelectedItem().toString().equals("ALL State"))flag=new Sql().is_crtlocation(p.getSeller_id(),state.getSelectedItem().toString(),district.getSelectedItem().toString());
					if(flag){
						list.add(p);
					}
				}
				fill(list);
				validate();
				repaint();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

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
		
	}

	public void fill(ArrayList<Product_bean> products){
		int number_of_items=products.size();
		//System.out.println(number_of_items);
		int x=17,y=10;

		for(int i=0;i<number_of_items;i++) {

			Product_bean obj=products.get(i);
			JPanel product=new JPanel();
			product.setLayout(null);

			String str=obj.getDescription();
			String s="";
			if(str.length()>200) {
				s=str.substring(0,200);
				s=s+"....";
			}
			else {
				s=str;
			}

			pr_description=new JTextArea(s);
			pr_description.setEditable(false);
			pr_description.setBounds(200, 30, 500, 70);
			pr_description.setLineWrap(true);
			pr_description.setWrapStyleWord(true);
			pr_description.setBackground(new Color(0,0,0,0));
			pr_description.setFont(new Font("TimesRoman",Font.PLAIN,15));
			product.add(pr_description);


			double price=obj.getPrice();
			pr_price=new JLabel("PRICE : $"+price);
			pr_price.setFont(new Font("TimesRoman",Font.BOLD,15));
			pr_price.setBounds(200, 100, 200, 30);
			product.add(pr_price);



			int offer=(int)obj.getOffer();
			pr_offer=new JLabel("UNDER OFFER OF "+offer+"%");
			pr_offer.setFont(new Font("TimesRoman",Font.BOLD,15));
			pr_offer.setBounds(600,100,200,30);
			product.add(pr_offer);

			JLabel thrla=new JLabel("SHOP NAME - ");
			thrla.setFont(new Font("TimesRoman",Font.BOLD,15));
			thrla.setBounds(300,150,200,30);
			thrla.setForeground(Color.blue);
			product.add(thrla);

			String shop_name=obj.getShop_name();
			shopname=new JLabel(shop_name);
			shopname.setFont(new Font("TimesRoman",Font.BOLD,15));
			shopname.setBounds(410,150,200,30);
			shopname.setForeground(Color.blue);
			product.add(shopname);


			pr_image=new JButton();
			pr_image.setBounds(20, 25, 120, 120);
			byte[] img=obj.getImg_array();
			ImageIcon image=new ImageIcon(img);
			Image im=image.getImage();
			Image myImg=im.getScaledInstance(pr_image.getWidth(),pr_image.getHeight(),Image.SCALE_SMOOTH);
			ImageIcon newImage=new ImageIcon(myImg);
			pr_image.setIcon(newImage);
			Icon icon1 = new ImageIcon(obj.getImagePath());
			pr_image.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Product_View(obj,customer);
				}
			});
			product.add(pr_image);

			pr_name=new JLabel(obj.getProduct_name().toUpperCase());
			pr_name.setFont(new Font("TimesRoman",Font.BOLD,15));
			pr_name.setHorizontalAlignment(JLabel.CENTER);
			pr_name.setBounds(30,150,100,30);
			product.add(pr_name);

			product.setBounds(x, y, 800, 200);
			product.setBackground(new Color(220,222,220,210));
			y=y+210;
			product.setBorder(new LineBorder(Color.black,1,true));
			midpanel.add(product);
		}
	}

}
