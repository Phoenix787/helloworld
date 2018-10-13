package com.example.helloworld;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Hello World with elements")
@Route(value = "element", layout = MainLayout.class)
public class HelloWorldElement extends Component {

    public static final String VALUE_PROPERTY = "value";

    public HelloWorldElement() {
        super(ElementFactory.createDiv());

        UI.getCurrent().getPage().addHtmlImport(
                "frontend://bower_components/paper-input/paper-input.html");

        Element input = new Element("paper-input");
        Element greeting = ElementFactory.createDiv();

        input.synchronizeProperty(VALUE_PROPERTY, "value-changed");

        input.addPropertyChangeListener(VALUE_PROPERTY, event -> updateGreeting(input, greeting));

        getElement().appendChild(input, greeting);


    }

    private void updateGreeting(Element input, Element greeting) {
        String name = input.getProperty(VALUE_PROPERTY, "");

        if (name == null || name.isEmpty()) {
            greeting.setText("Please enter your name");
        } else {
            greeting.setText(String.format("Hello %s", name));
        }
    }
}
