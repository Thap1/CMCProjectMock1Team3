package cmc.global.cms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cmc.global.cms.entity.User;
import cmc.global.cms.repository.UserRepository;
import cmc.global.cms.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly=true)
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return (List<User>) userRepository.findAll();
	}

	@Override
	@Transactional
	public void save(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}	

	@Override
	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<User> findById(Integer id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Override
	public List<User> findByUser_F_E_S(String keyword) {
		// TODO Auto-generated method stub
		return userRepository.findByE_P(keyword);
	}

	@Override
	public List<User> filterUserByF_E_S(String fullname, String email, int status) {
		return userRepository.filterUserByF_E_S(fullname, email, status);
	}

	@Override
	public List<User> filterUserByF_E(String fullname, String email) {
		// TODO Auto-generated method stub
		return userRepository.filterUserByF_E(fullname, email);
	}

	@Override
	public List<User> filterUserByF_S(String fullname, int status) {
		// TODO Auto-generated method stub
		return userRepository.filterUserByF_S(fullname, status);
	}

	@Override
	public List<User> filterUserByE_S(String email, int status) {
		// TODO Auto-generated method stub
		return userRepository.filterUserByE_S(email, status);
	}
	
	

}
