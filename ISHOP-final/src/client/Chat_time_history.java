package client;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import demo.Customer_bean;
import demo.History_bean;
import demo.Seller_bean;
import demo.Sql;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import server.*;

/**
 *
 * @author sreenath
 */
public class Chat_time_history {

    static JFrame frame;
    static JLabel title, sub_title;
    static JPanel main_panel;
    static JButton back;

    public Chat_time_history(String seller_id, String customer_id) throws Exception {

        frame = new JFrame("IShop");
        frame.setBounds(300, 150, 1200, 800);
        frame.getContentPane().setBackground(Color.getHSBColor(255, 216, 17));
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        title = new JLabel("I-SHOP");
        title.setBounds(100, 50, 1000, 75);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Helvetica", Font.BOLD, 20));
        title.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5, true));
        frame.add(title);

        sub_title = new JLabel("History");
        sub_title.setBounds(500, 150, 200, 50);
        sub_title.setHorizontalAlignment(JLabel.CENTER);
        sub_title.setFont(new Font("Helvetica", Font.BOLD, 20));
        sub_title.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5, true));
        frame.add(sub_title);

        back = new JButton("BACK");
        back.setBounds(125, 650, 150, 50);
        back.setFont(new Font("Helvetica", Font.BOLD, 15));
        back.setBackground(Color.WHITE);
        frame.add(back);

        main_panel = new JPanel();
        main_panel.setBounds(100, 220, 1000, 400);
        main_panel.setVisible(true);
        main_panel.setBackground(Color.WHITE);
        main_panel.setLayout(new BorderLayout());

        ArrayList<History_bean> history=new Sql().get_UserHistory(seller_id,customer_id);

        String[][] rec=new String[history.size()][8];

        for(int i=0;i<history.size();i++){
            rec[i][0]=history.get(i).getProduct_id();
            rec[i][1]=history.get(i).getCustomer_id();
            rec[i][2]=history.get(i).getGenre();
            rec[i][3]=history.get(i).getProduct_name();
            rec[i][4]= String.valueOf(history.get(i).getQuantity());
            rec[i][5]= String.valueOf(history.get(i).getPrice());
            rec[i][6]=history.get(i).getDatetime();
        }

        //String[][] rec = {{"product id", "customer id", "genre", "product name", "quantity", "price", "transaction detail", "transaction time"}};

        String[] header = {"product id", "customer id", "genre", "product name", "quantity", "price", "transaction time"};
        JTable table = new JTable(rec, header);
        table.setFont(new Font("Helvetica", Font.BOLD, 10));
        table.setRowHeight(30);
        main_panel.add(new JScrollPane(table));
        frame.add(main_panel);
        frame.setVisible(true);

        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                frame.dispose();
            }
        });
    }

//    public static void main(String[] args) throws Exception {
//    	Seller_bean seller=new Seller_bean();
//    	seller.setAddress("dsdasdasdada");
//    	seller.setDistrict("sdasda");
//    	seller.setEmail("dsdad@gmail.com");
//    	seller.setMobile_no("343232323");
//    	seller.setPassword("op");
//    	seller.setSeller_id("12");
//    	seller.setShop_name("panda");
//    	seller.setState("dasdasd");
//    	seller.setUsername("nanae");
//        new Chat_time_history(seller);
//    }

}
