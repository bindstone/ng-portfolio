import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class AuthenticationService {
  constructor(private http: HttpClient) {
    this.logout();
  }

  login(username: string, password: string) {

    // TODO: Don't store username and password in local Storage.
    // Replace by JWT: https://jwt.io/ and store the token.
    let hash = btoa(username + ':' + password);
    localStorage.setItem('currentUser', hash);

    return this.http.get<any>(`api/user/${username}`);
  }

  logout() {
    localStorage.removeItem('currentUser');
  }
}
