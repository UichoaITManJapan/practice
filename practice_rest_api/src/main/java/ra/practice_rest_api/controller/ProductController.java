package ra.practice_rest_api.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.practice_rest_api.dto.request.ProductRequest;
import ra.practice_rest_api.dto.response.Message;
import ra.practice_rest_api.dto.response.ProductResponse;
import ra.practice_rest_api.model.Product;
import ra.practice_rest_api.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("ecommerce/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;
//    @GetMapping
//    public ResponseEntity<List<ProductResponse>> findAll(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "5") int size,
//            @RequestParam(defaultValue = "ASC") String direction,
//            @RequestParam(defaultValue = "name") String orderBy
////            @RequestParam(defaultValue = "name") String searchBy
//    ) {
//        List<ProductResponse> listProduct = productService.findAll(page, size, direction, orderBy);
//        return new ResponseEntity<>(listProduct, HttpStatus.OK);
//    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(defaultValue = "name") String orderBy,
            @RequestParam(defaultValue = "name") String searchBy
    ) {
        List<ProductResponse> listProduct = productService.findAll(page, size, direction, orderBy,searchBy);
        return new ResponseEntity<>(listProduct, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        ProductResponse productResponse = productService.findById(id);
        if (productResponse == null) {
            return new ResponseEntity<>(new Message("Không tìm thấy mã sản phẩm"),HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productResponse,HttpStatus.OK);
        }
    }
    @PostMapping
    public ResponseEntity<ProductResponse> save(@RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.save(productRequest);
        return new ResponseEntity<>(productResponse,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ProductRequest productRequest,
                                    @PathVariable String id) {
        ProductResponse productResponse = productService.update(productRequest,id);
        if (productResponse == null) {
            return new ResponseEntity<>(new Message("Không tìm thấy mã sản phẩm"),HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productResponse,HttpStatus.OK);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable String id) {
        boolean result = productService.delete(id);
        if (result) {
            return new ResponseEntity<>(new Message("Xoá thành công"),HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(new Message("Không tìm thấy mã sản phẩm"),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/search/{input}")
    public ResponseEntity<List<ProductResponse>> searchByName(@PathVariable String input) {
        List<ProductResponse> listProduct = productService.searchByName(input);
        return new ResponseEntity<>(listProduct,HttpStatus.OK);
    }
}
