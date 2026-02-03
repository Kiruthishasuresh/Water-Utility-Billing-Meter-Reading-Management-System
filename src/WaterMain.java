import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.water.bean.MeterReading;
import com.water.service.BillingService;

public class WaterMain { 
private static BillingService billingService; 
public static void main(String[] args) { 
billingService = new BillingService(); 
Scanner sc = new Scanner(System.in); 
System.out.println("--- Water Utility Billing Console --");  
try { 
boolean reading = 
billingService.recordMeterReading(
	    "CUS1001",
	    "MTR-5001",
	    new Date(2025-1900-2-28),
	    1300,
	    "MeterReader02"
	);
System.out.println(reading ? "RECORDED" : "FAILED"); 
} catch(Exception e) { System.out.println(e); }
try { 
MeterReading prev = 
billingService.getMeterReadingService().findLatestReading("CUS1001"); 
MeterReading curr = new MeterReading(); 
curr.setReadingValue(1350); 
Calendar cal = Calendar.getInstance();
cal.set(2025, Calendar.MARCH, 31); 
Date currDate = cal.getTime();
curr.setReadingDate(currDate);
boolean r = 
BillingService.generateMonthlyBill("CUS1001", prev, curr); 
System.out.println(r ? "BILL GENERATED" : "FAILED"); 
} catch(Exception e) { System.out.println(e); } 
// TEST 3: Cancel a bill 
try { 
boolean r = BillingService.cancelBill(610001); 
System.out.println(r ? "CANCELLED" : "FAILED"); 
} catch(Exception e) { System.out.println(e); } 
sc.close(); 
} 
}
