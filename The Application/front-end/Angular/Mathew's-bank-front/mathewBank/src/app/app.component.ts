import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { WelcomePageComponent } from './components/welcome-page/welcome-page.component';
import { ApplyForAccountComponent } from './components/apply-for-account/apply-for-account.component';
import { LogInPageComponent } from './components/log-in-page/log-in-page.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, WelcomePageComponent, ApplyForAccountComponent,LogInPageComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'mathewBank';
}
