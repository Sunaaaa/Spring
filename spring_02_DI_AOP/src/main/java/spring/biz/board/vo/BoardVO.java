package spring.biz.board.vo;

import java.sql.Date;

public class BoardVO {
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private int cnt = 0;
	private String userid;

	public BoardVO() {
		super();
	}
	
	public BoardVO(int seq, String title, String writer, String content, Date regdate, int cnt, String userid) {
		super();
		this.seq = seq;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
		this.cnt = cnt;
		this.userid = userid;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regdate="
				+ regdate + ", cnt=" + cnt + ", userid=" + userid + "]";
	}

	

}
