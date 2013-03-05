package test.wendellup.junit;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wendellup.app.dao.contract.visit.IVisitDao;
import com.wendellup.app.service.contract.visit.IVisitService;
import com.wendellup.app.valueobject.entity.Visit;

public class TestSpring {
	private static Log log = LogFactory.getLog(TestSpring.class);

	public static void main(String[] args) throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"spring/applicationContext.xml");

		// 初始化默认的bean的名字是类名(首字母小写)
//		IVisitDao iVisitDao = (IVisitDao) ac.getBean("visitDaoImpl");
//		Visit visit = new Visit();
//		visit.setTime(new Date());
//		visit.setIp("127.0.0.1");
//		log.debug("visitTimes:" + iVisitDao.findVisitTimes(visit));

		// 初始化默认的bean的名字是类名(首字母小写)
		IVisitService iVisitService = (IVisitService) ac.getBean("visitServiceImpl");
		Visit visit = new Visit();
		visit.setTime(new Date());
		visit.setIp("127.0.0.1");
		log.debug("visitTimes:" + iVisitService.findVisitTimes(visit));
	}
}
