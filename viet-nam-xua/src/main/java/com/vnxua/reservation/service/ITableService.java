package com.vnxua.reservation.service;

import com.vnxua.reservation.entity.MyTable;

import java.util.Date;
import java.util.List;

public interface ITableService {
    public List<MyTable> GetAllTable();
    public MyTable GetTableById(String id);
    public MyTable CreateTable(MyTable table);
    public boolean UpdateTable(MyTable table);
    public boolean DeleteTable(String tableId);
    public List<MyTable> GetAvailableTable();

    public List<MyTable> GetFilterTable(String tableId, Date date);

    public List<MyTable> GetTableInTime(String id, Date from, Date end);
    public List<MyTable> GetAvailableTable(Date from, Date end, int quantity);
}
