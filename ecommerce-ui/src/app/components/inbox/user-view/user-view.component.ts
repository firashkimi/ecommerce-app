import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UserType } from 'src/app/common/user-type';
import { UserProfile } from 'src/app/common/userprofile';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { SessionStorageService } from 'src/app/services/session-storage.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-view',
  templateUrl: './user-view.component.html',
  styleUrls: ['./user-view.component.css']
})
export class UserViewComponent {

  public registerForm: FormGroup;
  public typeUSER: UserType = UserType.USER;
  public typeADMIN: UserType = UserType.ADMIN;
  userId: number=0;

  constructor(private fb: FormBuilder,
    private authenticationService: AuthenticationService,
    private sesionStorage: SessionStorageService,
    private userService: UserService,
    private router: Router,
    private toastr: ToastrService,
    private activatedRoute: ActivatedRoute)
  {
    this.userId=this.sesionStorage.getItem('userData').id;
    this.registerForm = this.fb.group({
      id: [0],
      username: ['', Validators.required],
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      email: ['', Validators.required],
      address: ['', Validators.required],
      cellphone: ['', Validators.required],
      userType: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.getUserById();
  }

  onSubmit() {
    const user = new UserProfile(
      this.registerForm.value.id,
      this.registerForm.value.email,
      this.registerForm.value.firstname,
      this.registerForm.value.lastname,
      this.registerForm.value.email,
      this.registerForm.value.address,
      this.registerForm.value.cellphone,
      this.registerForm.value.userType
    );
      console.log(user)
    this.authenticationService.updateprofile(user).subscribe({
      next: (response) => {
        this.toastr.success("Enregistrement de l’utilisateur réussi!","Succées")

        this.router.navigate(['/admin/users']);
      },
      error: error => console.error('Erreur lors de l’enregistrement d’un utilisateur ', error)
    })
}


getUserById() {
      if(this.userId!=0){
        this.userService.getUserById(this.userId).subscribe(
          data => {
            this.registerForm.patchValue({
              id: data.id,
              username: data.email,
              firstname: data.firstname,
              lastname:data.lastname,
              email:data.email,
              address:data.address,
              cellphone:data.cellphone,
              userType:data.userType
            });
          }
        )
      }
}

}
