package demo;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author sreenath
 */
public class CustomerHistory {

    JFrame frame;
    JLabel title,sub_title;
    JPanel main_panel;
    JButton back;
    Customer_bean customer;

    public CustomerHistory(Customer_bean customer) throws Exception {

    	
        this.customer=customer;

        frame = new JFrame("ISHOP");
        frame.setBounds(200, 30, 1200, 800);
        frame.getContentPane().setBackground(Color.getHSBColor(255, 216, 17));
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        

    	frame.setTitle("ISHOP");
		ImageIcon icon = new ImageIcon("IShop.png");
		frame.setIconImage(icon.getImage());

        title = new JLabel("ISHOP - SHOP THE FUTURE HERE");
        title.setForeground(Color.white);
        title.setBounds(100, 50, 1000, 75);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Helvetica", Font.BOLD, 20));
        title.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5, true));
        frame.add(title);
        
        sub_title = new JLabel("Customer History");
        sub_title.setForeground(Color.white);
        sub_title.setBounds(500, 150, 200, 50);
        sub_title.setHorizontalAlignment(JLabel.CENTER);
        sub_title.setFont(new Font("Helvetica", Font.BOLD, 20));
        sub_title.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5, true));
        frame.add(sub_title);

        back = new JButton("BACK");
        back.setBounds(125, 650, 100, 30);
        back.setFont(new Font("Helvetica", Font.BOLD, 15));
        back.setBackground(Color.WHITE);
        frame.add(back);

        main_panel = new JPanel();
        main_panel.setBounds(100, 220, 1000, 400);
        main_panel.setVisible(true);
        main_panel.setBackground(Color.WHITE);
        main_panel.setLayout(new BorderLayout());

        ArrayList<History_bean> history=new Sql().get_History(customer);

        String[][] rec=new String[history.size()][9];

        for(int i=0;i<history.size();i++){
            rec[i][0]=history.get(i).getProduct_id();
            rec[i][1]=history.get(i).getSeller_id();
            rec[i][2]=history.get(i).getShop_name();
            rec[i][3]=history.get(i).getGenre();
            rec[i][4]=history.get(i).getProduct_name();
            rec[i][5]= String.valueOf(history.get(i).getQuantity());
            rec[i][6]= String.valueOf(history.get(i).getPrice());
            rec[i][7]=history.get(i).getStatus();
            System.out.println("7 = "+rec[i][7]);
            rec[i][8]=history.get(i).getDatetime();
            System.out.println("8 = "+rec[i][8]);
        }

        //String[][] rec = {{"product i","seller id","shop name", "genre", "product name",  "quantity", "price","transaction detail","transaction time"}};

        String[] header = {"Product id","Seller id","Shop Name", "Genre", "Product name",  "Quantity", "Price","Transaction detail","Transaction time"};
        JTable table = new JTable(rec, header);
        table.setFont(new Font("Helvetica", Font.BOLD, 10));
        table.setRowHeight(25); 
        main_panel.add(new JScrollPane(table));
        frame.add(main_panel);
        frame.setVisible(true);
        
        ImageIcon image1=new ImageIcon("history.jpg");
		Image image2=image1.getImage();
		Image image=image2.getScaledInstance(1200, 870, Image.SCALE_SMOOTH); // LAST is used for the quality of image (highest quality)
		JLabel background=new JLabel();
		background.setIcon(new ImageIcon(image));
		background.setBounds(0, 0, 1570, 800);
		frame.getContentPane().add(background);

        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.dispose();
            }
        });
    }
    public static void main(String[] args){
        //new demo.CustomerHistory();
    }

}
