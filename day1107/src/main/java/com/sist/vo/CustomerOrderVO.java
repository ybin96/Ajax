package com.sist.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class CustomerOrderVO {
	private int custid;
	private String name;
	private String address;
	private String phone;
	private int orderid;
	private int saleprice;
	private Date orderdate;
}
