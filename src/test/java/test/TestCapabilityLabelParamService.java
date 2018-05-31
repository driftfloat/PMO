package test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.pom.dashboard.service.CapabilityLabelParamService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/conf/spring-mybatis.xml"
		})
@WebAppConfiguration
public class TestCapabilityLabelParamService {
	@Resource
	CapabilityLabelParamService capabilityLabelParamService;
	
	@Test
	public void maxSubCate() {
		String majorcateId = "001";
		String liushuihao = null;
		System.out.println(capabilityLabelParamService.maxSubCate(majorcateId));
	}
}
;