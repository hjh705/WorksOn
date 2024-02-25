package com.sh.workson.reservation.repository;

import com.sh.workson.reservation.dto.ReservationReturnDto;
import com.sh.workson.reservation.entity.Reservation;
import com.sh.workson.resource.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("from Reservation r join fetch r.employee join fetch r.resource where r.resource.id = :id and r.startAt >= :today")
    List<Reservation> findByResourceId(@Param("id") Long id, @Param("today") LocalDateTime today);

    @Query("from Reservation r join fetch r.employee join fetch r.resource where r.employee.id = :id and r.startAt >= :today and r.resource.type = :type")
    List<Reservation> findByEmpId(@Param("id") Long id, @Param("today") LocalDateTime today, @Param("type") Type type);

    @Query("from Reservation r join fetch r.employee join fetch r.resource where r.resource.id = :resourceId and r.startAt >= :startTime and r.endAt < :endTime order by r.startAt desc")
    Page<Reservation> findBetweenSearchDatePage(Pageable pageable, @Param("resourceId") Long resourceId, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    @Query("select count(*) from Reservation r where r.resource.id = :resourceId and r.startAt >= :startAt and r.endAt <= :endAt")
    int findBetweenSearchDate(@Param("resourceId") Long resourceId, @Param("startAt") LocalDateTime startAt, @Param("endAt") LocalDateTime endAt);

    @Query("from Reservation r join fetch r.employee join fetch r.resource where r.resource.id = :id and r.startAt > :today order by r.startAt desc")
    List<Reservation> findAllAfterToday(@Param("id") Long id, @Param("today") LocalDateTime today);
}
