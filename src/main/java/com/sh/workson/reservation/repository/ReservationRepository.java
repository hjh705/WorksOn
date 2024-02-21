package com.sh.workson.reservation.repository;

import com.sh.workson.reservation.dto.ReservationReturnDto;
import com.sh.workson.reservation.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("from Reservation where resourceId = :id")
    List<Reservation> findByResourceId(Long id);

    @Query("from Reservation where employee.id = :id")
    List<Reservation> findByEmpId(Long id);

    @Query("from Reservation r where r.resourceId = :resourceId and r.startAt >= :startTime and r.endAt < :endTime order by r.startAt desc")
    Page<Reservation> findBetweenSearchDatePage(Pageable pageable, Long resourceId, LocalDateTime startTime, LocalDateTime endTime);

    @Query("select count(*) from Reservation r where r.resourceId = :resourceId and r.startAt >= :startAt and r.endAt < :endAt")
    int findBetweenSearchDate(Long resourceId, LocalDateTime startAt, LocalDateTime endAt);

    @Query("from Reservation r where r.resourceId = :id and r.startAt > :today order by r.startAt desc")
    List<Reservation> findAllAfterToday(Long id, LocalDateTime today);
}
