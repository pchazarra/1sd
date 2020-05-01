package com.ufv.dis.practica2;

import java.util.UUID;

import com.ufv.dis.practica2.agenda.Agenda;
import com.ufv.dis.practica2.agenda.Contacto;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class AddContact_view extends Composite implements View {
    private Agenda agenda;
    
    public AddContact_view(Agenda agenda) {
        this.agenda = agenda;

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();

        Panel panel = new Panel("Nuevo contacto");
        panel.setWidth("50%");
        
        FormLayout form = new FormLayout();
        TextField name = new TextField("Nombre");
        name.setWidth("100%");
        TextField surname = new TextField("Apellidos");
        surname.setWidth("100%");
        TextField work = new TextField("Empresa");
        work.setWidth("100%");
        TextField phone = new TextField("Teléfono");
        phone.setWidth("100%");
        TextField email = new TextField("E-mail");
        email.setWidth("100%");
        TextField address = new TextField("Dirección");
        address.setWidth("100%");

        HorizontalLayout buttons = new HorizontalLayout();

        Button submit = new Button("Enviar");
        submit.addClickListener(clickEvent -> {
            boolean creado = agenda.addContacto(new Contacto(name.getValue(), surname.getValue(), work.getValue(), phone.getValue(), email.getValue(), address.getValue(), UUID.randomUUID().toString()));
            Notification notif;

            // name.clear();
            // surname.clear();
            // work.clear();
            // phone.clear();
            // email.clear();
            // address.clear();

            if (creado) {
                notif = new Notification("Contacto creado correctamente", Notification.Type.TRAY_NOTIFICATION);
            }
            else {
                notif = new Notification("Contacto no creado", "Ya existe ese contacto.", Notification.Type.TRAY_NOTIFICATION);
            }

            notif.setPosition(Position.MIDDLE_CENTER);
            notif.show(Page.getCurrent());
        });

        Button reset = new Button("Limpiar");
        reset.addClickListener(clickEvent -> {
            name.clear();
            surname.clear();
            work.clear();
            phone.clear();
            email.clear();
            address.clear();
        });

        buttons.addComponents(submit, reset);

        form.addComponents(name, surname, work, phone, email, address, buttons);
        form.setMargin(true);

        panel.setContent(form);

        layout.addComponent(panel);
        layout.setComponentAlignment(panel, Alignment.TOP_CENTER);

        setCompositionRoot(layout);
    }
}
