package com.purchase.central.Service;

import com.purchase.central.DTO.Request.PurchaseRequestDTO;
import com.purchase.central.DTO.Response.PurchaseResponseDTO;
import com.purchase.central.DTO.Response.ReceiptResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PurchaseService {
    public ResponseEntity<PurchaseResponseDTO> purchase(PurchaseRequestDTO request);
}
