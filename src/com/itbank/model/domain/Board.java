package com.itbank.model.domain;
//�� Ŭ������ ���� ����
//���߽� Ŭ���� �ȿ��� �� ������ �ۼ��ؾ� �� ��츸 �ִ°� �ƴϴ�.
//��, �� Ŭ������ ������ ��� ������ �ƴ϶�, ���� �����͸������ ���Ѹ�������
//�����Ѵ�..�̷� �������� ���ǵǴ� Ŭ������ ������ ����о߿����� DTO
//Data Transfer Object : ������ ���� ��ü!!
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













