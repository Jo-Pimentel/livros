package com.example.livros.controller;

import com.example.livros.model.Item;
import com.example.livros.repository.ItemRepository;
import com.example.livros.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biblioteca/itens")
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/buscarItens")
    @ResponseStatus(HttpStatus.OK)
    public Page<Item> findAll(Pageable pageable) {
        return itemService.buscarItens(pageable);
    }
}
