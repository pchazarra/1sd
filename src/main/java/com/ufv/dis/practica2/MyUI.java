package com.ufv.dis.practica2;

import javax.servlet.annotation.WebServlet;

import com.ufv.dis.practica2.agenda.Agenda;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.osgi.themes.ValoThemeContribution;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@PushStateNavigation
public class MyUI extends UI {
    private Agenda agenda = new Agenda();
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        agenda.importarJson("src/main/resources/contactos.json");

        Label menu_title = new Label("Agenda");
        menu_title.addStyleName(ValoTheme.MENU_TITLE);

        Button contacts_btn = new Button("Contactos", e -> getNavigator().navigateTo(""));
        contacts_btn.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
        Button addContact_btn = new Button("Nuevo Contacto", e -> getNavigator().navigateTo("addContact_view"));
        addContact_btn.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);

        CssLayout menu = new CssLayout(menu_title, contacts_btn, addContact_btn);
        menu.addStyleName(ValoTheme.MENU_ROOT);

        CssLayout viewContainer = new CssLayout();
        viewContainer.setSizeFull();

        GridLayout layout = new GridLayout(2, 1);
        layout.addComponent(menu, 0, 0);
        layout.addComponent(viewContainer, 1, 0);
        layout.setColumnExpandRatio(0, 1);
        layout.setColumnExpandRatio(1, 300);
        layout.setSizeFull();

        setContent(layout);

        Navigator navigator = new Navigator(this, viewContainer);
        navigator.addView("", new Contacts_view(agenda));
        navigator.addView("addContact_view", new AddContact_view(agenda));
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
