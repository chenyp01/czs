package com.beasts.czs.model.vo;
import org.springframework.format.annotation.DateTimeFormat;
import java.sql.Timestamp;


public class BookInfoVo{
	private Integer id;	//ID

	private String bookName;	//书名

	private String author;	//作者

	private String bookImg;	//小说封面图片

	private String type;	//小说类型

	private String status;	//小说状态

	private String updateTime;	//最新章节更新时间

	private String wordNum;	//总字数

	private String remark;	//简介

	private Timestamp createTime;	//创建时间

	private String source;	//来源

	private String sourceName;	//来源网站名称

	private String bookId;	//原网站书本ID

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
