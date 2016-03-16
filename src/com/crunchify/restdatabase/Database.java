package com.crunchify.restdatabase;

import java.util.HashMap;
import java.util.Map;

public class Database {

	static Map<String, User> userDatabase;

	static void init() {
//		Database db = new Database();
		userDatabase = new HashMap<String, User>();
	}

	public static Map<String, User> getDatabase() {
		if(userDatabase == null)
		{
			userDatabase = new HashMap<String, User>();
		}
		return userDatabase;
	}

}
