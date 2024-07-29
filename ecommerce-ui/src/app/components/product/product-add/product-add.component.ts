import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Category } from 'src/app/common/category';
import { Product } from 'src/app/common/product';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-product-add',
  templateUrl: './product-add.component.html',
  styleUrls: ['./product-add.component.css']
})
export class ProductAddComponent implements OnInit {

  productForm: FormGroup;
  selectedFile: File | null = null;
  categories: Category[] = []
  user: string= "";

  constructor(private fb: FormBuilder,
              private productService: ProductService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private toastr: ToastrService,
              private categoryService:CategoryService,
              private sesionStorage: SessionStorageService){
                this.productForm = this.fb.group({
                  id: [0],
                  name: ['', Validators.required],
                  code: ['', Validators.required],
                  description: ['', Validators.required],
                  urlImage: ['', Validators.required],
                  price: [0, [Validators.required, Validators.min(0)]],
                  userId: ['', Validators.required],
                  categoryId: [''],
                });
  }

  ngOnInit(): void {
    this.getProductById();
    this.getCategoryList();
    this.user= this.sesionStorage.getItem('userData').id;
    this.productForm.patchValue({
      userId: this.user.toString(),
    })
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }


  onSubmit() {
    //this.productForm.valid && this.selectedFile
    if (true) {
      const product = new Product(
        this.productForm.value.id,
        this.productForm.value.name,
        this.productForm.value.code,
        this.productForm.value.description,
        this.productForm.value.urlImage,
        this.selectedFile,
        this.productForm.value.price,
        this.productForm.value.userId,
        this.productForm.value.categoryId
      );

      this.productService.createProduct(product).subscribe(response => {
        if(product.id === 0) {
          this.toastr.success("Producto agregado exitosamente!","Productos")
        } else {
          this.toastr.success("Producto actualizado exitosamente!","Productos")
        }

        this.router.navigate(['admin/products']);
      }, error => {
        console.error('Error adding product:', error);
      });
    }
  }

  getProductById() {
    this.activatedRoute.params.subscribe(
      prod => {
        let id = prod['id'];
        if(id){
          console.log("ID a editar " + id)
          this.productService.getProductById(id).subscribe(
            data => {
              this.productForm.patchValue({
                id: data.id,
                name: data.name,
                code: data.code,
                description: data.description,
                urlImage: data.urlImage,
                price: data.price,
                userId: data.userId,
                categoryId: data.categoryId
              });
            }
          )
        }
      }
    );
  }

  habilitarCambiarImagen() {
    Swal.fire({
      title: "Estas seguro de realizar esta acción",
      text: "Seguro que quieres cambiar la imagen!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Si, proseguir!"
    }).then((result) => {
      if (result.isConfirmed) {

        this.productForm.get('urlImage')?.setValue("");

      }
    });
  }

  getCategoryList(){
    this.categoryService.getCategories().subscribe(
      data => this.categories = data
    )
  }
}
