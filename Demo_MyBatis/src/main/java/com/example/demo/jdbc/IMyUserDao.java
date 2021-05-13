package com.example.demo.jdbc;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

// 인터페이스 구현을 XML로 하겠다는 의미 --> XML로 SQL을 만들겠다는 의미
@Mapper
public interface IMyUserDao {
	
	List<MyUserDTO> list(); //테이블의 내용을 select하기 위한 메서드

}
