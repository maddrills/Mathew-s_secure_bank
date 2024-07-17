export class ApplicationHttpRoutes {
  private static readonly PROTOCOL: string = 'http';
  private static readonly BACKEND_SERVER: string = 'localhost';
  private static readonly PORT_NUMBER: string = '8080';
  private static readonly SOCKET: string =
    this.PROTOCOL + '://' + this.BACKEND_SERVER + ':' + this.PORT_NUMBER;

  //un reg routes
  static readonly GET_COUNTRY_S: string =
    this.SOCKET + '/exposed/getAllCountriesThatHaveBranches';
  static readonly GET_STATE_S: string =
    this.SOCKET + '/exposed/getAllStateBranchesInCountry';
  static readonly GET_STATE_AND_COUNTRY: string =
    this.SOCKET + '/exposed/getBranchByCountryAndState';
  static readonly REGISTER_USER: string =
    this.SOCKET + '/exposed/applyForAccount';

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
  static readonly FIND_EMPLOYEE_BY_ID: string =
    this.SOCKET + '/manager/get-employees-by-empId';
  static readonly GET_ALL_EMPLOYEE_BY_UNDER_BRANCH: string =
    this.SOCKET + '/manager/get-employees-by-branch';
  static readonly GET_ALL_APPLICATIONS: string =
    this.SOCKET + '/employee/getAllApplications';
  static readonly CHANGE_EMPLOYEE_PERMISSION_SET: string =
    this.SOCKET + '/admin/update-employee-permission-set';
  static readonly CHANGE_APPLICATION_ASSIGNED_TO: string =
    this.SOCKET + '/manager/assign-application-to';

  //admin exclusive
  static readonly REMOVE_MANAGER_FROM_BRANCH: string =
    this.SOCKET + '/admin/remove-manager-from-branch';
  static readonly PUT_MANAGER_IN_BANK: string =
    this.SOCKET + '/admin/manager_to_branch';
  static readonly REMOVE_EMPLOYEE_FROM_BRANCH_Admin: string =
    this.SOCKET + '/admin/remove-clerk-from-bank-by-admin';
  static readonly PUT_CLERK_IN_BANK_ADMIN: string =
    this.SOCKET + '/admin/add-clerk-to-any-branch';
  static readonly GET_BRANCH_BY_BRANCH_ID: string =
    this.SOCKET + '/admin/branches_with_manager';
  static readonly GET_APPLICATIONS_ASSIGNED_TO_ME: string =
    this.SOCKET + '/employee/getApplicationsAssignedToMe';
  static readonly GET_APPLICATIONS_ASSIGNED_To_SOMEONE: string =
    this.SOCKET + '/manager/getApplicationsUnderAnEmployee';
  static readonly GET_BANK_APPLICATIONS_BRANCH_BY_ID: string =
    this.SOCKET + '/employee/get-applications-under-branch';

  //Branch Management
  static readonly LIST_ALL_BRANCHES: string =
    this.SOCKET + '/admin/list_all_branches_with_manager';
  static readonly ADD_A_BRANCH_WITH_OR_WITHOUT_A_MANAGER: string =
    this.SOCKET + '/admin/create_a_branch';
}
