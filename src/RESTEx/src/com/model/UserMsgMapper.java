package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class UserMsgMapper implements ResultSetMapper<UserMsg>{
	@Override
	public UserMsg map(int index, ResultSet r, StatementContext ctx) throws SQLException
	{		
		return new UserMsg(r.getString("name"),r.getInt("age"),r.getString("address"),r.getDouble("salary"));		
	}
}