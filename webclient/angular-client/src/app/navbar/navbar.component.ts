import { Component, OnInit } from '@angular/core';
import { AuthService } from '../keycloak/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
  providers: [AuthService]
})
export class NavbarComponent implements OnInit {

  userProfile: any;

  logout() : void{
    this.authService.logout();
  }

  login() : void{
    AuthService.auth.keycloak.login();
  }

  hasRole(role:String) : boolean{
    return this.authService.hasRole(role);
  }

  constructor(private authService: AuthService) {
  }

  ngOnInit() {
    if(!AuthService.auth){
      return;
    }
    this.authService.getUserProfile().then(user => {
      this.userProfile = user;
    });
  }

}
