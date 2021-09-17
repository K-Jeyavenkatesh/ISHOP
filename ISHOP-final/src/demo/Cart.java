package demo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class Cart {

    JFrame frame;
    JLabel title, sub_title;
    JPanel main_panel;
    JButton back;
    int i=0;

    public Cart(Customer_bean customer) throws Exception {

        frame = new JFrame("IShop");
        frame.setBounds(200, 20, 1200, 800);
      //  frame.getContentPane().setBackground(new Color(233, 245, 242));
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        title = new JLabel("ISHOP - SHOP THE FUTURE HERE");
        title.setBounds(100, 20, 1000, 55);
        title.setForeground(Color.white);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Helvetica", Font.BOLD, 20));
        title.setBorder(BorderFactory.createLineBorder(Color.white, 1, true));
        frame.add(title);

        sub_title = new JLabel("CART");
        sub_title.setBounds(480, 100, 200, 40);
        sub_title.setHorizontalAlignment(JLabel.CENTER);
        sub_title.setForeground(Color.white);
        sub_title.setFont(new Font("Helvetica", Font.BOLD, 20));
        sub_title.setBorder(BorderFactory.createLineBorder(Color.white, 1, true));
        frame.add(sub_title);

        back = new JButton("BACK");
        back.setBounds(105, 700, 100, 30);
        back.setFont(new Font("Helvetica", Font.BOLD, 15));
        back.setBackground(Color.WHITE);
        frame.add(back);

        main_panel = new JPanel();

        ArrayList<Cart_bean> cartItems=new Sql().get_CustomerCart(customer);

        int n=cartItems.size();//number of products from db
        int x=17,y=10;
        
        
        for(i=0;i<n;i++) {
        	Product_bean obj=new Sql().get_CartProduct(cartItems.get(i));
			JPanel product=new JPanel();
			product.setLayout(null);
			
			//String str=obj.getDescription();
			//String s="fdfdfdsfdsfsdf";
//			if(str.length()>200) {
//				s=str.substring(0,200);
//				s=s+"....";
//			}
//			else {
//				s=str;
//			}
			
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
			
			JButton remove=new JButton("REMOVE");
			remove.setFont(new Font("TimesRoman",Font.BOLD,12));
			remove.setBounds(390, 105, 140, 20);
			remove.setOpaque(false);
			product.add(remove);
			
			remove.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						new Sql().removeFromCart(obj,customer);
						//product.revalidate();
//						product.repaint();
//						product.validate();
						new Cart(customer);
						frame.dispose();
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			});

			double offer=obj.getOffer();
			JLabel pr_offer=new JLabel("UNDER OFFER OF "+offer+"%");
			pr_offer.setFont(new Font("TimesRoman",Font.BOLD,15));
			pr_offer.setBounds(600,100,200,30);
			product.add(pr_offer);
			
			String product_id=obj.getProduct_id();
			JLabel pr_id = new JLabel("PRODCUT ID - "+product_id);
			pr_id.setFont(new Font("TimesRoman",Font.BOLD,15));
			pr_id.setBounds(275,150,400,30);
			product.add(pr_id);


			JButton pr_image = new JButton();
			pr_image.setBounds(20, 25, 120, 120);
			byte[] img=obj.getImg_array();
			ImageIcon image=new ImageIcon(img);
			Image im=image.getImage();
			Image myImg=im.getScaledInstance(pr_image.getWidth(),pr_image.getHeight(),Image.SCALE_SMOOTH);
			ImageIcon newImage=new ImageIcon(myImg);
			pr_image.setIcon(newImage);
			pr_image.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new Product_View(obj,customer);
						//frame.dispose();
					}
        });
			product.add(pr_image);
			
			JLabel pr_name = new JLabel(obj.getProduct_name().toUpperCase(),SwingConstants.CENTER);
			pr_name.setFont(new Font("TimesRoman",Font.BOLD,15));
			pr_name.setHorizontalAlignment(JLabel.CENTER);
			pr_name.setBounds(0,150,170,30);
			product.add(pr_name);
			
			product.setBounds(x, y, 800, 200);
			product.setBackground(new Color(220,230,240,210));
			y=y+210;
			product.setBorder(new LineBorder(Color.black,1,true));
			main_panel.add(product);
        }
        
        main_panel.setBounds(150, 190, 1000, 450);
        main_panel.setVisible(true);
        main_panel.setBackground(new Color(220,222,220,210));;
        main_panel.setPreferredSize(new Dimension(5000, 5450));
        
        main_panel.setLayout(new BorderLayout());
        
        final JScrollPane scrollPanel = new JScrollPane(
			    main_panel,
			    ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
			);
		
		scrollPanel.setBounds(150, 190, 850, 450);
        
        String[][] rec = {{"genre", "product name", "price"}};

        String[] header = {"genre", "product name", "price"};
        JTable table = new JTable(rec, header);
      //  table.setFont(new Font("Helvetica", Font.BOLD, 10));
        //table.setRowHeight(25);
      //  main_panel.add(new JScrollPane(table));
        scrollPanel.setOpaque(false);
		scrollPanel.getViewport().setOpaque(false);
		table.setOpaque(false);
        frame.add(scrollPanel);
        frame.setVisible(true);
        

        ImageIcon image1=new ImageIcon("history.jpg");
		Image image2=image1.getImage();
		Image image3=image2.getScaledInstance(1200, 870, Image.SCALE_SMOOTH); // LAST is used for the quality of image (highest quality)
		JLabel background=new JLabel();
		background.setIcon(new ImageIcon(image3));
		background.setBounds(0, 0, 1570, 800);
		frame.getContentPane().add(background);
		frame.setTitle("ISHOP");
		ImageIcon icon = new ImageIcon("IShop.png");
		frame.setIconImage(icon.getImage());

        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
               //new demo.Catalogue_buyer();
               frame.dispose();

            }
        });
    }

    public static void main(String[] args) {
        //new demo.Cart();
    }

}
