package com.example.helloworld;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;

@PageTitle("Hello World with components")
@Route(value = "component", layout = MainLayout.class)
public class HelloWorldComponent extends Composite<Div> {

    public HelloWorldComponent() {
        PaperInput input = new PaperInput();

        Div greeting = new Div();

        updateGreeting(input, greeting);

        input.addValueChangeListener(event -> updateGreeting(input, greeting));

        setId("inputId");
        greeting.setId("componentsGreeting");

        getContent().add(input, greeting);
    }

    private void updateGreeting(PaperInput input, Div greeting) {
        String name = input.getValue();

        if (name.isEmpty()) {
            greeting.setText("Please enter your name");
        } else {
            greeting.setText(String.format("Hello %s", name));
        }
    }


    @Tag("paper-input")
    @HtmlImport("frontend://bower_components/paper-input/paper-input.html")
    public static class PaperInput extends Component {

        @Synchronize("value-change")
        public String getValue() {
            return getElement().getProperty("value", "");
        }


        /**
         * Adds a listener that is automatically hooked up with a DOM event
         * based on annotations on the event class defined below
         *
         * @return
         */
        public Registration addValueChangeListener(ComponentEventListener<ValueChangeEvent> listener) {
            return addListener(ValueChangeEvent.class, listener);
        }


        /**
         * Custom event that is automatically connected to value-changed events from
         * the root element of the component for which listeners are added.
         */

        @DomEvent("value-changed")
        public static class ValueChangeEvent extends ComponentEvent<PaperInput> {
            /**
             * Creates a new event using the given source and indicator whether the
             * event originated from the client side or the server side.
             *
             * @param source
             *            the source component
             * @param fromClient
             *            <code>true</code> if the event originated from the client
             *            side, <code>false</code> otherwise
             */


            public ValueChangeEvent(PaperInput source, boolean fromClient) {
                super(source, fromClient);
            }


        }
    }
}
