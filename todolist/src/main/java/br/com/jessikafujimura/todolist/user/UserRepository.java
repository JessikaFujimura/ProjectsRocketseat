package br.com.jessikafujimura.todolist.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel,UUID> {

    Optional<UserModel> findByUserName(String username);
    
}
