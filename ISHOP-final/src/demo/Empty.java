package demo;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Empty extends JFrame{
    JPanel imagePanel=new JPanel();
    JPanel buttonPanel;
    JPanel contentPanel=new JPanel();
    JLabel imageLabel=new JLabel();
    JButton closeButton;
    ImageIcon image;
   
    Empty() {
        
    	JPanel panel=new JPanel();
    	panel.setBounds(100, 100, 600, 600);
    	panel.setLayout(null);
    	panel.setBorder(new LineBorder(Color.black,1,true));
    	add(panel);
    	
    	JButton op=new JButton("HELLO");
    	op.setBounds(400, 400,100, 200);
    	op.setBackground(new Color(0,0,0,10));
    	op.setBorder(null);
    	op.setFont(new Font("Verdana",Font.BOLD,17));
		op.setForeground(Color.black);
    	op.setOpaque(false);
    	panel.add(op);
    	
    	ImageIcon image1=new ImageIcon("background.jpg");
		Image image2=image1.getImage();
		Image image=image2.getScaledInstance(1200, 870, java.awt.Image.SCALE_SMOOTH); // LAST is used for the quality of image (highest quality)
		JLabel background=new JLabel();
		background.setIcon(new ImageIcon(image));
		background.setBounds(0, 0, 1570, 800);
		panel.add(background);
		
		
		setBounds(300,10,1200,800);
		setLayout(null);
		setVisible(true);
    }
    
    public static void main(String[] args) {
        new Empty();
    }
    
}