import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Category } from 'src/app/common/category';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-category-add',
  templateUrl: './category-add.component.html',
  styleUrls: ['./category-add.component.css']
})
export class CategoryAddComponent implements OnInit {

  categoryForm: FormGroup

  constructor(private categoryService: CategoryService, 
              private toastr:ToastrService, 
              private fb: FormBuilder,
              private router: Router,
              private activatedRoute: ActivatedRoute
              ){
                this.categoryForm = this.fb.group({
                  id: [0],
                  name: ['', Validators.required]
                });
  }

  ngOnInit(): void {
    this.getCategoryById()
  }
    
  onSubmit(){
    const category = new Category(
      this.categoryForm.value.id,
      this.categoryForm.value.name
    )

    this.categoryService.saveCategory(category).subscribe(
      response=> {
        if(category.id === 0) {
          this.toastr.success("Catgoría agregada exitosamente!","Categorías")
        } else {
          this.toastr.success("Catgoría actualizada exitosamente!","Categorías")
        }

        this.router.navigate(['admin/categories']);
      }, error => {
        console.error('Error agregando categoría:', error);
      }
    )
  }

  getCategoryById() {
    this.activatedRoute.params.subscribe(
      item=> {
        let id = item['id']
        if(id){
          this.categoryService.getCategoryById(id).subscribe(
            data=>{
              this.categoryForm.patchValue({
                id: data.id,
                name: data.name
              })
            }
          )
        }
      }
    )
  }

}
