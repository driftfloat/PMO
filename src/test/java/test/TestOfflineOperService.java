package test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.pmo.dashboard.entity.OfflineOper;
import com.pmo.dashboard.entity.User;
import com.pom.dashboard.service.OfflineOperService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/conf/spring-mybatis.xml"
//		,"file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml" 
//		,"classpath:/conf/spring-mvc.xml"
		})
@WebAppConfiguration
public class TestOfflineOperService {
	@Resource
	private OfflineOperService offlineOperService;

//	@Before
//	public void initMockMvc() {
//		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//	}
	
//	@Test
//	public void testPage() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/offlineOper/query").param("pageNumber", "1")).andReturn();
//	}
	@Test
	public void testQuery() {
		OfflineOper condition = new OfflineOper();
		condition.setYear("2018"); 
		condition.setMonth("4");
		User user = new User();
//		user.setUserId("cb00bad3f16a4e8baf450e7b88af7c4b");
//		user.setUserType("5");
		
		user.setUserType("3");  
		user.setUserId("c7b38226545c45e598f16a33031f85aa");
		user.setCsdeptId("9,12");
		
		List<OfflineOper> list = offlineOperService.query(condition, user) ;
		System.out.println(list.size());
	}
}
