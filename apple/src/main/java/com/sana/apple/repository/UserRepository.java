package com.sana.apple.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sana.apple.enums.StatusEnum;
import com.sana.apple.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByMobileNumber(String mobileNumber);

	Optional<User> findByUserName(String username);

	Optional<User> findByEmail(String username);
	
	@Query(value = "SELECT nextval('user_id_sequence')", nativeQuery = true)
    Long getNextUserId();


	Optional<List<User>> findByStatus(StatusEnum status);

}
