package com.icebear2n2.goodplace.store.controller;

import com.icebear2n2.goodplace.domain.dto.StoreDto;
import com.icebear2n2.goodplace.domain.request.StoreRequest;
import com.icebear2n2.goodplace.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;

    @PostMapping
    public StoreDto insertStore(@RequestBody StoreRequest storeRequest) {
        return storeService.insertStore(storeRequest);
    }

    @GetMapping("/all")
    public Page<StoreDto> getAll(
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {

        PageRequest pageRequest = PageRequest.of(size, page);
        return storeService.getAll(pageRequest);
    }

    @PutMapping("/{storeId}")
    public StoreDto updateStore(@PathVariable Long storeId, @RequestBody StoreRequest storeRequest) {
        return storeService.updateStore(storeId, storeRequest);
    }

    @DeleteMapping("/{storeId}")
    public void deleteStore(@PathVariable Long storeId) { storeService.deleteStore(storeId); }
}
