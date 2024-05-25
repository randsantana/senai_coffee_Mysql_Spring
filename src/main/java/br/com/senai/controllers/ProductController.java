package br.com.senai.controllers;


import br.com.senai.models.Product;
import br.com.senai.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/product")//  product/createProduct
public class ProductController{
    @Autowired
    ProductRepository productRepository;

    @GetMapping(value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    @PostMapping(value="/createProduct",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product createProduct(@RequestBody Product product){
        //Cria um novo objeto Product
        Product newProduct = new Product();
        //Seta as propriedades do Product
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setDescription(product.getDescription());
        //Chama o método save para salvar o objeto no banco de dados
        return productRepository.save(newProduct);
    }

    @PutMapping(value="/updateProduct",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@RequestBody Product product){
        Product getProduct = productRepository
                .findById(product.getId()).orElseThrow();
        Product updateProduct = new Product();

        updateProduct.setId(product.getId());
        updateProduct.setName(product.getName());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setDescription(product.getDescription());

        return productRepository.save(updateProduct);
    }
    //Método deletar product
    @DeleteMapping(value="/deleteProduct/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    //@PathVariable pega um valor passado junto a barra de endereço
    public Product deleteProduct(@PathVariable Long id){
        //Verificamos se existe o produto no banco de dados procurando o id
        Product getProduct = productRepository.findById(id).orElseThrow();
        //chamamos o método .delete e passamos o produto a ser deletado
        productRepository.delete(getProduct);
        return getProduct;
    }
}