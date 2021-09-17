package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

public class Chat_buyer_side extends JFrame implements ActionListener{

//	public static void main(String[] args) {
//		new Chat_buyer_side();
//	}
	
	
	JPanel headerpanel,midpanel,rightpanel;
	static JTextArea chatpanel;
	JLabel heading,heading2;
	JButton send;
	JTextArea message;

	public String seller_id;
	private Client client;
	
	public Chat_buyer_side(String name,String seller_id){

		this.seller_id=seller_id;

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
		
		heading2=new JLabel("INBOX");
		heading2.setFont(new Font("TimesRoman",Font.BOLD,18));
		heading2.setForeground(Color.black);
		heading2.setBounds(540,130,500,50);
		add(heading2);
		
		midpanel=new JPanel();

		chatpanel=new JTextArea();
		chatpanel.setBounds(140,40,640,300);
		chatpanel.setEditable(false);
		//chatpanel.setBackground(new Color(250,22,20,10));//headerpanel.setBackground(new Color(220,230,240,210)); //new Color(220,220,220)
		chatpanel.setBorder(new LineBorder(Color.black,1,true));
		chatpanel.setPreferredSize(new Dimension(640,4100));
		chatpanel.setLayout(null);
		//headerpanel.setOpaque(false);
		
		final JScrollPane scrollPanel = new JScrollPane(
			    chatpanel,
			    ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
			);
		
		scrollPanel.setBounds(140,40,640,300);
		midpanel.add(scrollPanel);
		
		message=new JTextArea();
		message.setBounds(170,360,500,30);
		midpanel.add(message);
		
		send=new JButton("SEND");
		send.setFont(new Font("TimesRoman",Font.BOLD,14));
		send.setBounds(680,364,80,20);
		midpanel.add(send);
		
		send.addActionListener(this);
		
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

		setBounds(300,10,1200,800);
		setLayout(null);
		setVisible(true);
		//getContentPane().setBackground(Color.black);
		    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==send) {
			if(!message.getText().equals("")) {
				client.send(message.getText()+" "+seller_id+" \\s");
				message.setText("");
			}
			validate();
			repaint();
		}
	}

	public static void printToConsole(String message){
		String str="";
		String msg[]=message.split(" ");
		for(int i=0;i<msg.length-1;i++)str+=msg[i]+" ";
		try {
			chatpanel.setText(chatpanel.getText() + str + "\n");
		}catch (Exception e){}
	}

}