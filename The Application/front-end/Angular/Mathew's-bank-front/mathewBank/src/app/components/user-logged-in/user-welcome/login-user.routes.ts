import { Route } from '@angular/router';
import { EditUserDetailsComponent } from '../edit-user-details/edit-user-details.component';
import { UserWelcomeComponent } from './user-welcome.component';
import { AuthGuardAdminService } from '../../../service/guards/authAdmin.guard';
export const LOGIN_ROUTER: Route[] = [
  {
    path: '',
    component: UserWelcomeComponent,
  },
  {
    path: 'details',
    component: EditUserDetailsComponent,
  },
];
