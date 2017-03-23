package com.company.events.web.calendar;


import com.haulmont.cuba.web.gui.components.WebCalendar;
import com.haulmont.cuba.web.toolkit.ui.CubaCalendar;
import com.vaadin.ui.Calendar;

/**
 * @author kolmakova
 * @version $Id$
 */
public class EventsCalendar extends WebCalendar {
    public EventsCalendar() {
        super();
    }

    @Override
    protected CubaCalendar createComponent() {
        return super.createComponent();
    }

    @Override
    protected void setNavigationButtonsStyle(boolean value) {
        super.setNavigationButtonsStyle(value);
    }
}
