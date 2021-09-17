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
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

public class Updating_Stock extends JFrame implements ActionListener{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		demo.Product_bean obj1=new demo.Product_bean();
//		obj1.setProduct_id("123");
//		obj1.setSeller_id("seller123");
//		obj1.setShop_name("weeeee");
//		obj1.setDescription("dasdasdasdgakjdabjkdad");
//		obj1.setGenre("pharmacy");
//		obj1.setImagePath("C:\\Users\\navee\\OneDrive\\Desktop\\BOOTATHON\\images\\catagory\\oldbackground.jpg");
//		obj1.setOffer(50);
//		obj1.setPrice(5000.00);
//		obj1.setProduct_name("Mobile");
//		obj1.setQuantity(200);
//
//
//		demo.Seller_bean abc=new demo.Seller_bean();
//		abc.setShop_name("shopne");
//		abc.setSeller_id("Seller123");
//
//		new demo.Updating_Stock(obj1,abc);
	}
	
	JPanel headerpanel,leftpanel,midpanel;
	JLabel heading,Sellerid,sellerid,Shopname;
	JButton back;
	JButton history,logout,profile,update;
	JLabel Product_Name,Product_Image,Product_Description,Price,Offers,Genre,Quantity,Product_id,product_id;
	JTextArea description;
	JTextField Name,price,offers,quantity;
	JButton attach,submit,delete;
	JLabel imagelabel;
	JComboBox genre;
	String filepath;
	Seller_bean seller;
	Product_bean curr_product;
	boolean imageadded=false;
	
	Updating_Stock(Product_bean product,Seller_bean seller){
		
		setTitle("ISHOP");
		ImageIcon icon = new ImageIcon("IShop.png");
		setIconImage(icon.getImage());

		this.seller=seller;
		this.curr_product=product;

		headerpanel=new JPanel();
		
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
		
		update=new JButton("UPDATE");
		update.setFont(new Font("Verdana",Font.BOLD,12));
		update.setBackground(Color.BLACK);
		update.setForeground(Color.WHITE);
		update.setBounds(35,230,100,45);
		leftpanel.add(update);
		
		
		logout=new JButton("LOGOUT");
		logout.setFont(new Font("TimesNewRoman",Font.BOLD,12));
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
		
		
		Shopname=new JLabel(seller.getShop_name());
		Shopname.setFont(new Font("Verdana",Font.BOLD,16));
		Shopname.setForeground(Color.blue);
		Shopname.setBounds(515,100,150,45);
		add(Shopname);
		
		Sellerid=new JLabel("SELLER ID - ");
		Sellerid.setFont(new Font("Verdana",Font.BOLD,12));
		Sellerid.setBounds(935,100,100,45);
		add(Sellerid);
		
		sellerid=new JLabel(seller.getSeller_id());
		sellerid.setBounds(1050, 100, 100, 40);
		sellerid.setFont(new Font("Verdana",Font.BOLD,12));
		add(sellerid);
		
		
		midpanel=new JPanel();

		final JScrollPane scrollPanel = new JScrollPane(
			    midpanel,
			    ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
			);
		
		scrollPanel.setBounds(280, 170, 850, 490);
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		midpanel.setBounds(280, 170, 850, 490);
		
		Product_Name=new JLabel("PRODUCT NAME");
		Product_Name.setForeground(Color.white);
		Product_Name.setBounds(175, 110, 150, 50);
		Product_Name.setFont(new Font("TimesRoman",Font.ITALIC+Font.BOLD,17));
		midpanel.add(Product_Name);
		
		Name=new JTextField(product.getProduct_name());
		Name.setBounds(400, 120, 150, 30);
		midpanel.add(Name);
		
		Product_id=new JLabel("PRODUCT_ID");
		Product_id.setForeground(Color.white);
		Product_id.setBounds(175, 40, 150, 50);
		Product_id.setFont(new Font("TimesRoman",Font.ITALIC+Font.BOLD,17));
		midpanel.add(Product_id);
		
		product_id=new JLabel(product.getProduct_id());
		product_id.setBounds(400,48,200,30);
		product_id.setFont(new Font("TimesRoman",Font.ITALIC+Font.BOLD,17));
		midpanel.add(product_id);
		
		
		Genre=new JLabel("GENRE");
		Genre.setForeground(Color.white);
		Genre.setBounds(175, 179, 100, 50);
		Genre.setFont(new Font("TimesRoman",Font.ITALIC+Font.BOLD,17));
		midpanel.add(Genre);
		
		
		
		String[] a= new String[5];
		
		int t=0;
		a[t++]=product.getGenre().toUpperCase();
		
		if(!product.getGenre().toUpperCase().equals("PHARMACY")) {
			a[t++]="PHARMACY";
		}
		if(!product.getGenre().toUpperCase().equals("ELECTRICAL")) {
			a[t++]="ELECTRICAL";		
		}
		if(!product.getGenre().toUpperCase().equals("MECHANICAL")) {
			a[t++]="MECHANICAL";		
		}
		if(!product.getGenre().toUpperCase().equals("DAILY")) {
			a[t++]="DAILY";
		}
		if(!product.getGenre().toUpperCase().equals("OTHERS")) {
			a[t++]="OTHERS";
		}
		
		
		genre=new JComboBox(a);
		genre.setBounds(400,193,150,30);
		midpanel.add(genre);
		
		
		Price=new JLabel("PRICE");
		Price.setForeground(Color.white);
		Price.setBounds(175, 250, 50, 50);
		Price.setFont(new Font("TimesRoman",Font.ITALIC+Font.BOLD,17));
		midpanel.add(Price);
		
		price=new JTextField(product.getPrice()+"");
		price.setBounds(400, 260, 200, 30);
		midpanel.add(price);
		
		
		Offers=new JLabel("OFFERS (if any in %)");
		Offers.setForeground(Color.white);
		Offers.setBounds(175, 320, 200, 50);
		Offers.setFont(new Font("TimesRoman",Font.ITALIC+Font.BOLD,17));
		midpanel.add(Offers);
		
		offers=new JTextField(product.getOffer()+"");
		offers.setBounds(400, 330, 200, 30);
		midpanel.add(offers);
		
		Quantity=new JLabel("QUANTITY");
		Quantity.setForeground(Color.white);
		Quantity.setBounds(175,400,100,50);
		Quantity.setFont(new Font("TimesRoman",Font.ITALIC+Font.BOLD,17));
		midpanel.add(Quantity);
		
		quantity=new JTextField(product.getQuantity()+"");
		quantity.setBounds(400,410,200,30);
		midpanel.add(quantity);
		
		
		Product_Description=new JLabel("DESCRIPTION");
		Product_Description.setForeground(Color.white);
		Product_Description.setBounds(175, 490, 150, 50);
		Product_Description.setFont(new Font("TimesRoman",Font.ITALIC+Font.BOLD,17));
		midpanel.add(Product_Description);
		
		description=new JTextArea(product.getDescription());
		description.setBounds(400,490,400,130);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		midpanel.add(description);
		
		
		Product_Image=new JLabel("IMAGE");
		Product_Image.setForeground(Color.white);
		Product_Image.setBounds(200, 675, 150, 50);
		Product_Image.setFont(new Font("TimesRoman",Font.ITALIC+Font.BOLD,17));
		midpanel.add(Product_Image);
		
		JLabel img_instruction=new JLabel("IMAGE MUST BE IN RESOLUTION OF 300 X 200 ");
		img_instruction.setBounds(410, 800, 400, 100);
		img_instruction.setForeground(Color.white);
		img_instruction.setFont(new Font("TimesRoman",Font.ITALIC+Font.BOLD,12));
		midpanel.add(img_instruction);
		
		
		filepath=product.getImagePath();
		imagelabel=new JLabel();
		imagelabel.setBorder(new LineBorder(Color.black,1,true));
		imagelabel.setBounds(410,640,300,200);
		byte[] img=product.getImg_array();
		ImageIcon image=new ImageIcon(img);
		Image im=image.getImage();
		Image myImg=im.getScaledInstance(imagelabel.getWidth(),imagelabel.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon newImage=new ImageIcon(myImg);
		imagelabel.setIcon(newImage);
		midpanel.add(imagelabel);
		
		attach=new JButton("ATTACH");
		attach.setBounds(450, 875, 100, 30);
		attach.setFont(new Font("TimesRoman",Font.ITALIC+Font.BOLD,13));
		midpanel.add(attach);
		
		attach.addActionListener(this);

		
		midpanel.setBackground(new Color(220,222,220,210)); //new Color(220,220,220)
		midpanel.setBorder(new LineBorder(Color.black,1,true));
		//midpanel.setOpaque(false);
		midpanel.setPreferredSize(new Dimension(5000, 950));
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
		
		submit=new JButton("SUBMIT");
		submit.setBounds(500, 680, 140, 40);
		submit.setForeground(Color.blue);
		submit.setOpaque(false);
		add(submit);
		
		submit.addActionListener(this);
		
		delete=new JButton("REMOVE");
		delete.setBounds(650, 680, 140, 40);
		delete.setForeground(Color.blue);
		delete.setOpaque(false);
		add(delete);
		
		delete.addActionListener(this);
		
		
		
		
		ImageIcon image1=new ImageIcon("product_view&_updating.jpg");
		Image image2=image1.getImage();
		Image image3=image2.getScaledInstance(1200, 870, Image.SCALE_SMOOTH); // LAST is used for the quality of image (highest quality)
		JLabel background=new JLabel();
		background.setIcon(new ImageIcon(image3));
		background.setBounds(0, 0, 1570, 800);
		getContentPane().add(background);
		
		
		setBounds(300,10,1200,800);
		setLayout(null);
		setVisible(true);
		//getContentPane().setBackground(new Color(94,173,189));
		
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
	public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==back) {
				try {
					new Seller_shop(new Sql().get_products_inshop(seller.getSeller_id()),seller);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			}
			
			if(e.getSource()==attach) {
				JFileChooser chooser =new JFileChooser();
				chooser.showOpenDialog(null);
				File f=chooser.getSelectedFile();
				filepath=f.getPath();
				ImageIcon imageicon=new ImageIcon(filepath);
				Image image99=imageicon.getImage();
				Image image=image99.getScaledInstance(300, 200, Image.SCALE_SMOOTH);
				midpanel.remove(imagelabel);
				imageadded=false;
				imagelabel=new JLabel(new ImageIcon(image));
				imagelabel.setBorder(new LineBorder(Color.black,1,true));
				imagelabel.setBounds(350,640,300,200);
				midpanel.add(imagelabel);
				validate();
				repaint();
				imageadded=true;
				
			}

			if(e.getSource()==delete){
				System.out.println("stram");
				Product_bean obj=new Product_bean();

				obj.setProduct_id(curr_product.getProduct_id());
				obj.setSeller_id(sellerid.getText());
				obj.setShop_name(Shopname.getText());
				obj.setProduct_name(Name.getText());
				obj.setGenre(genre.getSelectedItem().toString());
				obj.setDescription(description.getText());
				obj.setQuantity(Integer.parseInt(quantity.getText()));
				obj.setPrice(Double.parseDouble(price.getText()));
				obj.setOffer(Double.parseDouble(offers.getText()));
				obj.setImagePath(filepath);

				System.out.println("inside");
				try {
					
					new Sql().delete_product(obj);
				} catch (Exception ex) {
					ex.printStackTrace();
					System.out.println(ex);
				}

					
					JOptionPane.showMessageDialog(new JFrame(), "REMOVED SUCCESSFULLY");
					try {
						new Seller_shop(new Sql().get_products_inshop(seller.getSeller_id()),seller);
						dispose();
						//this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
					} catch (Exception ex) {
						ex.printStackTrace();
						System.out.println(e+" in update delete stock");
					}
				


			}
			
			if(e.getSource()==submit) {
				
				boolean errorfound=false;
				
				String pr_price=price.getText();
				String pr_name=Name.getText();
				String pr_description=description.getText();
				String pr_offers=offers.getText();
				String pr_quantity=quantity.getText();
				
				
				if(pr_price.length()==0 || pr_description.length()==0 || pr_name.length()==0 ) {
					JOptionPane.showMessageDialog(new JFrame(), "EVERYTHING MUST BE FILLED");
					errorfound=true;
					return;
				}
				
				for(int i=0;i<pr_quantity.length();i++) {
					if(!Character.isDigit(pr_quantity.charAt(i))) {
						JOptionPane.showMessageDialog(new JFrame(), "QUANTITY MUST CONTAIN ONLY NUMBERS");
						errorfound=true;
						break;
					}
				}
				
				for(int i=0;i<pr_price.length();i++) {
					try {
						double price=Double.parseDouble(pr_price);
					}catch (Exception e1){
						JOptionPane.showMessageDialog(new JFrame(), "PRICE MUST CONTAIN ONLY NUMBERS");
						errorfound=true;
						break;
					}
				}
				
				
				try {
					double offer=Double.parseDouble(pr_offers);
					if(offer>=100) {
						JOptionPane.showMessageDialog(new JFrame(), "OFFERS MUST BE BELOW 100%");
						errorfound=true;
						return;
					}
				}
				catch(Exception ae) {
					JOptionPane.showMessageDialog(new JFrame(), "OFFERS MUST CONTAIN ONLY NUMBERS");
					errorfound=true;
				}

				if(!errorfound){
					Product_bean obj=new Product_bean();

					obj.setProduct_id(curr_product.getProduct_id());
					obj.setSeller_id(sellerid.getText());
					obj.setShop_name(Shopname.getText());
					obj.setProduct_name(Name.getText());
					obj.setGenre(genre.getSelectedItem().toString());
					obj.setDescription(description.getText());
					obj.setQuantity(Integer.parseInt(quantity.getText()));
					obj.setPrice(Double.parseDouble(price.getText()));
					obj.setOffer(Double.parseDouble(offers.getText()));
					obj.setImagePath(filepath);


					try {
						new Sql().update_product(obj);
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}

				if(!errorfound) {
					JOptionPane.showMessageDialog(new JFrame(), "ADDED SUCCESSFULLY");
					
					try {
						System.out.println("movind");
						new Seller_shop(new Sql().get_products_inshop(seller.getSeller_id()),seller);
						dispose();
						//this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					
				}
				
				
			}
	}		

}
