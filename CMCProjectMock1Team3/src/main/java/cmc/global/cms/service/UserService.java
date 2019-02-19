package cmc.global.cms.service;

import java.util.List;
import java.util.Optional;

import cmc.global.cms.entity.User;

public interface UserService {
	
	List<User> findAll();
	
	void save(User user);
	
	Optional<User> findById(Integer id);

	void deleteUser(Integer id);
	
	List<User> findByUser_F_E_S(String keyword);

	List<User> filterUserByF_E_S(String fullname, String email, int status);
	
	List<User> filterUserByF_E(String fullname, String email);
	
	List<User> filterUserByF_S(String fullname, int status);
	
	List<User> filterUserByE_S(String email, int status);
}
