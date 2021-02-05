package com.example.searchengine.Utils;

import com.example.searchengine.DTO.Validation.SearchValidation;
import com.example.searchengine.Model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterBuilder {

     public static List<Product> filter(SearchValidation data, List<Product> products){

        if(data.getCategory() != null){
            products = products.stream()
                    .filter(item -> item.getCategory().compareTo(data.getCategory()) == 0)
                    .collect(Collectors.toList());
        }

        if(data.getFreeShipping() != null){
            products = products.stream()
                    .filter(item -> item.getFreeShipping() == data.getFreeShipping())
                    .collect(Collectors.toList());
        }

         if(data.getId() != null){
             products = products.stream()
                     .filter(item -> item.getId() == data.getId())
                     .collect(Collectors.toList());
         }

        Product[] prodArr =  products.toArray(Product[]::new);

        if(data.getOrder() != null){
            Sorter<Product> sorter = new HeapSort<>();
            Comparator<Product> c = null;
            switch (data.getOrder()){
                case 0:
                    c = (a, b) -> a.getName().compareTo(b.getName());
                    break;
                case 1:
                    c = (a, b) -> b.getName().compareTo(a.getName());
                    break;
                case 3:
                    c = (a, b) -> a.getPrice() - b.getPrice();
                    break;
                case 2:
                    c = (a, b) -> b.getPrice() - a.getPrice();
                    break;
                default:
                    c = null;
                    break;
            }
            if (c != null) sorter.sort(prodArr, c);
        }
        return Arrays.asList(prodArr);
    }
}
