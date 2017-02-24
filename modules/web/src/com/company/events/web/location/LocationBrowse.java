package com.company.events.web.location;

import com.company.events.entity.Location;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.components.actions.EditAction;
import com.haulmont.cuba.gui.components.actions.RemoveAction;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.DataSupplier;
import com.haulmont.cuba.gui.data.Datasource;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

public class LocationBrowse extends AbstractLookup {

    /**
     * The {@link CollectionDatasource} instance that loads a list of {@link Location} records
     * to be displayed in {@link LocationBrowse#locationsTable} on the left
     */
    @Inject
    private CollectionDatasource<Location, UUID> locationsDs;

    /**
     * The {@link Datasource} instance that contains an instance of the selected entity
     * in {@link LocationBrowse#locationsDs}
     * <p/> Containing instance is loaded in {@link CollectionDatasource#addItemChangeListener}
     * with the view, specified in the XML screen descriptor.
     * The listener is set in the {@link LocationBrowse#init(Map)} method
     */
    @Inject
    private Datasource<Location> locationDs;

    /**
     * The {@link Table} instance, containing a list of {@link Location} records,
     * loaded via {@link LocationBrowse#locationsDs}
     */
    @Inject
    private Table<Location> locationsTable;

    /**
     * The {@link BoxLayout} instance that contains components on the left side
     * of {@link SplitPanel}
     */
    @Inject
    private BoxLayout lookupBox;

    /**
     * The {@link BoxLayout} instance that contains buttons to invoke Save or Cancel actions in edit mode
     */
    @Inject
    private BoxLayout actionsPane;

    /**
     * The {@link FieldGroup} instance that is linked to {@link LocationBrowse#locationDs}
     * and shows fields of the selected {@link Location} record
     */
    @Inject
    private FieldGroup fieldGroup;

    /**
     * The {@link RemoveAction} instance, related to {@link LocationBrowse#locationsTable}
     */
    @Named("locationsTable.remove")
    private RemoveAction locationsTableRemove;

    @Inject
    private DataSupplier dataSupplier;

    /**
     * {@link Boolean} value, indicating if a new instance of {@link Location} is being created
     */
    private boolean creating;

    @Override
    public void init(Map<String, Object> params) {

        /**
         * Adding {@link com.haulmont.cuba.gui.data.Datasource.ItemChangeListener} to {@link locationsDs}
         * The listener reloads the selected record with the specified view and sets it to {@link locationDs}
         */
        locationsDs.addItemChangeListener(e -> {
            if (e.getItem() != null) {
                Location reloadedItem = dataSupplier.reload(e.getDs().getItem(), locationDs.getView());
                locationDs.setItem(reloadedItem);
            }
        });

        /**
         * Adding {@link CreateAction} to {@link locationsTable}
         * The listener removes selection in {@link locationsTable}, sets a newly created item to {@link locationDs}
         * and enables controls for record editing
         */
        locationsTable.addAction(new CreateAction(locationsTable) {
            @Override
            protected void internalOpenEditor(CollectionDatasource datasource, Entity newItem, Datasource parentDs, Map<String, Object> params) {
                locationsTable.setSelected(Collections.emptyList());
                locationDs.setItem((Location) newItem);
                enableEditControls(true);
            }
        });

        /**
         * Adding {@link EditAction} to {@link locationsTable}
         * The listener enables controls for record editing
         */
        locationsTable.addAction(new EditAction(locationsTable) {
            @Override
            protected void internalOpenEditor(CollectionDatasource datasource, Entity existingItem, Datasource parentDs, Map<String, Object> params) {
                if (locationsTable.getSelected().size() == 1) {
                    enableEditControls(false);
                }
            }
        });

        /**
         * Setting {@link RemoveAction#afterRemoveHandler} for {@link locationsTableRemove}
         * to reset record, contained in {@link locationDs}
         */
        locationsTableRemove.setAfterRemoveHandler(removedItems -> locationDs.setItem(null));

        disableEditControls();
    }

    /**
     * Method that is invoked by clicking Save button after editing an existing or creating a new record
     */
    public void save() {
        getDsContext().commit();

        Location editedItem = locationDs.getItem();
        if (creating) {
            locationsDs.includeItem(editedItem);
        } else {
            locationsDs.updateItem(editedItem);
        }
        locationsTable.setSelected(editedItem);

        disableEditControls();
    }

    /**
     * Method that is invoked by clicking Save button after editing an existing or creating a new record
     */
    public void cancel() {
        Location selectedItem = locationsDs.getItem();
        if (selectedItem != null) {
            Location reloadedItem = dataSupplier.reload(selectedItem, locationDs.getView());
            locationsDs.setItem(reloadedItem);
        } else {
            locationDs.setItem(null);
        }

        disableEditControls();
    }

    /**
     * Enabling controls for record editing
     * @param creating indicates if a new instance of {@link Location} is being created
     */
    private void enableEditControls(boolean creating) {
        this.creating = creating;
        initEditComponents(true);
        fieldGroup.requestFocus();
    }

    /**
     * Disabling editing controls
     */
    private void disableEditControls() {
        initEditComponents(false);
        locationsTable.requestFocus();
    }

    /**
     * Initiating edit controls, depending on if they should be enabled/disabled
     * @param enabled if true - enables editing controls and disables controls on the left side of the splitter
     *                if false - visa versa
     */
    private void initEditComponents(boolean enabled) {
        fieldGroup.setEditable(enabled);
        actionsPane.setVisible(enabled);
        lookupBox.setEnabled(!enabled);
    }
}