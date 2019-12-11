package com.haulmont.testtask;

import com.haulmont.testtask.pages.HomePage;
import com.haulmont.testtask.pages.PatientPage;
import com.haulmont.testtask.pages.RecipePage;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import kaesdingeling.hybridmenu.HybridMenu;
import kaesdingeling.hybridmenu.components.HMButton;
import kaesdingeling.hybridmenu.components.HMLabel;
import kaesdingeling.hybridmenu.components.LeftMenu;
import kaesdingeling.hybridmenu.data.MenuConfig;
import kaesdingeling.hybridmenu.design.DesignItem;

import com.haulmont.testtask.pages.DoctorPage;

@SuppressWarnings("deprecation")
@Theme("demo")
@Title("Haulmont TestTask 3")
public class MainUI extends UI {

    private HybridMenu hybridMenu = null;

    @Override
    protected void init(VaadinRequest request) {

        hybridMenu = HybridMenu.get()
                .withNaviContent(new VerticalLayout())
                .withConfig(MenuConfig.get().withDesignItem(DesignItem.getWhiteDesign()))
                .build();

        buildLeftMenu();
        setContent(hybridMenu);

    }

    private void buildLeftMenu()  {
        UI.getCurrent().getNavigator().setErrorView(HomePage.class);
        LeftMenu leftMenu = hybridMenu.getLeftMenu();

        leftMenu.add(HMLabel.get()
                .withCaption("<b>CLINIC</b> System"))
                .withIcon(VaadinIcons.DOCTOR_BRIEFCASE);

        leftMenu.add(HMButton.get()
                .withCaption("Doctors")
                .withIcon(VaadinIcons.SPECIALIST)
                .withNavigateTo(DoctorPage.class));

        leftMenu.add(HMButton.get()
                .withCaption("Patients")
                .withIcon(VaadinIcons.ACCESSIBILITY)
                .withNavigateTo(PatientPage.class));

        leftMenu.add(HMButton.get()
                .withCaption("Recipes")
                .withIcon(VaadinIcons.PILLS)
                .withNavigateTo(RecipePage.class));

    }
}
