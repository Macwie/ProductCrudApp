import { useEffect, useState } from "react";
import ProductService from "./product/ProductService";
import ProductTable from "./product/ProductTable";
import { ApiError } from "./error";
import { Product } from "./product/product";

function App() {
  const [products, setProducts] = useState<Product[]>([]);
  const [errors, setErrors] = useState<ApiError>();

  useEffect(() => {
    getProducts();
  }, []);

  function getProducts() {
    ProductService.getAllProducts().then((data) => setProducts(data));
  }

  function deleteProduct(productId: number) {
    ProductService.deleteProduct(productId).then(() => getProducts());
  }

  function addProduct(product: Product) {
    ProductService.addProduct(product)
      .then(() => {
        getProducts();
        clearErrors();
      })
      .catch((errors) => setErrors(errors.response.data));
  }

  function clearErrors() {
    setErrors(undefined);
  }

  return (
    <>
      <ProductTable products={products} onDelete={deleteProduct} onAdd={addProduct} errors={errors} clearErrors={clearErrors} />
    </>
  );
}

export default App;
