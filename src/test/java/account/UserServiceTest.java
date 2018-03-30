package account;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;

import com.lzh.account.AccountApplication;
import com.lzh.account.model.dto.request.RegisterRequest;
import com.lzh.account.model.dto.response.RegisterResponse;
import com.lzh.account.model.entity.TAccountUser;
import com.lzh.account.service.impl.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=AccountApplication.class)
public class UserServiceTest {

	@Autowired
	UserService userService;
	
	@Test
	public void testFindAll() {
		List<TAccountUser> list = userService.findAll(0, 2);
		Assert.assertTrue("有数据", list.size()>0);  
		Assert.assertFalse("没有数据", list.size()<=0);  
	}

	@Test(expected=DuplicateKeyException.class)
	public void testRegister() {
		RegisterRequest  rr = new RegisterRequest();
		rr.setBalance(new Long(10000));
		rr.setLoginPwd("123");
		rr.setMobile("12345");
		
		RegisterResponse registerResponse = userService.register(rr);
		Assert.assertTrue("保存成功", registerResponse.getCode()==20000); 
		Assert.assertFalse("保存失败", registerResponse.getCode()!=20000);
	}

	@Test
	@Ignore
	public void testFindString() {
		fail("Not yet implemented");
	}

}
