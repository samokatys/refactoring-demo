package com.scrumtrek.simplestore.demo;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assume.assumeTrue;

public class ArrayListTest {
    @Test(timeout = 1_000)
    public void shouldSizeIncrementedWhenElementAdd() { //BDD = DDD + tests
        //region Fixture | Arrange | Given
        ArrayList sut = new ArrayList();
        Object dummy = new Object();

        assumeTrue(sut.isEmpty());
        //endregion

        //region Act | When
        sut.add(dummy);
        //endregion

        //region Assert | Then
//        assertEquals("выдлпорвалдпровалопр ???? валопрвалопрвалопр", sut.getStatement());
//        assertTrue(sut.getStatement().contains("fdgfgd"));
        assertThat(sut)
                .contains(1,2,3)
                .doesNotContain(5);
        //Fluent API
        //endregion
    }

    @Ignore
    @Test(expected = Exception.class)
    public void shouldGetErrorWhenElementIsNull() {
        ArrayList<Object> sut = new ArrayList<>();
        sut.add(null);
    }
}
