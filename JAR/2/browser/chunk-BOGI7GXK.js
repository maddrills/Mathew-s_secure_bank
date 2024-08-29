import{A as W,B as i,C as u,D as b,E as V,F as E,K as D,N as L,O as k,P as Z,S as U,U as z,W as O,X as B,a as H,ca as $,d as G,da as J,e as j,ea as q,fa as Y,g as N,ga as K,ha as Q,i as h,ia as X,j as v,k as p,ka as tt,l as S,m as l,n as M,o as _,p as A,q as f,r as w,s as y,t as T,u as e,v as t,w as d,x,y as g,z as m}from"./chunk-S5BTXPVH.js";var F=(()=>{let s=class s{constructor(n){this.http=n,this.selectedAccountByUser=new H(null)}getAllBankBranches(){return this.http.get(O.LIST_ALL_BRANCHES,{observe:"response",withCredentials:!0})}getAllUserBankAccounts(){return this.http.get(O.GET_ALL_USER_ACCOUNTS,{observe:"response",withCredentials:!0})}getAccountSettingsByAccountName(n){return this.http.get(O.GET_ACCOUNT_SETTINGS,{params:new k().set("accountType",n),observe:"response",withCredentials:!0})}getAllAccountSetting(){return this.http.get(O.GET_ALL_ACCOUNT_SETTINGS,{observe:"response",withCredentials:!0})}moneyTransfer(n,o,r){return this.http.patch(O.TRANSFER_MONEY_PATCH,null,{params:new k().set("accountNumberFrom",n).set("accountNumberTo",o).set("amount",r),observe:"response",withCredentials:!0})}createABankAccount(n,o){return console.log(n,o),this.http.put(O.CREATE_USER_BANK_ACCOUNT_By_TYPE_PUT,null,{params:new k().set("accountName",n).set("initialAmount",o),observe:"response",withCredentials:!0})}getAllTransactions(){return this.http.get(O.GET_ALL_TRANSACTIONS,{observe:"response",withCredentials:!0})}getUserData(){return this.http.get(O.USER_GET_DETAILS,{observe:"response",withCredentials:!0})}getAccountByAccountNumber(n){return this.http.get(O.GET_ACCOUNT_DETAILS_BY_ACCOUNT_NUMBER,{params:new k().set("accountNumber",n),observe:"response",withCredentials:!0})}};s.\u0275fac=function(o){return new(o||s)(j(Z))},s.\u0275prov=G({token:s,factory:s.\u0275fac,providedIn:"root"});let a=s;return a})();var I=a=>({"toggle-down":a});function st(a,s){if(a&1){let c=x();e(0,"div",15)(1,"h6"),i(2,"IMPS money transfer"),t(),e(3,"form",21,0),g("ngSubmit",function(){h(c);let o=W(4),r=m();return v(r.transferCash(o))}),e(5,"label",22),i(6,"beneficiary account number"),t(),d(7,"input",23),e(8,"label",24),i(9,"Amount"),t(),d(10,"input",25),e(11,"button",26),i(12,"Pay"),t()()()}}function mt(a,s){if(a&1&&(e(0,"td",40),i(1),t()),a&2){let c=m().$implicit;l(),b("-",c.amount,"")}}function dt(a,s){if(a&1&&(e(0,"td",41),i(1),t()),a&2){let c=m().$implicit;l(),b("+",c.amount,"")}}function gt(a,s){if(a&1&&(e(0,"tr")(1,"td"),i(2),t(),e(3,"td"),i(4),t(),e(5,"td"),i(6),t(),_(7,mt,2,1,"td",40)(8,dt,2,1,"td",41),t()),a&2){let c=s.$implicit,n=m(2);l(2),u(c.fromAccountNumber),l(2),u(c.toAccountNumber),l(2),u(c.transactionDate),l(),f((n.selectedAllAccount==null?null:n.selectedAllAccount.id)==c.fromAccountNumber?7:8)}}function ut(a,s){if(a&1&&(e(0,"div",27)(1,"h6"),i(2,"Date filter"),t(),e(3,"form",28)(4,"span",29)(5,"label",30),i(6,"From :"),t(),d(7,"input",31),t(),e(8,"span",29)(9,"label",32),i(10,"To :"),t(),d(11,"input",33),t()()(),e(12,"div",34)(13,"table")(14,"tr",35)(15,"th"),i(16,"From"),t(),e(17,"th"),i(18,"To"),t(),e(19,"th"),i(20,"Date Time"),t(),e(21,"th"),i(22,"Amount"),t()(),y(23,gt,9,4,"tr",null,w),t(),e(25,"div",36),p(),e(26,"svg",6),d(27,"path",37),t(),S(),e(28,"span"),i(29,"1"),t(),e(30,"span"),i(31,"2"),t(),e(32,"span"),i(33,"3"),t(),e(34,"div",38),i(35,"..."),t(),e(36,"span"),i(37,"10"),t(),p(),e(38,"svg",6),d(39,"path",39),t()()()),a&2){let c=m();l(23),T(c.allUserTransactions)}}function pt(a,s){a&1&&(e(0,"p"),i(1," Inactivating the account will stop all monetary transactions from and to this account only "),t(),e(2,"button",45),i(3,"Inactivate"),t(),e(4,"button",46),i(5,"Activate"),t())}function _t(a,s){a&1&&(e(0,"p"),i(1," closing the account will permanently delete the account... please be sure before proceeding "),t(),e(2,"button"),i(3,"Close Account"),t())}function ft(a,s){if(a&1){let c=x();e(0,"div",20)(1,"div",42)(2,"div",43),g("click",function(){h(c);let o=m();return v(o.inactiveAccount=!o.inactiveAccount)}),e(3,"h6"),i(4,"Inactivate account"),t(),p(),e(5,"svg",13),d(6,"path",14),t()(),_(7,pt,6,0),t(),S(),e(8,"div",44)(9,"div",43),g("click",function(){h(c);let o=m();return v(o.closeAccount=!o.closeAccount)}),e(10,"h6"),i(11,"Close Account"),t(),p(),e(12,"svg",13),d(13,"path",14),t()(),_(14,_t,4,0),t()()}if(a&2){let c=m();l(5),A("ngClass",E(4,I,c.inactiveAccount)),l(2),f(c.inactiveAccount?7:-1),l(5),A("ngClass",E(6,I,c.closeAccount)),l(2),f(c.closeAccount?14:-1)}}var it=(()=>{let s=class s{constructor(n,o,r,C,P){this.router=n,this.route=o,this.userService=r,this.activatedRoute=C,this.UnRegService=P,this.startPoint=!1,this.moneyTransferActive=this.startPoint,this.transactions=this.startPoint,this.editSavingsAccount=this.startPoint,this.inactiveAccount=this.startPoint,this.closeAccount=this.startPoint,this.activeUser=null,this.fullName="",this.selectedAllAccount=null,this.accountSettings=null,this.allUserTransactions=null,this.gitData=!1;let R=Number(this.route.snapshot.queryParams.openDetailsUi);this.openPartOdUi(R),this.activeUser=JSON.parse(localStorage.getItem("activeUser")),this.fullName=this.activeUser?.fullName,this.selectedAllAccount=JSON.parse(localStorage.getItem("selectedAccount")),this.userService.getAccountSettingsByAccountName(this.selectedAllAccount?.accountTypeName).subscribe({next:ct=>{this.accountSettings=ct.body}}),P.checkIfUserIsLoggedIn(),this.selectedAllAccount&&this.updateUserAccountDetails(this.selectedAllAccount.id)}openPartOdUi(n){switch(console.log(Number(n)),n){case 1:this.moneyTransferActive=!0;break;case 2:this.transactions=!0,this.getAllUserTransactions();break;case 3:this.editSavingsAccount=!0;break;default:console.log("error");break}}editSavingsAccountLogic(){this.editSavingsAccount=!this.editSavingsAccount,this.editSavingsAccount||(this.inactiveAccount=!1,this.closeAccount=!1)}backToUserHome(){this.router.navigate(["user-welcome"])}transferCash(n){let o=n.form.value;console.log(o),!(!this.selectedAllAccount?.id||!o.accountNumber||!o.amount)&&this.userService.moneyTransfer(this.selectedAllAccount?.id,o.accountNumber,o.amount).subscribe({next:r=>{console.log(r);let C=this.selectedAllAccount?.amount;C=C-o.amount;let P=JSON.parse(localStorage.getItem("selectedAccount"));P.amount=C,this.selectedAllAccount=P;let R=JSON.stringify(P);localStorage.setItem("selectedAccount",R),n.resetForm()},error:r=>console.log(r)})}getAllUserTransactions(){if(this.transactions&&this.gitData){this.transactions=!this.transactions;return}this.userService.getAllTransactions().subscribe({next:n=>{console.log(n.body),this.allUserTransactions=n.body,this.gitData=!0},error:n=>{}}),this.transactions=!0}updateUserAccountDetails(n){this.userService.getUserData().subscribe({next:o=>{console.log("Activated account"),console.log(o.body);let r=JSON.stringify(o.body);localStorage.setItem("activeUser",r)},error:o=>console.log(o)}),console.log(n),this.userService.getAccountByAccountNumber(n).subscribe({next:o=>{console.log("Selected account"),console.log(o.body),console.log("end");let r=JSON.stringify(o.body);localStorage.setItem("selectedAccount",r)},error:o=>console.log(o)})}};s.\u0275fac=function(o){return new(o||s)(M(z),M(U),M(F),M(U),M(B))},s.\u0275cmp=N({type:s,selectors:[["app-edit-user-details"]],standalone:!0,features:[V],decls:51,vars:16,consts:[["f","ngForm"],[1,"container","containment-toping"],[1,"grid","grid--3-cols"],[1,"savings-account"],[1,"LHS","prevent-select"],[1,"back",3,"click"],["xmlns","http://www.w3.org/2000/svg","width","32","height","32","fill","#000000","viewBox","0 0 256 256"],["d","M224,128a8,8,0,0,1-8,8H59.31l58.35,58.34a8,8,0,0,1-11.32,11.32l-72-72a8,8,0,0,1,0-11.32l72-72a8,8,0,0,1,11.32,11.32L59.31,120H216A8,8,0,0,1,224,128Z"],[1,"account-details"],[1,"account"],[1,"amount"],[1,"transactions","prevent-select"],[1,"transactions-heading",3,"click"],["xmlns","http://www.w3.org/2000/svg","width","32","height","32","fill","#000000","viewBox","0 0 256 256",3,"ngClass"],["d","M216.49,104.49l-80,80a12,12,0,0,1-17,0l-80-80a12,12,0,0,1,17-17L128,159l71.51-71.52a12,12,0,0,1,17,17Z"],[1,"container","money-transfer-container"],[1,"transactions"],[1,"transactions-heading","prevent-select",3,"click"],[1,"edit-mode","prevent-select"],[1,"edit-text-icon","head-text",3,"click"],[1,"options","grid","grid--2-cols"],[3,"ngSubmit"],["for","to-account-number"],["id","to-account-number","type","number","name","accountNumber","ngModel",""],["for","destination-amount"],["id","destination-amount","type","number","name","amount","ngModel",""],["type","submit"],[1,"range","prevent-select"],["action",""],[1,"form-field"],["for","from"],["id","from","type","date"],["for","to"],["id","to","type","date"],[1,"container","transaction-table"],[1,"head","prevent-select"],[1,"next-bubble","prevent-select"],["d","M128,26A102,102,0,1,0,230,128,102.12,102.12,0,0,0,128,26Zm0,192a90,90,0,1,1,90-90A90.1,90.1,0,0,1,128,218Zm46-90a6,6,0,0,1-6,6H102.49l21.75,21.76a6,6,0,1,1-8.48,8.48l-32-32a6,6,0,0,1,0-8.48l32-32a6,6,0,0,1,8.48,8.48L102.49,122H168A6,6,0,0,1,174,128Z"],[1,"three-dotes"],["d","M128,26A102,102,0,1,0,230,128,102.12,102.12,0,0,0,128,26Zm0,192a90,90,0,1,1,90-90A90.1,90.1,0,0,1,128,218Zm44.24-94.24a6,6,0,0,1,0,8.48l-32,32a6,6,0,0,1-8.48-8.48L153.51,134H88a6,6,0,0,1,0-12h65.51l-21.75-21.76a6,6,0,0,1,8.48-8.48Z"],[1,"gone"],[1,"got"],[1,"caution"],[1,"edit-text-icon",3,"click"],[1,"caution","close-account"],[1,"inactive"],[1,"active"]],template:function(o,r){o&1&&(e(0,"div",1)(1,"section",2)(2,"div",3)(3,"div",4)(4,"label"),i(5),t(),e(6,"span",5),g("click",function(){return r.backToUserHome()}),p(),e(7,"svg",6),d(8,"path",7),t(),S(),e(9,"span"),i(10,"back"),t()()(),d(11,"hr"),e(12,"div")(13,"span")(14,"h6"),i(15),t()(),e(16,"article")(17,"p"),i(18," savings account is commonly used as a salary account or a quick draw account "),t()()()(),e(19,"div",8)(20,"div",9)(21,"h6"),i(22,"Account number"),t(),e(23,"label"),i(24),t()(),e(25,"div",10)(26,"h6"),i(27,"Balance"),t(),e(28,"label"),i(29),t()()(),e(30,"section",11)(31,"div",12),g("click",function(){return r.moneyTransferActive=!r.moneyTransferActive}),e(32,"h4"),i(33,"Money Transfer"),t(),p(),e(34,"svg",13),d(35,"path",14),t()(),_(36,st,13,0,"div",15),t(),S(),e(37,"section",16)(38,"div",17),g("click",function(){return r.getAllUserTransactions()}),e(39,"h4"),i(40,"transactions"),t(),p(),e(41,"svg",13),d(42,"path",14),t()(),_(43,ut,40,0),t(),S(),e(44,"section",18)(45,"div",19),g("click",function(){return r.editSavingsAccountLogic()}),e(46,"h4"),i(47,"Edit savings account"),t(),p(),e(48,"svg",13),d(49,"path",14),t()(),_(50,ft,15,8,"div",20),t()()()),o&2&&(l(5),b("",r.selectedAllAccount==null?null:r.selectedAllAccount.accountTypeName," Account"),l(10),b(" Interest rate : ",r.accountSettings==null?null:r.accountSettings.baseInterestRate,"% "),l(9),u(r.selectedAllAccount==null?null:r.selectedAllAccount.id),l(5),b("",r.selectedAllAccount==null?null:r.selectedAllAccount.amount," $"),l(5),A("ngClass",E(10,I,r.moneyTransferActive)),l(2),f(r.moneyTransferActive?36:-1),l(5),A("ngClass",E(12,I,r.transactions)),l(2),f(r.transactions?43:-1),l(5),A("ngClass",E(14,I,r.editSavingsAccount)),l(2),f(r.editSavingsAccount?50:-1))},dependencies:[L,D,tt,Q,$,X,J,q,K,Y],styles:[".containment-toping[_ngcontent-%COMP%]{margin-top:10rem}.grid[_ngcontent-%COMP%]{row-gap:3rem;column-gap:0rem}.savings-account[_ngcontent-%COMP%]{background-color:#1864ab;grid-column:1/3;display:flex;gap:4rem;padding:4rem}.savings-account[_ngcontent-%COMP%]   div[_ngcontent-%COMP%]{font-weight:600;color:#e7f5ff;font-size:2.4rem}.savings-account[_ngcontent-%COMP%]   .LHS[_ngcontent-%COMP%]{width:22rem}.savings-account[_ngcontent-%COMP%]   .LHS[_ngcontent-%COMP%]   .back[_ngcontent-%COMP%]{transition:all .33s}.savings-account[_ngcontent-%COMP%]   .LHS[_ngcontent-%COMP%]   .back[_ngcontent-%COMP%]:hover, .savings-account[_ngcontent-%COMP%]   .LHS[_ngcontent-%COMP%]   .back[_ngcontent-%COMP%]:active{cursor:pointer}.savings-account[_ngcontent-%COMP%]   span[_ngcontent-%COMP%]{display:flex;font-size:1.8rem;font-weight:100;color:#e7f5ff;align-items:center;margin-top:1rem}.savings-account[_ngcontent-%COMP%]   span[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%]{fill:#e7f5ff;width:2.2rem}.savings-account[_ngcontent-%COMP%]   span[_ngcontent-%COMP%]   span[_ngcontent-%COMP%]{display:inline;margin-top:0rem;margin-left:1rem}.savings-account[_ngcontent-%COMP%]   div[_ngcontent-%COMP%]:last-child   span[_ngcontent-%COMP%]{display:block;font-weight:100;font-size:2.2rem;margin-bottom:1rem}.savings-account[_ngcontent-%COMP%]   div[_ngcontent-%COMP%]:last-child   p[_ngcontent-%COMP%]{font-weight:100;font-size:1.2rem}.account-details[_ngcontent-%COMP%]{display:flex;flex-direction:column;gap:2rem;border:1px solid #1864ab;padding:2rem 4rem;font-size:2.4rem}.account-details[_ngcontent-%COMP%]   h6[_ngcontent-%COMP%]{margin-bottom:.7rem}.account-details[_ngcontent-%COMP%]   label[_ngcontent-%COMP%]{margin-bottom:.7rem;color:#1864ab}.edit-mode[_ngcontent-%COMP%]{border:1px solid #1864ab;padding:2rem;grid-column:1 /4;margin-bottom:4rem}.edit-text-icon.head-text[_ngcontent-%COMP%]{background-color:#e7f5ff;padding:1rem 2rem;transition:all .33s}.edit-text-icon.head-text[_ngcontent-%COMP%]:hover{cursor:pointer;background-color:#d0ebff;padding:1rem 2rem}.edit-text-icon.head-text[_ngcontent-%COMP%]:active{padding:1rem 2rem}.edit-text-icon[_ngcontent-%COMP%], .transactions-heading[_ngcontent-%COMP%]{display:flex;align-items:center;gap:1rem}.transactions[_ngcontent-%COMP%]{grid-column:1 /4;border:1px solid #1864ab}.transactions-heading[_ngcontent-%COMP%]{padding:1rem 4rem;background-color:#1864ab;text-transform:capitalize;transition:all .33s}.transactions-heading[_ngcontent-%COMP%]:hover, .transactions-heading[_ngcontent-%COMP%]:active{cursor:pointer;background-color:#1971c2}.edit-mode[_ngcontent-%COMP%]   h4[_ngcontent-%COMP%], .transactions-heading[_ngcontent-%COMP%]   h4[_ngcontent-%COMP%]{font-size:2.2rem;color:#1864ab}.range[_ngcontent-%COMP%]{display:flex;flex-direction:column;align-items:center;gap:1rem}.range[_ngcontent-%COMP%]   h6[_ngcontent-%COMP%]{font-size:1.8rem;font-weight:600;margin-top:2rem;color:#1864ab}.range[_ngcontent-%COMP%]   form[_ngcontent-%COMP%]{font-size:1.4rem;font-weight:600}.range[_ngcontent-%COMP%]   form[_ngcontent-%COMP%]   label[_ngcontent-%COMP%]{margin-right:1rem}.range[_ngcontent-%COMP%]   form[_ngcontent-%COMP%]   input[_ngcontent-%COMP%]{padding:.4rem;border-radius:10px;border:1px solid #1864ab}.form-field[_ngcontent-%COMP%]{display:inline-block;padding:1rem 2rem;margin-bottom:2.2rem}.edit-mode[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%]{transform:rotate(-180deg);transition:all .33s;fill:#1864ab}.edit-mode[_ngcontent-%COMP%]   svg.toggle-down[_ngcontent-%COMP%]{transform:rotate(0)}.transactions-heading[_ngcontent-%COMP%]   h4[_ngcontent-%COMP%]{color:#e7f5ff}.transactions-heading[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%]{fill:#e7f5ff;transform:rotate(-180deg);transition:all .33s}.transactions-heading[_ngcontent-%COMP%]   svg.toggle-down[_ngcontent-%COMP%]{transform:rotate(0)}.transaction-table[_ngcontent-%COMP%]{display:flex;flex-direction:column;align-items:center;justify-content:center;margin-bottom:2rem}.transaction-table[_ngcontent-%COMP%]   table[_ngcontent-%COMP%]{border-collapse:collapse;margin-bottom:1rem}.transaction-table[_ngcontent-%COMP%]   .head[_ngcontent-%COMP%]{color:#e7f5ff}.transaction-table[_ngcontent-%COMP%]   .head[_ngcontent-%COMP%]   th[_ngcontent-%COMP%]{font-size:1.44rem;max-width:25rem;min-width:25rem;padding:1rem;background-color:#1864ab;border-right:1px solid #d0ebff}.transaction-table[_ngcontent-%COMP%]   tr[_ngcontent-%COMP%]:nth-child(odd){padding:1rem;background-color:#e7f5ff}.transaction-table[_ngcontent-%COMP%]   tr[_ngcontent-%COMP%]   td[_ngcontent-%COMP%]{font-size:1.2rem;padding:1rem;text-align:center}.next-bubble[_ngcontent-%COMP%]{margin-top:1rem;display:flex;align-items:center;gap:.4rem}.next-bubble[_ngcontent-%COMP%]   span[_ngcontent-%COMP%]{font-size:1.2rem;border-radius:50%;color:#1864ab;padding:1rem;transition:all .33s}.next-bubble[_ngcontent-%COMP%]   span[_ngcontent-%COMP%]:hover, .next-bubble[_ngcontent-%COMP%]   span[_ngcontent-%COMP%]:active{cursor:pointer;color:#1864ab;background-color:#a5d8ff;font-size:1.2rem}.next-bubble[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%]{width:3.4rem;border-radius:50%;fill:#1864ab;transition:all .33s}.next-bubble[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%]:hover, .next-bubble[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%]:active{cursor:pointer;fill:#fff;border-radius:50%;background-color:#1864ab}.edit-mode[_ngcontent-%COMP%]   .options[_ngcontent-%COMP%]{margin-top:2.2rem;gap:2rem}.edit-mode[_ngcontent-%COMP%]   .options[_ngcontent-%COMP%]   h6[_ngcontent-%COMP%]{font-size:1.6rem}.edit-text-icon[_ngcontent-%COMP%]{cursor:pointer;padding:1.4rem 0rem;margin-bottom:1rem}.edit-mode[_ngcontent-%COMP%]   .options[_ngcontent-%COMP%]   p[_ngcontent-%COMP%]{font-size:1.2rem;margin-bottom:4rem}.caution[_ngcontent-%COMP%]{display:flex;flex-direction:column;align-self:baseline;padding:0rem 2rem 2rem;background-color:#fff9db}.edit-mode[_ngcontent-%COMP%]   .options[_ngcontent-%COMP%]   button[_ngcontent-%COMP%]{font-size:1.4rem;color:#e7f5ff;padding:1rem 2rem;align-self:center}.edit-mode[_ngcontent-%COMP%]   .edit-text-icon[_ngcontent-%COMP%]   h6[_ngcontent-%COMP%]{color:#e67700}.edit-mode[_ngcontent-%COMP%]   .options[_ngcontent-%COMP%]   [_ngcontent-%COMP%]:first-child   .inactive[_ngcontent-%COMP%]{border:1px solid #ff922b;background-color:#e67700;transition:all .33s}.edit-mode[_ngcontent-%COMP%]   .options[_ngcontent-%COMP%]   [_ngcontent-%COMP%]:first-child   .inactive[_ngcontent-%COMP%]:hover, .edit-mode[_ngcontent-%COMP%]   .options[_ngcontent-%COMP%]   [_ngcontent-%COMP%]:first-child   .inactive[_ngcontent-%COMP%]:active{cursor:pointer;border:1px solid #f08c00;background-color:#f08c00}.edit-mode[_ngcontent-%COMP%]   .options[_ngcontent-%COMP%]   [_ngcontent-%COMP%]:first-child   .active[_ngcontent-%COMP%]{border:1px solid #51cf66;background-color:#2b8a3e;transition:all .33s}.edit-mode[_ngcontent-%COMP%]   .options[_ngcontent-%COMP%]   [_ngcontent-%COMP%]:first-child   .active[_ngcontent-%COMP%]:hover, .edit-mode[_ngcontent-%COMP%]   .options[_ngcontent-%COMP%]   [_ngcontent-%COMP%]:first-child   .active[_ngcontent-%COMP%]:active{cursor:pointer;border:1px solid #2f9e44;background-color:#2f9e44}.edit-mode[_ngcontent-%COMP%]   .options[_ngcontent-%COMP%]   [_ngcontent-%COMP%]:last-child   button[_ngcontent-%COMP%]{border:1px solid #ff6b6b;background-color:#c92a2a;transition:all .33s}.edit-mode[_ngcontent-%COMP%]   .options[_ngcontent-%COMP%]   [_ngcontent-%COMP%]:last-child   button[_ngcontent-%COMP%]:hover, .edit-mode[_ngcontent-%COMP%]   .options[_ngcontent-%COMP%]   [_ngcontent-%COMP%]:last-child   button[_ngcontent-%COMP%]:active{cursor:pointer;border:1px solid #e03131;background-color:#e03131}.caution.close-account[_ngcontent-%COMP%]{background-color:#fff5f5;align-self:baseline}.caution[_ngcontent-%COMP%]   .edit-text-icon[_ngcontent-%COMP%]{align-items:center}.caution[_ngcontent-%COMP%]   .edit-text-icon[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%]{align-self:baseline;justify-self:baseline;transform:rotate(-180deg);transition:all .33s;width:2.2rem}.caution[_ngcontent-%COMP%]   .edit-text-icon[_ngcontent-%COMP%]   svg.toggle-down[_ngcontent-%COMP%]{transform:rotate(0)}.caution[_ngcontent-%COMP%]   .edit-text-icon[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%]{fill:#e67700}.caution.close-account[_ngcontent-%COMP%]{fill:#c92a2a}.close-account[_ngcontent-%COMP%]   .edit-text-icon[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%]{fill:#c92a2a}.caution.close-account[_ngcontent-%COMP%]   .edit-text-icon[_ngcontent-%COMP%]   h6[_ngcontent-%COMP%]{color:#c92a2a}.money-transfer-container[_ngcontent-%COMP%]{margin-top:4rem;display:flex;flex-direction:column;align-items:center;gap:2rem}.money-transfer-container[_ngcontent-%COMP%]   h6[_ngcontent-%COMP%]{font-size:1.8rem;color:#1864ab}.money-transfer-container[_ngcontent-%COMP%]   form[_ngcontent-%COMP%]{display:flex;flex-direction:column;margin-bottom:4rem;font-size:1.2rem}.money-transfer-container[_ngcontent-%COMP%]   form[_ngcontent-%COMP%]   label[_ngcontent-%COMP%]{font-weight:600;margin-bottom:.5rem}.money-transfer-container[_ngcontent-%COMP%]   form[_ngcontent-%COMP%]   [_ngcontent-%COMP%]:nth-child(2){margin-bottom:2rem}.money-transfer-container[_ngcontent-%COMP%]   form[_ngcontent-%COMP%]   input[_ngcontent-%COMP%]{padding:.6rem 1.4rem;border:1px solid #1864ab}.money-transfer-container[_ngcontent-%COMP%]   form[_ngcontent-%COMP%]   button[_ngcontent-%COMP%]{margin-top:2.2rem;background-color:#1864ab;border:1px solid #a5d8ff;color:#d0ebff;font-weight:600;font-size:1.44rem;align-self:center;max-width:8rem;padding:1rem 2rem;transition:all .33s}.money-transfer-container[_ngcontent-%COMP%]   form[_ngcontent-%COMP%]   button[_ngcontent-%COMP%]:hover, .money-transfer-container[_ngcontent-%COMP%]   form[_ngcontent-%COMP%]   button[_ngcontent-%COMP%]:active{cursor:pointer;background-color:#1971c2;border:1px solid #1971c2}.LHS.prevent-select[_ngcontent-%COMP%]   label[_ngcontent-%COMP%]{text-transform:capitalize}.gone[_ngcontent-%COMP%]{color:#c92a2a}.got[_ngcontent-%COMP%]{color:#2b8a3e}"]});let a=s;return a})();var Ct=a=>({"toggle-down":a});function ht(a,s){if(a&1&&i(0),a&2){let c=m(2);b(" ",c.accountSet.accountType," ")}}function vt(a,s){a&1&&i(0," Select Account ")}function Mt(a,s){if(a&1){let c=x();e(0,"li",14),g("click",function(){let o=h(c).$index,r=m(3);return v(r.selectThisAccount(o))}),i(1),t()}if(a&2){let c=s.$implicit;l(),b(" ",c.accountType," ")}}function bt(a,s){if(a&1&&(e(0,"ul"),y(1,Mt,2,1,"li",null,w),t()),a&2){let c=m(2);l(),T(c.accountSettings)}}function Ot(a,s){if(a&1){let c=x();e(0,"button",14),g("click",function(){h(c);let o=m(2);return v(o.createABankAccount())}),i(1,"Create Bank Account"),t()}}function Pt(a,s){if(a&1){let c=x();e(0,"div",11)(1,"div",13)(2,"h6",14),g("click",function(){h(c);let o=m();return v(o.displayAllAccountSettings())}),_(3,ht,1,1)(4,vt,1,0),t(),_(5,bt,3,0,"ul"),t(),e(6,"div",15)(7,"h6"),i(8,"Base Interest Rate"),t(),e(9,"p"),i(10),t()(),e(11,"div",15)(12,"h6"),i(13,"Joint Account"),t(),e(14,"p"),i(15),t()(),e(16,"div",15)(17,"h6"),i(18,"Min Starting Amount"),t(),e(19,"p"),i(20),t()(),e(21,"div",15)(22,"h6"),i(23,"Withdrawal count limit"),t(),e(24,"p"),i(25),t()(),e(26,"div",15)(27,"h6"),i(28,"Money Transfer Limit"),t(),e(29,"p"),i(30),t()(),e(31,"div",15)(32,"h6"),i(33,"Base Limit"),t(),e(34,"p"),i(35),t()(),e(36,"div",15)(37,"h6"),i(38,"Create Account"),t(),_(39,Ot,2,0,"button"),t()()}if(a&2){let c=m();l(3),f(c.accountSet?3:4),l(2),f(c.selectAnAccount?5:-1),l(5),u(c.accountSet==null?null:c.accountSet.baseInterestRate),l(5),u(c.accountSet==null?null:c.accountSet.ajointAccount),l(5),u(c.accountSet==null?null:c.accountSet.minStartingAmount),l(5),u(c.accountSet==null?null:c.accountSet.withdrawalCountLimit),l(5),u(c.accountSet==null?null:c.accountSet.moneyTransferLimit),l(5),u(c.accountSet==null?null:c.accountSet.baseLimit),l(4),f(c.accountSet?39:-1)}}function xt(a,s){if(a&1){let c=x();e(0,"div",18)(1,"div")(2,"span"),i(3,"balance :"),t(),e(4,"span"),i(5),t()(),e(6,"div",19)(7,"span"),i(8,"Account number :"),t(),e(9,"span"),i(10),t()(),e(11,"div",20)(12,"button",14),g("click",function(){h(c);let o=m().$index,r=m();return v(r.transferFund(o))}),i(13,"Transfer Fund"),t(),e(14,"button",14),g("click",function(){h(c);let o=m().$index,r=m();return v(r.transactions(o))}),i(15,"Transactions"),t(),e(16,"button",14),g("click",function(){h(c);let o=m().$index,r=m();return v(r.editDetails(o))}),i(17,"Edit details"),t()()()}if(a&2){let c=m().$implicit;l(5),u(c.amount),l(5),u(c.id)}}function St(a,s){if(a&1){let c=x();e(0,"div",12)(1,"h5",14),g("click",function(){h(c);let o=m();return v(o.savingsVisible=!o.savingsVisible)}),i(2),p(),e(3,"svg",16),d(4,"path",17),t()(),_(5,xt,18,2,"div",18),t()}if(a&2){let c=s.$implicit,n=m();l(2),b(" ",c.accountTypeName," "),l(),A("ngClass",E(3,Ct,n.savingsVisible)),l(2),f(n.savingsVisible?5:-1)}}var ot=(()=>{let s=class s{constructor(n,o,r,C){this.router=n,this.userService=o,this.activatedRoute=r,this.unRegService=C,this.activeUser=null,this.fullName="",this.allAccounts=null,this.selectAnAccount=!1,this.openCreateAccount=!1,this.accountSettings=null,this.accountSet=null,this.savingsVisible=!1,this.checkingVisible=!1,this.buildUpVisible=!1,this.activeUser=JSON.parse(localStorage.getItem("activeUser")),this.fullName=this.activeUser?.fullName,this.userService.getAllUserBankAccounts().subscribe({next:P=>{console.log(P.body),this.allAccounts=P.body}}),this.unRegService.checkIfUserIsLoggedIn()}visibilityInverter(n){n=!n}transferFund(n){console.log(n),this.privateSelectedAccount(n),this.router.navigate(["details"],{queryParams:{openDetailsUi:1},relativeTo:this.activatedRoute})}transactions(n){console.log(n),this.privateSelectedAccount(n),this.router.navigate(["details"],{queryParams:{openDetailsUi:2},relativeTo:this.activatedRoute})}editDetails(n){console.log(n),this.privateSelectedAccount(n),this.router.navigate(["details"],{queryParams:{openDetailsUi:3},relativeTo:this.activatedRoute})}privateSelectedAccount(n){this.userService.selectedAccountByUser.next(this.allAccounts[n]),localStorage.setItem("selectedAccount",JSON.stringify(this.allAccounts[n]))}displayAllAccountSettings(){console.log("Inverter");let n=!1;if(this.selectAnAccount){this.selectAnAccount=n,console.log("return");return}this.userService.getAllAccountSetting().subscribe({next:o=>{console.log(o.body),this.accountSettings=o.body,this.selectAnAccount=!n}})}selectThisAccount(n){console.log(n),this.selectAnAccount=!this.selectAnAccount,this.accountSet=this.accountSettings[n]}openUpAccountCreateSection(){this.openCreateAccount=!this.openCreateAccount,this.selectAnAccount=!1,this.accountSet=null}createABankAccount(){if(!this.accountSet)throw new Error("Method not implemented.");this.userService.createABankAccount(this.accountSet.accountType,this.accountSet.minStartingAmount).subscribe({next:n=>location.reload(),error:n=>console.log(n)})}updateUserAccountDetails(){this.userService.getUserData().subscribe({next:n=>console.log(n.body),error:n=>console.log(n)})}};s.\u0275fac=function(o){return new(o||s)(M(z),M(F),M(U),M(B))},s.\u0275cmp=N({type:s,selectors:[["app-user-welcome"]],standalone:!0,features:[V],decls:24,vars:2,consts:[[1,"container"],[1,"welcome-message"],[1,"single-line"],[1,"profile-view","prevent-select"],["xmlns","http://www.w3.org/2000/svg","width","32","height","32","fill","#000000","viewBox","0 0 256 256"],["d","M144,157.68a68,68,0,1,0-71.9,0c-20.65,6.76-39.23,19.39-54.17,37.17a8,8,0,1,0,12.24,10.3C50.25,181.19,77.91,168,108,168s57.75,13.19,77.87,37.15a8,8,0,0,0,12.26-10.3C183.18,177.07,164.6,164.44,144,157.68ZM56,100a52,52,0,1,1,52,52A52.06,52.06,0,0,1,56,100Zm196.25,43.07-4.66-2.69a23.6,23.6,0,0,0,0-8.76l4.66-2.69a8,8,0,1,0-8-13.86l-4.67,2.7a23.92,23.92,0,0,0-7.58-4.39V108a8,8,0,0,0-16,0v5.38a23.92,23.92,0,0,0-7.58,4.39l-4.67-2.7a8,8,0,1,0-8,13.86l4.66,2.69a23.6,23.6,0,0,0,0,8.76l-4.66,2.69a8,8,0,0,0,8,13.86l4.67-2.7a23.92,23.92,0,0,0,7.58,4.39V164a8,8,0,0,0,16,0v-5.38a23.92,23.92,0,0,0,7.58-4.39l4.67,2.7a7.92,7.92,0,0,0,4,1.07,8,8,0,0,0,4-14.93ZM216,136a8,8,0,1,1,8,8A8,8,0,0,1,216,136Z"],[1,"accounts","common-container","prevent-select"],[1,"create-an-account"],["xmlns","http://www.w3.org/2000/svg","width","32","height","32","fill","#000000","viewBox","0 0 256 256",3,"click"],["d","M224,128a96,96,0,1,1-96-96A96,96,0,0,1,224,128Z","opacity","0.2"],["d","M128,24A104,104,0,1,0,232,128,104.11,104.11,0,0,0,128,24Zm0,192a88,88,0,1,1,88-88A88.1,88.1,0,0,1,128,216Zm45.66-109.66a8,8,0,0,1,0,11.32l-40,40a8,8,0,0,1-11.32,0l-40-40a8,8,0,0,1,11.32-11.32L128,140.69l34.34-34.35A8,8,0,0,1,173.66,106.34Z"],[1,"account_info","grid","grid--4-cols"],[1,"account-type","savings"],[1,"account-options"],[3,"click"],[1,"time-info"],["xmlns","http://www.w3.org/2000/svg","width","32","height","32","fill","#000000","viewBox","0 0 256 256",3,"ngClass"],["d","M216.49,104.49l-80,80a12,12,0,0,1-17,0l-80-80a12,12,0,0,1,17-17L128,159l71.51-71.52a12,12,0,0,1,17,17Z"],[1,"container","account-info"],[1,"account-number"],[1,"options-button"]],template:function(o,r){o&1&&(e(0,"section",0)(1,"div",1)(2,"div",2)(3,"h3"),i(4,"Welcome :"),t(),e(5,"p"),i(6),t()(),e(7,"div",3)(8,"h6"),i(9,"Edit Profile"),t(),p(),e(10,"svg",4),d(11,"path",5),t()()(),S(),e(12,"div",6)(13,"h4"),i(14,"User's Bank Accounts"),t(),e(15,"div",7)(16,"h5"),i(17,"Create a new account"),t(),p(),e(18,"svg",8),g("click",function(){return r.openUpAccountCreateSection()}),d(19,"path",9)(20,"path",10),t(),_(21,Pt,40,9,"div",11),t(),y(22,St,6,5,"div",12,w),t()()),o&2&&(l(6),u(r.fullName),l(15),f(r.openCreateAccount?21:-1),l(),T(r.allAccounts))},dependencies:[L,D],styles:[".welcome-message[_ngcontent-%COMP%]{display:flex;font-size:2.4rem;margin:10rem 10rem 0rem;background-color:#1864ab;border:1px solid #1864ab;justify-content:space-between}.common-container[_ngcontent-%COMP%]{margin:3rem 10rem}.welcome-message[_ngcontent-%COMP%]   p[_ngcontent-%COMP%]{font-weight:600;color:#1864ab;margin-right:2rem}.welcome-message[_ngcontent-%COMP%]   h3[_ngcontent-%COMP%]{margin:1rem 2rem;font-weight:100}.profile-view[_ngcontent-%COMP%]{margin-right:2rem;padding:0rem 1rem;display:flex;background-color:#1864ab;color:#e7f5ff;transition:all .33s}.profile-view[_ngcontent-%COMP%]:hover, .profile-view[_ngcontent-%COMP%]:active{cursor:pointer;background-color:#1971c2}.profile-view[_ngcontent-%COMP%]   h6[_ngcontent-%COMP%]{font-size:1.4rem;align-self:center}.profile-view[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%]{margin-left:1.2rem;fill:#e7f5ff;align-self:center}.single-line[_ngcontent-%COMP%]{display:flex;align-items:baseline;gap:0rem;background-color:#fff;text-transform:capitalize}.accounts[_ngcontent-%COMP%]{display:flex;flex-direction:column;justify-items:center;border:1px solid #1864ab}.accounts[_ngcontent-%COMP%]   h4[_ngcontent-%COMP%]{text-align:center;font-size:2.2rem;padding:1rem;background-color:#1864ab;color:#e7f5ff;transition:all .33s}.account-type[_ngcontent-%COMP%]{margin:2.4rem 2rem 0rem;background-color:#d0ebff;color:#1864ab;text-transform:capitalize;transition:all .33s}.account-type[_ngcontent-%COMP%]:hover, .account-type[_ngcontent-%COMP%]:active{background-color:#e7f5ff}.build-up[_ngcontent-%COMP%]{margin-bottom:2.4rem}.account-type[_ngcontent-%COMP%]   h5[_ngcontent-%COMP%]{background-image:linear-gradient(#e7f5ff,#d0ebff);display:flex;justify-items:center;align-items:center;column-gap:1rem;font-size:1.73rem;padding:1rem 2rem;transition:all 1s}.account-type[_ngcontent-%COMP%]   h5[_ngcontent-%COMP%]:hover{cursor:pointer;background-image:linear-gradient(#e7f5ff,#baddf8)}.account-type[_ngcontent-%COMP%]   h5[_ngcontent-%COMP%]:active{cursor:pointer;background-image:linear-gradient(#baddf8,#e7f5ff)}.account-info[_ngcontent-%COMP%]{margin:2rem 0rem;display:flex;flex-direction:column;gap:1.2rem;font-size:1.6rem}.account-info[_ngcontent-%COMP%]   div[_ngcontent-%COMP%]:first-child{color:#555}.account-info[_ngcontent-%COMP%]   div[_ngcontent-%COMP%]:first-child   span[_ngcontent-%COMP%]:last-child{margin-left:1rem;font-weight:600}.account-number[_ngcontent-%COMP%]   span[_ngcontent-%COMP%]:last-child{margin-left:1rem;font-weight:600}.account-info[_ngcontent-%COMP%]   div[_ngcontent-%COMP%]:last-child{align-self:end;margin-left:1rem;font-weight:600}.account-info[_ngcontent-%COMP%]   div[_ngcontent-%COMP%]:last-child   button[_ngcontent-%COMP%]{background-image:linear-gradient(#e7f5ff,#d0ebff);color:#1864ab;font-size:1.2rem;font-weight:600;padding:1rem;border:1px solid #74c0fc;transition:all .33s}.account-info[_ngcontent-%COMP%]   div[_ngcontent-%COMP%]:last-child   button[_ngcontent-%COMP%]:hover{cursor:pointer;background-image:linear-gradient(#e7f5ff,#baddf8)}.account-info[_ngcontent-%COMP%]   div[_ngcontent-%COMP%]:last-child   button[_ngcontent-%COMP%]:active{cursor:pointer;background-image:linear-gradient(#d0ebff,#e7f5ff)}.account-type[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%]{width:2rem;transform:rotate(-180deg);fill:#1864ab;stroke:#1864ab;transition:all .33s}.account-type[_ngcontent-%COMP%]   svg.toggle-down[_ngcontent-%COMP%]{transform:rotate(0)}.options-button[_ngcontent-%COMP%]{display:flex;gap:1rem}.options-button[_ngcontent-%COMP%]   button[_ngcontent-%COMP%]{min-width:15rem}.create-an-account[_ngcontent-%COMP%]{display:flex;flex-direction:column;align-items:center;margin-top:1rem;align-self:center}.create-an-account[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%]{fill:#1864ab}.create-an-account[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%]:hover{fill:#1864ab;cursor:pointer}.create-an-account[_ngcontent-%COMP%]   svg[_ngcontent-%COMP%]:active{fill:#74c0fc;cursor:pointer}.account_info[_ngcontent-%COMP%]{background-color:#e6fcf5;border-radius:12px}.create-an-account[_ngcontent-%COMP%]   h5[_ngcontent-%COMP%]{font-size:1.8rem}.account-options[_ngcontent-%COMP%]   h6[_ngcontent-%COMP%]{font-size:1.8rem;text-transform:capitalize;color:#087f5b;padding:2rem;background-color:#96f2d7;border-top-left-radius:12px;transition:all .33s;box-shadow:2px 2px 5px #00000012}.account-options[_ngcontent-%COMP%]   h6[_ngcontent-%COMP%]:hover{cursor:pointer;background-color:#c3fae8;box-shadow:2px 2px 5px #0000}.account-options[_ngcontent-%COMP%]   ul[_ngcontent-%COMP%]{list-style:none;font-size:1.4rem;position:absolute;background-color:#fff;padding:.2rem .6rem;box-shadow:2px 2px 5px #00000012}.account-options[_ngcontent-%COMP%]   ul[_ngcontent-%COMP%]   li[_ngcontent-%COMP%]{margin:.5rem 1.2rem;transition:all .33s}.account-options[_ngcontent-%COMP%]   ul[_ngcontent-%COMP%]   li[_ngcontent-%COMP%]:hover{padding:.6rem 1rem;cursor:pointer;background-color:#555;color:#fff}.time-info[_ngcontent-%COMP%]{padding:1rem}.time-info[_ngcontent-%COMP%]   h6[_ngcontent-%COMP%]{font-size:1.4rem;color:#0ca678;margin-bottom:2rem}.time-info[_ngcontent-%COMP%]   p[_ngcontent-%COMP%]{font-size:1.4rem;font-weight:700}.time-info[_ngcontent-%COMP%]   button[_ngcontent-%COMP%]{padding:1rem;border-radius:12px;border:0;box-shadow:2px 2px 5px #00000012;transition:all .33s;background-color:#fff}.time-info[_ngcontent-%COMP%]   button[_ngcontent-%COMP%]:hover{box-shadow:2px 2px 5px #0000;cursor:pointer}"]});let a=s;return a})();var Zt=[{path:"",component:ot},{path:"details",component:it}];export{Zt as LOGIN_ROUTER};