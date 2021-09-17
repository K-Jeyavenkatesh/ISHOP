package payment_gateway;

import java.awt.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class OTPThread extends Thread{

	OTPverify o;
	
	public OTPThread(OTPverify o) {
		this.o = o;
	}
	
	public void run() {
		
		if(o.valid_time != null) {
			o.main_panel.remove(o.valid_time);
			o.frame.validate();
			o.frame.repaint();
		}
		o.valid_time = new JLabel("60 seconds");
		o.valid_time.setBounds(175, 310, 200, 100);
		o.valid_time.setFont(new Font("Helvetica", Font.BOLD, 20));
		o.main_panel.add(o.valid_time);
		try {
			new sample1(o).main(o);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Helper1 extends TimerTask
{
	OTPverify o;
	public Helper1(OTPverify o) {
		this.o = o;
	}
    public int i = 60;
    public void run()
    {
        o.valid_time.setText(--this.i+" seconds");
        o.frame.validate();
        o.frame.repaint();
        //System.out.println(i);
        if(this.i == 0)
        {
        	o.resend.setEnabled(true);
        	o.time_limit = 0;
            synchronized(sample1.obj)
            {
                sample1.obj.notify();
            }
        }
    }
      
}
  
class sample1
{
    protected static sample1 obj;
    
    OTPverify o;
	public sample1(OTPverify o) {
		this.o = o;
	}
    public void main(OTPverify o) throws InterruptedException
    {
        obj = new sample1(o);
        Timer timer = new Timer();
        TimerTask task = new Helper1(o);
        Date date = new Date();
  
        timer.scheduleAtFixedRate(task, date, 1000);
          
       // System.out.println("Timer running");
        synchronized(obj)
        {
            obj.wait();
            timer.cancel();
            System.out.println(timer.purge());
        }
    }
}
