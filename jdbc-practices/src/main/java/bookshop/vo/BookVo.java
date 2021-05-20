package bookshop.vo;

public class BookVo {
	private Long no;
	private String title;
	private String status;
	private Long authorNo; // 객체지향 X
	private String authorName; // 객체지향 X
	//private AuthorVo author; // 객체지향으로 설계
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getAuthorNo() {
		return authorNo;
	}
	public void setAuthorNo(Long authorNo) {
		this.authorNo = authorNo;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	@Override
	public String toString() {
		return "BookVo [no=" + no + ", title=" + title + ", status=" + status + ", authorNo=" + authorNo
				+ ", authorName=" + authorName + "]";
	}
	
}
