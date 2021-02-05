package com.purchase.central.DAO.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.purchase.central.DAO.PurchaseDAO;
import com.purchase.central.DTO.Response.ReceiptResponseDTO;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.util.List;

@Repository
public class PurchaseDAOImpl implements PurchaseDAO {

    @Override
    public Integer createReceipt(ReceiptResponseDTO receipt){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
            List<ReceiptResponseDTO> receipts = objectMapper.readValue(new ClassPathResource("static/receipts.json").getFile(), new TypeReference<List<ReceiptResponseDTO>>() {});
            Integer id = getLastReceiptId() + 1;
            receipt.setId(id);
            receipts.add(receipt);
            objectMapper.writeValue(new ClassPathResource("static/receipts.json").getFile(), receipts);
            return id;
        } catch(Exception e){
            e.getMessage();
            throw new RuntimeException("Error saving the receipt ");
        }
    }

    public Integer getLastReceiptId(){
        return readJson().stream().mapToInt(item -> item.getId()).max().orElseGet(() -> 0);
    }

    public List<ReceiptResponseDTO> readJson(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            //return objectMapper.readValue(ResourceUtils.getFile("classpath:static/receipts.json"), new TypeReference<List<ReceiptResponseDTO>>() {});
            return objectMapper.readValue(new ClassPathResource("static/receipts.json").getFile(), new TypeReference<List<ReceiptResponseDTO>>() {});
        } catch (Exception e){
            throw new RuntimeException("Error reading JSON file");
        }
    }
}
