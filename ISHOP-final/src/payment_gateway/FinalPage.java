package payment_gateway;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import demo.*;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date; 

public class FinalPage {

	JFrame frame;
	JScrollPane scroll;
	JPanel main_panel;
	JButton print, home;
	JLabel title;
	
	public FinalPage(double billAmount, ItemBean[] item) throws Exception {
		
		
		
		frame = new JFrame("IShop");
		frame.setBounds(300, 50, 1200, 800);
		frame.getContentPane().setBackground(Color.getHSBColor(255, 216, 17));
		frame.setVisible(true);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		title = new JLabel("I-SHOP - YOUR ORDERS ARE PLACED SUCCESSFULLY");
		title.setBounds(100, 50, 1000, 75);
		title.setFont(new Font("Helvetica", Font.BOLD, 30));
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5, true));
		frame.add(title); 
		main_panel = new JPanel();
		main_panel.setBounds(150, 190, 1000, 450);
        main_panel.setVisible(true);
        main_panel.setBackground(new Color(220,222,220,210));
        main_panel.setPreferredSize(new Dimension(1000, 300*item.length));
        main_panel.setLayout(new GridLayout(3,1,10,10));
		
        for(int i = 0; i < item.length; i++) {
        	main_panel.add(addPanel(item[i]));
			History_bean history=new History_bean();
			Product_bean product=new Sql().getSellerId_from_finalPage(item[i].getProductID());
			history.setHistory_id(new Sql().get_HistoryId());
			history.setCustomer_id(item[i].getCustomerID());
			history.setProduct_id(item[i].getProductID());
			history.setSeller_id(product.getSeller_id());
			history.setShop_name(product.getShop_name());
			history.setGenre(product.getGenre());
			history.setProduct_name(item[i].getProductName());
			history.setDescription(item[i].getProductDescription());
			history.setQuantity(Integer.parseInt(item[i].getProductQuantity()));
			history.setPrice(Integer.parseInt(item[i].getProductQuantity())*Double.parseDouble((item[i].getProductPrice())));
        	history.setStatus(item[i].getPaymentMode());
			history.setDatetime(item[i].getDataTime());

			new Sql().add_History(history);
			new Sql().updateStock(product,history.getQuantity());
		}
        
        
        scroll= new JScrollPane(
			    main_panel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(50, 150, 1075, 500);
		scroll.setBackground(Color.WHITE);
        
		print = new JButton("Print Bill");
		print.setBounds(50, 675, 200, 50);
		print.setFont(new Font("Helvetica", Font.BOLD, 18));
		frame.add(print);
		
		home = new JButton("Home");
		home.setBounds(950, 675, 100, 50);
		home.setFont(new Font("Helvetica", Font.BOLD, 18));
		frame.add(home);
		
		home.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});


		
		print.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new GeneratePdf().GeneratePdf(item);
				System.out.println(item[0].getCustomerName());
			}
		});


        frame.add(scroll);
		frame.validate();
		frame.repaint();

	}
	
	public JPanel addPanel(ItemBean itm) {
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 200, 50);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		
		JLabel  productName = new JLabel("Product Name : "+itm.getProductName());
		productName.setBounds(10, 10, 500, 50);
		productName.setForeground(Color.BLUE);
		productName.setFont(new Font("Helvetica", Font.BOLD, 25));
		panel.add(productName);
		
		JLabel  productID = new JLabel("Product ID : "+itm.getProductID());
		productID.setBounds(550, 10, 500, 50);
		productID.setForeground(Color.BLUE);
		productID.setFont(new Font("Helvetica", Font.BOLD, 25));
		panel.add(productID);
		
		JLabel  productQuantity = new JLabel("No of Quantity : "+itm.getProductQuantity());
		productQuantity.setBounds(10, 65, 400, 50);
		productQuantity.setFont(new Font("Helvetica", Font.BOLD, 23));
		panel.add(productQuantity);
		
		JLabel  productPrice = new JLabel("Product Final Price : "+itm.getProductPrice());
		productPrice.setBounds(550, 65, 450, 50);
		productPrice.setFont(new Font("Helvetica", Font.BOLD, 23));
		panel.add(productPrice);
		
		JLabel  ShopName = new JLabel("Product Shop Name : "+itm.getShopName());
		ShopName.setBounds(10, 115, 450, 50);
		ShopName.setFont(new Font("Helvetica", Font.BOLD, 23));
		panel.add(ShopName);
		
		JLabel  pay = new JLabel("Payment Mode : "+itm.getPaymentMode());
		pay.setBounds(550, 115, 450, 50);
		pay.setFont(new Font("Helvetica", Font.BOLD, 23));
		panel.add(pay);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
	    itm.setDataTime(formatter.format(date));
	    
		JLabel  dateTime = new JLabel("Purchase Date & Time : "+formatter.format(date));
		dateTime.setBounds(10, 165, 650, 50);
		dateTime.setFont(new Font("Helvetica", Font.BOLD, 23));
		panel.add(dateTime);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); 
		c.add(Calendar.DATE, 7); 
		itm.setDelivery(sdf.format(c.getTime()));
		
		JLabel  delivery = new JLabel("Delivery On : "+sdf.format(c.getTime()));
		delivery.setBounds(10, 215, 650, 50);
		delivery.setForeground(Color.RED);
		delivery.setFont(new Font("Helvetica", Font.BOLD, 23));
		panel.add(delivery);
		
		JLabel  paid = new JLabel("Paid Status : PAID");
		paid.setBounds(550, 215, 650, 50);
		paid.setFont(new Font("Helvetica", Font.BOLD, 23));
		panel.add(paid);
		
		JLabel  paid1 = new JLabel("Transaction ID : "+(int)Math.floor(Math.random()*(100000-99999999+1)+99999999));
		paid1.setBounds(550, 165, 650, 50);
		paid1.setFont(new Font("Helvetica", Font.BOLD, 23));
		panel.add(paid1);
		
		return panel;
	}
	
//	public static void main(String[] args) {
//		ItemBean[] b = beandummy.Itembean();
//		new FinalPage(6000.00,b);
//	}

}

