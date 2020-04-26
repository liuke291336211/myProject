import com.controller.ProductController;
import com.dao.IUserDao;
import com.domain.Product;
import com.domain.UserInfo;
import com.service.IProductService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class test {

    @Test
    public void testDatabase(){
        ApplicationContext mv = new ClassPathXmlApplicationContext("applicationContext.xml");
        IUserDao us = (IUserDao)mv.getBean(IUserDao.class);
        List<UserInfo> all = us.findAll();
        for (UserInfo i : all) {
            System.out.println(i);
        }
    }
}
