package guestbook;

import java.util.List;

public class MessageListView {
	private int messageTotalCount;
	private List<Message> messageList;
	private int currentPageNumber;
	private int pageTotalCount;
	private int messageCountPerPage;
	private int firstRow;
	private int endRow;
	public MessageListView(int messageTotalCount, List<Message> messageList, int currentPageNumber, int pageTotalCount,
			int messageCountPerPage, int firstRow, int endRow) {
		super();
		this.messageTotalCount = messageTotalCount;
		this.messageList = messageList;
		this.currentPageNumber = currentPageNumber;
		this.pageTotalCount = pageTotalCount;
		this.messageCountPerPage = messageCountPerPage;
		this.firstRow = firstRow;
		this.endRow = endRow;
		
		calculatePageTotalCount();
	}
	private void calculatePageTotalCount() {
		if(messageTotalCount==0) {
			pageTotalCount=0;
		}else {
			pageTotalCount = messageTotalCount/messageCountPerPage;
			if(messageTotalCount % messageCountPerPage>0) {
				pageTotalCount++;
			}
		}
	}
	public int getMessageTotalCount() {
		return messageTotalCount;
	}
	public void setMessageTotalCount(int messageTotalCount) {
		this.messageTotalCount = messageTotalCount;
	}
	public List<Message> getMessageList() {
		return messageList;
	}
	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}
	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}
	public int getPageTotalCount() {
		return pageTotalCount;
	}
	public void setPageTotalCount(int pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}
	public int getMessageCountPerPage() {
		return messageCountPerPage;
	}
	public void setMessageCountPerPage(int messageCountPerPage) {
		this.messageCountPerPage = messageCountPerPage;
	}
	public int getFirstRow() {
		return firstRow;
	}
	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	
	public boolean isEmpty() {
		return messageTotalCount==0;
	}
	
	
	
}
