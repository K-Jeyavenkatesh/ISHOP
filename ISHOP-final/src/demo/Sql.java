package demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class Sql {
   

    String url="jdbc:mysql://localhost:3306/bootathon1";   
	String uname="root";
	String pass="627001";
	
	public String forgets(String em) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,uname,pass);
	        Statement st= con.createStatement();
	        ResultSet r = st.executeQuery("select email,password from seller_details");
	        while(r.next()) {
	        	if(em.equals(r.getString(1))) {
	        		return r.getString(2);
	        	}
	        }
	        return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String forget(String em) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,uname,pass);
	        Statement st= con.createStatement();
	        ResultSet r = st.executeQuery("select email,password from customer_details");
	        while(r.next()) {
	        	if(em.equals(r.getString(1))) {
	        		return r.getString(2);
	        	}
	        }
	        return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
    public int is_customerRegistered(Customer_bean obj)throws Exception{
        String query="select email, mobile_no from customer_details";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st= con.createStatement();
        String s1="",s2="";

        ResultSet res=st.executeQuery(query);

        while (res.next()) {
            s1 = res.getString(1);
            s2 = res.getString(2);
            if (s1.equals(obj.getEmail())) return 1;
            if(s2.equals(obj.getMobile_no()))return 2;
        }

        return 0;
    }
    
    public void updateStock(Product_bean product,int quantity) throws Exception{
    	
    	quantity=product.getQuantity()-quantity;
    	 String query="update product set quantity='"+quantity+"' where product_id='"+product.getProduct_id()+"'";

         Class.forName("com.mysql.jdbc.Driver");
         Connection con=DriverManager.getConnection(url,uname,pass);
         Statement pst=con.createStatement();
        
         pst.executeUpdate(query);
         
         System.out.println("quantity = "+product.getQuantity());
         
//         if(product.getQuantity()<=0) {
//        	 //sends mail to seller for outofstock with procutid and seller id
//         }
         
    }

    public int is_UpdateCustomerRegistered(Customer_bean obj)throws Exception{
        String query="select email, mobile_no from customer_details";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st= con.createStatement();
        String s1="",s2="";

        ResultSet res=st.executeQuery(query);
        int i=0,j=0;
        while (res.next()) {
            s1 = res.getString(1);
            s2 = res.getString(2);
            if (s1.equals(obj.getEmail())) i++;
            if(s2.equals(obj.getMobile_no())) j++;
            if(i>1)return 1;
            if(j>1)return 2;
        }

        return 0;
    }

    public int is_UpdateSellerRegistered(Seller_bean obj)throws Exception{
        String query="select email, mobile_no from seller_details";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st= con.createStatement();
        String s1="",s2="";

        ResultSet res=st.executeQuery(query);
        int i=0,j=0;
        while (res.next()) {
            s1 = res.getString(1);
            s2 = res.getString(2);
            if (s1.equals(obj.getEmail())) i++;
            if(s2.equals(obj.getMobile_no())) j++;
            if(i>1)return 1;
            if(j>1)return 2;
        }

        return 0;
    }

    public int check_SellerRegistered(Seller_bean obj)throws Exception{
        String query="select email,mobile_no from seller_details";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st= con.createStatement();
        String s1="",s2="";

        ResultSet res=st.executeQuery(query);

        while (res.next()) {
            s1 = res.getString(1);
            s2 = res.getString(2);
            if (s1.equals(obj.getEmail())) return 1;
            if(s2.equals(obj.getMobile_no()))return 2;
        }

        return 0;
    }

    public Seller_bean is_SellerRegistered(Seller_bean obj)throws Exception{
        String query="select * from seller_details where email='"+obj.getEmail()+"'";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st= con.createStatement();
        String s="";

        ResultSet res=st.executeQuery(query);

        while (res.next()) {
            obj.setSeller_id(res.getString(1));
            obj.setUsername(res.getString(2));
            obj.setPassword(res.getString(3));
            obj.setShop_name(res.getString(4));
            obj.setEmail(res.getString(5));
            obj.setMobile_no(res.getString(6));
            obj.setState(res.getString(7));
            obj.setDistrict(res.getString(8));
            obj.setAddress(res.getString(9));
            return obj;
        }

        con.close();
        res.close();

        obj.setPassword("Not Found");
        return obj;
    }

    public void registerCustomer(Customer_bean b)throws  Exception{

        String query="insert into customer_details (customer_id,username,password,email,mobile_no,address) values (?,?,?,?,?,?)";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        PreparedStatement pst=con.prepareStatement(query);

        pst.setString(1,b.getCustomer_id());
        pst.setString(2,b.getUsername());
        pst.setString(3,b.getPassword());
        pst.setString(4,b.getEmail());
        pst.setString(5,b.getMobile_no());
        pst.setString(6,b.getAddress());

        pst.executeUpdate();

        System.out.println("Customer Details Registered!!");

    }

    public Customer_bean get_customer_information(Customer_bean obj)throws Exception{

        String query="select * from customer_details where email='"+obj.getEmail()+"'";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st= con.createStatement();

        String s="";

        ResultSet res=st.executeQuery(query);

        while (res.next()) {
            obj.setCustomer_id(res.getString(1));
            obj.setUsername(res.getString(2));
            obj.setPassword(res.getString(3));
            obj.setEmail(res.getString(4));
            obj.setMobile_no(res.getString(5));
            obj.setAddress(res.getString(6));
            return obj;
        }

        obj.setPassword("Not Found");
        return obj;

    }

    public void update_Customer(Customer_bean customer)throws Exception{

        String query="update customer_details set username=?,password=?,email=?,mobile_no=?,address=? where customer_id=?";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        PreparedStatement pst=con.prepareStatement(query);

        pst.setString(1,customer.getUsername());
        pst.setString(2,customer.getPassword());
        pst.setString(3,customer.getEmail());
        pst.setString(4,customer.getMobile_no());
        pst.setString(5,customer.getAddress());
        pst.setString(6,customer.getCustomer_id());

        pst.executeUpdate();

        System.out.println("Customer datails Updated!!");

    }

    public void update_Seller(Seller_bean seller)throws Exception{

        String query="update seller_details set username=?,password=?,shop_name=?,email=?,mobile_no=?,state=?,district=?,address=? where seller_id=?";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        PreparedStatement pst=con.prepareStatement(query);

        pst.setString(1,seller.getUsername());
        pst.setString(2,seller.getPassword());
        pst.setString(3,seller.getShop_name());
        pst.setString(4,seller.getEmail());
        pst.setString(5,seller.getMobile_no());
        pst.setString(6,seller.getState());
        pst.setString(7,seller.getDistrict());
        pst.setString(8,seller.getAddress());
        pst.setString(9,seller.getSeller_id());

        pst.executeUpdate();

        System.out.println("Seller datails Updated!!");

    }

    public void registerSeller(Seller_bean b)throws  Exception{

        String query="insert into seller_details (seller_id,username,password,shop_name,email,mobile_no,state,district,address) values (?,?,?,?,?,?,?,?,?)";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        PreparedStatement pst=con.prepareStatement(query);

        pst.setString(1,b.getSeller_id());
        pst.setString(2,b.getUsername());
        pst.setString(3,b.getPassword());
        pst.setString(4,b.getShop_name());
        pst.setString(5,b.getEmail());
        pst.setString(6,b.getMobile_no());
        pst.setString(7,b.getState());
        pst.setString(8,b.getDistrict());
        pst.setString(9,b.getAddress());

        pst.executeUpdate();

        System.out.println("Seller Details Registered!!");

    }
    
    public String getSellermail(String seller_id) throws SQLException, ClassNotFoundException {
    	
    	String query="select email from seller_details where seller_id='"+seller_id+"'";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st=con.createStatement();

        ResultSet res=st.executeQuery(query);
        
        res.next(); 
        	String str=res.getString(1);
        	System.out.println(str);
        	return str;
        
        
    	
    }

    public boolean is_crtlocation(String sellertId,String state,String district)throws Exception{

        String query="select state, district from seller_details where seller_id='"+sellertId+"'";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st=con.createStatement();

        ResultSet res=st.executeQuery(query);

        if(state.equals("ALL State"))return true;

        res.next();

        return ((res.getString(1).equals(state) || state.equals("ALL STATES"))&& (res.getString(2).equals(district) || district.equals("ALL DISTRICTS")));

    }

    public String get_ProductId() throws Exception{
        int count=0;
        String query="select * from product";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st= con.createStatement();
        String s="";

        ResultSet res=st.executeQuery(query);

        while(res.next()){
            count=Integer.parseInt(res.getString(1).substring(3));
        }
        count++;
        return "pid"+count;

    }

    public String get_SellerId() throws Exception{
        int count=0;
        String query="select * from seller_details";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st= con.createStatement();
        String s="";

        ResultSet res=st.executeQuery(query);

        while(res.next()){
            count=Integer.parseInt(res.getString(1).substring(3));
        }
        count++;
        return count+"";

    }

    public String get_CustomerId() throws Exception{
        int count=0;
        String query="select * from customer_details";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st= con.createStatement();
        String s="";

        ResultSet res=st.executeQuery(query);

        while(res.next()){
            count=Integer.parseInt(res.getString(1).substring(3));
        }
        count++;
        return count+"";

    }

    public void add_Product(Product_bean obj)throws Exception{

        String query="insert into product (product_id,seller_id,shop_name,genre,product_name,description,quantity,price,offer,image_path,image) values (?,?,?,?,?,?,?,?,?,?,?)";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        PreparedStatement ps=con.prepareStatement(query);

        InputStream is=new FileInputStream(new File(obj.getImagePath()));

        ps.setString(1,obj.getProduct_id());
        ps.setString(2,obj.getSeller_id());
        ps.setString(3,obj.getShop_name());
        ps.setString(4,obj.getGenre());
        ps.setString(5,obj.getProduct_name());
        ps.setString(6,obj.getDescription());
        ps.setInt(7,obj.getQuantity());
        ps.setDouble(8,obj.getPrice());
        ps.setDouble(9,obj.getOffer());
        ps.setString(10, obj.getImagePath());
        ps.setBlob(11,is);

        ps.executeUpdate();

        System.out.println("Product Added...");

        con.close();
        ps.close();

    }

    public ArrayList<Product_bean> get_products_inshop(String seller_id)throws Exception{

        ArrayList<Product_bean> products=new ArrayList<>();

        String query="select * from product";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st= con.createStatement();

        ResultSet res=st.executeQuery(query);

        while(res.next()){
            String s=res.getString(2);
            if(s.equals(seller_id)){
                Product_bean obj=new Product_bean();
                obj.setProduct_id(res.getString(1));
                obj.setSeller_id(res.getString(2));
                obj.setShop_name(res.getString(3));
                obj.setGenre(res.getString(4));
                obj.setProduct_name(res.getString(5));
                obj.setDescription(res.getString(6));
                obj.setQuantity(res.getInt(7));
                obj.setPrice(res.getDouble(8));
                obj.setOffer(res.getDouble(9));
                obj.setImagePath(res.getString(10));
                obj.setImg_array(res.getBytes(11));
                products.add(obj);
            }
        }

        return products;
    }

    public void delete_product(Product_bean obj)throws  Exception{

        String query="delete from product where product_id='"+obj.getProduct_id()+"'";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st= con.createStatement();

        int res=st.executeUpdate(query);

        System.out.println("delete "+obj.getProduct_id()+" "+res);
        con.close();

    }

    public void update_product(Product_bean obj)throws Exception{

        delete_product(obj);
        add_Product(obj);

    }

    public ArrayList<Product_bean> getProduct_byCatalog(String cname)throws Exception{

        ArrayList<Product_bean> products=new ArrayList<>();

        String query="select * from product where genre='"+cname+"'";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st= con.createStatement();

        ResultSet res=st.executeQuery(query);

        while(res.next()){
            Product_bean obj=new Product_bean();
            obj.setProduct_id(res.getString(1));
            obj.setSeller_id(res.getString(2));
            obj.setShop_name(res.getString(3));
            obj.setGenre(res.getString(4));
            obj.setProduct_name(res.getString(5));
            obj.setDescription(res.getString(6));
            obj.setQuantity(res.getInt(7));
            obj.setPrice(res.getDouble(8));
            obj.setOffer(res.getDouble(9));
            obj.setImagePath(res.getString(10));
            obj.setImg_array(res.getBytes(11));
            products.add(obj);
        }

        return products;
    }

    public ArrayList<History_bean> get_SellerHistory(Seller_bean seller)throws Exception{

        ArrayList<History_bean> sellerHistory=new ArrayList<>();

        String query="select * from history where seller_id='"+seller.getSeller_id()+"'";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st= con.createStatement();

        ResultSet res=st.executeQuery(query);

        while(res.next()){
            History_bean history=new History_bean();
            history.setHistory_id(res.getString(1));
            history.setCustomer_id(res.getString(2));
            history.setProduct_id(res.getString(3));
            history.setSeller_id(res.getString(4));
            history.setShop_name(res.getString(5));
            history.setGenre(res.getString(6));
            history.setProduct_name(res.getString(7));
            history.setDescription(res.getString(8));
            history.setQuantity(res.getInt(9));
            history.setPrice(res.getDouble(10));
            history.setStatus(res.getString(11));
            history.setDatetime(res.getString(12));
            sellerHistory.add(history);
        }

        return sellerHistory;

    }

    public ArrayList<History_bean> get_UserHistory(String seller_id, String customer_id)throws Exception{

        ArrayList<History_bean> user_history=new ArrayList<>();

        String query="select * from history where seller_id='"+seller_id+"' && customer_id='"+customer_id+"'";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st= con.createStatement();

        ResultSet res=st.executeQuery(query);

        while(res.next()){
            History_bean history=new History_bean();
            history.setHistory_id(res.getString(1));
            history.setCustomer_id(res.getString(2));
            history.setProduct_id(res.getString(3));
            history.setSeller_id(res.getString(4));
            history.setShop_name(res.getString(5));
            history.setGenre(res.getString(6));
            history.setProduct_name(res.getString(7));
            history.setDescription(res.getString(8));
            history.setQuantity(res.getInt(9));
            history.setPrice(res.getDouble(10));
            history.setStatus(res.getString(11));
            history.setDatetime(res.getString(12));
            user_history.add(history);
        }

        return user_history;

    }

    public void add_History(History_bean history)throws Exception{

        String query="insert into history (history_id,customer_id,product_id,seller_id,shop_name,genre,product_name,description,quantity,price,status,datetime) values (?,?,?,?,?,?,?,?,?,?,?,?)";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        PreparedStatement ps=con.prepareStatement(query);

        ps.setString(1,history.getHistory_id());
        ps.setString(2,history.getCustomer_id());
        ps.setString(3,history.getProduct_id());
        ps.setString(4,history.getSeller_id());
        ps.setString(5,history.getShop_name());
        ps.setString(6,history.getGenre());
        ps.setString(7,history.getProduct_name());
        ps.setString(8,history.getDescription());
        ps.setInt(9,history.getQuantity());
        ps.setDouble(10,history.getPrice());
        ps.setString(11,history.getStatus());
        ps.setString(12,history.getDatetime());

        ps.executeUpdate();

        System.out.println("History added...");

    }

    public Product_bean getSellerId_from_finalPage(String product_id)throws Exception{

        Product_bean obj=new Product_bean();

        String query="select * from product where product_id='"+product_id+"'";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st= con.createStatement();

        ResultSet res=st.executeQuery(query);

        res.next();

        obj.setProduct_id(res.getString(1));
        obj.setSeller_id(res.getString(2));
        obj.setShop_name(res.getString(3));
        obj.setGenre(res.getString(4));
        obj.setProduct_name(res.getString(5));
        obj.setDescription(res.getString(6));
        obj.setQuantity(res.getInt(7));
        obj.setPrice(res.getDouble(8));
        obj.setOffer(res.getDouble(9));
        obj.setImagePath(res.getString(10));
        obj.setImg_array(res.getBytes(11));

        return obj;

    }

    public ArrayList<History_bean> get_History(Customer_bean customer)throws Exception{

        ArrayList<History_bean> customerHistory=new ArrayList<>();

        String query="select * from history where customer_id='"+customer.getCustomer_id()+"'";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st= con.createStatement();

        ResultSet res=st.executeQuery(query);

        while(res.next()){
            History_bean history=new History_bean();
            history.setHistory_id(res.getString(1));
            history.setCustomer_id(res.getString(2));
            history.setProduct_id(res.getString(3));
            history.setSeller_id(res.getString(4));
            history.setShop_name(res.getString(5));
            history.setGenre(res.getString(6));
            history.setProduct_name(res.getString(7));
            history.setDescription(res.getString(8));
            history.setQuantity(res.getInt(9));
            history.setPrice(res.getDouble(10));
            history.setStatus(res.getString(11));
            history.setDatetime(res.getString(12));
            customerHistory.add(history);
        }

        return customerHistory;

    }

    public String get_HistoryId()throws Exception{

        int count=0;

        String query="select * from history";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st= con.createStatement();

        ResultSet res=st.executeQuery(query);

        while(res.next()){
            count++;
        }

        return "hid"+count;
    }

    public String get_CartId() throws Exception{
        int count=0;
        String query="select * from cart";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st= con.createStatement();
        String s="";

        ResultSet res=st.executeQuery(query);

        while(res.next()){
            count=Integer.parseInt(res.getString(1).substring(6));
        }
        count++;
        return "cartid"+count;

    }

    public void add_cart(Product_bean product, Customer_bean customer)throws  Exception{

        String query="insert into cart (cart_id,product_id,customer_id,genre,product_name,price) values (?,?,?,?,?,?)";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        PreparedStatement ps=con.prepareStatement(query);

        ps.setString(1,get_CartId());
        ps.setString(2,product.getProduct_id());
        ps.setString(3,customer.getCustomer_id());
        ps.setString(4,product.getGenre());
        ps.setString(5,product.getProduct_name());
        ps.setDouble(6,product.getPrice());

        ps.executeUpdate();

        System.out.println("demo.Cart added...");

    }

    public ArrayList<Cart_bean> get_CustomerCart(Customer_bean customer)throws  Exception{

        ArrayList<Cart_bean> cart=new ArrayList<>();

        String query="select * from cart where customer_id='"+customer.getCustomer_id()+"'";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st= con.createStatement();

        ResultSet res=st.executeQuery(query);

        while(res.next()){
            Cart_bean items=new Cart_bean();
            items.setCart_id(res.getString(1));
            items.setProduct_id(res.getString(2));
            items.setCustomer_id(res.getString(3));
            items.setGenre(res.getString(4));
            items.setGenre(res.getString(5));
            items.setPrice(res.getDouble(6));
            cart.add(items);
        }

        return cart;

    }

    public Product_bean get_CartProduct(Cart_bean cart)throws Exception{

        String query="select * from product where product_id='"+cart.getProduct_id()+"'";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st= con.createStatement();

        ResultSet res=st.executeQuery(query);

        res.next();

        Product_bean obj=new Product_bean();

        obj.setProduct_id(res.getString(1));
        obj.setSeller_id(res.getString(2));
        obj.setShop_name(res.getString(3));
        obj.setGenre(res.getString(4));
        obj.setProduct_name(res.getString(5));
        obj.setDescription(res.getString(6));
        obj.setQuantity(res.getInt(7));
        obj.setPrice(res.getDouble(8));
        obj.setOffer(res.getDouble(9));
        obj.setImagePath(res.getString(10));
        obj.setImg_array(res.getBytes(11));

        return obj;

    }

    public boolean is_InCart(Product_bean product,Customer_bean customer)throws Exception{

        int count=0;

        String query="select * from cart where product_id='"+product.getProduct_id()+"' && customer_id='"+customer.getCustomer_id()+"'";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st= con.createStatement();

        ResultSet res=st.executeQuery(query);

        while(res.next()){
            count++;
            return false;
        }

        return true;
    }

    public void removeFromCart(Product_bean product,Customer_bean customer)throws  Exception{

        String query="delete from cart where product_id='"+product.getProduct_id()+"' && customer_id='"+customer.getCustomer_id()+"'";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st= con.createStatement();

        int res=st.executeUpdate(query);

        System.out.println("removed from cart!!!");

        con.close();

    }

    public Seller_bean get_SellerDetails(Product_bean product)throws Exception{

        Seller_bean seller=new Seller_bean();

        String query="select * from seller_details where seller_id='"+product.getSeller_id()+"'";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con=DriverManager.getConnection(url,uname,pass);
        Statement st= con.createStatement();

        ResultSet res=st.executeQuery(query);

        res.next();

        seller.setSeller_id(res.getString(1));
        seller.setUsername(res.getString(2));
        seller.setPassword(res.getString(3));
        seller.setShop_name(res.getString(4));
        seller.setEmail(res.getString(5));
        seller.setMobile_no(res.getString(6));
        seller.setState(res.getString(7));
        seller.setDistrict(res.getString(8));
        seller.setAddress(res.getString(9));

        return seller;

    }

}