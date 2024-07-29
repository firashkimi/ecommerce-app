import { Injectable } from '@angular/core';
import { ItemCart } from '../common/item-cart';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private listSource = new BehaviorSubject<ItemCart[]>([]);
  currentList = this.listSource.asObservable();

  private items: Map<number, ItemCart> =new Map<number, ItemCart>();
  itemList: ItemCart[] = [];

  constructor() { }

  updateList(newList: ItemCart[]) {
    this.listSource.next(newList);
  }

  addItemCart(itemCart: ItemCart){
    this.items.set(itemCart.productId, itemCart);
    this.updateList(this.convertToListFromMap());
  }

  deleteItemCart(productId: number){
    this.items.delete(productId);
    this.items.forEach(
      (valor, clave)=>{
        console.log(clave, valor)
      }
    )
    this.updateList(this.convertToListFromMap());
  }

  totalCart(){
    let totalCart=0;
    this.items.forEach(
      (item, clave)=> {
        totalCart+= item.getTotalPriceItem();
      }
    );
    return totalCart;
  }

  convertToListFromMap(){
    this.itemList.splice(0, this.itemList.length);

    this.items.forEach(
      (item, clave)=>{
        this.itemList.push(item);
      }
    );

    return this.itemList;
  }

  totalItems(){
    return this.items.size;
  }
}
