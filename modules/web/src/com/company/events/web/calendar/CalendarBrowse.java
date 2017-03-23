package com.company.events.web.calendar;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.Calendar;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.cuba.web.gui.components.WebButton;
import com.haulmont.cuba.web.gui.components.WebHBoxLayout;
import com.haulmont.cuba.web.gui.components.WebLabel;
import com.haulmont.cuba.web.gui.components.WebPopupButton;
import com.vaadin.shared.ui.calendar.DateConstants;
import com.vaadin.ui.Label;
import com.vaadin.ui.components.calendar.event.CalendarEvent;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author kolmakova
 * @version $Id$
 */
public class CalendarBrowse extends AbstractLookup {
    @Inject
    protected ComponentsFactory componentsFactory;
    @Inject
    protected TimeSource timeSource;
    @Inject
    private Calendar calendar;
    private WebButton prevButton;
    private WebButton todayButton;
    private WebLabel monthLabel;
    private WebButton nextButton;
    private WebButton monthViewButton;
    private WebButton weekViewButton;
    private WebButton dayViewButton;
    protected DateFormat fmt;
    protected Date selectedDay;
    @Inject
    private HBoxLayout hBox;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        fmt = new SimpleDateFormat("LLLL yyyy", Locale.getDefault());
        setMonthView();
        hBox.add(createViewButtonsLayout());
        hBox.add(createPopupDateButton());
        hBox.add(createDateLayout());
        selectedDay = timeSource.currentTimestamp();
    }

    protected WebHBoxLayout createViewButtonsLayout() {
        WebHBoxLayout viewButtonsLayout = componentsFactory.createComponent(WebHBoxLayout.class);
        //viewButtonsLayout.addStyleName("buttons-group");
        //viewButtonsLayout.setSpacing(false);
        //viewButtonsLayout.setMargin(false);

        monthViewButton = componentsFactory.createComponent(WebButton.class);
        monthViewButton.setCaption(messages.getMessage(getClass(), "monthMsg"));
        weekViewButton = componentsFactory.createComponent(WebButton.class);
        weekViewButton.setCaption(messages.getMessage(getClass(), "weekMsg"));
        dayViewButton = componentsFactory.createComponent(WebButton.class);
        dayViewButton.setCaption(messages.getMessage(getClass(), "dayMsg"));
        dayViewButton.addStyleName("left");
        monthViewButton.addStyleName("right");

        weekViewButton.setAction(new AbstractAction("weekViewAction") {
            @Override
            public void actionPerform(Component component) {
                setWeekView(selectedDay, false);
            }
        });
        dayViewButton.setAction(new AbstractAction("dayViewAction") {
            @Override
            public void actionPerform(Component component) {
                setFirstMonthDayView();
            }
        });
        monthViewButton.setAction(new AbstractAction("monthViewAction") {
            @Override
            public void actionPerform(Component component) {
                setMonthView();
            }
        });

        viewButtonsLayout.add(dayViewButton);
        viewButtonsLayout.add(weekViewButton);
        viewButtonsLayout.add(monthViewButton);

        monthViewButton.setVisible(true);
        weekViewButton.setEnabled(true);
        dayViewButton.setEnabled(true);
        return viewButtonsLayout;
    }

    private WebPopupButton createPopupDateButton() {
        WebPopupButton popupDateButton = componentsFactory.createComponent(WebPopupButton.class);
        //popupDateButton.setIcon(WebComponentsHelper.getIcon("font-icon:CALENDAR").getMIMEType());
        //popupDateButton.addStyleName("calendar-popup");
        //popupDateButton.addStyleName(ThesisTheme.THESIS_WITHOUT_OUTLINE);
       // popupDateButton.addStyleName(ThesisTheme.THESIS_WITHOUT_INDICATOR);
        //popupDateButton.setImmediate(true);

        final WebHBoxLayout hl = componentsFactory.createComponent(WebHBoxLayout.class);
        //hl.setHeight("200px");
        //hl.setMargin(false);
        //hl.addStyleName("white-background");
        // Set start date to first date in this month
        popupDateButton.setPopupComponent(hl);
        //popupDateButton.addPopupVisibilityListener(new PopupDateVisibilityListener(hl));

        return popupDateButton;
    }

    protected WebHBoxLayout createDateLayout() {
        prevButton = componentsFactory.createComponent(WebButton.class);
        prevButton.setCaption("<");
        //prevButton.addStyleName("cuba-paging-change-page");
        //prevButton.addStyleName("cuba-paging-prev");
        //prevButton.addStyleName("left");


        nextButton = componentsFactory.createComponent(WebButton.class);
        nextButton.setCaption(">");
        //nextButton.addStyleName("cuba-paging-change-page");
        //nextButton.addStyleName("cuba-paging-next");
        //nextButton.addStyleName("right");

        todayButton = componentsFactory.createComponent(WebButton.class);
        todayButton.setCaption(messages.getMessage(getClass(), "todayMsg"));

        monthLabel = componentsFactory.createComponent(WebLabel.class);
        //monthLabel.setWidth("-1px");

       nextButton.setAction(new AbstractAction("nextAction") {
            @Override
            public void actionPerform(Component component) {
                //removeEvents();
                changePeriod(true, false);
                //updateTodayButton(calendar.getStartDate(), calendar.getEndDate());

            }
        });
        prevButton.setAction(new AbstractAction("prevAction") {
            @Override
            public void actionPerform(Component component) {
                //removeEvents();
                changePeriod(false, false);
                //updateTodayButton(calendar.getStartDate(), calendar.getEndDate());
            }
        });
       /* todayButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                setTodayView();
            }
        });*/
        WebHBoxLayout dateLayout = componentsFactory.createComponent(WebHBoxLayout.class);
        dateLayout.setWidth("270px");
        //Label spacer = new Label();
        //spacer.setWidth("16px");
        //dateLayout.addStyleName("buttons-group");
        dateLayout.add(prevButton);
        dateLayout.add(todayButton);
        dateLayout.add(nextButton);
        //dateLayout.add(spacer);
        dateLayout.add(monthLabel);

        //dateLayout.setExpandRatio(monthLabel, 1);
        //dateLayout.setComponentAlignment(monthLabel, com.vaadin.ui.Alignment.MIDDLE_LEFT);
        return dateLayout;
    }

    protected void changePeriod(boolean next, boolean isYear) {
        int direction = next ? 1 : -1;
        long currentCalDateRange = calendar.getEndDate().getTime()
                - calendar.getStartDate().getTime();
        GregorianCalendar startDate = new GregorianCalendar();
        startDate.setTime(calendar.getStartDate());
        GregorianCalendar endDate = new GregorianCalendar();
        endDate.setTime(calendar.getEndDate());
        if (isYear) {
            startDate.add(java.util.Calendar.YEAR, direction);
            endDate.add(java.util.Calendar.YEAR, direction);
            changeSelectedDay(java.util.Calendar.YEAR, direction);
        } else if (currentCalDateRange < 7 * DateConstants.DAYINMILLIS) {
            startDate.add(java.util.Calendar.DATE, direction);
            endDate.add(java.util.Calendar.DATE, direction);
            changeSelectedDay(java.util.Calendar.DATE, direction);
            for (int i = 0; i < currentCalDateRange / DateConstants.DAYINMILLIS; i++) {
                startDate.add(java.util.Calendar.DATE, direction);
                endDate.add(java.util.Calendar.DATE, direction);
                changeSelectedDay(java.util.Calendar.DATE, direction);
            }
        } else if (currentCalDateRange <= 2 * DateConstants.WEEKINMILLIS) {
            startDate.add(java.util.Calendar.WEEK_OF_YEAR, direction);
            endDate.add(java.util.Calendar.WEEK_OF_YEAR, direction);
            changeSelectedDay(java.util.Calendar.WEEK_OF_YEAR, direction);
            for (int i = 0; i < currentCalDateRange / DateConstants.WEEKINMILLIS; i++) {
                startDate.add(java.util.Calendar.WEEK_OF_YEAR, direction);
                endDate.add(java.util.Calendar.WEEK_OF_YEAR, direction);
                changeSelectedDay(java.util.Calendar.WEEK_OF_YEAR, direction);
            }
        } else {
            startDate.add(java.util.Calendar.MONTH, direction);
            changeSelectedDay(java.util.Calendar.MONTH, direction);
            if (next)
                endDate.add(java.util.Calendar.MONTH, 2);
            endDate.set(java.util.Calendar.DAY_OF_MONTH, 0);

        }
        calendar.setStartDate(startDate.getTime());
        calendar.setEndDate(endDate.getTime());
        /*Date currentDate = timeSource.currentTimestamp();
        if (calendar.getStartDate().compareTo(currentDate) < 0 && calendar.getEndDate().compareTo(currentDate) > 0) {
            selectedDay = currentDate;
        }
        //updateTodayButton(calendar.getStartDate(), calendar.getEndDate());
        monthLabel.setValue(formatDateCaption(calendar.getStartDate(), calendar.getEndDate()));*/
        //refreshCalendarEvents();
    }

    protected void changeSelectedDay(int tipeTimeField, int value) {
        GregorianCalendar selectedDate = new GregorianCalendar();
        selectedDate.setTime(selectedDay);
        selectedDate.add(tipeTimeField, value);
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.setTime(timeSource.currentTimestamp());
        if (tipeTimeField == java.util.Calendar.MONTH) {
            selectedDate.set(java.util.Calendar.DAY_OF_MONTH, 1);
        }
        selectedDay = selectedDate.getTime();
    }


    protected String formatDateCaption(Date startDate, Date endDate) {
        return fmt.format(startDate);
    }

    protected void setWeekView(Date startDate, boolean isTwoWeek) {
        calendar.setFirstVisibleDayOfWeek(1);
        calendar.setLastVisibleDayOfWeek(7);
        GregorianCalendar startCalendar = new GregorianCalendar();
        startCalendar.setFirstDayOfWeek(java.util.Calendar.MONDAY);
        GregorianCalendar endCalendar = new GregorianCalendar();
        startCalendar.setTime(startDate);
        startCalendar.set(java.util.Calendar.DAY_OF_WEEK, startCalendar.getFirstDayOfWeek());
        endCalendar.setFirstDayOfWeek(java.util.Calendar.MONDAY);
        endCalendar.setTime(startCalendar.getTime());
        endCalendar.set(java.util.Calendar.WEEK_OF_YEAR, startCalendar.get(java.util.Calendar.WEEK_OF_YEAR) + (isTwoWeek ? 1 : 0));
        if (endCalendar.getActualMaximum(java.util.Calendar.DAY_OF_YEAR) - startCalendar.get(java.util.Calendar.DAY_OF_YEAR) < 6)
            endCalendar.add(java.util.Calendar.YEAR, 1);
        endCalendar.set(java.util.Calendar.DAY_OF_WEEK, (startCalendar.getFirstDayOfWeek() + 6) % 7);
        calendar.setStartDate(startCalendar.getTime());
        calendar.setEndDate(getEndDateTime(endCalendar.getTime()));
        monthLabel.setValue(formatDateCaption(endCalendar.getTime(), endCalendar.getTime()));
        //setSelectedPeriod(false, true, false);
        //updatePressedButton();
        //updateTodayButton(calendar.getStartDate(), calendar.getEndDate());
    }

    protected void setFirstMonthDayView() {
        GregorianCalendar startDateCalendar = new GregorianCalendar();
        startDateCalendar.setTime(selectedDay);
        startDateCalendar.set(java.util.Calendar.HOUR_OF_DAY, 0);
        calendar.setStartDate(startDateCalendar.getTime());
        calendar.setEndDate(startDateCalendar.getTime());
    }

    protected void setMonthView() {
        calendar.setFirstVisibleDayOfWeek(1);
        calendar.setLastVisibleDayOfWeek(7);
        // Set start date to first date in this month
        GregorianCalendar startDate = new GregorianCalendar();
        startDate.setTime(calendar.getStartDate());
        startDate.set(java.util.Calendar.DATE, 1);
        calendar.setStartDate(startDate.getTime());

        // Set end date to last day of this month
        GregorianCalendar endDate = new GregorianCalendar();
        endDate.setTime(calendar.getStartDate());
        endDate.set(java.util.Calendar.DATE, 1);
        endDate.roll(java.util.Calendar.DATE, -1);
        calendar.setEndDate(getEndDateTime(endDate.getTime()));
    }

    public Date getEndDateTime(Date date) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(date);
        cal.set(java.util.Calendar.HOUR_OF_DAY, 23);
        cal.set(java.util.Calendar.MINUTE, 59);
        cal.set(java.util.Calendar.SECOND, 59);
        cal.set(java.util.Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    protected void setStartDate(Date date) {
        calendar.setStartDate(date);
    }

    protected void setEndDate(Date date) {
        calendar.setEndDate(date);
    }
}
