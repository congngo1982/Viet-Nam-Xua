package com.vnxua.reservation.controller;

import com.vnxua.reservation.entity.MyTable;
import com.vnxua.reservation.entity.Reservation;
import com.vnxua.reservation.service.ITableService;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/table")
public class TableRestController {
    @Autowired
    private ITableService iTableService;

    @GetMapping("/gettall")
    public List<MyTable> GetAllTable() {
        return iTableService.GetAllTable();
    }

    @GetMapping("/getfilter")
    public List<MyTable> GetFilterTable(@RequestParam(name = "txtTable") String tableName, @RequestParam(name = "txtDate") String date) {
        LocalDateTime d = LocalDateTime.parse(date + "T01:00");
        System.out.println("Ok: " + d);
        System.out.println(Timestamp.valueOf(d));

        return iTableService.GetFilterTable(tableName, Timestamp.valueOf(d));
    }

//    @GetMapping("/gettavailable")
//    public List<MyTable> GetAvailableTable() {
//        return iTableService.GetAvailableTable();
//    }

    @GetMapping("/gettavailable")
    public List<MyTable> GetEmptyTable(@RequestParam(name = "date") String createdDate, @RequestParam(name = "quantity") int quantity) {
        System.out.println(createdDate);
        LocalDateTime d = LocalDateTime.parse(createdDate);
        System.out.println(Timestamp.valueOf(d.minusHours(3)) + " - " + Timestamp.valueOf(d.plusHours(3)));
        List<MyTable> table = iTableService.GetAvailableTable(Timestamp.valueOf(d.minusHours(3)), Timestamp.valueOf(d.plusHours(3)), quantity);
        return table;
    }

    @PostMapping("/create")
    public MyTable CreateTable(@RequestBody MyTable table) throws Exception {
        if(iTableService.GetTableById(table.getId()) != null){
            throw new Exception("Table name is existed !!!");
        }
        return iTableService.CreateTable(table);
    }

    @PostMapping("/update")
    public boolean UpdateTable(@RequestBody MyTable table) {
        return iTableService.UpdateTable(table);
    }

    @PostMapping("/delete")
    public boolean DeleteTable(@RequestBody String id) {
        return iTableService.DeleteTable(id);
    }
}
