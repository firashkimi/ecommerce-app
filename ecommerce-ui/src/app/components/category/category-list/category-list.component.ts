import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/common/category';
import { CategoryService } from 'src/app/services/category.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit {

  categories: Category[] = []

  constructor(private categoryService: CategoryService){}

  ngOnInit(): void {
    this.listCategories()
  }

  listCategories(){
    this.categoryService.getCategories().subscribe(
      data => {
        this.categories = data
      }
    )
  }

  deleteCategory(id: number){
    Swal.fire({
      title: "Estas seguro de Eliminar",
      text: "Seguro de querer realizar esta acción!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Si, eliminar!"
    }).then((result) => {
      if (result.isConfirmed) {

        this.categoryService.deleteCategory(id).subscribe(
          ()=> this.listCategories()
        )

        Swal.fire({
          title: "Registro eliminado!",
          text: "El registro fue eliminado satisfactoriamente.",
          icon: "success"
        });
      }
    });
  }

}
