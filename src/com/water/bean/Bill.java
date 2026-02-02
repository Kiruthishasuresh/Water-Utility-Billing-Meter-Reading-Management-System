package com.water.bean;

import java.util.Date;

public class Bill {
	private int billID;
	private String consumerID;
	private Date periodFrom;
	private Date periodTo;
	private double unitsConsumed ;
	private double amount ; 
	 private Date billDate; 
	 private String status ;
	 public Bill(int billID, String consumerID, Date periodFrom, Date periodTo, double unitsConsumed, double amount,
			Date billDate, String status) {
		super();
		this.billID = billID;
		this.consumerID = consumerID;
		this.periodFrom = periodFrom;
		this.periodTo = periodTo;
		this.unitsConsumed = unitsConsumed;
		this.amount = amount;
		this.billDate = billDate;
		this.status = status;
	 }
	 public int getBillID() {
		 return billID;
	 }
	 public void setBillID(int billID) {
		 this.billID = billID;
	 }
	 public String getConsumerID() {
		 return consumerID;
	 }
	 public void setConsumerID(String consumerID) {
		 this.consumerID = consumerID;
	 }
	 public Date getPeriodFrom() {
		 return periodFrom;
	 }
	 public void setPeriodFrom(Date periodFrom) {
		 this.periodFrom = periodFrom;
	 }
	 public Date getPeriodTo() {
		 return periodTo;
	 }
	 public void setPeriodTo(Date periodTo) {
		 this.periodTo = periodTo;
	 }
	 public double getUnitsConsumed() {
		 return unitsConsumed;
	 }
	 public void setUnitsConsumed(double unitsConsumed) {
		 this.unitsConsumed = unitsConsumed;
	 }
	 public double getAmount() {
		 return amount;
	 }
	 public void setAmount(double amount) {
		 this.amount = amount;
	 }
	 public Date getBillDate() {
		 return billDate;
	 }
	 public void setBillDate(Date billDate) {
		 this.billDate = billDate;
	 }
	 public String getStatus() {
		 return status;
	 }
	 public void setStatus(String status) {
		 this.status = status;
	 }
	

}
