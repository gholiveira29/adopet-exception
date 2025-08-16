package adopet.api.controller;

import adopet.api.dto.CadastroPetDTO;
import adopet.api.dto.PetDTO;
import adopet.api.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("pets")
public class PetController {

    @Autowired
    private PetService service;

    @GetMapping
    public ResponseEntity<List<PetDTO>> buscarTodos() {
        List<PetDTO> pets = service.listarTodos();
        return ResponseEntity.ok(pets);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestPart("dados") @Valid CadastroPetDTO dados, @RequestPart("imagem") MultipartFile imagem) {
        try {
            service.cadastrar(dados, imagem);
        } catch (IOException ex) {
            ResponseEntity.badRequest().body(ex.getMessage());
        }

        return ResponseEntity.ok().build();
    }


}
