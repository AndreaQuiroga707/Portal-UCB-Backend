package bo.edu.ucb.backend.bl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.edu.ucb.backend.dao.EventosDAO;
import bo.edu.ucb.backend.entity.Eventos;
import org.springframework.validation.BindingResult;

@Service
public class EventosBL {
    @Autowired
    private EventosDAO eventosDAO;


    public Eventos save(Eventos eventos, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        return eventosDAO.save(eventos);
    }

    public Eventos findEventosById(Integer eventoId) {
        return eventosDAO.findById(eventoId).orElseThrow(() -> new RuntimeException("Evento no encontrado"));
    }

    public Iterable<Eventos> findAllEventos() {
        try {
            return eventosDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los eventos");
        }
    }

    public void deleteEventosById(Integer eventoId) {
        if (eventosDAO.findById(eventoId).isPresent()) {
            eventosDAO.deleteById(eventoId);
        } else {
            throw new RuntimeException("Evento no encontrado");
        }
    }

    public Eventos updateEventos(Eventos eventos, BindingResult result){
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        if (eventosDAO.findById(eventos.getEventoId()).isPresent()) {
            return eventosDAO.save(eventos);
        } else {
            throw new RuntimeException("Evento no encontrado");
        }
    }

}
