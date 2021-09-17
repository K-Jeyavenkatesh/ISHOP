package demo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import payment_gateway.*;
import server.Server;
import client.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Product_View extends JFrame implements ActionListener{

//	public static void main(String[] args) {
//		new demo.Product_View();
//	}
	
	JPanel headerpanel;
	static JPanel midpanel;
	JTextArea pr_description;
	JLabel heading,heading2,pr_name,pr_offers,pr_id,shop_name;
	static JLabel pr_price;
	JButton Back,pr_image,buy_now,add_to_cart,bargain;
	public static Customer_bean customer;
	public static Product_bean product;
	static double final_price=0;
	
	SpinnerModel value;
	JSpinner quantity;                 
	
	public Product_View(Product_bean Product, Customer_bean Customer){
		setTitle("ISHOP");
		ImageIcon icon = new ImageIcon("IShop.png");
		setIconImage(icon.getImage());

		customer=Customer;
		product=Product;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		headerpanel=new JPanel();
		
		heading=new JLabel("ISHOP - SHOP THE FUTURE HERE");
		heading.setFont(new Font("Verdana",Font.BOLD,17));
		heading.setForeground(Color.black);
		heading.setBounds(220,10,500,50);
		headerpanel.add(heading);
		
		headerpanel.setBounds(150, 10, 710, 70);
		headerpanel.setBackground(new Color(220,230,240,210));//headerpanel.setBackground(new Color(220,230,240,210)); //new Color(220,220,220)
		headerpanel.setBorder(new LineBorder(Color.black,1,true));
		headerpanel.setLayout(null);
		//headerpanel.setOpaque(false);
		add(headerpanel);
		
		
		midpanel=new JPanel();

		pr_image=new JButton();
		pr_image.setBounds(250,50,200,100);
		byte[] img=product.getImg_array();
		ImageIcon image=new ImageIcon(img);
		Image im=image.getImage();
		Image myImg=im.getScaledInstance(pr_image.getWidth(),pr_image.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon newImage=new ImageIcon(myImg);
		pr_image.setIcon(newImage);
		pr_image.setBorder(new LineBorder(Color.black,1,true));

		midpanel.add(pr_image);
		
		pr_name=new JLabel(product.getProduct_name(),SwingConstants.CENTER);
		pr_name.setFont(new Font("TimesRoman",Font.BOLD,15));
		pr_name.setForeground(Color.black);
		pr_name.setBounds(250, 160, 200, 20);
		midpanel.add(pr_name);
		
		
		
		pr_price=new JLabel("Rs  "+product.getPrice());
		pr_price.setFont(new Font("TimesRoman",Font.BOLD,21));
		pr_price.setBounds(280, 220, 150, 30);
		midpanel.add(pr_price);
		
		pr_offers=new JLabel(String.valueOf(product.getOffer())+"% on offer");
		pr_offers.setFont(new Font("TimesRoman",Font.BOLD,15));
		pr_offers.setBounds(410, 220, 150, 30);
		midpanel.add(pr_offers);
		
		String s=product.getDescription();
		if(s.length()>700) {
			s=s.substring(0,500)+"....";
		}
		
		pr_description=new JTextArea(s);
		pr_description.setEditable(false);
		pr_description.setFont(new Font("TimesRoman",Font.PLAIN,15));
		pr_description.setLineWrap(true);
		pr_description.setWrapStyleWord(true);
		pr_description.setOpaque(false);
		pr_description.setBounds(70, 280, 550, 190);
		pr_description.setBorder(new LineBorder(new Color(220,222,220,210)));
		
		midpanel.add(pr_description);
		
		shop_name=new JLabel("SOLD BY "+product.getShop_name().toUpperCase() +"   ",SwingConstants.CENTER);
		shop_name.setFont(new Font("TimesRoman",Font.BOLD,15));
		shop_name.setBounds(180, 440, 350, 30);
		midpanel.add(shop_name);
		
		value =   new SpinnerNumberModel(1,1,10,1); //initial value   //minimum value  //maximum value   //step  
		quantity = new JSpinner(value);   
		quantity.setBackground(new Color(220,230,240,210));
		quantity.setBounds(50, 500, 100, 20);
		midpanel.add(quantity);
		
		
		
		buy_now=new JButton("BUY NOW");
		buy_now.setFont(new Font("TimesRoman",Font.BOLD,12));
		buy_now.setBounds(210, 500, 100, 20);
		midpanel.add(buy_now);
		
		add_to_cart=new JButton("ADD TO CART");
		add_to_cart.setFont(new Font("TimesRoman",Font.BOLD,12));
		add_to_cart.setBounds(380, 500, 130, 20);
		midpanel.add(add_to_cart);
		
		bargain=new JButton("BARGAIN");
		bargain.setFont(new Font("TimesRoman",Font.BOLD,12));
		bargain.setBounds(580, 500, 100, 20);
		midpanel.add(bargain);
		
		
		midpanel.setBounds(150, 100, 710, 580);
		midpanel.setBackground(new Color(220,230,240,210)); //new Color(220,220,220)
		midpanel.setBorder(new LineBorder(Color.black,1,true));
		midpanel.setPreferredSize(new Dimension(5000, 900));
		//midpanel.setOpaque(false);
		midpanel.setLayout(null);
		add(midpanel);
		
		Back = new JButton("BACK");
		Back.setBounds(430, 700, 130, 20);
		Back.setForeground(Color.blue);
		Back.setOpaque(false);
		add(Back);
		
		Back.addActionListener(this);
		
		ImageIcon image1=new ImageIcon("product_view&_updating.jpg");
		Image image2=image1.getImage();
		Image image3=image2.getScaledInstance(1000, 870, Image.SCALE_DEFAULT); // LAST is used for the quality of image (highest quality)
		JLabel background=new JLabel();
		background.setIcon(new ImageIcon(image3));
		background.setBounds(0, 0, 1570, 800);
		getContentPane().add(background);

		add_to_cart.addActionListener(this);
		buy_now.addActionListener(this);
		bargain.addActionListener(this);

		setBounds(300,10,1000,800);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

//	public Product_View() {
//		
//	}

	public void actionPerformed(ActionEvent e) {
		payment_gateway.ItemBean item= new payment_gateway.ItemBean();
		payment_gateway.ItemBean[] ia=new payment_gateway.ItemBean[1];

		if(e.getSource()==Back) {
			final_price=0;
			dispose();
		}

		if(e.getSource()==add_to_cart){
			boolean notincart=false;
			try {
				notincart=new Sql().is_InCart(product,customer);
				if(notincart)new Sql().add_cart(product,customer);
				else JOptionPane.showMessageDialog(new JFrame(), "ALREADY IN CART");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		if(e.getSource()==buy_now) {
			
			if((product.getQuantity() - (int)quantity.getValue())<0) {
				JOptionPane.showMessageDialog(new JFrame(), "AVALAIBLE STOCK IS "+product.getQuantity());
				
			}
			
			else {
				try {
					Seller_bean seller=new Sql().get_SellerDetails(product);
					item.setCustomerID(customer.getCustomer_id());
					item.setPhoneNumber(customer.getMobile_no());
					item.setShopName(seller.getShop_name());
					item.setProductID(product.getProduct_id());
					item.setProductName(product.getProduct_name());
					item.setProductDescription(product.getDescription());
					item.setProductQuantity(String.valueOf(quantity.getValue()));
					item.setProductPrice(String.valueOf(product.getPrice()));
					ia[0]=item;
					//item.setProductPrice();
					//new page1(6000.00, item);
	
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				System.out.println("inside buynow "+final_price);
				if(final_price!=0) {
					ia[0].setProductPrice(String.valueOf(final_price));
				}
				double pri=Double.parseDouble(ia[0].getProductPrice());
				double qu=Double.parseDouble(String.valueOf(quantity.getValue()));
				System.out.println(item.getProductName());
				System.out.println(ia[0].getProductName()+"   ");
				new Page1((qu*pri),ia,true,product,customer);
			}
			
			if(product.getQuantity()-(int)quantity.getValue()<5) {
				
				try {
					String em = product.getSeller_id();
					String mail=new Sql().getSellermail(em);
			        if(mail != null){
			        new Gmail().Gmail("19euit065@skcet.ac.in", mail, "Kjvenkatesheuit065", "STOCK UPDATE", "The Stock of "
			        				+ "Product with ID - "+product.getProduct_id()+" with name "+product.getProduct_name()+" is less than minimum please update");
			        		System.out.println("mail sent to "+mail+" regarding his stock");
			        	} else {
			        	System.out.println("mail not sent regarding stock ");
			        }
				} catch (Exception ep) {
					ep.printStackTrace();
				}
			}			
		}
		if(e.getSource()==bargain){
			new Chat_buyer_side(customer.getCustomer_id(),product.getSeller_id());
			
		}

	}
	
//	public void recall() {
//		new Product_View(this.product,this.customer);
//		dispose();
//	}

	public static void change_price(double final_price2){
		final_price=final_price2;
		System.out.println("op  "+final_price2);
		midpanel.remove(pr_price);
		pr_price=new JLabel("Rs  "+final_price);
		pr_price.setFont(new Font("TimesRoman",Font.BOLD,21));
		pr_price.setBounds(280, 220, 150, 30);
		midpanel.add(pr_price);
		
		
		
	}

}
