import { Injectable } from '@angular/core';
import { resolve, reject } from 'q';

declare var Keycloak: any;

@Injectable()
export class AuthService {
  static auth: any = {};

  init(): Promise<any> {
    return new Promise((resolve, reject) => {
      const keycloakAuth = Keycloak('assets/keycloak/keycloak.json');
      console.log(keycloakAuth);
      keycloakAuth.init({ checkLoginIframe: false, onLoad: 'check-sso' }) //login-required {onLoad: 'check-sso'}
        .then(() => {
          AuthService.auth.loggedIn = keycloakAuth.authenticated;
          AuthService.auth.keycloak = keycloakAuth;
          AuthService.auth.logoutUrl = keycloakAuth.authServerUrl
            + '/realms/stiwa/protocol/openid-connect/logout?redirect_uri='
            + document.baseURI;
          resolve();
        })
        .catch(() => {
          reject();
        });
    });
  }

  getToken(): Promise<string> {
    return new Promise<string>((resolve, reject) => {
      if (AuthService.auth.keycloak.token) {
        AuthService.auth.keycloak
          .updateToken(5) //5 minites validity
          .success((refreshed) => {
            console.log('refreshed ' + refreshed);
            resolve(<string>AuthService.auth.keycloak.token);
          })
          .error(() => {
            reject('Failed to refresh token');
          });
      } else {
        AuthService.auth.keycloak.login();
        reject('Not logged in');
      }
    });
  }

  getParsedToken() {
    return AuthService.auth.keycloak.tokenParsed;
  }

  logout() {
    console.log('*** LOGOUT');
    AuthService.auth.loggedIn = false;
    AuthService.auth.authz = null;
    window.location.href = AuthService.auth.logoutUrl;
  }

  getUserProfile(): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      if (!AuthService.auth || !AuthService.auth.keycloak || !AuthService.auth.keycloak.authenticated) {
        reject();
      }
      else if (AuthService.auth.userProfile) {
        resolve(AuthService.auth.userProfile);
      }
      else {
        AuthService.auth.keycloak.loadUserProfile().success(user => {
          AuthService.auth.userProfile = user;
          resolve(AuthService.auth.userProfile);
        }).error(err =>{
          reject(err);
        });
      }
    });

  }

  hasRole(role: String): boolean {
    if (!AuthService.auth || !AuthService.auth.keycloak) {
      return false;
    }
    if (AuthService.auth.keycloak.hasRealmRole(role)) {
      return true;
    }
    return AuthService.auth.keycloak.hasResourceRole(role);
  }
}
