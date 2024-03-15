package guestbook;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.DBCPInit;

public class WriteMessageService {
	private static WriteMessageService instance  = new WriteMessageService();
	
	public static WriteMessageService getInstance(){
		return instance;
	}
	
	private WriteMessageService() {}
	
	public void write(Message message) {
		try(Connection conn = DBCPInit.getConnection()){
			MessageDao messageDao = MessageDao.getInstance();
			messageDao.insert(conn, message);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
