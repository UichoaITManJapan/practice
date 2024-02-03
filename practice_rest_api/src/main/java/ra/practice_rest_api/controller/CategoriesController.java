package ra.practice_rest_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.practice_rest_api.dto.request.CategoriesRequest;
import ra.practice_rest_api.dto.response.CategoriesResponse;
import ra.practice_rest_api.dto.response.CategoriesResponseActive;
import ra.practice_rest_api.dto.response.Message;
import ra.practice_rest_api.service.CategoriesService;

import java.util.List;

@RestController
@RequestMapping("ecommerce/api/v1/categories")
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;
    @GetMapping
    public ResponseEntity<List<CategoriesResponse>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "name") String orderBy
    ) {
        List<CategoriesResponse> listCatalog = categoriesService.find(page,size,direction,orderBy);
        return new ResponseEntity<>(listCatalog, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        CategoriesResponse categoriesResponse = categoriesService.findById(id);
        if (categoriesResponse == null) {
            return new ResponseEntity<>(new Message("Không tìm thấy ID"),HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(categoriesResponse,HttpStatus.OK);
        }
    }
    @PostMapping
    public ResponseEntity<CategoriesResponse> save(@RequestBody CategoriesRequest categoriesRequest) {
        CategoriesResponse categoriesResponse = categoriesService.save(categoriesRequest);
        return new ResponseEntity<>(categoriesResponse,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody CategoriesRequest categoriesRequest,
                                    @PathVariable long id) {
        CategoriesResponse categoriesResponse = categoriesService.update(categoriesRequest, id);
        if(categoriesResponse == null) {
            return new ResponseEntity<>(new Message("Không tim thấy Id"),HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(categoriesResponse,HttpStatus.OK);
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<CategoriesResponse> updateStatus(@PathVariable long id) {
        CategoriesResponse categoriesResponse = categoriesService.updateStatus(id);
        return new ResponseEntity<>(categoriesResponse,HttpStatus.OK);
    }
    @GetMapping("/combobox")
    public ResponseEntity<List<CategoriesResponseActive>> activeCategories() {
        List<CategoriesResponseActive> listCatalog = categoriesService.activeCategories();
        return new ResponseEntity<>(listCatalog,HttpStatus.OK);
    }
}
