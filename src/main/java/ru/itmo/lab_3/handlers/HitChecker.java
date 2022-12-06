package ru.itmo.lab_3.handlers;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.math.BigDecimal;

@ManagedBean
@ApplicationScoped
public class HitChecker {
    private static final BigDecimal ZERO = new BigDecimal("0");
    private static final BigDecimal TWO = new BigDecimal("2");

    public boolean checkHit(BigDecimal r, BigDecimal x, BigDecimal y) {
        //noinspection BigDecimalMethodWithoutRoundingCalled
        BigDecimal semiR = r.divide(TWO);

        boolean firstQuarterTriangle = ( x.compareTo(ZERO) >= 0 && y.compareTo(ZERO) >= 0 &&
                (y.add(x.multiply(TWO)).compareTo(r) <= 0) );

        boolean secondQuarterCircle = ( x.compareTo(ZERO) >= 0 && y.compareTo(ZERO) >= 0 &&
                ((x.multiply(x)).add(y.multiply(y)).compareTo(semiR.multiply(semiR)) <= 0) );

        boolean thirdQuarterRectangle = ( x.compareTo(ZERO) >= 0 && x.compareTo(r) <= 0 &&
                y.compareTo(ZERO) <= 0 && y.compareTo(semiR.negate()) >= 0 );

        return firstQuarterTriangle || secondQuarterCircle || thirdQuarterRectangle;
    }
}
