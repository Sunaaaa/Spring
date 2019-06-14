package spring;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.JDBCType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.JDBCUtil;

public class Test01_jdbcutil {

	Connection con;
	
	@Before
	public void setUp() {
		con = JDBCUtil.getConnection();
		System.out.println("con 할당");
	}
	
	@After
	public void tearDown(){
		JDBCUtil.close(con, null, null);
		System.out.println("con 해제");
	}
	
	@Test
	public void test() {
		assertNotNull(con);
	}

}
