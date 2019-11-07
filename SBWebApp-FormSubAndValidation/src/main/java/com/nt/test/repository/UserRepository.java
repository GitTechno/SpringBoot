package com.nt.test.repository;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nt.test.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Serializable>{
    
	@Query("select email from UserEntity")
	public List<String> getAllEmail();
	
	@Query("select email from UserEntity where userId=:uid")
	public String getUserById(int uid);
	
	@Query(value="update UserEntity set deletedFlag=0 where userId=:uid")
	@Modifying
	@Transactional
	public void updateDelete(Integer uid);
	
	@Query(value="select user from UserEntity user where user.deletedFlag=true")
	public Page<UserEntity> getUserAfterSoftDelete(PageRequest pg);

}
