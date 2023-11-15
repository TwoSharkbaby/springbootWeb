package org.web.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.web.springboot.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long > {
}
