package com.vnxua.reservation.controller;

import com.vnxua.reservation.entity.Account;
import com.vnxua.reservation.entity.MyTable;
import com.vnxua.reservation.entity.Reservation;
import com.vnxua.reservation.service.EmailService;
import com.vnxua.reservation.service.IAccountService;
import com.vnxua.reservation.service.IReservationService;
import com.vnxua.reservation.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/reservation")
public class ReservationRestController {
    @Autowired
    private IReservationService iReservationService;
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private ITableService iTableService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/getreservation")
    public List<Reservation> GetReservation(Authentication authentication) {
        List<Reservation> reservations = iReservationService.GetReservationByAccount(authentication.getName());
        Settable(reservations);
        return reservations;
    }

    @GetMapping("/getfilter")
    public List<Reservation> GetFilterReservation(@RequestParam(name = "txtTable") String tableName,
                                                  @RequestParam(name = "txtDate") String date,
                                                  @RequestParam(name = "txtstatus") int status) {
        LocalDateTime d = LocalDateTime.parse(date + "T01:00");
        List<Reservation> reservations = iReservationService.GetFilterReservation(tableName, Timestamp.valueOf(d), status);
//        for (Reservation reservation : reservations) {
//            Set<MyTable> myTables = reservation.getMyTables();
//            for (MyTable myTable : myTables) {
//                reservation.setNote(myTable.getId() + "`" + (reservation.getNote() == null ? "" : reservation.getNote()));
//            }
//        }
        Settable(reservations);
        return reservations;
    }

    @PostMapping("/create/{tableId}")
    public String CreateReservation(@RequestBody Reservation reservation, @PathVariable("tableId") String tableId, Authentication authentication) {
        String result = "Account not found !!!";
        if (authentication != null) {
            Reservation current = iReservationService.GetCurrentReservation(authentication.getName(), reservation.getCreatedDate());
            if (current != null && current.getStatus() == 1) {
                result = "You can Create only one Reservation per day";
            } else {
                reservation.setAccount(iAccountService.GetAccountByUsername(authentication.getName()));
                MyTable table = iTableService.GetTableById(tableId);
                if (table != null) {
                    Set<MyTable> tables = new HashSet<>();
                    tables.add(table);
                    reservation.setMyTables(tables);
                    Reservation reservation1 = iReservationService.CreateReservation(reservation);
                    result = "Reservation with code: " + reservation1.getId() + " was created !!!";
                    Account account = iAccountService.GetAccountByUsername(authentication.getName());
                    emailService.sendEmail(account.getEmail(),
                            "Your Reservation has been Created",
                            EmailTemplate(reservation1, table.getId()));
                }

            }
        }
        System.out.println(result);
        return result;
    }

    @GetMapping("/getsuccessful")
    public List<Reservation> GetSuccessfulReservation(Authentication authentication) throws Exception {
        if (authentication == null) {
            throw new Exception("Cannot authentication !!!");
        }
        List<Reservation> reservations = iReservationService.GetSuccessfulReservation();
        Settable(reservations);
        return reservations;
    }

    @GetMapping("/getreserbyid/{txtId}")
    public List<Reservation> GetReservationById(Authentication authentication, @PathVariable("txtId") long id) throws Exception {
        if (authentication == null) {
            throw new Exception("Cannot authentication !!!");
        }
        List<Reservation> reservations = iReservationService.GetReservationById(id);
        if (reservations != null) {
            Settable(reservations);
        }
        return reservations;
    }

    @GetMapping("/getcurrentreservation/{date}")
    public Reservation GetCurrentReservation(Authentication authentication, @PathVariable("date") String date) throws Exception {
        if (authentication == null) {
            throw new Exception("Cannot authentication !!!");
        }
        LocalDateTime localDateTime = LocalDateTime.parse(date);
        Reservation reservation = iReservationService.GetCurrentReservation(authentication.getName(), Timestamp.valueOf(localDateTime));
        if (reservation != null) {
            Set<MyTable> myTables = reservation.getMyTables();
            for (MyTable myTable : myTables) {
                reservation.setNote(myTable.getId() + "`" + (reservation.getNote() == null ? "" : reservation.getNote()));
            }
        }
        return reservation;
    }

    @PutMapping("/cancelreservation/{id}")
    public void CancelReservation(@PathVariable("id") int id, Authentication authentication) {
        if (authentication != null) {
            iReservationService.UpdateReservation(id, 0);
        }
    }

    @PutMapping("/complete/{id}")
    public void CompleteResewrvation(@PathVariable("id") long id, Authentication authentication) {
        if (authentication != null) {
            iReservationService.UpdateReservation(id, 2);
        }
    }

    public void Settable(List<Reservation> reservations) {
        for (Reservation reservation : reservations) {
            Set<MyTable> myTables = reservation.getMyTables();
            for (MyTable myTable : myTables) {
                reservation.setNote(myTable.getId() + "`" + (reservation.getNote() == null ? "" : reservation.getNote()));
            }
        }
    }

    public String EmailTemplate(Reservation reservation, String table) {
        String template = String.format("Xin chào %s,\n" +
                "\n" +
                "Chúng tôi xin thông báo bạn đã đặt chỗ thành công.\n" +
                "\n" +
                "Thông tin chi tiết đơn đặt chỗ:\n" +
                "Mã đặt chỗ: %s\n" +
                "Bàn: %s\n" +
                "Số lượng: %d\n" +
                "Ngày đặt: %s\n" +
                "Chú thích: %s\n" +
                "\n" +
                "Cảm ơn bạn đã tham khảo và sử dụng dịch vụ tại Viet Nam Xua Restaurant,\n" +
                "\n" +
                "VIETNAMXUA.", reservation.getAccount().getFullName(), reservation.getId(), table, reservation.getQuantity(), reservation.getCreatedDate().toString(), reservation.getNote());
        return template;
    }
}
