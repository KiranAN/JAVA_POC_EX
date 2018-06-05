package com.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;

import com.model.UserMsg;
import com.model.UserMsgMapper;

public class UserDAOImpl implements UserDAO{
	public static UserDAOImpl instance = null;	
	public String connectionString = "jdbc:h2:~/test;AUTO_SERVER=TRUE";
	public DBI jdbi = null;
	public static UserDAOImpl getInstance() {
		if(instance == null) {
			instance = new UserDAOImpl();
		}
		return instance;
	}
	
	public UserDAOImpl() {
		loadDriver();
	}
	
	public void loadDriver() {
		try {
			System.out.println("Loading driver...");
			Class.forName("org.h2.Driver");
			System.out.println("Driver loaded!");
			jdbi = new DBI(connectionString,"sa","sa");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<UserMsg> getAllUsers() {
		Handle handle = null;
		try {					
			handle = jdbi.open();			
			Query<Map<String, Object>> q =handle.createQuery("SELECT * FROM USERS");
			List<Map<String, Object>> l = q.list();
			List<UserMsg> users = new ArrayList<UserMsg>();
			for (Map<String, Object> r : l) {
			    UserMsg user = new UserMsg(r.get("name").toString(),
					Integer.parseInt(r.get("age").toString()),
					r.get("address").toString(),
					Double.parseDouble((r.get("salary").toString())));      
			    users.add(user);
			 }
			
		return users;						
		}catch(Exception e){
		}
		finally {		
		    if (handle != null) {
			handle.close();
		    }
		}
		return null;
	}

	@Override
	public UserMsg getUser(int id) {
		Handle handle = null;
		try {						
			handle = jdbi.open();			
			Query<Map<String, Object>> q =handle.createQuery("SELECT * FROM USERS WHERE ID = ?").bind(0, id);		
			UserMsg user = q.map(new UserMsgMapper()).first();
			return user;						
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		    if (handle != null) {
			handle.close();
		    }
		}
		return null;
	}

	@Override
	public void updateUser(UserMsg user, int id) {
		Handle handle = null;
		try {						
			handle = jdbi.open();				
			handle.execute("UPDATE USERS SET Name = ?,Age =?,Address =?, Salary =? WHERE id = ?",user.name,user.age,user.address,user.salary,id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		    if (handle != null) {
			handle.close();
		    }
		}				
	}

	@Override
	public boolean deleteUser(int id) {
		Handle handle = null;
		try {							
			handle = jdbi.open();				
			handle.execute("DELETE FROM USERS WHERE ID=?",id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		    if (handle != null) {
			handle.close();
		    }
		}
		return true;
	}

	@Override
	public int createUser(UserMsg user) {
		Handle handle = null;
		try {						
			handle = jdbi.open();				
			handle.execute("INSERT INTO USERS (Name,Age,Address, Salary) VALUES (?,?,?,?)",user.name,user.age,user.address,user.salary);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		    if (handle != null) {
			handle.close();
		    }
		}
		// TO DO : SHOULD BE ROW ID
		return 1;
	}

}
