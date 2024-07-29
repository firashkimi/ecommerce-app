import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/common/user';
import { UserType } from 'src/app/common/user-type';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registerForm: FormGroup;

  constructor(private fb: FormBuilder,
              private authenticationService: AuthenticationService,
              private router: Router,
              private toastr: ToastrService)
  {
    this.registerForm = this.fb.group({
      id: [0],
      username: ['', Validators.required],
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      email: ['', Validators.required],
      address: ['', Validators.required],
      cellphone: ['', Validators.required],
      userType: [UserType.USER, Validators.required],
      password: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    localStorage.clear();
  }

  onSubmit() {
      const user = new User(
        this.registerForm.value.id,
        this.registerForm.value.email,
        this.registerForm.value.firstname,
        this.registerForm.value.lastname,
        this.registerForm.value.email,
        this.registerForm.value.address,
        this.registerForm.value.cellphone,
        this.registerForm.value.userType,
        this.registerForm.value.password
      );
        console.log(user)
      this.authenticationService.register(user).subscribe({
        next: (response) => {
          this.toastr.success("Enregistrement réussi!","Sign Up")

          this.router.navigate(['/user/login']);
        },
        error: error => console.error('Erreur lors de l’inscription ', error)
      })
  }

}
