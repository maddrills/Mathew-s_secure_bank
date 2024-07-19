import { Injectable, inject } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivateFn,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable, map } from 'rxjs';
import { EmployeeService } from '../employee-post-login.service';
import { UnRegService } from '../unRegService';
import { EmployeePermissionService } from '../employee-permission.service';

//check app.router.ts for implementation
@Injectable({ providedIn: 'root' })
export class AuthGuardAdminService {
  //   static employeeLoginAuthGuardFn: CanActivateFn = (
  //     route: ActivatedRouteSnapshot,
  //     state: RouterStateSnapshot
  //   ): Observable<boolean | UrlTree> => {
  //     const router = inject(Router);
  //     const authService = inject(EmployeeService);
  //     const employeeLoggedIn = inject(UnRegService);
  //     const permissionsChecker = inject(EmployeePermissionService);
  //     return employeeLoggedIn.employeeIsLoggedIn.pipe(
  //       map((employee) => {
  //         if (employee) return true;
  //         //if not logged in send back to login page
  //         return router.createUrlTree(['/welcome']);
  //       })
  //     );
  //   };

  //admin
  static checkAdmin: CanActivateFn = (
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean | UrlTree> => {
    //injections
    const router = inject(Router);
    const permissionsChecker = inject(EmployeePermissionService);

    return permissionsChecker.isAdminSub.pipe(
      map((employee) => {
        if (employee) {
          return true;
        }
        //if not logged in send back to login page
        return router.createUrlTree(['/welcome']);
      })
    );
  };

  //manager
  static checkManager: CanActivateFn = (
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean | UrlTree> => {
    //injections
    const router = inject(Router);
    const permissionsChecker = inject(EmployeePermissionService);

    return permissionsChecker.isManagerSub.pipe(
      map((employee) => {
        if (employee) {
          return true;
        }
        //if not logged in send back to login page
        return router.createUrlTree(['/welcome']);
      })
    );
  };

  //clerk
  static checkClerk: CanActivateFn = (
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean | UrlTree> => {
    //injections
    const router = inject(Router);
    const permissionsChecker = inject(EmployeePermissionService);

    return permissionsChecker.isClerkSub.pipe(
      map((employee) => {
        if (employee) {
          return true;
        }
        //if not logged in send back to login page
        return router.createUrlTree(['/welcome']);
      })
    );
  };
}
