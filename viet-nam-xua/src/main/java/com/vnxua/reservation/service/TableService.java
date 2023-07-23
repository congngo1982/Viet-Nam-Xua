package com.vnxua.reservation.service;

import com.vnxua.reservation.entity.MyTable;
import com.vnxua.reservation.repository.MyTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TableService implements ITableService {
    @Autowired
    private MyTableRepository myTableRepository;

    @Override
    public List<MyTable> GetAllTable() {
        return myTableRepository.findAll();
    }

    @Override
    public MyTable GetTableById(String id) {
        return myTableRepository.findMyTableById(id);
    }

    @Override
    public MyTable CreateTable(MyTable table) {
        return myTableRepository.save(table);
    }

    @Override
    public boolean UpdateTable(MyTable table) {
        boolean result = false;
        if (myTableRepository.findMyTableById(table.getId()) != null) {
            myTableRepository.save(table);
            result = true;
        }
        return result;
    }

    @Override
    public List<MyTable> GetFilterTable(String tableId, Date date) {
        return myTableRepository.GetFilterTable(tableId, date);
//        return null;
    }

    @Override
    public boolean DeleteTable(String tableId) {
        boolean result = false;
        if (myTableRepository.findMyTableById(tableId) != null) {
            myTableRepository.deleteById(tableId);
            result = true;
        }
        return result;
    }

    @Override
    public List<MyTable> GetAvailableTable() {
        return myTableRepository.findMyTableByStatus(1);
    }

    @Override
    public List<MyTable> GetTableInTime(String id, Date from, Date end) {
        return myTableRepository.GetTableInTime(from, end);
    }

    @Override
    public List<MyTable> GetAvailableTable(Date from, Date end, int quantity) {
        return myTableRepository.GetAvailableTable(from, end, quantity, quantity + 4);
    }
}
