package de.pingpong.vsoteamroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.pingpong.vsoteamroom.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
