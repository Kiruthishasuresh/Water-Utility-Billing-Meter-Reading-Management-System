package com.water.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.water.bean.MeterReading;
import com.water.util.DBUtil;

public class MeterReadingDAO {
	Connection connection=DBUtil.getDBConnection();
	public int generateReadingID() {
		Connection connection=DBUtil.getDBConnection();
		String query=" select transactionId_seq2.NEXTVAL from dual";
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
	public boolean recordReading(MeterReading reading) {
		String query="insert into meter_reading_tbl values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(1, reading.getReadingID());
	        ps.setString(2, reading.getConsumerID());
	        ps.setString(3, reading.getMeterNumber());
	        ps.setDate(4, new java.sql.Date(reading.getReadingDate().getTime()));
	        ps.setDouble(5, reading.getReadingValue());
	        ps.setString(6, reading.getRecordedBy());
	        int row=ps.executeUpdate();
	        if(row>0)
	        	return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}
	public MeterReading findLatestReading(String consumerID) {
		MeterReading reading=null;
		String query="select * from meter_reading_tbl where consumer_id=? order by reading_date desc";
				PreparedStatement ps;
				try {
					ps = connection.prepareStatement(query);
					ps.setString(1, consumerID);
					ResultSet rs=ps.executeQuery();
					if(rs.next()) {
						reading=new MeterReading(rs.getInt(1),
				                rs.getString(2),
				                rs.getString(3),
				                rs.getDate(4),
				                rs.getDouble(5),
				                rs.getString(6));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return reading;
		
	}
	public List<MeterReading> viewReadingHistory(String consumerID){
		List<MeterReading>reading_List=new ArrayList<>();
		String query="select * from meter_reading_tbl where consumer_id=? order by reading_date";
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, consumerID);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				MeterReading reading=new MeterReading(
						rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getDouble(5),rs.getString(6));
				reading_List.add(reading);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return reading_List;
		
	}

}
