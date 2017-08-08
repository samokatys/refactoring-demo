package com.scrumtrek.simplestore.demo;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class ArrayListTest {
    @Ignore
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

    @Test
    public void shouldToStringDependsOnElementsWhenStubToString() {
        //region Given
        ArrayList<Object> sut = new ArrayList<>();
        String stub = mock(String.class);
        when(stub.toString())
                .thenReturn("test string 1")
                .thenReturn("test string 2");

        /*
        when(stub.equals("1")).thenReturn(false);
        when(stub.equals("2")).thenReturn(true);
        when(stub.equals(anyString())).thenReturn(false);
        when(stub.equals(any(MyClass.class))).thenReturn(false);
        */
        sut.add(stub);
        //endregion

        //region When
        String result = sut.toString();
        //endregion

        //region Then
        assertThat(result).contains("test string");
        //endregion
    }

    @Test
    public void shouldToStringDependsOnElementsWhenMockToString() {
        ArrayList<Object> sut = new ArrayList<>();
        Object mock = mock(Object.class);
        sut.add(mock);

        sut.toString();

        verify(mock, times(1)).toString();
    }
}
