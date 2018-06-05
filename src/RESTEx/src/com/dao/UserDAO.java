package com.dao;

import com.model.*;
import java.util.List;

public interface UserDAO {
	public List<UserMsg> getAllUsers();
	public UserMsg getUser(int id);
	public int createUser(UserMsg user);
	public void updateUser(UserMsg user, int id);
	public boolean deleteUser(int id);
}
