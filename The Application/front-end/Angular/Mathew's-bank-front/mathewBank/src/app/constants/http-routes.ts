export class ApplicationHttpRoutes {
  private static PROTOCOL: string = 'http';
  private static BACKEND_SERVER: string = 'localhost';
  private static PORT_NUMBER: string = '8080';
  private static SOCKET: string =
    this.PROTOCOL + '://' + this.BACKEND_SERVER + ':' + this.PORT_NUMBER;

  // log in route routes employee and user
  static LOGIN_ROUTE_EMPLOYEE: string =
    this.SOCKET + '/employee/employee-login';

  static LOGIN_ROUTE_USER: string = this.SOCKET + '/bankUser/login';
}
