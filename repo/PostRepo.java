package com.crud.crud.operation.repo;

import com.crud.crud.operation.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Integer> {
}
