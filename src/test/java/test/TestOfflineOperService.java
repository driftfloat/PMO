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

import com.github.pagehelper.PageInfo;
import com.pmo.dashboard.dao.OfflineOperMapper;
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
	
	@Resource
	private OfflineOperMapper OfflineOperMapper;

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
		int pageSize = 10, pageNumber = 1 ;
		OfflineOper condition = new OfflineOper();
		condition.setYear("2018"); 
		condition.setMonth("4");
		User user = new User();
		
//		user.setUserId("cb00bad3f16a4e8baf450e7b88af7c4b");  // 张培
//		user.setUserId("cff5fa689a2e40afa02ba2ceda914bbb");  // 梁嘉杰
//		user.setUserType("5");
		
//		user.setUserType("3");  
//		user.setUserId("c7b38226545c45e598f16a33031f85aa"); // c7b38226545c45e598f16a33031f85aa 李佳洲
//		user.setCsdeptId("9,12");
		
		user.setUserType("1"); 
		user.setUserId("1573"); // 风控数据事业部  潘亮
		user.setBu("风控数据事业部");
		
		List<OfflineOper> list = offlineOperService.query(condition, user, pageSize, pageNumber) ;
		PageInfo<OfflineOper> page = new PageInfo(list);
		
		System.out.println(list.size());
		System.out.println(page.getTotal());
		
	}
	
	
	public void testRWCount() {
		OfflineOper condition = new OfflineOper();
		condition.setYear("2018"); 
		condition.setMonth("4");
		String rmId = "cb00bad3f16a4e8baf450e7b88af7c4b" ; 
		condition.setRmId(rmId); ;
		int count = OfflineOperMapper.rmCount(condition);
		System.out.println(count);
	}
	
	
	public void queryFromEmployeeByRM() {
		int pageSize = 10, pageNumber = 1 ;
		OfflineOper condition = new OfflineOper();
		condition.setYear("2018"); 
		condition.setMonth("4");
		User user = new User();
		
		user.setUserId("cb00bad3f16a4e8baf450e7b88af7c4b");  // 张培
//		user.setUserId("cff5fa689a2e40afa02ba2ceda914bbb");  // 梁嘉杰
		user.setUserType("5");
		
//		OfflineOperMapper.queryFromEmployeeByRM(condition);
		
	}
}
