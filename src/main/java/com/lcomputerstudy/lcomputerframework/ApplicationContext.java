package com.lcomputerstudy.lcomputerframework;

import java.util.HashMap;
import java.util.Map;

import com.lcomputerstudy.demo.dao.UserDao;
import com.lcomputerstudy.demo.dao.UserDaoImpl;
import com.lcomputerstudy.demo.service.UserService;
import com.lcomputerstudy.lcomputerframework.database.DataSource;
import com.lcomputerstudy.lcomputerframework.database.DataSourcePoolJdbc;
import com.lcomputerstudy.lcomputerframework.jdbc.JdbcTemplate;

// bean 생성 및 관리 (컨테이너라고 부름)
public class ApplicationContext {
	public static Map<String, Object> beans = new HashMap<String, Object>();
	
	private static DataSource dataSource = null;
	private static UserService userService = null;
	private static UserDao userDao = null;
	private static JdbcTemplate jdbcTemplate = null;
	
	public static void init() {
		System.out.println("ApplicationContext (컨테이너) 가 Bean 을 생성 및 관리");
		
		// bean 생성
		dataSource = new DataSourcePoolJdbc();			// pool에 저장돼 있는 connection 을 가져옴
		//dataSource = new DataSourceLegacyJdbc();		// 매번 connection 을 생성
		userService = new UserService();
		userDao = new UserDaoImpl();
		jdbcTemplate = new JdbcTemplate();
		
		// bean 종속성 설정
		jdbcTemplate.setDataSource(dataSource);
		userDao.setJdbcTemplate(jdbcTemplate);
		userService.setUserDao(userDao);
		
		// bean 등록
		beans.put("userService", userService);
	}
	
	public static Object getBean(String beanName) {
		return beans.get(beanName);
	}
}
