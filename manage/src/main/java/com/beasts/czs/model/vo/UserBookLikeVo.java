package com.beasts.czs.model.vo;
import org.springframework.format.annotation.DateTimeFormat;
import java.sql.Timestamp;


public class UserBookLikeVo{
	private Integer id;	//ID

	private Integer userId;	//用户ID

	private Integer bookId;	//小说ID

	private String bookName;	//书名

	private String author;	//作者

	private Timestamp createTime;	//创建时间


	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}

	public Integer getUserId(){
		return userId;
	}
	public void setUserId(Integer userId){
		this.userId = userId;
	}

	public Integer getBookId(){
		return bookId;
	}
	public void setBookId(Integer bookId){
		this.bookId = bookId;
	}

	public String getBookName(){
		return bookName;
	}
	public void setBookName(String bookName){
		this.bookName = bookName;
	}

	public String getAuthor(){
		return author;
	}
	public void setAuthor(String author){
		this.author = author;
	}

	public Timestamp getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Timestamp createTime){
		this.createTime = createTime;
	}


}
