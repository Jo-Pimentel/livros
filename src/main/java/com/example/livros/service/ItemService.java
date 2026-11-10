package com.example.livros.service;

import com.example.livros.model.Item;
import com.example.livros.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    /*private List<String> tiposItens = itemRepository.buscarTipoItens();*/

    public Page<Item> buscarItens(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }
}
