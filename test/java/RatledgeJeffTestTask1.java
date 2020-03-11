package cm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;


public class RatledgeJeffTestTask1 {
    public Period a, b, periodStay;
    public ArrayList<Period> normalPeriod;
    public ArrayList<Period> reducedPeriod;
    public CarParkKind kind;
    public BigDecimal normal, reduced;
    public Rate test;


    @org.junit.Test(expected = IllegalArgumentException.class)
    public void RateCannotBeNull() throws Exception{
        a = new Period(8, 12);
        b = new Period(0, 7);
        normalPeriod = new ArrayList<Period>();
        reducedPeriod = new ArrayList<Period>();
        normalPeriod.add(a);
        reducedPeriod.add(b);

        kind = CarParkKind.STAFF;
        normal = new BigDecimal(0);
        reduced = new BigDecimal(0);
        test = new Rate(kind, normal, reduced, reducedPeriod, normalPeriod);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void BothRatesLessThanZero() throws Exception{
        a = new Period(8, 12);
        b = new Period(0, 7);
        normalPeriod = new ArrayList<Period>();
        reducedPeriod = new ArrayList<Period>();
        normalPeriod.add(a);
        reducedPeriod.add(b);

        kind = CarParkKind.STAFF;
        normal = new BigDecimal(-2);
        reduced = new BigDecimal(-1);
        test = new Rate(kind, normal, reduced, reducedPeriod, normalPeriod);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void NormalRateLessThanZero() throws Exception{
        a = new Period(8, 12);
        b = new Period(0, 7);
        normalPeriod = new ArrayList<Period>();
        reducedPeriod = new ArrayList<Period>();
        normalPeriod.add(a);
        reducedPeriod.add(b);

        kind = CarParkKind.STAFF;
        normal = new BigDecimal(-1);
        reduced = new BigDecimal(3);
        test = new Rate(kind, normal, reduced, reducedPeriod, normalPeriod);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ReducedRateLessThanZero() throws Exception{
        a = new Period(8, 12);
        b = new Period(0, 7);
        normalPeriod = new ArrayList<Period>();
        reducedPeriod = new ArrayList<Period>();
        normalPeriod.add(a);
        reducedPeriod.add(b);

        kind = CarParkKind.STAFF;
        normal = new BigDecimal(4);
        reduced = new BigDecimal(-1);
        test = new Rate(kind, normal, reduced, reducedPeriod, normalPeriod);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void NormalRateGreaterThanReducedRate() throws Exception{
        a = new Period(8, 12);
        b = new Period(0, 7);
        normalPeriod = new ArrayList<Period>();
        reducedPeriod = new ArrayList<Period>();
        normalPeriod.add(a);
        reducedPeriod.add(b);

        kind = CarParkKind.STAFF;
        normal = new BigDecimal(2);
        reduced = new BigDecimal(4);
        test = new Rate(kind, normal, reduced, reducedPeriod, normalPeriod);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void NormalPeriodIsWithin24h() throws Exception{
        a = new Period(8, -12);
        b = new Period(0, 7);
        normalPeriod = new ArrayList<Period>();
        reducedPeriod = new ArrayList<Period>();
        normalPeriod.add(a);
        reducedPeriod.add(b);

        kind = CarParkKind.STAFF;
        normal = new BigDecimal(4);
        reduced = new BigDecimal(2);
        test = new Rate(kind, normal, reduced, reducedPeriod, normalPeriod);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void ReducedPeriodIsWithin24h() throws Exception{
        a = new Period(8, 12);
        b = new Period(0, -7);
        normalPeriod = new ArrayList<Period>();
        reducedPeriod = new ArrayList<Period>();
        normalPeriod.add(a);
        reducedPeriod.add(b);

        kind = CarParkKind.STAFF;
        normal = new BigDecimal(4);
        reduced = new BigDecimal(2);
        test = new Rate(kind, normal, reduced, reducedPeriod, normalPeriod);
    }

    @org.junit.Test
    public void CalculateInNormalPeriod() throws Exception{
        a = new Period(8, 12);
        b = new Period(0, 7);
        periodStay = new Period(9, 10);
        normalPeriod = new ArrayList<Period>();
        reducedPeriod = new ArrayList<Period>();
        normalPeriod.add(a);
        reducedPeriod.add(b);

        kind = CarParkKind.STAFF;
        normal = new BigDecimal(4);
        reduced = new BigDecimal(2);
        test = new Rate(kind, normal, reduced, reducedPeriod, normalPeriod);
        BigDecimal outCome = new BigDecimal(4);
        assertEquals(outCome, test.calculate(periodStay));
    }

    @org.junit.Test
    public void CalculateInReducedPeriod() throws Exception{
        a = new Period(8, 12);
        b = new Period(0, 7);
        periodStay = new Period(2, 5);
        normalPeriod = new ArrayList<Period>();
        reducedPeriod = new ArrayList<Period>();
        normalPeriod.add(a);
        reducedPeriod.add(b);

        kind = CarParkKind.STAFF;
        normal = new BigDecimal(4);
        reduced = new BigDecimal(2);
        test = new Rate(kind, normal, reduced, reducedPeriod, normalPeriod);
        BigDecimal outCome = new BigDecimal(6);
        assertEquals(outCome, test.calculate(periodStay));
    }

    @org.junit.Test
    public void CalculateInOverlapPeriod() throws Exception{
        a = new Period(8, 12);
        b = new Period(0, 7);
        periodStay = new Period(6, 10);
        normalPeriod = new ArrayList<Period>();
        reducedPeriod = new ArrayList<Period>();
        normalPeriod.add(a);
        reducedPeriod.add(b);

        kind = CarParkKind.STAFF;
        normal = new BigDecimal(4);
        reduced = new BigDecimal(2);
        test = new Rate(kind, normal, reduced, reducedPeriod, normalPeriod);
        BigDecimal outCome = new BigDecimal(10);
        assertEquals(outCome, test.calculate(periodStay));
    }
}

