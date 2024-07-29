import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ItemCart } from 'src/app/common/item-cart';
import { Product } from 'src/app/common/product';
import { CartService } from 'src/app/services/cart.service';
import { HomeService } from 'src/app/services/home.service';
import { ProductService } from 'src/app/services/product.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';

@Component({
  selector: 'app-detail-product',
  templateUrl: './detail-product.component.html',
  styleUrls: ['./detail-product.component.css']
})
export class DetailProductComponent implements OnInit {

  cartForm: FormGroup;
  userType: string=""

  constructor(private homeService: HomeService,
              private cartService: CartService,
              private activatedRoute: ActivatedRoute,
              private fb: FormBuilder,
              private toastr: ToastrService,
              private sesionStorage: SessionStorageService){
    this.cartForm = this.fb.group({
      id: [0],
      name: ['', Validators.required],
      code: ['', Validators.required],
      description: [''],
      urlImage: [''],
      price: [0, [Validators.required, Validators.min(0)]],
      quantity: [1, [Validators.required, Validators.min(0)]]
      //userId: ['', Validators.required],
      //categoryId: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.getProductById();
    this.userType= this.sesionStorage.getItem('userData').type;
  }

  getProductById(){
    this.activatedRoute.params.subscribe(
      data => {
        let id = data['id']
        if(id){
          this.homeService.getProductById(id).subscribe(
            response => {
              this.cartForm.patchValue({
                id: response.id,
                name: response.name,
                code: response.code,
                description: response.description,
                urlImage: response.urlImage,
                price: response.price
              })
            }
          )
        }
      }
    )
  }

  addCart(id: number) {
    let item= new ItemCart(
      this.cartForm.value.id,
      this.cartForm.value.name,
      this.cartForm.value.quantity,
      this.cartForm.value.price
      )

      this.cartService.addItemCart(item);

      console.log("Total Panier " + this.cartService.totalCart())

      this.toastr.success("Produit ajouté au panier!", "Panier d'achat");
  }


}
