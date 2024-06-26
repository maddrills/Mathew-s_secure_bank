export class ApplicationHttpRoutes {
  private static readonly PROTOCOL: string = 'http';
  private static readonly BACKEND_SERVER: string = 'localhost';
  private static readonly PORT_NUMBER: string = '8080';
  private static readonly SOCKET: string =
    this.PROTOCOL + '://' + this.BACKEND_SERVER + ':' + this.PORT_NUMBER;

  // log in route routes employee and user
  static readonly LOGIN_ROUTE_EMPLOYEE: string =
    this.SOCKET + '/employee/employee-login';

  static readonly LOGIN_ROUTE_USER: string = this.SOCKET + '/bankUser/login';
}
