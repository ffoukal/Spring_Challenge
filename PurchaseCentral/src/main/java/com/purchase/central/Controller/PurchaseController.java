package com.purchase.central.Controller;

import com.purchase.central.DTO.Request.PurchaseRequestDTO;
import com.purchase.central.DTO.Response.PurchaseResponseDTO;
import com.purchase.central.DTO.Response.ReceiptResponseDTO;
import com.purchase.central.Service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class PurchaseController {

    private PurchaseService service;

    @Autowired

    public PurchaseController(PurchaseService service) {
        this.service = service;
    }

    @PostMapping("/purchase-request")
    public ResponseEntity<PurchaseResponseDTO> getPurchaseInfo(@RequestBody PurchaseRequestDTO request){
        return this.service.purchase(request);
    }

    @PostMapping("/checkout")
    public ResponseEntity checkout(){
        return this.service.checkout();
    }

}
