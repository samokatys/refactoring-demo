package com.scrumtrek;

import com.scrumtrek.simplestore.Customer;
import com.scrumtrek.simplestore.Movie;
import com.scrumtrek.simplestore.PriceCodes;
import com.scrumtrek.simplestore.Rental;
import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {
    @Test
    public void shouldSuccessWhenNewRelease() {
        // region Given
        Customer sut = new Customer("Slave");

        Movie movie = new Movie("Gladiator", PriceCodes.NewRelease);
        Rental rental = new Rental(movie, 5);
        // endregion

        // region When
        sut.addRental(rental);
        // endregion

        // region Then
        String statement = sut.Statement();
        Assert.assertEquals(
                "Rental record for Slave\n" +
                        "\tGladiator\t15.0\n" +
                        "Amount owed is 15.0\n" +
                        "You earned 2 frequent renter points.",
                statement);
        // endregion
    }
    @Test
    public void shouldSuccessWhenNewReleaseNoDays() {
        // region Given
        Customer sut = new Customer("Slave");

        Movie movie = new Movie("Gladiator", PriceCodes.NewRelease);
        Rental rental = new Rental(movie, 0);
        // endregion

        // region When
        sut.addRental(rental);
        // endregion

        // region Then
        String statement = sut.Statement();
        Assert.assertEquals(
                "Rental record for Slave\n" +
                        "\tGladiator\t0.0\n" +
                        "Amount owed is 0.0\n" +
                        "You earned 1 frequent renter points.",
                statement);
        // endregion
    }

    @Test
    public void shouldSuccessWhenRegularDaysLessOrEqual2()
    {
        // region Given
        Customer sut = new Customer("Slave");

        Movie movie = new Movie("Gladiator", PriceCodes.Regular);
        Rental rental = new Rental(movie, 2);
        // endregion

        // region When
        sut.addRental(rental);
        // endregion

        // region Then
        String statement = sut.Statement();
        Assert.assertEquals(
                "Rental record for Slave\n" +
                        "\tGladiator\t2.0\n" +
                        "Amount owed is 2.0\n" +
                        "You earned 1 frequent renter points.",
                statement);
        // endregion
    }

    @Test
    public void shouldSuccessWhenRegularDaysMore2() {
        // region Given
        Customer sut = new Customer("Slave");

        Movie movie = new Movie("Gladiator", PriceCodes.Regular);
        Rental rental = new Rental(movie, 5);
        // endregion

        // region When
        sut.addRental(rental);
        // endregion

        // region Then
        String statement = sut.Statement();
        Assert.assertEquals(
                "Rental record for Slave\n" +
                        "\tGladiator\t6.5\n" +
                        "Amount owed is 6.5\n" +
                        "You earned 1 frequent renter points.",
                statement);
        // endregion
    }

    @Test
    public void shouldSuccessWhenChildrensDaysMore3() {
        // region Given
        Customer sut = new Customer("Slave");

        Movie movie = new Movie("Gladiator", PriceCodes.Regular);
        Rental rental = new Rental(movie, 30);
        // endregion

        // region When
        movie.setPriceCode(PriceCodes.Childrens);
        sut.addRental(rental);
        // endregion

        // region Then
        String statement = sut.Statement();
        Assert.assertEquals(
                "Rental record for Slave\n" +
                        "\tGladiator\t40.5\n" +
                        "Amount owed is 40.5\n" +
                        "You earned 1 frequent renter points.",
                statement);
        // endregion
    }

    @Test
    public void shouldSuccessWhenChildrensDaysLessOrEqual3() {
        // region Given
        Customer sut = new Customer("Slave");

        Movie movie = new Movie("Gladiator", PriceCodes.Childrens);
        Rental rental = new Rental(movie, 1);
        // endregion

        // region When
        sut.addRental(rental);
        // endregion

        // region Then
        String statement = sut.Statement();
        Assert.assertEquals(
                "Rental record for Slave\n" +
                        "\tGladiator\t1.5\n" +
                        "Amount owed is 1.5\n" +
                        "You earned 1 frequent renter points.",
                statement);
        // endregion
    }

    @Test
    public void shouldSuccessWhenGetName() {
        // region Given
        // endregion

        // region When
        Customer sut = new Customer("Slave");
        // endregion

        // region Then
        Assert.assertEquals("Slave",
                sut.getName());
        // endregion
    }

    /*
    @Test
    public void shouldResultWhenAction() {
        // region Given
        // endregion

        // region When
        // endregion

        // region Then
        // endregion
    }
    * */
}
