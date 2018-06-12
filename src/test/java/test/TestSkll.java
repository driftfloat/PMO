package test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pom.dashboard.service.CapabilityLabelParamService;
import com.pom.dashboard.service.EmployeeSkillService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/conf/spring-mybatis.xml"
		})
@WebAppConfiguration
public class TestSkll {
	@Resource
	private EmployeeSkillService employeeSkillService;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
//	@Test
	public void detail() throws Exception  {
		String eHr = "E000830374";
		System.out.println(objectMapper.writeValueAsString(employeeSkillService.detail(eHr)));
	}
	
	@Test
	public void toEdit() throws Exception  {
		String eHr = "E000830374";
		eHr="E000240803";
		System.out.println(objectMapper.writeValueAsString(employeeSkillService.toEdit(eHr)));
	}
	
}
