package com.luv2code.springbootlibrary.config;

import com.luv2code.springbootlibrary.entity.Book;
import com.luv2code.springbootlibrary.entity.Message;
import com.luv2code.springbootlibrary.entity.Review;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * @author Guillaume COLLET
 */
@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    private String theAllowedOrigins = "http://localhost:3000";

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        /*
         * Tableau qui regroupe les types de requêtes qu'on ne souhaite pas autoriser.
         * Ici, on veut de la lecture seule (GET only).
         * */
        HttpMethod[] theUnsupportedActions = {
                HttpMethod.POST,
                HttpMethod.DELETE,
                HttpMethod.PATCH,
                HttpMethod.PUT
        };

        /*
         * Exposer les ID pour les classes afin de savoir précisément de quel livre, review, message il est question
         * dans les requêtes.
         * */
        config.exposeIdsFor(Book.class);
        config.exposeIdsFor(Review.class);
        config.exposeIdsFor(Message.class);

        /*
         * Mise en application de la désactivation des types de requêtes cités plus haut.
         * */
        disableHttpMethods(Book.class, config, theUnsupportedActions);
        disableHttpMethods(Review.class, config, theUnsupportedActions);
        disableHttpMethods(Message.class, config, theUnsupportedActions);

        /*
         * Configure CORS Mapping : https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
         * */
        cors.addMapping(config.getBasePath() + "/**")
                .allowedOrigins(theAllowedOrigins);
    }

    /*
     * Implémentation de la fonction qui permet de désactiver les types de requêtes voulus.
     * */
    private void disableHttpMethods(Class theClass,
                                    RepositoryRestConfiguration config,
                                    HttpMethod[] theUnsupportedHttpMethods) {

        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) ->
                        httpMethods.disable(theUnsupportedHttpMethods))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedHttpMethods));
    }
}
