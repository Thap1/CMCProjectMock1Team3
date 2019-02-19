package cmc.global.cms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cmc.global.cms.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("FROM User u WHERE u.fullname  like  %:keyword% and email like  %:keyword%")
	public List<User> findByE_P(@Param("keyword") String keyword);
	
	@Query("FROM User u WHERE u.fullname  like  %:fullname% and u.email like  %:email% and u.status = :status")
	public List<User> filterUserByF_E_S(@Param("fullname") String fullname,@Param("email") String email,@Param("status") int status);
	
	@Query("FROM User u WHERE u.fullname  like  %:fullname% and u.email like  %:email%")
	public List<User> filterUserByF_E(@Param("fullname") String fullname,@Param("email") String email);
	
	@Query("FROM User u WHERE u.fullname  like  %:fullname% and u.status = :status")
	public List<User> filterUserByF_S(@Param("fullname") String fullname,@Param("status") int status);
	
	@Query("FROM User u WHERE u.email like  %:email% and u.status = :status")
	public List<User> filterUserByE_S(@Param("email") String email,@Param("status") int status);
	
	Optional<User> findByEmail(String email);

	Boolean existsByEmail(String email);
	
}
