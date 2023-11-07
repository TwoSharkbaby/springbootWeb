package org.web.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.springboot.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
