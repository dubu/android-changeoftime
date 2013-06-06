



import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * User: kingkingdubu
 * Date: 13. 6. 6
 * Time: 오후 1:03
 */
public class TimeTest {

    static int MinOfHour =60;

    @Test
    public void asdfkjl(){

        GregorianCalendar today = new GregorianCalendar ( );

        int year = today.get ( today.YEAR );
        int dayOfYear = today.get ( today.DAY_OF_YEAR );
        int month = today.get ( today.MONTH ) + 1;
        int yoil = today.get ( today.DAY_OF_MONTH );

        GregorianCalendar gc = new GregorianCalendar ( );

        System.out.println ( gc.get ( Calendar.YEAR ) );
        System.out.println ( String.valueOf ( gc.get ( Calendar.MONTH ) + 1 ) );
        System.out.println ( gc.get ( Calendar.DATE ) );

        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.KOREA);
        Calendar cal = Calendar.getInstance(Locale.KOREA);

        int minOfDay =  cal.get(Calendar.HOUR_OF_DAY) * MinOfHour  +   cal.get(Calendar.MINUTE);


        System.out.println("dayOfYear = " +dayOfYear );
        System.out.println("minOfDay  = " +minOfDay );

        //System.out.println ( cal.get ( Calendar.YEAR ) + "년 " + ( cal.get ( Calendar.MONTH ) + 1 ) + "월 " + cal.get ( Calendar.DATE ) + "일 " + cal.get ( Calendar.HOUR_OF_DAY ) + "시 " +cal.get ( Calendar.MINUTE ) + "분 " + cal.get ( Calendar.SECOND ) + "초 " );

    }
}
