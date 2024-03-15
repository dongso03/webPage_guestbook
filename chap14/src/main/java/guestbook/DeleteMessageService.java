package guestbook;

import java.sql.Connection;
import java.sql.SQLException;

import javax.el.MethodNotFoundException;

import jdbc.DBCPInit;
import jdbc.JdbcUtil;

public class DeleteMessageService {

	private static DeleteMessageService instance = new DeleteMessageService();

	
	public static DeleteMessageService getInstance() {
		return instance;
	}

	private DeleteMessageService() {}
	
	public void deleteMessage(int messageId, String password) {
		
		try(Connection conn = DBCPInit.getConnection();){
			conn.setAutoCommit(false);
			
			MessageDao messageDao = MessageDao.getInstance();
			Message message = messageDao.select(conn, messageId);
			if(message==null) {
				throw new MethodNotFoundException("no exist message");
			}
			if(!message.matchPassword(password)) {
			}
			messageDao.delete(conn, messageId);
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
