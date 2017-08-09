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
public class CustomerUnitTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 1, "Gladiator", PriceCodes.Childrens,
                        "Rental record for Slave\n" +
                        "\tGladiator\t1.5\n" +
                        "Amount owed is 1.5\n" +
                        "You earned 1 frequent renter points." },
                { 30, "Gladiator", PriceCodes.Childrens,
                        "Rental record for Slave\n" +
                                "\tGladiator\t42.0\n" +
                                "Amount owed is 42.0\n" +
                                "You earned 1 frequent renter points."},


                { 5, "Gladiator", PriceCodes.NewRelease,
                        "Rental record for Slave\n" +
                                "\tGladiator\t15.0\n" +
                                "Amount owed is 15.0\n" +
                                "You earned 2 frequent renter points." },
                { 0, "Gladiator", PriceCodes.NewRelease,
                        "Rental record for Slave\n" +
                                "\tGladiator\t0.0\n" +
                                "Amount owed is 0.0\n" +
                                "You earned 1 frequent renter points."},


                { 2, "Gladiator", PriceCodes.Regular,
                        "Rental record for Slave\n" +
                                "\tGladiator\t2.0\n" +
                                "Amount owed is 2.0\n" +
                                "You earned 1 frequent renter points."},
                { 5, "Gladiator", PriceCodes.Regular,
                        "Rental record for Slave\n" +
                                "\tGladiator\t6.5\n" +
                                "Amount owed is 6.5\n" +
                                "You earned 1 frequent renter points."},
        });
    }

    @Parameterized.Parameter // first data value (0) is default
    public int fDaysRental;

    @Parameterized.Parameter(1)
    public String fTitle;

    @Parameterized.Parameter(2)
    public PriceCodes fPriceCode;

    @Parameterized.Parameter(3)
    public String fExpected;

    @Test
    public void test() {
        // region Given
        Customer sut = new Customer("Slave");

        Movie mockMovie = mock(Movie.class);
        Rental mockRental = mock(Rental.class);

        when(mockRental.getMovie()).thenReturn(mockMovie);
        when(mockRental.getDaysRented()).thenReturn(fDaysRental);

        when(mockMovie.getPriceCode()).thenReturn(fPriceCode);
        when(mockMovie.getTitle()).thenReturn(fTitle);

        sut.addRental(mockRental);
        // endregion

        // region When
        String statement = sut.Statement();
        // endregion

        // region Then
        assertThat(statement).contains(fTitle);
        Assert.assertEquals(fExpected, statement);

        // endregion
    }
}
