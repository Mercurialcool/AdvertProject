import by.vasiliuk.project.controller.validator.Validator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {

	@Test
	public void isValidName_whenNameValid_returnsTrue() {
		assertTrue(Validator.isValidName("ValidName"));
	}

	@Test
	public void isValidName_whenNameHas7Symbols_returnsFalse() {
		assertFalse(Validator.isValidName("ValidNa"));
	}

	@Test
	public void isValidName_whenNameHasSpecialChars_returnsFalse() {
		assertFalse(Validator.isValidName("@@@@@@@@"));
	}

	@Test
	public void isValidEmail_whenEmailValid_returnsTrue() {
		assertTrue(Validator.isValidEmail("email@email.com"));
	}

	@Test
	public void isValidEmail_whenEmailHasNoAt_returnsfalse() {
		assertFalse(Validator.isValidEmail("emailemail.com"));
	}
}
