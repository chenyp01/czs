package com.beasts.czs.model.po;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "CZS_BOOK_INFO")
public class BookInfo{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;	//ID

	@Column(name = "BOOK_NAME")
	private String bookName;	//书名

	@Column(name = "AUTHOR")
	private String author;	//作者

	@Column(name = "BOOK_IMG")
	private String bookImg;	//小说封面图片

	@Column(name = "TYPE")
	private String type;	//小说类型

	@Column(name = "STATUS")
	private String status;	//小说状态

	@Column(name = "UPDATE_TIME")
	private String updateTime;	//最新章节更新时间

	@Column(name = "WORD_NUM")
	private String wordNum;	//总字数

	@Column(name = "REMARK")
	private String remark;	//简介

	@DateTimeFormat( pattern = "yyyy-MM-dd  HH:mm:ss")
	@Column(name = "CREATE_TIME")
	private Timestamp createTime;	//创建时间

	@Column(name = "SOURCE")
	private String source;	//来源

	@Column(name = "SOURCE_NAME")
	private String sourceName;	//来源网站名称

	@Column(name = "BOOK_ID")
	private String bookId;	//原网站书本ID

	@Column(name = "NEW_CATALOG")
	private String newCatalog;	//最新章节


	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
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

	public String getBookImg(){
		return bookImg;
	}
	public void setBookImg(String bookImg){
		this.bookImg = bookImg;
	}

	public String getType(){
		return type;
	}
	public void setType(String type){
		this.type = type;
	}

	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}

	public String getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

	public String getWordNum(){
		return wordNum;
	}
	public void setWordNum(String wordNum){
		this.wordNum = wordNum;
	}

	public String getRemark(){
		return remark;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}

	public Timestamp getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Timestamp createTime){
		this.createTime = createTime;
	}

	public String getSource(){
		return source;
	}
	public void setSource(String source){
		this.source = source;
	}

	public String getSourceName(){
		return sourceName;
	}
	public void setSourceName(String sourceName){
		this.sourceName = sourceName;
	}

	public String getBookId(){
		return bookId;
	}
	public void setBookId(String bookId){
		this.bookId = bookId;
	}

	public String getNewCatalog(){
		return newCatalog;
	}
	public void setNewCatalog(String newCatalog){
		this.newCatalog = newCatalog;
	}


}
