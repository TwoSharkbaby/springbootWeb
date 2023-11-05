package org.web.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.springboot.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long> {

}
