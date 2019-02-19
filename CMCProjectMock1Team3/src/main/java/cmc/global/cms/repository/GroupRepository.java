package cmc.global.cms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cmc.global.cms.entity.Group;

@Repository
public interface GroupRepository extends CrudRepository<Group, Integer> {
	
	List<Group> findByGroupNameContaining(String name);

	Group findByGroupId(int id);
	
//	List<GroupUser> findByGroupNameContaining(String name);
	

}
