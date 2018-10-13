package com.example.helloworld;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value="", layout = MainLayout.class)
@PageTitle("Hello World")
public class EntryPoint extends Div {


    public EntryPoint() {
        setText("Select version from the list above");
    }
}
