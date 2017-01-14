package com.example.config;

import com.example.controllers.BookController;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;


/**
 * Created by Amir Shams on 12/14/2016.
*/
@Component
public class JesrseyConfig extends ResourceConfig {
    public JesrseyConfig() {
        register(BookController.class);
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }

}
