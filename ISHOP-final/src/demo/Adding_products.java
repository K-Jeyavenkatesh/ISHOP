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

public class Adding_products extends JFrame implements ActionListener{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new demo.Adding_products();
	}
	
	JPanel headerpanel,leftpanel,midpanel;
	JLabel heading,Sellerid,sellerid,Shopname;
	JButton back;
	JButton history,logout,profile,update;
	JLabel Product_Name,Product_Image,Product_Description,Price,Offers,Genre,Quantity;
	JTextArea description;
	JTextField Name,price,offers,quantity;
	JButton attach,submit;
	JLabel imagelabel;
	JComboBox genre;
	String filepath;
	Seller_bean seller;
	boolean imageadded=false;
	
	Adding_products(Seller_bean seller){

		this.seller=seller;
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
		
		scrollPanel.setBounds(130, 170, 980, 490);
		
		
		
		midpanel.setBounds(130, 170, 980, 490);
		
		Product_Name=new JLabel("PRODUCT NAME");
		Product_Name.setBounds(155, 40, 150, 50);
		Product_Name.setFont(new Font("TimesRoman",Font.ITALIC+Font.BOLD,17));
		midpanel.add(Product_Name);
		
		Name=new JTextField();
		Name.setBounds(350,50,200,30);
		midpanel.add(Name);
		
		
		Genre=new JLabel("GENRE");
		Genre.setBounds(225, 130, 100, 50);
		Genre.setFont(new Font("TimesRoman",Font.ITALIC+Font.BOLD,17));
		midpanel.add(Genre);
		
		String[] a= {"PHARMACY","ELECTRICAL","DAILY","MECHANICAL","OTHERS"};
		genre=new JComboBox(a);
		genre.setBounds(350,143,150,30);
		midpanel.add(genre);
		
		
		Product_Description=new JLabel("DESCRIPTION");
		Product_Description.setBounds(180, 260, 150, 50);
		Product_Description.setFont(new Font("TimesRoman",Font.ITALIC+Font.BOLD,17));
		midpanel.add(Product_Description);
		
		description=new JTextArea();
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setBounds(350,220,400,130);
		midpanel.add(description);
		
		
		Price=new JLabel("PRICE");
		Price.setBounds(235, 380, 50, 50);
		Price.setFont(new Font("TimesRoman",Font.ITALIC+Font.BOLD,17));
		midpanel.add(Price);
		
		price=new JTextField();
		price.setBounds(350, 390, 200, 30);
		midpanel.add(price);
		
		
		Offers=new JLabel("OFFERS (if any in %)");
		Offers.setBounds(170, 450, 200, 50);
		Offers.setFont(new Font("TimesRoman",Font.ITALIC+Font.BOLD,17));
		midpanel.add(Offers);
		
		offers=new JTextField();
		offers.setBounds(350, 460, 200, 30);
		midpanel.add(offers);
		
		Quantity=new JLabel("QUANTITY");
		Quantity.setBounds(235,560,100,50);
		Quantity.setFont(new Font("TimesRoman",Font.ITALIC+Font.BOLD,17));
		midpanel.add(Quantity);
		
		quantity=new JTextField();
		quantity.setBounds(350,560,200,30);
		midpanel.add(quantity);
		
		
		
		Product_Image=new JLabel("IMAGE");
		Product_Image.setBounds(200, 675, 150, 50);
		Product_Image.setFont(new Font("TimesRoman",Font.ITALIC+Font.BOLD,17));
		midpanel.add(Product_Image);
		
		JLabel img_instruction=new JLabel("IMAGE MUST BE IN RESOLUTION OF 300 X 200 ");
		img_instruction.setBounds(360, 800, 400, 100);
		img_instruction.setFont(new Font("TimesRoman",Font.ITALIC+Font.BOLD,12));
		midpanel.add(img_instruction);
		
		imagelabel=new JLabel();
		imagelabel.setBorder(new LineBorder(Color.black,1,true));
		imagelabel.setBounds(350,640,300,200);
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
		getContentPane().add(scrollPanel);
		
		
		
		back=new JButton("BACK");
		back.setBounds(60, 680, 140, 40);
		back.setForeground(Color.blue);
		back.setOpaque(false);
		add(back);
		
		back.addActionListener(this);
		
		submit=new JButton("SUBMIT");
		submit.setBounds(560, 680, 140, 40);
		submit.setForeground(Color.blue);
		submit.setOpaque(false);
		add(submit);
		
		submit.addActionListener(this);
		
		
		ImageIcon image1=new ImageIcon("cartold.jpg");
		Image image2=image1.getImage();
		Image image=image2.getScaledInstance(1200, 870, Image.SCALE_SMOOTH); // LAST is used for the quality of image (highest quality)
		JLabel background=new JLabel();
		background.setIcon(new ImageIcon(image));
		background.setBounds(0, 0, 1570, 800);
		getContentPane().add(background);


		
		
		setBounds(300,10,1200,800);
		setLayout(null);
		setVisible(true);
		//getContentPane().setBackground(Color.black);
		
		setTitle("ISHOP");
		ImageIcon icon = new ImageIcon("IShop.png");
		setIconImage(icon.getImage());
		
		
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
				imageadded=true;
				
			}

			
			if(e.getSource()==submit) {
				
				boolean errorfound=false;
				
				String pr_price=price.getText();
				String pr_name=Name.getText();
				String pr_description=description.getText();
				String pr_offers=offers.getText();
				String pr_quantity=quantity.getText();
				
				
				if(pr_price.length()==0 || pr_description.length()==0 || pr_name.length()==0 || !imageadded ) {
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
					if(!Character.isDigit(pr_price.charAt(i))) {
						JOptionPane.showMessageDialog(new JFrame(), "PRICE MUST CONTAIN ONLY NUMBERS");
						errorfound=true;
						break;
					}
				}
				
				
				try {
					int n=Integer.parseInt(pr_offers);
					if(n>=100) {
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
					try {
						obj.setProduct_id(new Sql().get_ProductId());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					obj.setSeller_id(sellerid.getText());
					obj.setShop_name(Shopname.getText());
					obj.setProduct_name(Name.getText());
					obj.setGenre(genre.getSelectedItem().toString());
					obj.setDescription(description.getText());
					obj.setQuantity(Integer.parseInt(quantity.getText()));
					obj.setPrice(Integer.parseInt(price.getText()));
					obj.setOffer(Integer.parseInt(offers.getText()));
					obj.setImagePath(filepath);

					try {
						new Sql().add_Product(obj);
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}

				if(!errorfound) {
					JOptionPane.showMessageDialog(new JFrame(), "ADDED SUCCESSFULLY");
					try {
						new Seller_shop(new Sql().get_products_inshop(seller.getSeller_id()),seller);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
				}


				
			}
	}		

}
