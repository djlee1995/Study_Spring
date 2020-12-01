/*create table tab_mybatis(
    id varchar2(15),
    name VARCHAR2(15),
    email varchar2(50),
    phone varchar2(15)
);*/

package com.spring.mybatis;

public class MemberVO {
	String id;
	String name;
	String email;
	String phone;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
