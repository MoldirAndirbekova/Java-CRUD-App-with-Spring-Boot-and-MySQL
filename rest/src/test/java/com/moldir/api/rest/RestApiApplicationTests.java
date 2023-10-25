package com.moldir.api.rest;

import com.moldir.api.rest.user.User;
import com.moldir.api.rest.user.UserRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

@SpringBootTest
class RestApiApplicationTests {
	@Autowired private UserRepo userRepo;
	@Test
	void addNewUser_userAddedToTable() {
		User user = new User();
		user.setFirstname("Example");
		user.setLastname("Examplovich");
		user.setAge(18);
		user.setOccupation("programmer");

		User savedUser = userRepo.save(user);

		Assertions.assertThat(savedUser).isNotNull();
		Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	void listUsers_listOfUsersReturned() {
		Iterable<User> users = userRepo.findAll();

		Assertions.assertThat(users).hasSizeGreaterThan(0);
	}

	@Test
	void updateUser_userUpdated() {
		Long id = Long.valueOf(4);
		Optional<User> optionalUser =  userRepo.findById(id);
		User user = optionalUser.get();
		user.setLastname("Cool");
		userRepo.save(user);

		User updatedUser = userRepo.findById(id).get();

		Assertions.assertThat(updatedUser.getLastname()).isEqualTo("Cool");
	}

	@Test
	void deleteUser_userDeleted() {
		Long id = Long.valueOf(2);
		userRepo.deleteById(id);

		Optional<User> deletedUser = userRepo.findById(id);

		Assertions.assertThat(deletedUser).isNotPresent();

	}

}
