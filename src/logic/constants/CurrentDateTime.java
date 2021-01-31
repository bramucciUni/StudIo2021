package logic.constants;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class CurrentDateTime {
	
	private CurrentDateTime() {
		/* default constructor */
	}
	
	public static String getCurrentDate() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
		int month = calendar.get(Calendar.MONTH);
		return (calendar.get(Calendar.DAY_OF_MONTH) + "/" + getMonthFromInt(month) + "/" + calendar.get(Calendar.YEAR));
	}
	
	public static String getCurrentTime() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
		int min = calendar.get(Calendar.MINUTE);
		if(min < 10) {
			return (calendar.get(Calendar.HOUR_OF_DAY) + ":0" + min);
		} else {
			return (calendar.get(Calendar.HOUR_OF_DAY) + ":" + min);
		}
	}
	
	/*
	 * Method to convert MONTH format 
	 * (0:January, 1:February... 11:December) 
	 * to 
	 * (01:January, 02:February...12:December)
	 */
	private static String getMonthFromInt(int num) {
        String month;
        int monthNum = ++num;
        if(num < 10) {
        	month = "0" + monthNum;
        } else {
        	month = String.valueOf(monthNum);
        }
        return month;
    }

}
