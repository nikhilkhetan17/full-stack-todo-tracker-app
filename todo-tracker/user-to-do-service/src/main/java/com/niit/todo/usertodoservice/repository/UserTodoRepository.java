package com.niit.todo.usertodoservice.repository;

import com.niit.todo.usertodoservice.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTodoRepository extends MongoRepository<User, String> {
}
