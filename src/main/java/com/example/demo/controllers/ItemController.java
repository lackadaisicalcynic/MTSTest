package com.example.demo.controllers;

import com.example.demo.FormResponds.ItemData;
import com.example.demo.models.ItemProfile;
import com.example.demo.repositories.ItemRepository;
import com.example.demo.security.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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

    //@PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/add")
    public void add(@RequestBody ItemData item, @AuthenticationPrincipal UserPrincipal user){

        ItemProfile newItem = new ItemProfile();
        newItem.setName(item.getName());
        newItem.setCategory(item.getCategory());
        newItem.setPrice(item.getPrice());
        newItem.setDescription(item.getDescription());
        newItem.setAmount(item.getAmount());
        itemRepository.save(newItem);

    }
    @PatchMapping("/update/{name}")
    public void update(@PathVariable String name, @RequestBody ItemData item){
        ItemProfile updatedItem = itemRepository.findItemProfileByName(name);
        if (updatedItem == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no item with this name");
        }
        if (item.getName() != null){
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
    }
    @Transactional
    @DeleteMapping("delete/{name}")
    public void delete(@PathVariable String name){
        if (itemRepository.findItemProfileByName(name) == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no item with this name");
        }
        else{
            itemRepository.deleteItemProfileByName(name);
        }
    }

    @GetMapping("/category/{category}")
    public List<ItemProfile> category(@PathVariable String category){

        return itemRepository.findAllByCategory(category);

    }
}
