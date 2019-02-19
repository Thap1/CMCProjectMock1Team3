package cmc.global.cms.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cmc.global.cms.entity.Role;
import cmc.global.cms.entity.User;
import cmc.global.cms.message.request.LoginForm;
import cmc.global.cms.message.request.SignUpFrom;
import cmc.global.cms.message.response.JwtResponse;
import cmc.global.cms.message.response.ResponseMessage;
import cmc.global.cms.model.RoleName;
import cmc.global.cms.repository.RoleRepository;
import cmc.global.cms.repository.UserRepository;
import cmc.global.cms.security.jwt.JwtProvider;
import cmc.global.cms.security.services.UserDetailsServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

//		User user = userDetailsService.checkLogin(loginRequest.getUsername(), loginRequest.getPassword());
//		String token = "";
//		if (user != null) {
//			token = jwtProvider.generator(new User());
//			// response.setHeader("token", token);
//			return new ResponseEntity<>(user, HttpStatus.OK);
//		} else
//			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpFrom signUpRequest) {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Faile -> Email is already token!"),
					HttpStatus.BAD_REQUEST);
		}
		// creating users account
		User user = new User(signUpRequest.getEmail(), signUpRequest.getFullName(), signUpRequest.getMobile(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getStatus());
		Set<String> strRoles = signUpRequest.getRole();

		Set<Role> roles = new HashSet<>();
		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole = roleRepository.findByRoleName(RoleName.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(adminRole);
				break;
			case "pm":
				Role mngRole = roleRepository.findByRoleName(RoleName.ROLE_MANAGER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(mngRole);
				break;

			default:
				Role mbRole = roleRepository.findByRoleName(RoleName.ROLE_MEMBER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(mbRole);
			}
		});

		user.setRoles(roles);
		userRepository.save(user);
		return new ResponseEntity<>(new ResponseMessage("User Registered successfully!"), HttpStatus.OK);

	}

}
