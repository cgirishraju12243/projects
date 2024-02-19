import { Product } from "./product.model";
import { User } from "./user.model";

export interface CartItem {
    id: number;
    user: User;
    product: Product;
    quantity: number;
}