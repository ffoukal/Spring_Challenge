package com.purchase.central.DAO;

import com.purchase.central.DTO.Response.ReceiptResponseDTO;

import java.util.List;

public interface PurchaseDAO {
    Integer createReceipt(ReceiptResponseDTO receipt);
}
