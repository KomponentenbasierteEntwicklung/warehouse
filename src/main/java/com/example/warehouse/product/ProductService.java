package com.example.warehouse.product;

import com.example.warehouse.dlc.Dlc;
import com.example.warehouse.dlc.DlcRepository;
import com.example.warehouse.game.Game;
import com.example.warehouse.game.GameRepository;
import com.example.warehouse.helper.CSVHelper;
import com.example.warehouse.helper.ProductAsString;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final GameRepository gameRepository;
    private final DlcRepository dlcRepository;



    @Autowired
    public ProductService(ProductRepository productRepository, GameRepository gameRepository, DlcRepository dlcRepository ) {
        this.productRepository = productRepository;
        this.gameRepository = gameRepository;
        this.dlcRepository = dlcRepository;
    }

    public List<Product> getProducts() throws MalformedURLException {
        return productRepository.findAll();
    }

    public void addNewProduct(Product product) {
        productRepository.save(product);
    }

    public void importProductsFromCSV(MultipartFile file) {

        try {
            List<Product> products = new ArrayList<Product>();

            List<ProductAsString> productAsStrings = CSVHelper.csvToProducts(file.getInputStream());
            for (ProductAsString productAsString : productAsStrings) {
                Optional<Game> optionalGame = gameRepository.findGameByName(productAsString.getGameName());
                if (optionalGame == null) continue;
                List<Dlc> dlcs = new ArrayList<Dlc>();
                for (String dlc : productAsString.getDlcs()) {
                    Optional<Dlc> optionalDlc = dlcRepository.findDlcByName(dlc);
                    if (optionalGame.isPresent()) dlcs.add(optionalDlc.get());
                }
                products.add(new Product(optionalGame.get(), dlcs));
            }

                productRepository.saveAll(products);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public void deleteProduct(Long productId) {
        boolean exists = productRepository.existsById(productId);
        if(!exists){
            throw new IllegalStateException("Product with Id " + productId + " doesn't exist");
        }
        productRepository.deleteById(productId);
    }

    @Transactional
    public void updateProduct(Long productId, String name, Game game, Dlc[] dlcs) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("game with id " + productId + " does not exist"));

        if(name != null && name.length() > 0){
            product.setName(name);
        }

        if(game != null && !Objects.equals(product.getGame(), game)){
            product.setGame(game);
        }

        if(dlcs != null && dlcs.length > 0 && !Objects.equals(product.getDlcs(), dlcs)){
            product.setDlcs(dlcs);
        }
    }

    public Product getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("product with id " + productId + " does not exist"));
        return product;
    }
}
