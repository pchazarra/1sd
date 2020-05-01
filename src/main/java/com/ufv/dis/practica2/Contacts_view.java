package com.ufv.dis.practica2;

import com.ufv.dis.practica2.agenda.Agenda;
import com.ufv.dis.practica2.agenda.Contacto;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.components.grid.MultiSelectionModel;
import com.vaadin.ui.renderers.ButtonRenderer;

public class Contacts_view extends Composite implements View {
    public Contacts_view(Agenda agenda) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();

        Grid<Contacto> grid = new Grid<Contacto>();

        TextField name = new TextField();
        TextField surname = new TextField();
        TextField work = new TextField();
        TextField phone = new TextField();
        TextField email = new TextField();
        TextField address = new TextField();

        grid.setItems(agenda.getContactos());
        MultiSelectionModel<Contacto> selectionModel = (MultiSelectionModel<Contacto>) grid.setSelectionMode(SelectionMode.MULTI);
        Button deleteSelected = new Button("Borrar seleccionados");
        
        selectionModel.addMultiSelectionListener(event -> {
            deleteSelected.setEnabled(event.getNewSelection().size() > 0);

            deleteSelected.addClickListener(clickEvent -> {
                for (Contacto contacto : event.getNewSelection()) {
                    agenda.eliminarContacto(contacto.getId());
                }

                grid.setItems(agenda.getContactos());
            });
        });

        grid.addColumn(Contacto::getNombre).setCaption("Nombre").setEditorComponent(name, Contacto::setNombre);
        grid.addColumn(Contacto::getApellidos).setCaption("Apellido").setEditorComponent(surname, Contacto::setApellidos);
        grid.addColumn(Contacto::getEmpresa).setCaption("Empresa").setEditorComponent(work, Contacto::setEmpresa);
        grid.addColumn(Contacto::getTelefono).setCaption("Teléfono").setEditorComponent(phone, Contacto::setTelefono);
        grid.addColumn(Contacto::getEmail).setCaption("E-mail").setEditorComponent(email, Contacto::setEmail);
        grid.addColumn(Contacto::getDireccion).setCaption("Dirección").setEditorComponent(address, Contacto::setDireccion);

        grid.getEditor().setEnabled(true);
        grid.getEditor().setSaveCaption("Guardar");
        grid.getEditor().setCancelCaption("Cancelar");

        grid.setWidth("100%");
        grid.setHeight("100%");

        layout.addComponents(grid, deleteSelected);

        setCompositionRoot(layout);
    }
}
