package com.carolina.giggle.repository;

import com.carolina.giggle.entity.Post;
import com.carolina.giggle.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PostRepository extends JpaRepository<Post, Long> {
    Iterable<Post> findAllByUser(String username);
}
