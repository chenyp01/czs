package com.beasts.czs.model.po;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "CZS_USER")
public class User{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;	//ID

	@Column(name = "USER_NAME")
	private String userName;	//用户名

	@Column(name = "PASSWORD")
	private String password;	//密码

	@Column(name = "OTHER_NAME")
	private String otherName;	//别名

	@Column(name = "PHONE_NO")
	private String phoneNo;	//手机号码

	@Column(name = "CARD_NO")
	private String cardNo;	//身份证号

	@Column(name = "REAL_NAME")
	private String realName;	//真实姓名

	@Column(name = "USER_TYPE")
	private String userType;	//用户类型（BOOK:小说注册用户）

	@Column(name = "REMARK")
	private String remark;	//备注

	@Column(name = "STATUS")
	private String status;	//用户状态（1.有效; 0.无效）

	@DateTimeFormat( pattern = "yyyy-MM-dd  HH:mm:ss")
	@Column(name = "CREATE_TIME")
	private Timestamp createTime;	//创建时间

	@Column(name = "HEAD_IMG")
	private String headImg;	//用户头像


	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}

	public String getUserName(){
		return userName;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}

	public String getOtherName(){
		return otherName;
	}
	public void setOtherName(String otherName){
		this.otherName = otherName;
	}

	public String getPhoneNo(){
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo){
		this.phoneNo = phoneNo;
	}

	public String getCardNo(){
		return cardNo;
	}
	public void setCardNo(String cardNo){
		this.cardNo = cardNo;
	}

	public String getRealName(){
		return realName;
	}
	public void setRealName(String realName){
		this.realName = realName;
	}

	public String getUserType(){
		return userType;
	}
	public void setUserType(String userType){
		this.userType = userType;
	}

	public String getRemark(){
		return remark;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}

	public Timestamp getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Timestamp createTime){
		this.createTime = createTime;
	}

	public String getHeadImg(){
		return headImg;
	}
	public void setHeadImg(String headImg){
		this.headImg = headImg;
	}


}
