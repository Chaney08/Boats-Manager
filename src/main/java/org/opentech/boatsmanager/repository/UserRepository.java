package org.opentech.boatsmanager.repository;

import org.opentech.boatsmanager.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUserName(String username);
}
