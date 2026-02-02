package com.water.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.water.bean.Bill;
import com.water.util.DBUtil;

public class BillDAO {
	Connection connection=DBUtil.getDBConnection();
	public int generateBillID() {
		
		String query=" select transactionId_seq1.NEXTVAL from dual";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int sequence_number=rs.getInt(1);
			return sequence_number;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	public boolean recordBill(Bill bill) {
		String query="insert into bill_tbl values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(1, bill.getBillID());
			ps.setString(2, bill.getConsumerID());
			ps.setDate(3,new Date(bill.getPeriodFrom().getTime()));
			ps.setDate(4,new Date(bill.getPeriodTo().getTime()));
			ps.setDouble(5,bill.getUnitsConsumed());
			ps.setDouble(6, bill.getAmount());
            ps.setString(7, bill.getStatus());
            ps.setDate(8, new Date(System.currentTimeMillis()));
            int isadd=ps.executeUpdate();
            if(isadd>0)
            	return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	public boolean updateBillStatus(int billID,String status) {
		String query="update bill_tbl set status=? where bill_id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,status);
			ps.setInt(2,billID);
			int isupdate=ps.executeUpdate();
			if(isupdate>0)
				return true;
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
		
	}
	public Bill findBill(int billID) {
		Bill bill=null;
		String query="select * from bill_tbl where bill_ID=?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, billID);
		    ResultSet rs=ps.executeQuery();
			if(rs.next()) {
			 bill=new Bill(
						rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getDate(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        rs.getDate(7),
                        rs.getString(8));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bill;
		
	}
	}
	

