import { Routes } from '@angular/router';
import { WelcomePageComponent } from './components/welcome-page/welcome-page.component';
import { ApplyForAccountComponent } from './components/apply-for-account/apply-for-account.component';
import { LogInPageComponent } from './components/log-in-page/log-in-page.component';
import { UserWelcomeComponent } from './components/user-logged-in/user-welcome/user-welcome.component';
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
  {
    path: 'user-welcome',
    //user logged in ... lazy load everything else that follows
    loadChildren: () =>
      import('./components/user-logged-in/user-welcome/login-user.routes').then(
        (mode) => mode.LOGIN_ROUTER
      ),
  },
  {
    path: 'employee-welcome',
    //employee logged in ... lazy load everything else that follows
    loadChildren: () =>
      import('./components/employee-logged-in/emmployee-logedin.routes').then(
        (mode) => mode.EMPLOYEE_LOGGED_IN
      ),
  },
  {
    path: '**',
    component: WelcomePageComponent,
  },
];
