import { Routes } from '@angular/router';
import { WelcomePageComponent } from './components/welcome-page/welcome-page.component';
import { ApplyForAccountComponent } from './components/apply-for-account/apply-for-account.component';
import { LogInPageComponent } from './components/log-in-page/log-in-page.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'welcome',
    pathMatch: 'full',
  },
  {
    path: 'welcome',
    component: WelcomePageComponent,
  },
  {
    path: 'log-in',
    component: LogInPageComponent,
  },
  {
    path: 'application',
    component: ApplyForAccountComponent,
  },
];
