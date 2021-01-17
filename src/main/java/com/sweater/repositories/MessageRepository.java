package com.sweater.repositories;

import com.sweater.models.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message,Long> {
    List<Message>  findByTag(String tag);
}
