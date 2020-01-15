package com.fdmgroup.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fdmgroup.getaways.configuration.Config;
import com.fdmgroup.getaways.model.AccountUser;
import com.fdmgroup.getaways.model.UserType;
import com.fdmgroup.getaways.repository.AccountUserJpaDao;
import com.fdmgroup.getaways.service.LoginService;
import com.fdmgroup.getaways.service.UserService;

public class AccountUserTests {

	private AnnotationConfigApplicationContext context;
	private UserService userService;
	private List<AccountUser> listOfUsers;
	private AccountUser accountUser;
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private LoginService loginService;
	private AccountUserJpaDao userJpaDao;

	@BeforeEach
	public void init() {
		context = new AnnotationConfigApplicationContext(Config.class);
		userService = context.getBean(UserService.class);
		accountUser = new AccountUser();
		listOfUsers = new ArrayList<>(); 
		entityManagerFactory = Persistence.createEntityManagerFactory("Getaways");
		entityManager = entityManagerFactory.createEntityManager();
		new AccountUserJpaDao(entityManager); 
	}

	@AfterEach
	public void cleanUp() {
		context.close();
	}

	@Test
	public void test_ReadAll_ShouldReturnOneMoreUserWhenOneUserIsAdded() {
		listOfUsers = userService.getAllAccountUser(); 
		userService.addAccountUser(accountUser);
		List<AccountUser> newUserList = userService.getAllAccountUser();
		int expected = listOfUsers.size() + 1;
		int actual = newUserList.size();
		assertEquals(expected,actual);	
	}
	
	@Test
	public void testGetAllUsersDoesntGiveAnEmptyListWhenThereAreInfactUsers() {
		userService.addAccountUser(accountUser);
		List<AccountUser> userList = userService.getAllAccountUser();
		assertFalse(userList.isEmpty());
	}

	@Test
	public void test_ReadOneById_ReadingOneByIdShouldReturnTheUserWIthTheIdREturnedByTheSevice() {
		userService.addAccountUser(accountUser);
		AccountUser readInUser = userService.getAnAccountUserByID( accountUser.getId() );   
		long expected = accountUser.getId();
		long actual = readInUser.getId();
		assertEquals(expected, actual);	
	}
	
	@Test
	public void test_ReadOneById_ReadingOneByIdShouldNotReturnOneCustomerWithTheIdTwenty() {
		AccountUser readInUser = userService.getAnAccountUserByID(1);  
		long expected = 20;
		long actual = readInUser.getId();
		assertTrue(expected != actual);	
	}
	
	@Test
	public void test_Delete_DeletingTheUserShouldSuccessfulyRemoveThatCustomerFromTheDatabase() {
		userService.addAccountUser(accountUser);
		List<AccountUser> initialUserList = userService.getAllAccountUser();
		int expected = initialUserList.size() -1;
		userService.removeUser(accountUser);
		List<AccountUser> newUserList = userService.getAllAccountUser();
		int actual = newUserList.size();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testThatRemovingUserDoesntCauseTheAmounntOfUsersToIncrease() {
		userService.addAccountUser(accountUser);
		List<AccountUser> initialUserList = userService.getAllAccountUser();
		int expected = initialUserList.size()-1;
		userService.removeUser(accountUser);
		List<AccountUser> newUserList = userService.getAllAccountUser();
		int actual = newUserList.size();
		assertEquals(expected,actual);
	}
	
	@Test
	public void test_Update_CallingUpdateShouldSuccesfullyUpdateTheDatabase() {
		AccountUser userToUpdate = userService.getAnAccountUserByID(1);
		userToUpdate.setFirstName("Brian");
		userService.updateAccountUser(userToUpdate);
		AccountUser updatedUser = userService.getAnAccountUserByID(1);
		String actual = updatedUser.getFirstName();
		String expected = "Brian";
		assertEquals(actual, expected);
	}
	
	@Test
	public void test_Update_CallingUpdateShouldNotChangeFirstNameToBobWhenISetItAsBlob() {
		accountUser.setFirstName("Blob");
		userService.updateAccountUser(accountUser);
		List<AccountUser>finalListOfUsers = userService.getAllAccountUser();
		String expected = "Bob";
		String actual = finalListOfUsers.get(finalListOfUsers.size()-1).getFirstName();
		assertTrue(expected != actual);
	}
	
	@Test
	public void testGetUsernameAndPasswordReturnsTheExpectedUser() {
		accountUser.setUserName("username");
		accountUser.setPassword("password");
		userService.addAccountUser(accountUser);
		AccountUser returnedUser = userJpaDao.getUserNameAndPass("username", "password");
		String expected = returnedUser.getUserName();
		assertEquals(expected, "username");
	}
	
	@Test
	public void testToSeeIfTheUserIsAndAdmin() {
		AccountUser accountUser = new AccountUser();
		accountUser.setUserType(UserType.ADMIN);
		accountUser.setVerified(true);
		userService.addAccountUser(accountUser);
		AccountUser admin = userService.getAnAccountUserByID(1);
		UserType adminUserType = admin.getUserType();
		assertEquals(UserType.ADMIN,adminUserType);
	}
}
