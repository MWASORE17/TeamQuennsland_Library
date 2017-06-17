package com.example.zeth32.mylibrary01.main.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Zeth32 on 11/06/2017.
 */

public class PinjamBuku {
    private String email;
    private String barcode;
    public static int _id = 1;
    private String tanggalAwalPinjam;
    private String status;

    public static ArrayList<PinjamBuku> pinjamBukuList = new ArrayList<>();

    public PinjamBuku() {
    }

    public PinjamBuku(String email, String barcode, String tanggalAwalPinjam,String status) {
        this.email = email;
        this.barcode = barcode;
        this.status = status;
        this.tanggalAwalPinjam = tanggalAwalPinjam;

        _id++;
    }

    public int getId() {
        return _id;
    }

    public String getEmail() { return email; }
    public  void setEmail(String email) {this.email = email;}

    public String getBarcode(){return barcode;}
    public void setBarcode(String barcode){this.barcode = barcode;}

    public String getStatus(){return status;}
    public void setStatus(String status){this.status = status;}

    public String getTanggalAwalPinjam(){return tanggalAwalPinjam;}
    public void setTanggalAwalPinjam(String tanggalAwalPinjam) {this.tanggalAwalPinjam=tanggalAwalPinjam;}

    public long hitungDenda(){
        long bayaran=1000;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dtStart;
        Date now;
        long lamaPinjam=0;
        try {
            dtStart = format.parse(tanggalAwalPinjam);
            now = new Date();
//            Calendar calStart = Calendar.getInstance(); calStart.setTime(dtStart);
//            Calendar calNow = Calendar.getInstance(); calNow.setTime(now);
            lamaPinjam += daysBetween(dtStart, now);
        } catch (ParseException ex){
            // handle parsing exception if date string was different from the pattern applying into the SimpleDateFormat contructor
        }
        if(lamaPinjam < 8){
            return 0;
        } else{
            return bayaran*(lamaPinjam-7);
        }
    }

    private long daysBetween(Date startDate, Date endDate){
        Calendar sDate = getDatePart(startDate);
        Calendar eDate = getDatePart(endDate);

        long daysBetween = 0;
        while(sDate.before(eDate)){
//            sDate.add(Calendar.DAY_OF_MONTH,1);
            sDate.add(Calendar.SECOND,1);
            daysBetween++;
        }
        return daysBetween;
    }

    private Calendar getDatePart(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.SECOND, 0);
//        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

}
