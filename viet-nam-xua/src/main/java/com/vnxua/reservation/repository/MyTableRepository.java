package com.vnxua.reservation.repository;

import com.vnxua.reservation.entity.MyTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MyTableRepository extends JpaRepository<MyTable, String> {
    public MyTable findMyTableById(String id);

    public List<MyTable> findMyTableByStatus(int status);

    @Query(value = "SELECT myTable FROM MyTable myTable WHERE myTable NOT IN (SELECT myTable FROM MyTable myTable " +
            "JOIN myTable.reservation reservation " +
            "WHERE reservation.status != 0 AND reservation.createdDate BETWEEN :fromDate AND :endDate) " +
            "AND myTable.quantity BETWEEN :min AND :max")
    public List<MyTable> GetAvailableTable(@Param("fromDate") Date fromDate,
                                           @Param("endDate") Date endDate, @Param("min") int min, @Param("max") int max);

    @Query(value = "SELECT myTable FROM MyTable myTable WHERE myTable IN (SELECT myTable FROM MyTable myTable " +
            "JOIN myTable.reservation reservation " +
            "WHERE cast(reservation.createdDate as date ) = cast(:date as date ) ) AND myTable.id = :tableId")
    public List<MyTable> GetFilterTable(@Param("tableId") String tableId,@Param("date") Date date);

    @Query(value = "SELECT myTable FROM MyTable myTable " +
            "JOIN myTable.reservation reservation " +
            "WHERE reservation.createdDate BETWEEN :fromDate AND :endDate")
    public List<MyTable> GetTableInTime(@Param("fromDate") Date fromDate,
                                        @Param("endDate") Date endDate);
}
