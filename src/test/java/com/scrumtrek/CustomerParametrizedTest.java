package com.scrumtrek;

import com.scrumtrek.simplestore.Customer;
import com.scrumtrek.simplestore.Movie;
import com.scrumtrek.simplestore.PriceCodes;
import com.scrumtrek.simplestore.Rental;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.fest.assertions.api.Assertions.assertThat;

@Ignore
@RunWith(Parameterized.class)
public class CustomerParametrizedTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 1, "Rental record for Slave\n" +
                        "\tGladiator\t1.5\n" +
                        "Amount owed is 1.5\n" +
                        "You earned 1 frequent renter points." },
        });
    }

    @Parameterized.Parameter // first data value (0) is default
    public int fInput;

    @Parameterized.Parameter(1)
    public String fExpected;

    @Test
    public void test() {
        // region Given
        Customer sut = new Customer("Slave");

        Movie movie = new Movie("Gladiator", PriceCodes.Childrens);
        Rental rental = new Rental(movie, fInput);
        // endregion

        // region When
        sut.addRental(rental);
        // endregion

        // region Then
        String statement = sut.Statement();
        //for (auto each : fExpected)
        assertThat(statement).contains(fExpected);
        //Assert.assertEquals(fExpected, statement);
        // endregion
    }
}
