package com.example.demo.controllers;

import com.example.demo.FormResponds.ItemData;
import com.example.demo.models.ItemProfile;
import com.example.demo.repositories.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private ItemRepository itemRepository;
    public ItemController(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }
    private static final Logger logger = LoggerFactory.getLogger(SignUpController.class);


    //@PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/add")
    public ItemProfile add(@RequestBody ItemData item){
        logger.info("Adding new item: " + item.getName());
        ItemProfile newItem = new ItemProfile();
        if (itemRepository.existsByName(item.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item with this name exists");
        }
        newItem.setName(item.getName());
        newItem.setCategory(item.getCategory());
        newItem.setPrice(item.getPrice());
        newItem.setDescription(item.getDescription());
        newItem.setAmount(item.getAmount());
        itemRepository.save(newItem);
        return newItem;

    }
    @PatchMapping("/update/{name}")
    public ItemProfile update(@PathVariable String name, @RequestBody ItemData item){
        logger.info("Updating item: " + item.getName());
        ItemProfile updatedItem = itemRepository.findItemProfileByName(name);
        if (updatedItem == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no item with this name");
        }
        if (item.getName() != null){
            if (itemRepository.existsByName(item.getName())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This name is already used");
            }
            updatedItem.setName(item.getName());
        }
        if (item.getCategory() != null){
            updatedItem.setCategory(item.getCategory());
        }
        if (item.getDescription() != null){
            updatedItem.setCategory(item.getCategory());
        }
        if (item.getPrice() != 0){
            updatedItem.setPrice(item.getPrice());
        }
        if (item.getAmount() != 0){
            updatedItem.setAmount(item.getAmount());
        }
        itemRepository.save(updatedItem);
        return updatedItem;
    }
    @Transactional
    @DeleteMapping("delete/{name}")
    public void delete(@PathVariable String name){
        logger.info("Deleting item: " + name);
        if (itemRepository.findItemProfileByName(name) == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no item with this name");
        }
        else{
            itemRepository.deleteItemProfileByName(name);
        }
    }

    @GetMapping("/category/{category}")
    public List<ItemProfile> category(@PathVariable String category){
        logger.info("Requesting all items in by category: \"" + category +"\"");
        return itemRepository.findAllByCategory(category);

    }
}
