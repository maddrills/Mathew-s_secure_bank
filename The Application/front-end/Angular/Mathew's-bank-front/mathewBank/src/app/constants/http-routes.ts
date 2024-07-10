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
  static readonly LOGOUT: string = this.SOCKET + '/logout';

  //employee stuff
  static readonly GET_ALL_EMPLOYEE_DATA: string =
    this.SOCKET + '/admin/get_all_employees';
  static readonly GET_EMPLOYEE_BY_ID: string =
    this.SOCKET + '/manager/get-employees-by-empId';
  static readonly GET_employee_UNDER_EMPLOYEE: string =
    this.SOCKET + '/admin/get_all_employees_under';
  static readonly GET_ALL_OFFICE_PERMISSIONS: string =
    this.SOCKET + '/manager/getAllAuthNames';
  static readonly POST_AN_EMPLOYEE: string =
    this.SOCKET + '/admin/add_an_employee';
}
