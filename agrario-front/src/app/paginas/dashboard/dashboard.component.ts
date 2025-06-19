import { Component, OnInit } from '@angular/core';
//import { JwtHelperService } from '@auth0/angular-jwt';
import { environment } from '../../../environments/environment.development';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {
  username: string="Bienvenido";
  ngOnInit(): void {

  }
 }

