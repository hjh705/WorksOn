package com.sh.workson.approval.repository;

import com.sh.workson.approval.dto.ApprovalHomeLeaveDto;
import com.sh.workson.approval.dto.IApprovalCooperation;
import com.sh.workson.approval.dto.IApprovalEquipment;
import com.sh.workson.approval.dto.IApprovalLeave;
import com.sh.workson.approval.entity.Approval;
import com.sh.workson.approval.entity.ApprovalLeave;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApprovalRepository extends JpaRepository<Approval, Long> {



    @Query(value = """
    select
        a. *
        , le.name
        , le.title
    from
        employee e join approval a
            on e.id = a.emp_id  
         join approval_leave le
            on a.approval_leave_id = le.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id
    order by
        a.id desc
""", nativeQuery = true)
    List<Approval> findAllLeave(Long id);

    @Query(value = """
    select
        a. *
        , eq.name
        , eq.title
    from
        employee e join approval a
            on e.id = a.emp_id
         join approval_equipment eq
            on a.approval_equipment_id = eq.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id
    order by
        a.id desc
""", nativeQuery = true)
    List<Approval> findAllEquipment(Long id);

    @Query(value = """
    select
        a. *
        , co.name
        , co.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_cooperation co
            on a.approval_cooperation_id = co.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id
    order by
        a.id desc
""", nativeQuery = true)
    List<Approval> findAllCooperation(Long id);


    @Query(value = """
    select
        a. *
        , le.name
        , le.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_leave le
            on a.approval_leave_id = le.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id
        and a.status not in ('임시저장')
    order by
        a.id desc
""", nativeQuery = true)
    List<Approval> findReLeave(Long id);

    @Query(value = """
    select
        a. *
        , eq.name
        , eq.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_equipment eq
            on a.approval_equipment_id = eq.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id
        and a.status not in ('임시저장')
    order by
        a.id desc
""", nativeQuery = true)
    List<Approval> findReEquipment(Long id);

    @Query(value = """
    select
        a. *
        , co.name
        , co.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_cooperation co
            on a.approval_cooperation_id = co.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id
        and a.status not in ('임시저장')
    order by
        a.id desc
""", nativeQuery = true)
    List<Approval> findReCooperation(Long id);

    @Query(value = """
    select
        a. *
        , le.name
        , le.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_leave le
            on a.approval_leave_id = le.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id and
        a.status in ('승인', '반려')
    order by
        a.id desc
""", nativeQuery = true)
    Page<Approval> findCheckLeave(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , eq.name
        , eq.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_equipment eq
            on a.approval_equipment_id = eq.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id and
        a.status in ('승인', '반려')
    order by
        a.id desc
""", nativeQuery = true)
    Page<Approval> findCheckEquipment(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , co.name
        , co.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_cooperation co
            on a.approval_cooperation_id = co.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id and
        a.status in ('승인', '반려')
    order by
        a.id desc
""", nativeQuery = true)
    Page<Approval> findCheckCooperation(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , le.name
        , le.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_leave le
            on a.approval_leave_id = le.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id and
        a.status in ('진행중')
    order by
        a.id desc
""", nativeQuery = true)
    Page<Approval> findProceedingLeave(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , eq.name
        , eq.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_equipment eq
            on a.approval_equipment_id = eq.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id and
        a.status in ('진행중')
    order by
        a.id desc
""", nativeQuery = true)
    Page<Approval> findProceedingEquipment(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , co.name
        , co.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_cooperation co
            on a.approval_cooperation_id = co.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id and
        a.status in ('진행중')
    order by
        a.id desc
""", nativeQuery = true)
    Page<Approval> findProceedingCooperation(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , le.name
        , le.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_leave le
            on a.approval_leave_id = le.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id
        and a.status in ('대기', '진행중')
    order by
        a.id desc
""", nativeQuery = true)
    Page<Approval> findReceiveLeave(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , eq.name
        , eq.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_equipment eq
            on a.approval_equipment_id = eq.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id
        and a.status in ('대기', '진행중')
    order by
        a.id desc
""", nativeQuery = true)
    Page<Approval> findReceiveEquipment(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , co.name
        , co.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_cooperation co
            on a.approval_cooperation_id = co.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id
        and a.status in ('대기', '진행중')
    order by
        a.id desc
""", nativeQuery = true)
    Page<Approval> findReceiveCooperation(Long id, Pageable pageable);

    @Query(value = """
    select
         a. *
         , le.name
         , le.title
         , d.name
     from
         employee e join approval a
            on e.id = a.emp_id
        join department d
            on e.dept_id = d.id
        join approval_leave le
            on a.approval_leave_id = le.id
        left join approval_attachment at
            on a.id = at.approval_id
     where
         e.dept_id = :deptId
         and a.emp_id not in :id
         and a.status not in ('임시저장')
    order by
        a.id desc
""", nativeQuery = true)
    List<Approval> findReceptionLeave(Long deptId, Long id);

    @Query(value = """
    select
         a. *
         , eq.name
         , eq.title
         , d.name
     from
         employee e join approval a
            on e.id = a.emp_id
        join department d
            on e.dept_id = d.id
        join approval_equipment eq
            on a.approval_equipment_id = eq.id
        left join approval_attachment at
            on a.id = at.approval_id
     where
         e.dept_id = :deptId
         and a.emp_id not in :id
         and a.status not in ('임시저장')
    order by
        a.id desc
""", nativeQuery = true)
    List<Approval> findReceptionEquipment(Long deptId, Long id);

    @Query(value = """
    select
         a. *
         , co.name
         , co.title
         , d.name
     from
         employee e join approval a
            on e.id = a.emp_id
        join department d
            on e.dept_id = d.id
        join approval_cooperation co
            on a.approval_cooperation_id = co.id
        left join approval_attachment at
            on a.id = at.approval_id
     where
         e.dept_id = :deptId
         and a.emp_id not in :id
         and a.status not in ('임시저장')
    order by
        a.id desc
""", nativeQuery = true)
    List<Approval> findReceptionCooperation(Long deptId, Long id);

    @Query(value = """
    select
        a. *
        , le.name
        , le.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_leave le
            on a.approval_leave_id = le.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id and
        a.status = '대기'
    order by
        a.id desc
""", nativeQuery = true)
    List<Approval> findWaitLeave(Long id);

    @Query(value = """
    select
        a. *
        , eq.name
        , eq.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_equipment eq
            on a.approval_equipment_id = eq.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id and
        a.status = '대기'
    order by
        a.id desc
""", nativeQuery = true)
    List<Approval> findWaitEquipment(Long id);

    @Query(value = """
    select
        a. *
        , co.name
        , co.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_cooperation co
            on a.approval_cooperation_id = co.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id and
        a.status = '대기'
    order by
        a.id desc
""", nativeQuery = true)
    List<Approval> findWaitCooperation(Long id);

    @Query(value = """
    select
        a. *
        , le.name
        , le.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_leave le
            on a.approval_leave_id = le.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id and
        a.status = '예정'
    order by
        a.id desc
""", nativeQuery = true)
    List<Approval> findExceptedLeave(Long id);

    @Query(value = """
    select
        a. *
        , eq.name
        , eq.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_equipment eq
            on a.approval_equipment_id = eq.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id and
        a.status = '예정'
    order by
        a.id desc
""", nativeQuery = true)
    List<Approval> findExceptedEquipment(Long id);

    @Query(value = """
    select
        a. *
        , co.name
        , co.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_cooperation co
            on a.approval_cooperation_id = co.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        a.emp_receives_id = :id and
        a.status = '예정'
    order by
        a.id desc
""", nativeQuery = true)
    List<Approval> findExceptedCooperation(Long id);


    @Query(value = """
    select
        a.id
        , a.approval_end_date as approvalEndDate
        , e.name as empId
        , d.name as deptId
        , le.id as leId
        , le.start_date as startDate
        , le.end_date as endDate
        , le.leave_content as leaveContent
        , le.leave_type as leaveType
        , le.annul as annul
        , le.leave_count as leaveCount
    from
        employee e join department d
            on e.dept_id = d.id
        join approval a
            on e.id = a.emp_id
        join approval_leave le
            on a.approval_leave_id = le.id
        left join approval_attachment at
            on a.id = at.approval_id
        left join approval_line li
            on a.id = li.approval_id
    where
        a.id = :id
    order by
        a.id desc
""", nativeQuery = true)
    IApprovalLeave findLeaveDetailById(@Param("id") Long id);

    @Query(value = """
    select
        a.id
        , a.approval_end_date as approvalEndDate
        , e.name as empId
        , d.name as deptId
        , eq.id as eqId
        , eq.title as title
        , eq.content as content
        , eq.product_name as productName
        , eq.price as price
        , eq.count as count
        , eq.price * eq.count as totalPrice
        , eq.usage as usage
    from
        employee e join department d
            on e.dept_id = d.id
        join approval a
            on e.id = a.emp_id
        join approval_equipment eq
            on a.approval_equipment_id = eq.id
        left join approval_attachment at
            on a.id = at.approval_id
        left join approval_line li
            on a.id = li.approval_id
    where
        a.id = :id
    order by
        a.id desc
""", nativeQuery = true)
    IApprovalEquipment findEquipmentDetailById(Long id);

    @Query(value = """
    select
        a.id
        , a.approval_end_date as approvalEndDate
        , e.name as empId
        , d.name as deptId
        , co.id as coId
        , co.title as title
        , co.content as content
        , co.cooperation_dept as cooperationDept
        , co.start_date as startDate
        , co.end_date as endDate
    from
        employee e join department d
            on e.dept_id = d.id
        join approval a
            on e.id = a.emp_id
        join approval_cooperation co
            on a.approval_cooperation_id = co.id
        left join approval_attachment at
            on a.id = at.approval_id
        left join approval_line li
            on a.id = li.approval_id
    where
        a.id = :id
    order by
        a.id desc
""", nativeQuery = true)
    IApprovalCooperation findCooperationDetailById(Long id);

    @Query(value = """
    select
        a. *
        , le.name
        , le.title
    from
        employee e join approval a
            on e.id = a.emp_id
         join approval_leave le
            on a.approval_leave_id = le.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id and
        a.status = '임시저장'
    order by
        a.id desc
""", nativeQuery = true)
    List<Approval> findTempoeraryLeave(Long id);

    @Query(value = """
    select
        a. *
        , eq.name
        , eq.title
    from
        employee e join approval a
            on e.id = a.emp_id
         join approval_equipment eq
            on a.approval_equipment_id = eq.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id and
        a.status = '임시저장'
    order by
        a.id desc
""", nativeQuery = true)
    List<Approval> findTemporaryEquipment(Long id);

    @Query(value = """
    select
        a. *
        , co.name
        , co.title
    from
        employee e join approval a
            on e.id = a.emp_id
         join approval_cooperation co
            on a.approval_cooperation_id = co.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id and
        a.status = '임시저장'
    order by
        a.id desc
""", nativeQuery = true)
    List<Approval> findTemporaryCooperation(Long id);

    @Query(value = """
    select
        a. *
        , le.name
        , le.title
    from
        employee e join approval a
            on e.id = a.emp_id  
         join approval_leave le
            on a.approval_leave_id = le.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id
    order by
        a.id desc
""", nativeQuery = true)
    Page<Approval> findLeave(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , eq.name
        , eq.title
    from
        employee e join approval a
            on e.id = a.emp_id
         join approval_equipment eq
            on a.approval_equipment_id = eq.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id
    order by
        a.id desc
""", nativeQuery = true)
    Page<Approval> findEquipment(Long id, Pageable pageable);

    @Query(value = """
    select
        a. *
        , co.name
        , co.title
    from
        employee e join approval a
            on e.id = a.emp_id   
         join approval_cooperation co
            on a.approval_cooperation_id = co.id
        left join approval_attachment at
            on a.id = at.approval_id
    where
        e.id = :id
    order by
        a.id desc
""", nativeQuery = true)
    Page<Approval> findCooperation(Long id, Pageable pageable);
}

