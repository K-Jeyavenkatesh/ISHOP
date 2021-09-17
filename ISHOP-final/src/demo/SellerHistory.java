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
public class SellerHistory {

    static JFrame frame;
    static JLabel title, sub_title;
    static JPanel main_panel;
    static JButton back;

    public SellerHistory(Seller_bean seller) throws Exception {
    	
    	

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
        title.setBounds(100, 50, 1000, 75);
        title.setForeground(Color.white);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Helvetica", Font.BOLD, 20));
        title.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5, true));
        frame.add(title);

        sub_title = new JLabel("Seller History");
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

        ArrayList<History_bean> history=new Sql().get_SellerHistory(seller);

        String[][] rec=new String[history.size()][8];

        for(int i=0;i<history.size();i++){
            rec[i][0]=history.get(i).getProduct_id();
            rec[i][1]=history.get(i).getCustomer_id();
            rec[i][2]=history.get(i).getGenre();
            rec[i][3]=history.get(i).getProduct_name();
            rec[i][4]= String.valueOf(history.get(i).getQuantity());
            rec[i][5]= String.valueOf(history.get(i).getPrice());
            rec[i][6]=history.get(i).getStatus();
            rec[i][7]=history.get(i).getDatetime();
        }

        //String[][] rec = {{"product id", "customer id", "genre", "product name", "quantity", "price", "transaction detail", "transaction time"}};

        String[] header = {"product id", "customer id", "genre", "product name", "quantity", "price", "transaction detail", "transaction time"};
        JTable table = new JTable(rec, header);
        table.setFont(new Font("Helvetica", Font.BOLD, 10));
        table.setRowHeight(25);
        table.setOpaque(false);
        
        
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
                // TODO Auto-generated method stub
                frame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        //new demo.SellerHistory();
    }

}
