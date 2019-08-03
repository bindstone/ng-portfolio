import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {ErrorPageComponent} from "./error-page/error-page.component";
import {HomePageComponent} from "./home-page/home-page.component";
import {CurrencyPageComponent} from "./currency/currency-page.component";
import {ActuatorComponent} from "./admin/actuator.component";
import {AuthGuard} from "./security/AuthGuard";
import {UserLoginComponent} from "./user/user-login.component";
import {UserCreateComponent} from "./user/user-create.component";

/**
 * Route Definition of the application and link to Component
 */
const routes: Routes = [
  {path: 'login', component: UserLoginComponent},
  {path: 'create-user', component: UserCreateComponent},
  {path: '', component: HomePageComponent, canActivate: [AuthGuard]},
  {path: 'currency', component: CurrencyPageComponent, canActivate: [AuthGuard]},
  {path: 'sysview', component: ActuatorComponent, canActivate: [AuthGuard]},
  {path: '**', component: ErrorPageComponent, data: {error: 404}}
];

/**
 * Separate NgModule declaration for routing
 */
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
