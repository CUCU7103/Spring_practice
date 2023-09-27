package com.example.firstproject.repository;

import com.example.firstproject.entity.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
// 리퍼지토리는 사용자가 직접 구현할 수도 있고 jpa에서 제공하는 리파지터리 인터페이스를 활용해 만들 수도 잇다.
// CrudRepository를 상속받아서 별다른 작업 없이 CRUD 작업이 가능하도록 해준다.
public interface MemberRepository extends CrudRepository<Member, Long> {
    @Override // Iterable -> ArrayList로 다운 캐스팅 진행
    ArrayList<Member> findAll();
    // <관리 대상 엔티티의 클래스타입 , 관리대상 엔티티의 대표값 타입>


}
