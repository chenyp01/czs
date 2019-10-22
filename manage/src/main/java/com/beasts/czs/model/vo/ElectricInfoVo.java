package com.beasts.czs.model.vo;

public class ElectricInfoVo{
	private Integer id;	//ID

	private String userNo;	//户号

	private String userName;	//户名

	private String address;	//地址

	private String price;	//电价

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
