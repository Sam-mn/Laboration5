package ltu;

import java.util.Calendar;
import java.util.Date;

public class MockCalendar implements ICalendar {
    private Calendar cal = null;

    public MockCalendar() {
        cal = Calendar.getInstance();
    }

    public void SetDate(int year, int month, int day) {
        cal.clear();
        cal.set(year, month - 1, day);
    }

    @Override
    public Date getDate() {
        return cal.getTime();
    }
}
