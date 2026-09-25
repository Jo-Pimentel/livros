package com.example.livros.service;

import com.example.livros.model.Item;
import com.example.livros.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> buscarItens() {
        return itemRepository.findAll();
    }
}
