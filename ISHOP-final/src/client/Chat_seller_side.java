package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.border.LineBorder;

import server.*;

public class Chat_seller_side extends JFrame implements ActionListener{

	Client client;

	public static void main(String[] args) {
//		new Chat_seller_side();
	}
	
	
	JPanel headerpanel,midpanel,rightpanel;
	static JTextArea chatpanel,message;
	JLabel heading,heading2;
	JButton send;
	
	public Chat_seller_side(String name){

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				new Server();
				Server.remove_from_server(name);
			}
		});
		
//		String name= JOptionPane.showInputDialog("Enter Name");
		client = new Client(name,"localhost",6050);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		headerpanel=new JPanel();
		
		heading=new JLabel("ISHOP - SHOP THE FUTURE HERE");
		heading.setFont(new Font("Verdana",Font.BOLD,17));
		heading.setForeground(Color.black);
		heading.setBounds(350,10,500,50);
		headerpanel.add(heading);
		
		headerpanel.setBounds(130, 30, 940, 70);
		headerpanel.setBackground(new Color(220,222,220,210));//headerpanel.setBackground(new Color(220,230,240,210)); //new Color(220,220,220)
		headerpanel.setBorder(new LineBorder(Color.black,1,true));
		headerpanel.setLayout(null);
		//headerpanel.setOpaque(false);
		add(headerpanel);
		
		heading2=new JLabel("SELLER-INBOX");
		heading2.setFont(new Font("TimesRoman",Font.BOLD,18));
		heading2.setForeground(Color.black);
		heading2.setBounds(500,130,500,50);
		add(heading2);
		
		midpanel=new JPanel();
		
		rightpanel=new JPanel();
		
		JButton final_price=new JButton("ENTER PRICE");
		final_price.setFont(new Font("TimesRoman",Font.BOLD,14));
		final_price.setBounds(20,120,160,30);
		rightpanel.add(final_price);
		
		final_price.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame=new JFrame();
				JLabel last_price=new JLabel("FINAL PRICE");
				last_price.setFont(new Font("TimesRoman",Font.BOLD,14));
				last_price.setBounds(50,20,200,30);
				frame.add(last_price);
				
				JTextField price=new JTextField();
				price.setBounds(200,20,150,30);
				frame.add(price);
				
				JLabel customer_id=new JLabel("CUSTOMER I'D");
				customer_id.setFont(new Font("TimesRoman",Font.BOLD,14));
				customer_id.setBounds(50,80,200,30);
				frame.add(customer_id);
				
				JTextField Customer_id=new JTextField();
				Customer_id.setBounds(200,80,150,30);
				frame.add(Customer_id);
				
				JButton submit=new JButton("SUBMIT");
				submit.setBounds(160,160,100,20);
				frame.add(submit);
				
				submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String fp=price.getText();
						String cid=Customer_id.getText();
						client.send("\\fin:"+fp+" "+cid+" \\c");
						frame.dispose();
					}
				});
				
				frame.setBounds(300,10,500,300);
				frame.setLayout(null);
				frame.setVisible(true);
			}
		});
		
		JButton view_history=new JButton("VIEW HISTORY");
		view_history.setFont(new Font("TimesRoman",Font.BOLD,14));
		view_history.setBounds(20,200,160,30);
		rightpanel.add(view_history);
		
		view_history.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String customer_id=JOptionPane.showInputDialog("Enter Customer I'd ");
					new Chat_time_history(name,customer_id);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		
		rightpanel.setBounds(729, 20, 200, 400);
		rightpanel.setBackground(new Color(250,22,20,10));//headerpanel.setBackground(new Color(220,230,240,210)); //new Color(220,220,220)
		rightpanel.setBorder(new LineBorder(Color.black,1,true));
		rightpanel.setLayout(null);
		//headerpanel.setOpaque(false);
		midpanel.add(rightpanel);
		
		chatpanel=new JTextArea();
		chatpanel.setBounds(20,20,640,300);
		chatpanel.setEditable(false);
		chatpanel.setLayout(null);
		//headerpanel.setOpaque(false);
		
		final JScrollPane scrollPanel = new JScrollPane(
			    chatpanel,
			    ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
			);
		
		scrollPanel.setBounds(20,20,640,300);
		midpanel.add(scrollPanel);
		
		message=new JTextArea();
		message.setBounds(40,330,500,30);
		midpanel.add(message);
		
		send=new JButton("SEND");
		send.setFont(new Font("TimesRoman",Font.BOLD,14));
		send.setBounds(550,334,80,20);
		midpanel.add(send);
		
		midpanel.setBounds(130, 200, 940, 450);
		midpanel.setBackground(new Color(220,222,220,210)); //new Color(220,220,220)
		midpanel.setBorder(new LineBorder(Color.black,1,true));
		
		//midpanel.setOpaque(false);
		midpanel.setLayout(null);
		add(midpanel);
		
		
		
		ImageIcon image1=new ImageIcon("C:\\Users\\navee\\OneDrive\\Desktop\\BOOTATHON\\images\\catagory\\background.jpg");
		Image image2=image1.getImage();
		Image image=image2.getScaledInstance(1000, 870, Image.SCALE_SMOOTH); // LAST is used for the quality of image (highest quality)
		JLabel background=new JLabel();
		background.setIcon(new ImageIcon(image));
		background.setBounds(0, 0, 1570, 800);
		//getContentPane().add(background);

		send.addActionListener(this);

		setBounds(300,10,1200,800);
		setLayout(null);
		setVisible(true);
		//getContentPane().setBackground(Color.black);
		   
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==send){
			if(!message.getText().equals("")) {
				client.send(message.getText()+" \\c");
				message.setText("");
			}
		}

	}

	public static void printToConsole(String message){
		String str="";
		String msg[]=message.split(" ");
		for(int i=0;i<msg.length;i++)str+=msg[i]+" ";
		chatpanel.setText(chatpanel.getText()+str+"\n");
	}

}
