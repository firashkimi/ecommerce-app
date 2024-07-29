import { AfterViewInit, Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';

@Component({
  selector: 'app-header-user',
  templateUrl: './header-user.component.html',
  styleUrls: ['./header-user.component.css']
})
export class HeaderUserComponent implements OnInit {

  count: number = 0;
  public active: boolean= false;
  public logged: boolean= false;
  public usuario: string= "";

  constructor(private cartService: CartService, private sessionStorage: SessionStorageService) {}

  ngOnInit(): void {
    this.isActive();
    this. isUserLogged();
    this.cartService.currentList.subscribe(list => {
      this.count = 0
      list.forEach(
        item => {

          this.count += item.quantity
          console.log("item count ", this.count)
        }
      )

    })
  }

  isActive(){
    let type: string= this.sessionStorage.getItem('userData')?.type;
    console.log("token {}", type)
    if(type === null || type === undefined || type==='USER'){
      this.active=true;
    }
  }

  isUserLogged(){
    if(this.sessionStorage.getItem('userData')!==null){
      let type: string= this.sessionStorage.getItem('userData')?.type;
      if(type==='USER'){
        this.logged=true;
        if(this.sessionStorage.getItem('userData')?.nombre.length > 0){
          this.usuario=this.sessionStorage.getItem('userData')?.nombre;
        } else {
          this.usuario="Usuario";
        }

      }
    }

  }
}
