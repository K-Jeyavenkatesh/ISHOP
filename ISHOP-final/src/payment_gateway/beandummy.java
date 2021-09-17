package payment_gateway;

import demo.ItemBean;

public class beandummy {
	
	public static ItemBean[] Itembean() {
		ItemBean[] arr = new ItemBean[3];
		for(int i = 0; i < 3; i++) {
			arr[i] = new ItemBean();
			arr[i].setCustomerName("Jeyavenkatesh");
			arr[i].setCustomerID("kjv123");
			arr[i].setPhoneNumber("9876543210");
			arr[i].setProductID("redmi123");
			arr[i].setProductName("redmi");
			arr[i].setProductDescription("redmi phone");
			arr[i].setDataTime("19-08-2021");
			arr[i].setDelivery("21-08-2021");
			arr[i].setPaymentMode("COD");
			arr[i].setProductPrice("15000.00");
			arr[i].setProductQuantity("1");
			arr[i].setShopName("Jeya Mobiles");
		}
		return arr;
	}
	

}
