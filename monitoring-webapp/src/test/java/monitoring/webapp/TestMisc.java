package monitoring.webapp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import monitoring.webapp.ui.log.presenter.LogViewPresenter;

public class TestMisc {

    @Test
    public void test() {

        int value = 0;

        value = LogViewPresenter.toggleBit(value, 0);

        assertEquals(1, value);

        value = LogViewPresenter.toggleBit(value, 0);

        assertEquals(0, value);

        value = LogViewPresenter.toggleBit(value, 1);

        assertEquals(2, value);

        value = LogViewPresenter.toggleBit(value, 1);

        assertEquals(0, value);

        value = LogViewPresenter.toggleBit(value, 2);

        assertEquals(4, value);

        value = LogViewPresenter.toggleBit(value, 2);

        assertEquals(0, value);

        value = LogViewPresenter.toggleBit(value, 0);

        value = LogViewPresenter.toggleBit(value, 1);

        value = LogViewPresenter.toggleBit(value, 2);

        assertEquals(7, value);

        value = LogViewPresenter.toggleBit(value, 2);

        assertEquals(3, value);

    }

}
