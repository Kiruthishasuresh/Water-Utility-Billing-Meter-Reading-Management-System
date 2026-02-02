package com.water.service;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.water.bean.Bill;
import com.water.bean.Consumer;
import com.water.bean.MeterReading;
import com.water.dao.BillDAO;
import com.water.dao.ConsumerDAO;
import com.water.dao.MeterReadingDAO;
import com.water.util.ReadingAlreadyExistsException;
import com.water.util.ValidationException;

public class BillingService {
	static ConsumerDAO cdao=new ConsumerDAO();
	static MeterReadingDAO mdao=new MeterReadingDAO();
	static BillDAO bdao=new BillDAO();
	
	public MeterReadingDAO getMeterReadingService() {
        return mdao;
    }
	public void viewConsumerDetails(String consumerID){
		Consumer consumer=null;
		consumer=cdao.findConsumer(consumerID);
		if(consumer==null){
			System.out.println( new ValidationException());
				return;}
		
		System.out.println("Consumer ID       : " + consumer.getConsumerID());
	    System.out.println("Full Name         : " + consumer.getFullName());
	    System.out.println("Address           : " + consumer.getAddress());
	    System.out.println("Meter Number      : " + consumer.getMeterNumber());
	    System.out.println("Connection Type   : " + consumer.getConnectionType());
	    System.out.println("Outstanding Bal.  : " + consumer.getOutstandingBalance());
		
	}
	public List<Consumer> viewAllConsumers(){
		List<Consumer>consumer_List=cdao.viewAllConsumers();
		return consumer_List;
		
	}
	public boolean addNewConsumer(Consumer consumer) {
		boolean isadd=false;
		isadd=cdao.insertConsumer(consumer);
		return isadd;
		
	}
	public boolean recordMeterReading(String consumerID,String meterNumber,Date readingDate,double readingValue,String recordedBy) {
		
		if(consumerID==null||meterNumber==null||readingDate==null||readingValue<0) {
			System.out.println( new ValidationException());
			return false;
		}
		
		Consumer consumer=cdao.findConsumer(consumerID);
		if(consumer==null) {
			System.out.println("Consumer does not exist.");
	        return false;
		}
		MeterReading LastReading=mdao.findLatestReading(consumerID);
		if(LastReading!=null&&readingValue<LastReading.getReadingValue()) {
			System.out.print(new ValidationException());
			return false;
		}
		if (LastReading != null) {
	        Calendar newDate = Calendar.getInstance();
	        newDate.setTime(LastReading.getReadingDate());

	        Calendar oldDate = Calendar.getInstance();
	        oldDate.setTime(readingDate);

	        if (newDate.get(Calendar.MONTH) == oldDate.get(Calendar.MONTH)
	                && newDate.get(Calendar.YEAR) == oldDate.get(Calendar.YEAR)) {
	            System.out.println(new ReadingAlreadyExistsException());
	            return false;
	        }
	    }			
			int readingId=mdao.generateReadingID();
			MeterReading newReading = new MeterReading();
	        newReading.setReadingID(readingId);
	        newReading.setConsumerID(consumerID);
	        newReading.setMeterNumber(meterNumber);
	        newReading.setReadingDate(readingDate);
	        newReading.setReadingValue(readingValue);
	        newReading.setRecordedBy(recordedBy);
	        boolean isrecorded=mdao.recordReading(newReading);			
	        if(!isrecorded) {
	        return false;
            }
	        if(LastReading!=null) {
	        	boolean autobill=generateMonthlyBill(consumerID,LastReading,newReading);
	        	if(!autobill) {
	        		return false;
	        	}
	        }
		return true;
	}
	public static boolean generateMonthlyBill(String consumerID, MeterReading previousReading, MeterReading currentReading) {
		if(consumerID==null||previousReading==null||currentReading==null) {
			System.out.println( new ValidationException());
			return false;
		}
		double prevreading=previousReading.getReadingValue();
		double currreading=currentReading.getReadingValue();
		if(prevreading>currreading) {
			System.out.println(new ValidationException());
	        return false;
		}
		double units=currreading-prevreading;
		double newamount=units*5;
		 Consumer consumer = cdao.findConsumer(consumerID);
		    if (consumer == null) {
		        return false;
		    }
		    double newBalance = consumer.getOutstandingBalance() + newamount;

		    boolean updated = cdao.updateOutstandingBalance(consumerID, newBalance);
		    if (!updated) {
		        return false;
		    }
		    System.out.println("Bill Generated");
		    System.out.println("Units Consumed : " + units);
		    System.out.println("Bill Amount    : " + newamount);
		    System.out.println("New Balance    : " + newBalance);
		return true;
		
	}
	public static boolean cancelBill(int billID) {

	    if (billID <= 0) {
	        System.out.println(new ValidationException());
	        return false;
	    }

	    Bill bill = bdao.findBill(billID);
	    if (bill == null) {
	        System.out.println("Bill not found.");
	        return false;
	    }

	    if ("CANCELLED".equals(bill.getStatus())) {
	        System.out.println("Bill already cancelled.");
	        return false;
	    }

	    Consumer consumer = cdao.findConsumer(bill.getConsumerID());
	    if (consumer == null) {
	        return false;
	    }

	    double newBalance = consumer.getOutstandingBalance() - bill.getAmount();

	    boolean updated = cdao.updateOutstandingBalance(bill.getConsumerID(), newBalance);
	    if (!updated) {
	        return false;
	    }

	    boolean cancelled =bdao.updateBillStatus(billID, "CANCELLED");
	    if (!cancelled) {
	        return false;
	    }

	    System.out.println("Bill cancelled successfully.");
	    return true;
	}


}
