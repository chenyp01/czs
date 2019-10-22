package com.beasts.czs.model.vo;
import org.springframework.format.annotation.DateTimeFormat;
import java.sql.Timestamp;


public class BookContentVo{
	private Integer id;	//ID

	private Integer bookId;	//书本ID

	private Integer catalogId;	//章节ID

	private String catalogName;	//章节名称

	private String catalogUpdateTime;	//章节更新时间

	private String content;	//章节内容

	private Timestamp createTime;	//CREATE_TIME

	private String source;	//来源

	private String sourceName;	//来源名称

	private Integer orderNo;	//排序


	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}

	public Integer getBookId(){
		return bookId;
	}
	public void setBookId(Integer bookId){
		this.bookId = bookId;
	}

	public Integer getCatalogId(){
		return catalogId;
	}
	public void setCatalogId(Integer catalogId){
		this.catalogId = catalogId;
	}

	public String getCatalogName(){
		return catalogName;
	}
	public void setCatalogName(String catalogName){
		this.catalogName = catalogName;
	}

	public String getCatalogUpdateTime(){
		return catalogUpdateTime;
	}
	public void setCatalogUpdateTime(String catalogUpdateTime){
		this.catalogUpdateTime = catalogUpdateTime;
	}

	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content;
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

	public Integer getOrderNo(){
		return orderNo;
	}
	public void setOrderNo(Integer orderNo){
		this.orderNo = orderNo;
	}


}
