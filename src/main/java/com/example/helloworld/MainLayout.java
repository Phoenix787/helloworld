package com.example.helloworld;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.router.*;

import java.util.HashMap;
import java.util.Map;

@StyleSheet("frontend://css/style.css")
public class MainLayout extends Composite<Div>
        implements RouterLayout, AfterNavigationObserver {
    private Map<String, RouterLink> targetPaths = new HashMap<>();

    private Div container;

    public MainLayout() {
        H1 heading = new H1("See 3 ways to say Hello");
        Text intro = new Text("Три разных способа реализовать Hello World используя Vaadin Flow");

        container = new Div();
        container.addClassName("container");
        Div menu = buildMenu();

        getContent().setClassName("main-view");
        getContent().add(heading, intro, menu, container);
    }

    private Div buildMenu() {
        RouterLink template = new RouterLink("Template", HelloWorldTemplate.class);
        template.setId("template-link");

        RouterLink components = new RouterLink("Component", HelloWorldComponent.class);
        components.setId("components-link");

        RouterLink elements = new RouterLink("Element", HelloWorldElement.class);
        elements.setId("elements-link");

        targetPaths.put("template", template);
        targetPaths.put("component", components);
        targetPaths.put("element", elements);

        HtmlContainer ul = new HtmlContainer("ul");
        ul.setClassName("topnav");
        ul.add(template, components, elements);

        Div menu = new Div();
        menu.setClassName("menu");

        menu.add(ul);

        return menu;
    }

    @Override
    public void showRouterLayoutContent(HasElement child) {
        //обновляем что мы показываем когда бы подчиненное представление не изменилось
        container.removeAll();

        if (child != null) {
            container.add(new H2(child.getClass()
                    .getAnnotation(PageTitle.class).value()));
            container.add((Component) child);
        }
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        targetPaths.values().forEach(link -> link.removeClassNames("selected"));

        if (!event.getLocation().getPath().isEmpty()) {
            targetPaths.get(event.getLocation().getPath())
                    .addClassName("selected");
        }
    }
}
