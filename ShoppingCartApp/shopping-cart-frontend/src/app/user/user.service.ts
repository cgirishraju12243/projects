// user.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { UpdatePasswordRequest } from '../models/update-password-request.model';
import { AuthenticationRequest } from '../models/authentication-request';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl = 'http://localhost:8080/users';

  constructor(private http: HttpClient) {}

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl);
  }

  createUser(user: User): Observable<User> {
    return this.http.post<User>(this.apiUrl, user);
  }

  getUserByUsername(username: string): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${username}`);
  }

  updatePassword(request: UpdatePasswordRequest): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/updatePassword`, request);
  }

  authenticate(request: AuthenticationRequest): Observable<boolean> {
    return this.http.post<boolean>(`${this.apiUrl}/authenticate`, request);
  }
}
