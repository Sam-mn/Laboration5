package ltu;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class PaymentTest
{
    private PaymentImpl test = null;

    @Before
    public void start() throws IOException
    {
        test = new PaymentImpl(new CalendarImpl());
    }

    @Test
    public void testSilly()
    {
        assertEquals(1, 1);
    }

    @Test
    public void test101TheStudentIsOver20()
    {
        assertEquals(0, test.getMonthlyAmount("20060323-1221", 0, 100, 90));
    }

    @Test
    public void test101TheStudentIsUnder20()
    {
        assertEquals(9904, test.getMonthlyAmount("20040323-1221", 0, 100, 90));
    }

    @Test
    public void test102TheStudentUnder56()
    {
        assertEquals(2816, test.getMonthlyAmount("19690323-1221", 0, 100, 90));
    }

    @Test
    public void test102TheStudentOver56()
    {
        assertEquals(0, test.getMonthlyAmount("19650323-1221", 0, 100, 90));
    }
    
    @Test
    public void test103TheStudentIsUnder47()
    {
        assertEquals(9904, test.getMonthlyAmount("19790323-1221", 0, 100, 90));
    }

    @Test
    public void test103TheStudentIsOver47()
    {
        assertEquals(2816, test.getMonthlyAmount("19760323-1221", 0, 100, 90));
    }

    @Test
    public void test103TheStudentIs47()
    {
        assertEquals(2816, test.getMonthlyAmount("19771111-1221", 0, 100, 90));
    }


   @Test
    public void test201TheStudyPaceUnder50()
    {
        assertEquals(0, test.getMonthlyAmount("20040323-1221", 0, 0, 90));
    }

    @Test
    public void test201TheStudyPaceOver50()
    {
        assertEquals(9904, test.getMonthlyAmount("20040323-1221", 0, 100, 90));
    }

    @Test
    public void test202TheStudyPaceIs50()
    {
        assertEquals(4960, test.getMonthlyAmount("20040323-1221", 0, 50, 90));
    }

    @Test
    public void test202TheStudyPaceIs75()
    {
        assertEquals(4960, test.getMonthlyAmount("20040323-1221", 0, 75, 90));
    }

    @Test
    public void test203TheStudyPace100()
    {
        assertEquals(9904, test.getMonthlyAmount("20040323-1221", 0, 100, 90));
    }

    @Test
    public void testFullTimeMaxIncome85812Sek301()
    {
        assertEquals(7088+2816, test.getMonthlyAmount("20040323-1221", 85812, 100, 90));
    }

    @Test
    public void testFullTimeMaxIncome85813Sek301()
    {
        assertEquals(7088+2816, test.getMonthlyAmount("20040323-1221", 85813, 100, 90));
    }

    @Test
    public void testFullTimeMaxIncome85814Sek301()
    {
        assertEquals(0, test.getMonthlyAmount("20040323-1221", 85814, 100, 90));
    }

    @Test
    public void testFullTimeMaxIncome85000Sek301()
    {
        assertEquals(7088+2816, test.getMonthlyAmount("20040323-1221", 85000, 100, 90));
    }

    @Test
    public void testFullTimeMaxIncome86000Sek301()
    {
        assertEquals(0, test.getMonthlyAmount("20040323-1221", 86000, 100, 90));
    }

    @Test
    public void testMoreThanFullTimeMaxIncome85812Sek301()
    {
        assertEquals(7088+2816, test.getMonthlyAmount("20040323-1221", 85812, 101, 90));
    }

    @Test
    public void testMoreThanFullTimeMaxIncome85814Sek301()
    {
        assertEquals(0, test.getMonthlyAmount("20040323-1221", 85814, 101, 90));
    }

    @Test
    public void testHalfTimeMaxIncome128721Sek302()
    {
        assertEquals(3564+1396, test.getMonthlyAmount("20040323-1221", 128721, 50, 90));
    }

    @Test
    public void testHalfTimeMaxIncome128722Sek302()
    {
        assertEquals(3564+1396, test.getMonthlyAmount("20040323-1221", 128722, 50, 90));
    }

    @Test
    public void testHalfTimeMaxIncome128723Sek302()
    {
        assertEquals(0, test.getMonthlyAmount("20040323-1221", 128723, 50, 90));
    }

    @Test
    public void testHalfTimeMaxIncome128000Sek302()
    {
        assertEquals(3564+1396, test.getMonthlyAmount("20040323-1221", 128000, 50, 90));
    }

    @Test
    public void testHalfTimeMaxIncome129000Sek302()
    {
        assertEquals(0, test.getMonthlyAmount("20040323-1221", 129000, 50, 90));
    }

    @Test
    public void testCompletionRatioLessThan50Percent401()
    {
        assertEquals(0, test.getMonthlyAmount("20040323-1221", 128722, 49, 49));
    }
}
