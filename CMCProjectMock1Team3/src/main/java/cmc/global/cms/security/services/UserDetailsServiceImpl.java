package cmc.global.cms.security.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cmc.global.cms.entity.User;
import cmc.global.cms.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with -> email : " + email));

		return UserPrinciple.build(user);
	}

	public User checkLogin(String email, String password) {
		Optional<User> optional = userRepository.findByEmail(email);
		if (optional.isPresent()) {
			if (optional.get().getPassword().length() < 5) {
				if (optional.get().getPassword().equals(password)) {
					return optional.get();
				} else
					return null;
			} else {
				if (getPasswordEncoder().matches(password, optional.get().getPassword())) {
					return optional.get();
				} else
					return null;
			}

		} else
			return null;
	}

	private PasswordEncoder getPasswordEncoder() {
		return new PasswordEncoder() {

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return true;
			}

			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString();
			}
		};

	}
}
