package jpa.study_ex01.service;

import jpa.study_ex01.domain.Item;
import jpa.study_ex01.item.Book;
import jpa.study_ex01.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private  final ItemRepository repository;

    @Transactional(readOnly = false)
    public void saveItem(Item item){
        repository.save(item);
    }

    public List<Item> findItems(){
        return repository.findAll();
    }

    public Item findOne(Long id){
        return repository.findOne(id);
    }

    @Transactional
    public void updateItem(Long itemId, Book param) {
        Item item = repository.findOne(itemId);
        item.setPrice(param.getPrice());
        item.setName(param.getName());
        item.setStockQuantity(param.getStockQuantity());
    }
}
