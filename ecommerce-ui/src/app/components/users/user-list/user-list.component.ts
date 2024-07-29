import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/common/user';
import { SessionStorageService } from 'src/app/services/session-storage.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit{

   users: User[] = []
   logged: number =0
   updatable: boolean= false;

  constructor(private userService: UserService, private sesionStorage: SessionStorageService){}

  ngOnInit(): void {
    this.listCategories()
    this.logged= this.sesionStorage.getItem('userData').id;
  }

  listCategories(){
    this.userService.getUsers().subscribe(
      data => {
        this.users = data
      }
    )
  }
}
