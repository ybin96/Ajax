package com.sist.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class EmpVO {
	private int eno;
	private String ename;
	private int dno;
	private int salary;
	private int comm;
	private Date hiredate;
	private String phone;
	private String addr;
	private int mgr;
	private String job;
	private String jumin;
	private String email;
	
}
