package ltu;


import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class WhenAndAmountTest
{
    private PaymentImpl test = null;
    private String idNoLoan = "19750615-5441";
    private String idWithLoan = "19900615-5441";
    public MockCalendar cal = new MockCalendar();

    private class Student {
        public String id = idNoLoan;
        public int income = 0;
        public int studyRate = 100;
        public int completionRatio = 100;
    }

    private Student student = new Student();

    private void SetId(String newId) {
        student.id = newId;
    }

    private void SetStudyRate(int newRate) {
        student.studyRate = newRate;
    }

    private void SetDate(int year, int month, int day) {
        cal.SetDate(year, month, day);
    }

    @Before
    public void start() throws IOException
    {
        test = new PaymentImpl(cal);
    }

    @Test
    public void testTheLoanFulltime501A()
    {
        SetId(idNoLoan);
        SetStudyRate(100);
        int subsidy = test.getMonthlyAmount(student.id, student.income, student.studyRate, student.completionRatio);
        SetId(idWithLoan);
        int monthly = test.getMonthlyAmount(student.id, student.income, student.studyRate, student.completionRatio);
        assertEquals(monthly - subsidy, 7088);
    }

    @Test
    public void testTheLoanFulltime501B()
    {
        SetId(idNoLoan);
        SetStudyRate(101);
        int subsidy = test.getMonthlyAmount(student.id, student.income, student.studyRate, student.completionRatio);
        SetId(idWithLoan);
        int monthly = test.getMonthlyAmount(student.id, student.income, student.studyRate, student.completionRatio);
        assertEquals(monthly - subsidy, 7088);
    }

    @Test
    public void testTheSubsidyFulltime502A()
    {
        SetId(idNoLoan);
        SetStudyRate(100);
        assertEquals(test.getMonthlyAmount(student.id, student.income, student.studyRate, student.completionRatio), 2816);
    }

    @Test
    public void testTheSubsidyFulltime502B()
    {
        SetId(idNoLoan);
        SetStudyRate(101);
        assertEquals(test.getMonthlyAmount(student.id, student.income, student.studyRate, student.completionRatio), 2816);
    }

    @Test
    public void testTheLoanParttime503A()
    {
        SetId(idNoLoan);
        SetStudyRate(50);
        int subsidy = test.getMonthlyAmount(student.id, student.income, student.studyRate, student.completionRatio);
        SetId(idWithLoan);
        int monthly = test.getMonthlyAmount(student.id, student.income, student.studyRate, student.completionRatio);
        assertEquals(monthly - subsidy, 3564);
    }

    @Test
    public void testTheLoanParttime503B()
    {
        SetId(idNoLoan);
        SetStudyRate(99);
        int subsidy = test.getMonthlyAmount(student.id, student.income, student.studyRate, student.completionRatio);
        SetId(idWithLoan);
        int monthly = test.getMonthlyAmount(student.id, student.income, student.studyRate, student.completionRatio);
        assertEquals(monthly - subsidy, 3564);
    }

    @Test
    public void testTheLoanParttime503C()
    {
        SetId(idNoLoan);
        SetStudyRate(0);
        int subsidy = test.getMonthlyAmount(student.id, student.income, student.studyRate, student.completionRatio);
        SetId(idWithLoan);
        int monthly = test.getMonthlyAmount(student.id, student.income, student.studyRate, student.completionRatio);
        assertEquals(monthly - subsidy, 0);
    }

    @Test
    public void testTheLoanParttime503D()
    {
        SetId(idNoLoan);
        SetStudyRate(-1);
        try {
            int subsidy = test.getMonthlyAmount(student.id, student.income, student.studyRate, student.completionRatio);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testTheSubsidyParttime504A()
    {
        SetId(idNoLoan);
        SetStudyRate(50);
        assertEquals(test.getMonthlyAmount(student.id, student.income, student.studyRate, student.completionRatio), 1396);
    }

    @Test
    public void testTheSubsidyParttime504B()
    {
        SetId(idNoLoan);
        SetStudyRate(99);
        assertEquals(test.getMonthlyAmount(student.id, student.income, student.studyRate, student.completionRatio), 1396);
    }

    @Test
    public void testTheSubsidyParttime504C()
    {
        SetId(idNoLoan);
        SetStudyRate(0);
        assertEquals(test.getMonthlyAmount(student.id, student.income, student.studyRate, student.completionRatio), 0);
    }

    @Test
    public void testTheSubsidyParttime504D()
    {
        SetId(idNoLoan);
        SetStudyRate(-1);
        try {
            int subsidy = test.getMonthlyAmount(student.id, student.income, student.studyRate, student.completionRatio);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testAmountAlwaysFull505A()
    {
        SetId(idWithLoan);
        SetStudyRate(100);
        assertEquals(test.getMonthlyAmount(student.id, student.income, student.studyRate, student.completionRatio), 9904);
    }

    @Test
    public void testAmountAlwaysFull505B()
    {
        SetId(idWithLoan);
        SetStudyRate(50);
        assertEquals(test.getMonthlyAmount(student.id, student.income, student.studyRate, student.completionRatio), 4960);
    }

    @Test
    public void testCalendar506A()
    {
        SetId(idWithLoan);
        SetStudyRate(50);
        SetDate(2016, 1, 1);
        String paymentDay = test.getNextPaymentDay();
        assertEquals(paymentDay, "20160129");
    }

    @Test
    public void testCalendar506B()
    {
        SetId(idWithLoan);
        SetStudyRate(50);
        SetDate(2016, 1, 31);
        String paymentDay = test.getNextPaymentDay();
        assertEquals(paymentDay, "20160129");
    }

    @Test
    public void testCalendar506C()
    {
        SetId(idWithLoan);
        SetStudyRate(50);
        SetDate(2016, 1, 30);
        String paymentDay = test.getNextPaymentDay();
        assertEquals(paymentDay, "20160129");
    }

    @Test
    public void testCalendar506D()
    {
        SetId(idWithLoan);
        SetStudyRate(50);
        SetDate(2016, 2, 1);
        String paymentDay = test.getNextPaymentDay();
        assertEquals(paymentDay, "20160229");
    }
}
