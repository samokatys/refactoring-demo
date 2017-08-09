package com.scrumtrek;

import com.scrumtrek.simplestore.Customer;
import com.scrumtrek.simplestore.Movie;
import com.scrumtrek.simplestore.PriceCodes;
import com.scrumtrek.simplestore.Rental;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class PornoTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 2, PriceCodes.Regular, new Customer.CalcData(2.0, 1)},
                { 5, PriceCodes.Regular, new Customer.CalcData(6.5, 1)},

                { 2, PriceCodes.XXX, new Customer.CalcData(2.0*(0.95), 1)},
                { 5, PriceCodes.XXX, new Customer.CalcData(6.5*(0.95), 1)},
        });
    }

    @Parameterized.Parameter // first data value (0) is default
    public int fDaysRental;

    @Parameterized.Parameter(1)
    public PriceCodes fPriceCode;

    @Parameterized.Parameter(2)
    public Customer.CalcData fResult;

    @Test
    public void test() {
        // region Given
        Customer sut = new Customer("Slave");

        Movie mockMovie = mock(Movie.class);
        Rental mockRental = mock(Rental.class);

        when(mockRental.getMovie()).thenReturn(mockMovie);
        when(mockRental.getDaysRented()).thenReturn(fDaysRental);

        when(mockMovie.getPriceCode()).thenReturn(fPriceCode);
        // endregion

        // region When
        Customer.CalcData data = sut.CalcStatement(mockRental);
        // endregion

        // region Then
        Assert.assertEquals(fResult.amount, data.amount, 0.001);
        Assert.assertEquals(fResult.frequentRenterPoints, data.frequentRenterPoints);
        // endregion
    }
}
