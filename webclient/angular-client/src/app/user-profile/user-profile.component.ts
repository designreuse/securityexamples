import { Component, OnInit } from '@angular/core';
import { AuthService } from '../keycloak/auth.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.less']
})
export class UserProfileComponent implements OnInit {

  userProfile: any;

  constructor(private authService: AuthService) {}
  
  ngOnInit() {
    if(!AuthService.auth){
      return;
    }
    this.authService.getUserProfile().then(user => {
      this.userProfile = user;
      console.log(user);
    });
  }

}
