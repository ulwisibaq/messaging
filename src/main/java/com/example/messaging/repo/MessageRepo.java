package com.example.messaging.repo;

import java.util.Optional;

import com.example.messaging.entity.Message;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Long> {
    Optional<Message> findById(Long id);
}