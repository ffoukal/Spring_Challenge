package com.purchase.central.DTO.Response;

public class PurchaseResponseDTO {
    private ReceiptResponseDTO receipt;
    private StatusResponseDTO statusCode;

    public PurchaseResponseDTO() {
        this.receipt = new ReceiptResponseDTO();
        this.statusCode = new StatusResponseDTO();
    }

    public ReceiptResponseDTO getReceipt() {
        return receipt;
    }

    public void setReceipt(ReceiptResponseDTO receipt) {
        this.receipt = receipt;
    }

    public StatusResponseDTO getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusResponseDTO statusCode) {
        this.statusCode = statusCode;
    }
}
