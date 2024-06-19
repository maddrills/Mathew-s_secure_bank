export class ApplicationHttpRoutes {
  private static PROTOCOL: string = 'http://';
  private static BACKEND_SERVER: string = 'localhost:8080';

  // log in route routes employee and user
  static LOGIN_ROUTE_EMPLOYEE: string =
    this.PROTOCOL + this.BACKEND_SERVER + '/employee/employee-login';

  static LOGIN_ROUTE_USER: string =
    this.PROTOCOL + this.BACKEND_SERVER + '/bankUser/login';
}
