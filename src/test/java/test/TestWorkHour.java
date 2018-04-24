package test;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.pmo.dashboard.dao.ChinaWorkHourMapper;
import com.pmo.dashboard.dao.HKWorkHourMapper;
import com.pmo.dashboard.dao.MLWorkHourMapper;
import com.pmo.dashboard.dao.WorkHourMapper;
import com.pmo.dashboard.entity.ChinaWorkHour;
import com.pmo.dashboard.entity.HKWorkHour;
import com.pmo.dashboard.entity.MLWorkHour;
import com.pmo.dashboard.entity.WorkHour;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/conf/spring-mybatis.xml"
//		,"file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml" 
//		,"classpath:/conf/spring-mvc.xml"
		})
@WebAppConfiguration
public class TestWorkHour {
//	@Resource
//	private ChinaWorkHourService chinaWorkHourService;
	
	@Resource
	ChinaWorkHourMapper chinaWorkHourMapper;
	
	@Resource
	HKWorkHourMapper HKWorkHourMapper;
	
	@Resource
	MLWorkHourMapper MLWorkHourMapper;
	
	@Test
	public void queryWorkHour() {
		WorkHour workHour = new ChinaWorkHour();
		WorkHourMapper mapper ;
		workHour.setYear("2018");
		workHour.setMonth("4月");
		mapper = chinaWorkHourMapper;
		BigDecimal b = mapper.queryWorkHour(workHour) ;
		System.out.println(b.toString());
		
		workHour = new HKWorkHour();
		workHour.setYear("2018");
		workHour.setMonth("4月");
		mapper = HKWorkHourMapper;
		b = mapper.queryWorkHour(workHour);
		System.out.println(b.toString());
		
		workHour = new MLWorkHour();
		workHour.setYear("2018");
		workHour.setMonth("4月");
		mapper = MLWorkHourMapper;
		b = mapper.queryWorkHour(workHour);
		System.out.println(b.toString());
	}

}
