package com.sist.vo;

import java.util.Date;

import lombok.Data;

@Data
public class CustomerOrder {
	private int custid;
	private String name;
	private String address;
	private String phone;
	private int orderid;
	private int saleprice;
	private Date orderdate;
}
