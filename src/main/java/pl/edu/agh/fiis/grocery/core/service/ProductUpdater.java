package pl.edu.agh.fiis.grocery.core.service;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import pl.edu.agh.fiis.grocery.core.data.Product;

@Component
public class ProductUpdater extends Product {
	
	public void update(Product productToUpdate, Product productWithNewParameters) {
		
		updateSingleField(productToUpdate::setCategory, productWithNewParameters::getCategory);
		updateSingleField(productToUpdate::setDescription, productWithNewParameters::getDescription);
		updateSingleField(productToUpdate::setName, productWithNewParameters::getName);

	}
	
	private void updateSingleField(Consumer<String> existingProductSetter, Supplier<String> newProductGetter) {
		String newValue = newProductGetter.get();
		if("<CLEAR>".equals(newValue)) {
			existingProductSetter.accept("");
		} else if (!StringUtils.isEmpty(newValue)) {
			existingProductSetter.accept(newValue);
		}
	}

}
