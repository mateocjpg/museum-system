package com.museum;

import com.museum.model.Artist;
import com.museum.model.Category;
import com.museum.model.Museum;
import com.museum.model.ExhibitionRoom;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;

@Singleton
public class DataInitializer {

    @Inject
    EntityManager em;

    @Transactional
    public void onStart(@Observes StartupEvent ev) {
        Long count = em.createQuery("SELECT COUNT(m) FROM Museum m", Long.class).getSingleResult();

        if (count == 0) {
            Museum museum = new Museum(
                "Museo Nacional de Arte", 
                "Principal centro de conservación del patrimonio artístico nacional.", 
                4.6097, 
                -74.0817, 
                "https://www.museonacional.gov.co"
            );
            em.persist(museum);

            ExhibitionRoom room = new ExhibitionRoom(
                "Sala Colonial", 
                "Exhibición de obras y reliquias del periodo colonial.", 
                50, 
                museum
            );
            em.persist(room);

            Artist artist = new Artist(
                "Fernando Botero", 
                "Famoso pintor y escultor colombiano conocido por sus figuras voluminosas.", 
                "Colombiana"
            );
            em.persist(artist);

            Category category = new Category(
                "Pintura al Óleo", 
                "Obras pictóricas realizadas con pigmentos aglutinados al aceite."
            );
            em.persist(category);

            System.out.println(">>> ¡Datos de prueba del Museo cargados exitosamente en la BD! <<<");
        }
    }
}