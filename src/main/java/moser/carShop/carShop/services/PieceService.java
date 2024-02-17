package moser.carShop.carShop.services;

import jakarta.persistence.EntityNotFoundException;
import moser.carShop.carShop.dto.PieceDTO;
import moser.carShop.carShop.entities.Piece;
import moser.carShop.carShop.repositories.PieceRepository;
import moser.carShop.carShop.services.exceptions.DatabaseException;
import moser.carShop.carShop.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PieceService {

    @Autowired
    private PieceRepository repository;

    public PieceDTO findById(Long idPiece) {
        Optional<Piece> piece = repository.findById(idPiece);

        if(piece.get() != null) {
            PieceDTO pieceDTO = new PieceDTO(piece.get());
            return pieceDTO;
        } else {
            throw new ResourceNotFoundException(idPiece);
        }
    }

    public List<PieceDTO> findAll() {
        List<Piece> pieceList = repository.findAll();
        List<PieceDTO> pieceDTOS = new ArrayList<>();
        for (Piece p : pieceList) {
            pieceDTOS.add(new PieceDTO(p));
        }

        return pieceDTOS;
    }

    public Piece createPiece(Piece piece) {
        piece.setDtCriacao(new Date());
        Piece p = repository.save(piece);
        return p;
    }

    public void delete(Long idPiece) {
        try {
            repository.deleteById(idPiece);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(idPiece);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Piece update(Long idPiece, PieceDTO pieceDTO) {
        try {

            Piece originalPiece = repository.getReferenceById(idPiece);
            updatePiece(originalPiece, pieceDTO.transformToObject());

            return repository.save(originalPiece);
        } catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(idPiece);
        }
    }

    public void updatePiece(Piece originalPiece, Piece p) {
        originalPiece.setNome(p.getNome());
        originalPiece.setCodigo(p.getCodigo());
        originalPiece.setMarca(p.getMarca());
        originalPiece.setPrice(p.getPrice());
        originalPiece.setEstadoConservacao(p.getEstadoConservacao());
        originalPiece.setPieceStatus(p.getPieceStatus());
        originalPiece.setCarros(p.getCarros());
        originalPiece.setEmpresa(p.getEmpresa());
    }

}
