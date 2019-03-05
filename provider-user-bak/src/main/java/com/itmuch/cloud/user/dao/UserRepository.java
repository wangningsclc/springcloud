package com.itmuch.cloud.user.dao;


import com.itmuch.cloud.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auth wn
 * @Date 2019/2/22
 */
@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

}
