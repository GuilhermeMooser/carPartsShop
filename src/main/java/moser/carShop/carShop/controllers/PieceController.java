package moser.carShop.carShop.controllers;

import moser.carShop.carShop.dto.PieceDTO;
import moser.carShop.carShop.entities.Piece;
import moser.carShop.carShop.services.PieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/pieces")
public class PieceController {

    @Autowired
    PieceService pieceService;

    @GetMapping(value = "/{idPiece}")
    public ResponseEntity<PieceDTO> findById(@PathVariable Long idPiece) {
        PieceDTO p = pieceService.findById(idPiece);
        return ResponseEntity.ok().body(p);
    }

    @GetMapping(value = "/allUsers")
    public ResponseEntity<List<PieceDTO> > findAll() {
        List<PieceDTO> p = pieceService.findAll();
        return ResponseEntity.ok().body(p);
    }

    @PostMapping(value = "")
    public ResponseEntity<PieceDTO> createPiece(@RequestBody PieceDTO pieceDTO) {
        Piece p = pieceService.createPiece(pieceDTO.transformToObject());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getId()).toUri();
        return ResponseEntity.created(uri).body(new PieceDTO(p));
    }

    @DeleteMapping(value = "/{idPiece}")
    public ResponseEntity<Void> deletePiece(@PathVariable Long idPiece) {
        pieceService.delete(idPiece);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{idPiece}")
    public ResponseEntity<PieceDTO> updatePiece(@PathVariable Long idPiece, @RequestBody PieceDTO pieceDTO) {
        Piece piece = pieceService.update(idPiece, pieceDTO);
        return ResponseEntity.ok().body(new PieceDTO(piece));
    }

}
