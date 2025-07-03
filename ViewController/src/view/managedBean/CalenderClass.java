package view.managedBean;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class CalenderClass {
   
    String stringDate;
    String day;
    public CalenderClass() {
            getTime();
        }

    public void setStringDate(String stringDate) {
        this.stringDate = stringDate;
    }

    public String getStringDate() {
        return stringDate;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }

//    public String convertDate(){
//        Date date = new Date();
//        SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
//       // stringDate = DateFor.format(date);
////        System.out.println("Date Format with MM/dd/yyyy : "+stringDate);
////        DateFor = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
////        stringDate = DateFor.format(date);
////        System.out.println("Date Format with dd-M-yyyy hh:mm:ss : "+stringDate);
//       // DateFor = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
//       // stringDate = DateFor.format(date);
//       // System.out.println("Date Format with dd MMMM yyyy : "+stringDate);
//        
//            DateFor = new SimpleDateFormat("EEEE");
//            day =  DateFor.format(date);
//           day = day.toUpperCase();
//            System.out.println("Day =="+day);
////        DateFor = new SimpleDateFormat("dd MMMM yyyy zzzz");
////        stringDate = DateFor.format(date);
////        System.out.println("Date Format with dd MMMM yyyy zzzz : "+stringDate);
////        DateFor = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z");
////        stringDate = DateFor.format(date);
////        System.out.println("Date Format with E, dd MMM yyyy HH:mm:ss z :"+stringDate);
//        return stringDate;
//        }
    
    public void getTime(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+4"), Locale.getDefault());
        Date currentLocalTime = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        stringDate = dateFormat.format(currentLocalTime);

        dateFormat = new SimpleDateFormat("EEEE");
        day = dateFormat.format(date);
        day = day.toUpperCase();
       // day= day.concat(stringDate);
       // System.out.println("Day ==" + day);
        day = day+" "+stringDate;
        System.out.println("Day ==" + day);
       // System.out.println("stringDate==" + stringDate);
    }
//    public static void main(String args[]){
//        CalenderClass calenderClass  = new CalenderClass();
//    }
}
