package demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Catalogue_buyer extends JFrame implements ActionListener{

	public static void main(String[] args) {
		
	}
	
	JPanel headerpanel,leftpanel,midpanel;
	JLabel heading;
	JButton history,cart,logout,profile,back;
	JButton daily,electrical,pharmacy,mechanical,others;
	Customer_bean customer;
	
	Catalogue_buyer(Customer_bean customer){
		setTitle("ISHOP");
		ImageIcon icon = new ImageIcon("IShop.png");
		setIconImage(icon.getImage());

		this.customer=customer;

		headerpanel=new JPanel();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		heading=new JLabel("ISHOP - SHOP THE FUTURE HERE");
		heading.setFont(new Font("Verdana",Font.BOLD,17));
		heading.setForeground(Color.black);
		heading.setBounds(320,10,500,50);
		headerpanel.add(heading);
		
		headerpanel.setBounds(110, 40, 960, 70);
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
		
		leftpanel.setBounds(100, 200, 170, 400);
		leftpanel.setBackground(new Color(220,222,220,210)); //new Color(220,220,220)
		leftpanel.setBorder(new LineBorder(Color.black,1,true));
		leftpanel.setLayout(null);
		//leftpanel.setOpaque(false);
		add(leftpanel);
		
		
		midpanel=new JPanel();

		Icon icon1 = new ImageIcon("food.jpg");
	     daily = new JButton(icon1);
	     daily.setBounds(70, 50, 120, 120);
	     midpanel.add(daily);
	     daily.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						new Menu_List(new Sql().getProduct_byCatalog("daily"),"DAILY",customer);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					dispose();
				}
	     });
	     
	    JLabel Daily=new JLabel("DAILY");
	    Daily.setFont(new Font("Verdana",Font.BOLD,12));
	    Daily.setBounds(100,170,100,45);
		midpanel.add(Daily);
	     
	     Icon icon2 = new ImageIcon("electrical.jpg");
	     electrical = new JButton(icon2);
	     electrical.setBounds(300, 50, 120, 120);
	     midpanel.add(electrical);
	     
	     electrical.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						new Menu_List(new Sql().getProduct_byCatalog("electrical"),"ELECTRICAL",customer);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					dispose();
				}
	     });
	     
	     JLabel Electrical=new JLabel("ELECTRICAL");
	     Electrical.setFont(new Font("Verdana",Font.BOLD,12));
	     Electrical.setBounds(321,170,100,45);
		midpanel.add(Electrical);
	     
	     Icon icon3=new ImageIcon("pharmacy.jpg");
	     pharmacy=new JButton(icon3);
	     pharmacy.setBounds(525, 50, 120, 120);
	     midpanel.add(pharmacy);
	     
	     pharmacy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						new Menu_List(new Sql().getProduct_byCatalog("pharmacy"),"PHARMACY",customer);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					dispose();
				}
	     });
	     
	     JLabel Pharmacy=new JLabel("PHARMACY");
	     Pharmacy.setFont(new Font("Verdana",Font.BOLD,12));
	     Pharmacy.setBounds(551,170,100,45);
		midpanel.add(Pharmacy);
	     
	     Icon icon4 = new ImageIcon("mechanical.jpg");
	     mechanical = new JButton(icon4);
	     mechanical.setBounds(170, 250, 120, 120);
	     midpanel.add(mechanical);
	     
	     mechanical.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						new Menu_List(new Sql().getProduct_byCatalog("mechanical"),"MECHANICAL",customer);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					dispose();
				}
	     });
	     
	     JLabel Mechanical=new JLabel("MECHANICAL");
	     Mechanical.setFont(new Font("Verdana",Font.BOLD,12));
	     Mechanical.setBounds(186,370,100,45);
		midpanel.add(Mechanical);
	     
	     Icon icon5 = new ImageIcon("others.jpg");
	     others = new JButton(icon5);
	     others.setBounds(400, 250, 120, 120);
	     midpanel.add(others);
	     
	     others.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						new Menu_List(new Sql().getProduct_byCatalog("others"),"OTHERS",customer);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					dispose();
				}
	     });
	     
	     JLabel Others=new JLabel("OTHERS");
	     Others.setFont(new Font("Verdana",Font.BOLD,12));
	     Others.setBounds(433,370,100,45);
		midpanel.add(Others);
	     
		midpanel.setBounds(400, 200, 700, 450);
		midpanel.setBackground(new Color(220,222,220,210)); //new Color(220,220,220)
		midpanel.setBorder(new LineBorder(Color.black,1,true));
		//midpanel.setOpaque(false);
		midpanel.setLayout(null);
		add(midpanel);
		
		back=new JButton("BACK");
		back.setBounds(113, 620, 140, 40);
		add(back);
		back.addActionListener(this);
		
		
		ImageIcon image1=new ImageIcon("catolgue.jpg");
		Image image2=image1.getImage();
		Image image=image2.getScaledInstance(1200, 870, Image.SCALE_SMOOTH); // LAST is used for the quality of image (highest quality)
		JLabel background=new JLabel();
		background.setIcon(new ImageIcon(image));
		background.setBounds(0, 0, 1570, 800);
		getContentPane().add(background);

		profile.addActionListener(this);
		history.addActionListener(this);
		cart.addActionListener(this);
		logout.addActionListener(this);
		
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
                // setTitle(msg);
            }
		});    
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==back) {
			new Buyer_Login();
			dispose();
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
		
		if(e.getSource()==logout) {
			new Buyer_Login();
			dispose();
		}

	}
}
