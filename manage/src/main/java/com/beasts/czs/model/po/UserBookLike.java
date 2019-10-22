package com.beasts.czs.model.po;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "CZS_USER_BOOK_LIKE")
public class UserBookLike{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;	//ID

	@Column(name = "USER_ID")
	private Integer userId;	//用户ID

	@Column(name = "BOOK_ID")
	private Integer bookId;	//小说ID

	@Column(name = "BOOK_NAME")
	private String bookName;	//书名

	@Column(name = "AUTHOR")
	private String author;	//作者

	@DateTimeFormat( pattern = "yyyy-MM-dd  HH:mm:ss")
	@Column(name = "CREATE_TIME")
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
