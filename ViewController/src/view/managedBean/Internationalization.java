package view.managedBean;

import java.util.Locale;

import javax.el.ELContext;
import javax.el.ELContextListener;

import javax.el.ExpressionFactory;

import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import java.text.Format;  
import java.text.SimpleDateFormat;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.Date;  
import java.util.Calendar;
import java.util.TimeZone;
 import javax.faces.context.FacesContext;

import oracle.adf.model.BindingContext;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;  
 import org.apache.myfaces.trinidad.util.Service;  

public class Internationalization {

    private RichOutputText bindDateAndDay;
    private boolean popupShown = false;
    public Internationalization() {
        getDateInfo();
    }
private String currentLanguage;
private String dateAndDay;
    public String changeLocation_action() {
        // Add event code here...
        getExpressionLangValue();
        String lang = currentLanguage;
        Locale local = new Locale(lang);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(local);
        return null;
    }
    
    private String setExpression(){
        return "#{sessionScope.lang}";
    }
    
    public void getExpressionLangValue(){
        String expression = setExpression();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        Application app = facesContext.getApplication();
        ExpressionFactory expressionFactory = app.getExpressionFactory();
        ValueExpression valueExpression = expressionFactory.createValueExpression(elContext, expression, Object.class);
        currentLanguage = valueExpression.getValue(elContext).toString();
        

    }

    public void setBindDateAndDay(RichOutputText bindDateAndDay) {
        this.bindDateAndDay = bindDateAndDay;
    }

    public RichOutputText getBindDateAndDay() {
        return bindDateAndDay;
    }

    public void setDateAndDay(String dateAndDay) {
        this.dateAndDay = dateAndDay;
    }

    public String getDateAndDay() {
        return dateAndDay;
    }
    public String getDateInfo(){
        //returns a Calendar object whose calendar fields have been initialized with the current date and time  
        Calendar cal = Calendar.getInstance();
       String monthName = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH );
        //creating a constructor of the SimpleDateFormat class  
        SimpleDateFormat sdf = new SimpleDateFormat("dd");  
        //getting current date  
        System.out.println("Today's date: "+sdf.format(cal.getTime()));  
        String dates = sdf.format(cal.getTime());
        //creating a constructor of the Format class  
        //displaying full-day name  
        Format f = new SimpleDateFormat("EEEE");  
        String days = f.format(new Date());  
        //prints day name  
         dateAndDay = days+" "+getTime()+","+monthName+" "+dates+"th,";
        System.out.println("Day Name  and date: "+dateAndDay);  
        return dateAndDay;
    }
    
    public String getTime(){
        System.out.println("HI");
        SimpleDateFormat formatDate = new SimpleDateFormat(
                    "HH:mm");
                Date date = new Date();
                // initialize "Date" class
         
                formatDate.setTimeZone(TimeZone.getTimeZone("Asia/Muscat"));
                // converting to IST or format the Date as IST
         
                System.out.println(formatDate.format(date));
                // print formatted date and time
                return formatDate.format(date);
            }
}
