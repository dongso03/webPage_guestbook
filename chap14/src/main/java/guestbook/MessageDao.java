package guestbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MessageDao {
	private static MessageDao messageDao = new MessageDao();

	public static MessageDao getInstance() {
		return messageDao;
	}

	private MessageDao() {
	}

	public int insert(Connection conn, Message message) throws SQLException {
		String sql = "inset into guestbook_message" + "(guest_name,password,message)values (?,?,?)";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, message.getGuestName());
			stmt.setString(2, message.getPassword());
			stmt.setString(3, message.getMessage());

			return stmt.executeUpdate();
		}
	}

	public Message select(Connection conn, int messageId) throws SQLException {
		String sql = "select * from guestbook_message where message_id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, messageId);
			try (ResultSet rs = stmt.executeQuery();) {
				if (rs.next()) {
					return makeMessageFromResultSet(rs);
				} else {
					return null;
				}
			}
		}
	}

	private Message makeMessageFromResultSet(ResultSet rs) throws SQLException {
		Message message = new Message();
		message.setId(rs.getInt("message_id"));
		message.setGuestName(rs.getString("guest_name"));
		message.setPassword(rs.getString("password"));
		message.setMessage(rs.getString("message"));
		return message;
	}

	public int selectCount(Connection conn) throws SQLException {
		String sql = "select count(*) from guestbook_message";
		try (Statement stmt = conn.createStatement();) {
			try (ResultSet rs = stmt.executeQuery(sql);) {
				rs.next();
				return rs.getInt(1);
			}
		}
	}
	
	public List<Message> selectList(Connection conn,int firstRow, int endRow) throws SQLException{
		try(PreparedStatement stmt = conn.prepareStatement("select * from guestbook_message order by message_id desc limit ?,?");){
			stmt.setInt(1, firstRow -1);
			stmt.setInt(2, endRow-firstRow +1);
			try(ResultSet rs = stmt.executeQuery()){
				if(rs.next()) {
					List<Message> messageList = new ArrayList<Message>();
					do {
						messageList.add(makeMessageFromResultSet(rs));
					} while(rs.next());
					return messageList;
				}else {
					return Collections.emptyList();
				}
			}
		}
	}
	public int delete(Connection conn , int messageId) throws SQLException {
		try(PreparedStatement stmt = conn.prepareStatement("delet from guestbook_message where message_id =?")){
			stmt.setInt(1, messageId);
			return stmt.executeUpdate();
		}
	}
	
}
