package com.example.Puzzlogging.domain.member.repository;

import com.example.Puzzlogging.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByName(String name);
}
