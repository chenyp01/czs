package com.beasts.czs.model.po;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "CZS_BOOK_CATALOG_INFO")
public class BookCatalogInfo{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;	//ID

	@Column(name = "CATALOG_NAME")
	private String catalogName;	//章节名称

	@Column(name = "DETAIL_URL")
	private String detailUrl;	//章节内容链接地址

	@Column(name = "ORDER_NO")
	private Integer orderNo;	//排序

	@Column(name = "SOURCE")
	private String source;	//来源

	@Column(name = "SOURCE_NAME")
	private String sourceName;	//来源名称

	@DateTimeFormat( pattern = "yyyy-MM-dd  HH:mm:ss")
	@Column(name = "CREATE_TIME")
	private Timestamp createTime;	//创建时间

	@Column(name = "BOOK_ID")
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
