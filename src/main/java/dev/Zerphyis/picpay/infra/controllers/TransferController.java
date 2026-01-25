package dev.Zerphyis.picpay.infra.controllers;

import dev.Zerphyis.picpay.aplication.dtos.TransferValueRequestDTO;
import dev.Zerphyis.picpay.aplication.usecases.transfer.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
