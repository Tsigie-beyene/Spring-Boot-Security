package com.example.springsecurity.repository;

import com.example.springsecurity.model.Notice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long> {

    @Query("""
    SELECT n
    FROM Notice n
    WHERE CURRENT_DATE BETWEEN n.noticBegDt AND n.noticEndDt
""") List<Notice> findAllActiveNotices();

}