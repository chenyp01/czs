package com.beasts.czs.model.vo;
import org.springframework.format.annotation.DateTimeFormat;
import java.sql.Timestamp;


public class BookCatalogInfoVo{
	private Integer id;	//ID

	private String catalogName;	//章节名称

	private String detailUrl;	//章节内容链接地址

	private Integer orderNo;	//排序

	private String source;	//来源

	private String sourceName;	//来源名称

	private Timestamp createTime;	//创建时间

	private Integer bookId;	//书本ID


	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}

	public String getCatalogName(){
		return catalogName;
	}
	public void setCatalogName(String catalogName){
		this.catalogName = catalogName;
	}

	public String getDetailUrl(){
		return detailUrl;
	}
	public void setDetailUrl(String detailUrl){
		this.detailUrl = detailUrl;
	}

	public Integer getOrderNo(){
		return orderNo;
	}
	public void setOrderNo(Integer orderNo){
		this.orderNo = orderNo;
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

	public Timestamp getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Timestamp createTime){
		this.createTime = createTime;
	}

	public Integer getBookId(){
		return bookId;
	}
	public void setBookId(Integer bookId){
		this.bookId = bookId;
	}


}
