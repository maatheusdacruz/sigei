package br.vianna.trabalho.sigei.resource;


import br.vianna.trabalho.sigei.model.Participante;
import br.vianna.trabalho.sigei.service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/user")
public class UsuarioResource {

    @Autowired
    ParticipanteService cs;

    @GetMapping("")
    public ResponseEntity<?> getUserLogado(
            UsernamePasswordAuthenticationToken auth){
        return ResponseEntity.ok(auth);
    }
//    @GetMapping("/dog")
//    public ResponseEntity<?> getUserLogado(){
//        Cachorro c = new Cachorro(1,"toto",18,"pinscher",
//                100,null);
//        return ResponseEntity.ok(c);
//    }

    @GetMapping("/participante/{id}")
    public ResponseEntity<?> getclienteLogado(@PathVariable Integer id){
        try {
            Participante c = cs.getParticipante(id);
            if (c==null){
                return ResponseEntity.status(404).body("Participante não encontrado!!!");
            }
            return ResponseEntity.ok(c);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).body("Participante não encontrado!!!");
        }
    }

}
