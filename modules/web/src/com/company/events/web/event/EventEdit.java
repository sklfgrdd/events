package com.company.events.web.event;

import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.events.entity.Event;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.DateField;

import javax.inject.Named;
import java.util.Date;
import java.util.Map;

public class EventEdit extends AbstractEditor<Event> {
    @Named("fieldGroup.startDate")
    private DateField startDateField;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        startDateField.addValueChangeListener(new ValueChangeListener() {
            @Override
            public void valueChanged(ValueChangeEvent e) {
                Date startDate = (Date) e.getValue();
                if (startDate.getHours() == 0) {
                startDate.setHours(9);
                startDate.setMinutes(0);
                    startDateField.setValue(startDate);
                }
                //startDateField.getti
            }
        });
    }
}