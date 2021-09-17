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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import client.*;

public class Seller_shop extends JFrame implements ActionListener{

	public static void main(String[] args) {
		
	}
	
	JPanel headerpanel,leftpanel,midpanel;
	JLabel heading,shopname,pr_id;
	JButton history,logout,profile,back,chat,add_items,pr_image;
	JLabel pr_name;
	Seller_bean seller;
	
	Seller_shop(ArrayList<Product_bean> products,Seller_bean seller){
		
		setTitle("ISHOP");
		ImageIcon icon = new ImageIcon("IShop.png");
		setIconImage(icon.getImage());

		this.seller=seller;
		headerpanel=new JPanel();
		
		heading=new JLabel("ISHOP - SHOP THE FUTURE HERE");
		heading.setFont(new Font("Verdana",Font.BOLD,17));
		heading.setForeground(Color.black);
		heading.setBounds(320,10,500,50);
		headerpanel.add(heading);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
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
		
		chat=new JButton("CHAT");
		chat.setFont(new Font("Verdana",Font.BOLD,12));
		chat.setBackground(Color.BLACK);
		chat.setForeground(Color.WHITE);
		chat.setBounds(35,230,100,45);
		leftpanel.add(chat);
		
		
		logout=new JButton("LOGOUT");
		logout.setFont(new Font("Verdana",Font.BOLD,12));
		logout.setBackground(Color.BLACK);
		logout.setForeground(Color.WHITE);
		logout.setBounds(35,330,100,45);
		leftpanel.add(logout);
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Seller_login();
				dispose();
			}
			
		});
		
		leftpanel.setBounds(50, 203, 170, 400);
		leftpanel.setBackground(new Color(220,222,220,210)); //new Color(220,220,220)
		leftpanel.setBorder(new LineBorder(Color.black,1,true));
		leftpanel.setLayout(null);
		//leftpanel.setOpaque(false);
		add(leftpanel);
		
		
		/*state=new JLabel("STATE");    
		state.setBounds(865,110,150,40);
		add(state);
		
		district=new JLabel("DISTRICT");
		district.setBounds(1055,107,160,40);
		add(district);
		*/
		
		
		midpanel=new JPanel();
		
		shopname=new JLabel(seller.getShop_name(),SwingConstants.CENTER);
		shopname.setFont(new Font("TimesRoman",Font.BOLD,19));
		shopname.setForeground(Color.black);
		shopname.setForeground(Color.blue);
		shopname.setBorder(new CompoundBorder());
		shopname.setBounds(500,100,250,50);
		add(shopname);
		
		JLabel Seller_id=new JLabel("SELLER ID - ");
		Seller_id.setFont(new Font("TimesRoman",Font.BOLD,14));
		Seller_id.setForeground(Color.black);
		Seller_id.setBorder(new CompoundBorder());
		Seller_id.setBounds(850,100,250,50);
		add(Seller_id);
		
		JLabel seller_id=new JLabel(seller.getSeller_id());
		seller_id.setFont(new Font("TimesRoman",Font.BOLD,14));
		seller_id.setForeground(Color.black);
		seller_id.setBorder(new CompoundBorder());
		seller_id.setBounds(950,100,250,50);
		add(seller_id);
		
		int number_of_items=products.size();
		
		int x=17,y=10;
		
		for(int i=0;i<number_of_items;i++) {
			
			Product_bean obj=products.get(i);
			JPanel product=new JPanel();
			product.setLayout(null);
			
			String str="Mobile advertising may seem similar to mobile marketing, but the two are inherently different. Mobile marketing is a more general term that encompasses mobile advertising. While it uses personal data collected, mobile marketing also makes use of technology such as location services to personalize ads based on user preference, habits, or location. This means that some mobile advertisements may only appear when a mobile user is in close proximity to a certain store or service provider.";
			String s="";
			if(str.length()>200) {
				s=str.substring(0,200);
				s=s+"....";
			}
			else {
				s=str;
			}
			
			JTextArea pr_description=new JTextArea(obj.getDescription());
			pr_description.setEditable(false);
			pr_description.setBounds(200, 30, 500, 70);
			pr_description.setLineWrap(true);
			pr_description.setWrapStyleWord(true);
			pr_description.setBackground(new Color(0,0,0,0));
			pr_description.setFont(new Font("TimesRoman",Font.PLAIN,15));
			product.add(pr_description);
			
			
			double price=obj.getPrice();
			JLabel pr_price=new JLabel("PRICE : $"+price);
			pr_price.setFont(new Font("TimesRoman",Font.BOLD,15));
			pr_price.setBounds(200, 100, 200, 30);
			product.add(pr_price);
			
			int quantity=obj.getQuantity();
			JLabel pr_quantity=new JLabel("STOCK : "+quantity);
			pr_quantity.setFont(new Font("TimesRoman",Font.BOLD,15));
			pr_quantity.setBounds(400, 100, 200, 30);
			product.add(pr_quantity);
			
			int offer=(int)obj.getOffer();
			JLabel pr_offer=new JLabel("UNDER OFFER OF "+offer+"%");
			pr_offer.setFont(new Font("TimesRoman",Font.BOLD,15));
			pr_offer.setBounds(600,100,200,30);
			product.add(pr_offer);
			
			String product_id=obj.getProduct_id();
			pr_id=new JLabel("PRODCUT ID - "+product_id);
			pr_id.setFont(new Font("TimesRoman",Font.BOLD,15));
			pr_id.setBounds(275,150,400,30);
			product.add(pr_id);
			
			pr_image=new JButton();
			pr_image.setBounds(20, 25, 120, 120);
			byte[] img=obj.getImg_array();
			ImageIcon image=new ImageIcon(img);
			Image im=image.getImage();
			Image myImg=im.getScaledInstance(pr_image.getWidth(),pr_image.getHeight(),Image.SCALE_SMOOTH);
			ImageIcon newImage=new ImageIcon(myImg);
			pr_image.setIcon(newImage);
			//Icon icon1 = new ImageIcon(obj.getImagePath());
			pr_image.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new Updating_Stock(obj,seller);
						dispose();
					}
			});
			product.add(pr_image);
			
			pr_name=new JLabel(obj.getProduct_name(),SwingConstants.CENTER);
			pr_name.setBounds(20, 95, 120, 120);
			pr_name.setFont(new Font("TimesRoman",Font.BOLD,15));
			product.add(pr_name);
			
			product.setBounds(x, y, 800, 200);
			product.setBackground(new Color(224,224,222,212));
			
			y=y+210;
			product.setBorder(new LineBorder(Color.black,1,true));
			midpanel.add(product);
		}
		
		JPanel extra=new JPanel();
		extra.setLayout(null);
		extra.setBounds(x, y, 800, 200);
		extra.setBackground(new Color(220,230,240,150));
		extra.setBorder(new LineBorder(Color.black,1,true));
		midpanel.add(extra);
		
		add_items=new JButton("ADD MORE PRODUCTS");
		add_items.setFont(new Font("TimesRoman",Font.BOLD,15));
		add_items.setOpaque(false);
		add_items.setBackground(new Color(0,0,0,10));
    	add_items.setBorder(null);
		add_items.setBounds(150, 60, 500, 50);
		extra.add(add_items);
		add_items.addActionListener(this);
		
		
		final JScrollPane scrollPanel = new JScrollPane(
			    midpanel,
			    ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
			);
		
		scrollPanel.setBounds(280, 170, 850, 490);
		
		
		
		midpanel.setBounds(280, 170, 850, 490);
		midpanel.setBackground(new Color(220,222,220,210)); //new Color(220,220,220)
		//midpanel.setBorder(new LineBorder(Color.black,1,true));
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

		history.addActionListener(this);
		profile.addActionListener(this);
		chat.addActionListener(this);
		
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
		
		
		if(e.getSource()==back) {
			// login page for seller 
			new Seller_login();
			dispose();
		}

		if(e.getSource()==history){
			try {
				new SellerHistory(seller);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		if(e.getSource()==profile){
			new Seller_profile(seller);
		}

		if(e.getSource()==add_items) {
			new Adding_products(seller);
			dispose();
		}

		if(e.getSource()==chat){
			new Chat_seller_side(seller.getSeller_id());
		}

	}

}
