package com.devausa.foro_hub_project.repository;

import com.devausa.foro_hub_project.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
