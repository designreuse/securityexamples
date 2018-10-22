import { Component, OnInit } from '@angular/core';
import { AuthService } from '../keycloak/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
  providers: [AuthService]
})
export class NavbarComponent implements OnInit {

  logout() : void{
    this.authService.logout();
  }

  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

}
