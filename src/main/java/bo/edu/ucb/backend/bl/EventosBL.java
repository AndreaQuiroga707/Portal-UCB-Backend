package bo.edu.ucb.backend.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.edu.ucb.backend.dao.EventosDAO;
import bo.edu.ucb.backend.dto.EventosDTO;
import org.springframework.validation.BindingResult;

@Service
public class EventosBL {
    @Autowired
    private EventosDAO eventosDAO;
    
    public EventosDTO save(EventosDTO eventosDTO, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        return eventosDAO.save(eventosDTO);
    }

    public EventosDTO findEventosById(Integer eventoId) {
        return eventosDAO.findById(eventoId).orElseThrow(() -> new RuntimeException("Evento no encontrado"));
    }

    public Iterable<EventosDTO> findAllEventos() {
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

    public EventosDTO updateEventos(EventosDTO eventosDTO, BindingResult result){
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        if (eventosDAO.findById(eventosDTO.getEventoId()).isPresent()) {
            return eventosDAO.save(eventosDTO);
        } else {
            throw new RuntimeException("Evento no encontrado");
        }
    }

}
