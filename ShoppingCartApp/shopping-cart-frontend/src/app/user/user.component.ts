import { Component, OnInit } from '@angular/core';
import { User } from '../models/user.model';
import { UserService } from './user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit{
  user: User | undefined;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.loadUserProfile();
  }

  loadUserProfile(): void {
    const username = 'exampleUser';

    this.userService.getUserByUsername(username).subscribe((data) => (this.user = data));
  }
}
