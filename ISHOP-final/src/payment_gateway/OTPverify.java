package payment_gateway;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

import demo.Customer_bean;
import demo.Product_bean;

public class OTPverify {
	
	JFrame frame;
	JLabel title, line_1, otpTitle, valid_text, valid_time;
	JPanel main_panel;
	JButton submit, resend;
	JTextField otp_input;
	OTPverify current;
	int otp_final, time_limit = 1;
	private double billAmount;
	private ItemBean[] item;
	private boolean paid;
	private Product_bean Product;
	private Customer_bean Customer;
	
	
	public OTPverify(double billAmount, ItemBean[] item, boolean paid,Product_bean Product,Customer_bean Customer) {
		
		current  = this;
		this.item = item;
		this.paid = paid;
		this.Product = Product;
		this.Customer = Customer;
		frame = new JFrame("IShop");
		frame.setBounds(500, 50, 800, 800);
		frame.getContentPane().setBackground(Color.getHSBColor(255, 216, 17));
		frame.setVisible(true);
		frame.setLayout(null);
		//frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		main_panel = new JPanel();
		main_panel.setBounds(50, 50, 680, 650);
		main_panel.setLayout(null);
		main_panel.setBackground(Color.WHITE);
		frame.add(main_panel);
		
		title = new JLabel("OTP VERIFICATION");
		title.setBounds(220, 20, 250, 100);
		title.setFont(new Font("Helvetica", Font.BOLD, 25));
		main_panel.add(title);
		
		String email = "19euit065@skcet.ac.in";  //*********input from database**********
		int char_at = email.indexOf("@");
		String text_1 = "<html><p>We have sent One Time Password (OTP) to registered<br/>"
				+ " E-mail address "+email.substring(0,2)+"*********"+email.substring(char_at)+"</p></html>";
		line_1 = new JLabel(text_1);
		line_1.setBounds(75, 120, 550, 100);
		line_1.setFont(new Font("Helvetica", Font.BOLD, 20));
		main_panel.add(line_1);
		
		otpTitle = new JLabel("ENTER OTP HERE");
		otpTitle.setBounds(75, 190, 200, 100);
		otpTitle.setFont(new Font("Helvetica", Font.BOLD, 20));
		main_panel.add(otpTitle);
		
		otp_input = new JTextField();
		otp_input.setBounds(75, 275, 200, 50);
		otp_input.setFont(new Font("Helvetica", Font.BOLD, 20));
		main_panel.add(otp_input);
		
		valid_text = new JLabel("Valid Till ");
		valid_text.setBounds(75, 310, 200, 100);
		valid_text.setFont(new Font("Helvetica", Font.BOLD, 20));
		main_panel.add(valid_text);
		
		resend = new JButton("RESEND OTP");
		resend.setBounds(400, 335, 200, 50);
		resend.setFont(new Font("Helvetica", Font.BOLD, 15));
		resend.setEnabled(false);
		main_panel.add(resend);
		
		submit = new JButton("SUBMIT");
		submit.setBounds(75, 440, 100, 50);
		submit.setFont(new Font("Helvetica", Font.BOLD, 15));
		main_panel.add(submit);
		
		OTPThread t1 = new OTPThread(this);
		t1.start();
		
		otp_final = new OTPGenerator().OTPGenerator(Customer);
		//otp_final = 123456;
		System.out.println("From otp page : "+otp_final);
		
		otp_input.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent k) {
				
				String otp_t = otp_input.getText();
				Pattern p1 = Pattern.compile("([a-zA-Z!@#$%^&])|([-][-])|[/]|[+]|[*]");
				Matcher m1 = p1.matcher(otp_t);
				if(m1.find()) {
					JOptionPane.showMessageDialog(frame, "Invalid Character", "Invalid", 0);
					otp_input.setText("");
					return;
				}
				if(otp_t.length() > 5) {
					otp_input.setText(otp_t.substring(0,6));
				}
			}
		});
		
		resend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				resend.setEnabled(false);
				otp_input.setText("");
				time_limit = 1;
				OTPThread t1 = new OTPThread(current);
				t1.start();
				//otp_final = 123456;
				otp_final = new OTPGenerator().OTPGenerator(Customer);
				System.out.println("From otp page after resend: "+otp_final);
			}
		});
		
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String otp_current = otp_input.getText();
				if(otp_current.equals(String.valueOf(otp_final))) {
					if(time_limit == 0) {
						JOptionPane.showMessageDialog(frame, "Try with Resend OTP", "TIME EXCEEDED", JOptionPane.WARNING_MESSAGE);
					} else {
						new Loading(billAmount,item, paid, Product, Customer);
						frame.dispose();
					}
				} else {
					JOptionPane.showMessageDialog(frame, "OTP is not matched", "Invalid OTP", JOptionPane.ERROR_MESSAGE);
					if(time_limit == 0) {
						JOptionPane.showMessageDialog(frame, "Try with Resend OTP", "TIME EXCEEDED", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		frame.validate();
		frame.repaint();
		
	} 
	
	public static void main(String[] args) {
		//new OTPverifiy();
	}
}
