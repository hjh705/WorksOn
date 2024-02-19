package com.sh.workson.project.repository;

import com.sh.workson.project.entity.Issue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    @Query("from Issue i left join fetch i.owner o left join fetch o.department left join fetch o.position left join fetch i.employee e left join fetch e.department left join fetch e.position left join fetch i.project left join fetch i.task p where i.owner.id = :id or i.employee.id = :id order by i.id desc")
    Page<Issue> findAllMyIssue(Long id, Pageable pageable);
}
