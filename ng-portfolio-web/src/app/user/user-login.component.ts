import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../service/authentication.service";
import {NotificationComponent} from "../notification/notification.component";

@Component({
  selector: 'bs-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css'],
  providers: [AuthenticationService, NotificationComponent]
})
export class UserLoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private notification: NotificationComponent) {
  }

  get f() {
    return this.loginForm.controls;
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.authenticationService.logout();

    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  createUser() {
    this.router.navigate(["create-user"]);
  }

  onSubmit() {
    this.submitted = true;

    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.authenticationService.login(this.f.username.value, this.f.username.value)
      .subscribe(
        data => {
          console.log("Login...");
          //this.router.navigate([this.returnUrl]);
          this.router.navigate([""]);
        },
        error => {
          const currentUser = localStorage.getItem('currentUser');
          if (currentUser) {
            localStorage.removeItem('currentUser');
          }
          this.notification.error(error);
          this.loading = false;
        });
  }
}
