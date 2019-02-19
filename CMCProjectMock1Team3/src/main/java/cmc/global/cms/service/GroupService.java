package cmc.global.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;

import cmc.global.cms.entity.Group;

public interface GroupService {
	
	List<Group> getAllGroupUser();
	
	Optional<Group> findById(int id);
	
	Group saveGroupUser(Group object);
	
	void deleteGroupUserById(int id);
	
	void deleteAllGroupUser();
	
	List<Group> findByGroupNameContaining(String name);
		
	Group findByGroupId(int groupUserId);
	
	Group createGroupUser(@RequestBody Group groupUser);
}
