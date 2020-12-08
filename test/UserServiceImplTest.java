import by.vasiliuk.project.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import by.vasiliuk.project.model.dao.UserDao;
import by.vasiliuk.project.model.dao.impl.UserDaoImpl;
import by.vasiliuk.project.model.entity.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
	private UserDao userDao;

	@Before
	public void setUp() {
		userDao = Mockito.mock(UserDao.class);
		UserDaoImpl.setInstance(userDao);
	}

	@Test
	public void logInUser_whenUserExistsInDao_returnTheUser() throws Exception {
		User user = new User(123, "username", 2);
		when(userDao.findUserByPassword("user")).thenReturn(user);

		Assert.assertEquals(
			user,
			UserServiceImpl.getInstance().logInUser("user")
		);
	}

	@Test
	public void updateUser_whenDataValid_updatesUserInDao() throws Exception {
		UserServiceImpl.getInstance().updateUser("oldNickname", "nickName", "email@email.com");

		verify(userDao).updateUser("oldNickname", "nickName", "email@email.com");
	}

	@Test
	public void updateUser_whenDataNotValid_neverUpdatesUserInDao() throws Exception {
		boolean result = UserServiceImpl.getInstance().updateUser("oldNickname", "nickName", "email");

		verify(userDao, never()).updateUser("oldNickname", "nickName", "email");
		assertFalse(result);
	}
}
