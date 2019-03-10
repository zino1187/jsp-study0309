package com.itbank.model.domain;
//이 클래스의 정의 목적
//개발시 클래스 안에는 꼭 로직을 작성해야 할 경우만 있는건 아니다.
//즉, 이 클래스는 로직을 담기 위함이 아니라, 단지 데이터만을담기 위한목적으로
//정의한다..이런 목적으로 정의되는 클래스를 가리켜 설계분야에서는 DTO
//Data Transfer Object : 데이터 전달 객체!!
public class Board {
	private int board_id; 
	private String writer;
	private String title;
	private String content;
	private String regdate;
	private int hit;
	
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
	
}













