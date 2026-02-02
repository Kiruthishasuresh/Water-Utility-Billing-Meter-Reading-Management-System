package com.water.bean;

public class Consumer {
	private String consumerID;
	private String fullName ;
	private String address ;
	private String meterNumber ;
	private String 	connectionType;
	private double outstandingBalance; 

	public Consumer(String consumerID, String fullName, String address, String meterNumber, String connectionType,
			double outstandingBalance) {
		super();
		this.consumerID = consumerID;
		this.fullName = fullName;
		this.address = address;
		this.meterNumber = meterNumber;
		this.connectionType = connectionType;
		this.outstandingBalance = outstandingBalance;
	}

	public String getConsumerID() {
		return consumerID;
	}

	public void setConsumerID(String consumerID) {
		this.consumerID = consumerID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMeterNumber() {
		return meterNumber;
	}

	public void setMeterNumber(String meterNumber) {
		this.meterNumber = meterNumber;
	}

	public String getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

	public double getOutstandingBalance() {
		return outstandingBalance;
	}

	public void setOutstandingBalance(double outstandingBalance) {
		this.outstandingBalance = outstandingBalance;
	}

	

}
