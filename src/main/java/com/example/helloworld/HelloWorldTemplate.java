package com.example.helloworld;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.polymertemplate.TemplateParser;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;

import java.util.Optional;

@Tag("hello-world")
@HtmlImport("frontend://src/HelloWorld.html")
@PageTitle("Hello World with Template")
@Route(value = "template", layout = MainLayout.class)
public class HelloWorldTemplate extends PolymerTemplate<HelloWorldTemplate.HelloWorldModel> {

    private static final String EMPTY_NAME_GREETING = "Please enter your name";
    public HelloWorldTemplate() {
        updateGreeting();

        getElement().addPropertyChangeListener("name",
                event -> updateGreeting());

        setId("template");
    }

    private void updateGreeting() {
        String name = getModel().getName();

        if (name == null || name.isEmpty()) {
            getModel().setGreeting("Please enter your name");
        } else {
            getModel().setGreeting(String.format("Hello %s!", name));
        }
    }

    @EventHandler
    private void sayHello(){
        getModel().setGreeting(Optional.ofNullable(getModel().getName())
        .filter(name->!name.isEmpty())
        .map(greeting->String.format("Hello %s by button click", greeting))
        .orElse(EMPTY_NAME_GREETING));
    }


    public interface HelloWorldModel extends TemplateModel {
        /**
         * The name shown in the input is updated from client
         * @return current name in model
         */
        String getName();

        /**
         * The greeting is updated from Java code on the server
         * @param greeting - greeting to set to the model
         */
        void setGreeting(String greeting);


    }
}
