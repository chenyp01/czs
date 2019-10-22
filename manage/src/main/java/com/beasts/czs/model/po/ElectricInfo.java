package com.beasts.czs.model.po;

import javax.persistence.*;

@Table(name = "CZS_ELECTRIC_INFO")
public class ElectricInfo{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;	//ID

	@Column(name = "USER_NO")
	private String userNo;	//户号

	@Column(name = "USER_NAME")
	private String userName;	//户名

	@Column(name = "ADDRESS")
	private String address;	//地址

	@Column(name = "PRICE")
	private String price;	//电价

	@Column(name = "STATUS")
	private String status;	//是否有效（1.有效; 0.无效）


	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}

	public String getUserNo(){
		return userNo;
	}
	public void setUserNo(String userNo){
		this.userNo = userNo;
	}

	public String getUserName(){
		return userName;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getAddress(){
		return address;
	}
	public void setAddress(String address){
		this.address = address;
	}

	public String getPrice(){
		return price;
	}
	public void setPrice(String price){
		this.price = price;
	}

	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}


}
