package com.example.demo.jdbc;

import lombok.Data;

//데이터베이스 테이블에서 SQL문의 쿼리로 발생하는 데이터를 처리 테이블의 컬럼명으로 변수 사용

@Data
public class MyUserDTO {

	private String id;
	private String name;
}