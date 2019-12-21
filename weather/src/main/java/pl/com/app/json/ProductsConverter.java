package pl.com.app.json;

import pl.com.app.dto.ProductDto;

import java.util.List;

public class ProductsConverter extends JsonConverter<List<ProductDto>> {
    public ProductsConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
