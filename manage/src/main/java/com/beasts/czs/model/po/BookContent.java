package com.beasts.czs.model.po;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "CZS_BOOK_CONTENT")
public class BookContent{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;	//ID

	@Column(name = "BOOK_ID")
	private Integer bookId;	//书本ID

	@Column(name = "CATALOG_ID")
	private Integer catalogId;	//章节ID

	@Column(name = "CATALOG_NAME")
	private String catalogName;	//章节名称

	@Column(name = "CATALOG_UPDATE_TIME")
	private String catalogUpdateTime;	//章节更新时间

	@Column(name = "CONTENT")
	private String content;	//章节内容

	@DateTimeFormat( pattern = "yyyy-MM-dd  HH:mm:ss")
	@Column(name = "CREATE_TIME")
	private Timestamp createTime;	//CREATE_TIME

	@Column(name = "SOURCE")
	private String source;	//来源

	@Column(name = "SOURCE_NAME")
	private String sourceName;	//来源名称

	@Column(name = "ORDER_NO")
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
