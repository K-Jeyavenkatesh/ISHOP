package payment_gateway;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
//import com.itextpdf.text.Document;  
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;  
//import com.itextpdf.text.pdf.PdfWriter; 

public class GeneratePdf { 
	
	public void GeneratePdf(ItemBean arr[]) {  
	  
		JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        f.showSaveDialog(null);
        String path = f.getSelectedFile().toString();
        
        String path2 = "";
        for(int i = 0; i < path.length(); i++) {
        	
        	if(path.charAt(i) == '\\') {
        		path2 += "\\\\";
        		System.out.println(path2);
        	} else {
        		path2 += path.charAt(i);
        	}
        }
        path2 = path2+"\\\\";
        String  name = JOptionPane.showInputDialog("Download PDF as","I-Shop OrderBill");
        path2 += name+".pdf";
        System.out.println();
        com.itextpdf.text.Document doc = new com.itextpdf.text.Document();  
		try {  
			com.itextpdf.text.pdf.PdfWriter writer = com.itextpdf.text.pdf.PdfWriter.getInstance(doc, new FileOutputStream(path2));  
			System.out.println("PDF created.");    
			doc.open();
			String line0 = "BILL FOR SHOPPING ORDERS\n";
			String line01 = "---------------------------------------------------------"
					+ "-------------------------------------------------------------------------";
			String line2 = "	I_SHOP - a future shop";
			doc.add(new Paragraph(line0));
			doc.add(new Paragraph(line01));
			doc.add(new Paragraph(line2));
			for(int ii = 0; ii < arr.length; ii++) {
				
			String line1 = "NO : "+(ii+1);
			//String line4 = "Customer name : "+arr[ii].getCustomerName();
			String line5 = "Customer ID : "+arr[ii].getCustomerID();
			String line6 = "Phone number : "+arr[ii].getPhoneNumber();
			String line7 = "Shop Name : "+arr[ii].getShopName();
			String line8 = "Product ID : "+arr[ii].getProductID();
			String line9 = "Product name : "+arr[ii].getProductName();
			String line10 = "Product Description : "+arr[ii].getProductDescription();
			String line11 = "Product Quantity : "+arr[ii].getProductQuantity();
			String line12 = "Product Price : "+arr[ii].getProductPrice();
			String line13= "Date and Time : "+arr[ii].getDataTime();
			String line14 = "Payment Mode :"+arr[ii].getPaymentMode();
			String line15 = "Delivery on : "+arr[ii].getDelivery();
			
			String[] arr1 = {line01,line1,line5,line6,line7,line8,line9,line10,line11,line12,line13,line14,line15,line01};
			for(int i = 0; i < arr1.length; i++){
				doc.add(new Paragraph(arr1[i]));
			}
			}
			doc.close();  
			writer.close();  
		}   
		catch (DocumentException e) {  
			e.printStackTrace();  
		}   
		catch (FileNotFoundException e) {  
			e.printStackTrace();  
		} 
	}  
}  
