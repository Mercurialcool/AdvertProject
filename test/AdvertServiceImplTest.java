import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.vasiliuk.project.service.impl.AdvertServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import by.vasiliuk.project.model.dao.AdvertDao;
import by.vasiliuk.project.model.dao.DaoException;
import by.vasiliuk.project.model.dao.impl.AdvertDaoImpl;
import by.vasiliuk.project.model.entity.Advert;
import by.vasiliuk.project.service.ServiceException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class AdvertServiceImplTest {
	private AdvertDao advertDao;

	@Before
	public void setUp() throws Exception {
		advertDao = Mockito.mock(AdvertDao.class);
		AdvertDaoImpl.setInstance(advertDao);
	}

	@Test
	public void findAllAds_whenDaoReturnsOneAdvert_returnsSameList() throws Exception {
		List<Advert> adverts = new ArrayList<>();
		adverts.add(new Advert(1, "text", "title"));
		// whenever  dao's findAll is triggered we return the list above
		Mockito.when(advertDao.findAll()).thenReturn(adverts);

		Assert.assertEquals(
			adverts,
			AdvertServiceImpl.getInstance().findAllAds()
		);
	}

	@Test(expected = ServiceException.class)
	public void findAllAds_whenDaoThrowsException_throwsServiceException() throws Exception {
		Mockito.when(advertDao.findAll()).thenThrow(new DaoException());

		AdvertServiceImpl.getInstance().findAllAds();
	}

	@Test
	public void findUsersAdvertById_whenDaoReturnsOneAdvert_returnsSameList() throws Exception {
		List<Advert> adverts = new ArrayList<>();
		adverts.add(new Advert(1, "text", "title"));
		// whenever  dao's findAll is triggered we return the list above
		Mockito.when(advertDao.findUsersAdvertById(1)).thenReturn(adverts);

		assertEquals(
			adverts,
			AdvertServiceImpl.getInstance().findUsersAdvertById(1)
		);
	}

	@Test
	public void findAdvertBySection_whenDaoReturnsOneAdvert_returnsSameList() throws Exception {
		List<Advert> adverts = new ArrayList<>();
		adverts.add(new Advert(1, "text", "title"));
		// whenever  dao's findAll is triggered we return the list above
		Mockito.when(advertDao.findAdvertBySection(1)).thenReturn(adverts);

		assertEquals(
			adverts,
			AdvertServiceImpl.getInstance().findAdvertBySection(1)
		);
	}

	@Test
	public void findAdById_whenDaoReturnsOneAdvert_returnsIt() throws Exception {
		Mockito.when(advertDao.findById(1)).thenReturn(Optional.of(new Advert(1, "text", "title")));

		assertEquals(
			new Advert(1, "text", "title"),
			AdvertServiceImpl.getInstance().findAdById(1).get()
		);
	}

	@Test
	public void saveAdvert_savesToDao() throws Exception {
		AdvertServiceImpl.getInstance().saveAdvert("title", "text", 1, "section");

		verify(advertDao).save("title", "text", 1, "section");
	}
}
