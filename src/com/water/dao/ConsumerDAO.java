package com.water.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.water.bean.Consumer;
import com.water.util.DBUtil;


public class ConsumerDAO {
	Connection connection=DBUtil.getDBConnection();

	public Consumer findConsumer(String consumerID){
		Consumer consumer=null;
		
		String query="select * from consumer_tbl where consumer_id=?";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1,consumerID);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
			consumer=new Consumer(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6));	
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return consumer;
		
		
	}
	public List<Consumer> viewAllConsumers(){

		List<Consumer> consumer_list = new ArrayList<>();
		String query="select * from consumer_tbl";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Consumer consumer= new Consumer(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6));
				consumer_list.add(consumer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return consumer_list;
		
	}
	public boolean insertConsumer(Consumer consumer) {
		String query="insert into consumer_tbl values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, consumer.getConsumerID());
			ps.setString(2, consumer.getFullName());
			ps.setString(3, consumer.getAddress());
			ps.setString(4, consumer.getMeterNumber());
			ps.setString(5, consumer.getConnectionType());
			ps.setDouble(6, consumer.getOutstandingBalance());
			int row=ps.executeUpdate();
			if(row>0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
		
	}
	public boolean updateOutstandingBalance(String consumerID,double newBalance) {
		String query="Update consumer_tbl set OUTSTANDING_BALANCE=? where consumer_id=?";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setDouble(1,newBalance);
			ps.setString(2, consumerID);
			int isupdate=ps.executeUpdate();
			if(isupdate>0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	public boolean deleteConsumer(String consumerID) {
		int isdelete=0;
		String query="delete from consumer_tbl where consumer_id=?";
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1, consumerID);
			isdelete=ps.executeUpdate();
			if(isdelete>0)
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}
	

}
