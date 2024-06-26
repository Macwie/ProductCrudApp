import axios from "axios";
import { Product } from "./product";

const apiClient = axios.create({
  baseURL: "http://localhost:8080",
  timeout: 1000,
});

class ProductService {
  async getAllProducts(): Promise<Product[]> {
    const response = await apiClient.get("/products");
    return response.data;
  }

  async getProductById(productId: number): Promise<Product> {
    const response = await apiClient.get("/products/" + productId);
    return response.data;
  }

  async addProduct(product: Omit<Product, "id">): Promise<Product> {
    const response = await apiClient.post("/products", product);
    return response.data;
  }

  async deleteProduct(productId: number): Promise<void> {
    await apiClient.delete("/products/" + productId);
  }
}

export default new ProductService();
