package dev.Zerphyis.picpay.infra.controllers;

import dev.Zerphyis.picpay.aplication.dtos.TransferValueRequestDTO;
import dev.Zerphyis.picpay.aplication.usecases.transfer.TransferService;
import dev.Zerphyis.picpay.domain.entities.transfers.Transaction;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<Void> transfer(
            @Valid @RequestBody TransferValueRequestDTO request
    ) {
        transferService.execute(
                request.payerId(),
                request.payeeId(),
                request.value()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> listAll() {
        return ResponseEntity.ok(transferService.listAllTransactions());
    }

    @PostMapping("/{transactionId}/refund")
    public ResponseEntity<Void> refund(
            @PathVariable Long transactionId
    ) {
        transferService.refund(transactionId);
        return ResponseEntity.noContent().build();
    }
}
