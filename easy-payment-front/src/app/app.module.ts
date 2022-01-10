import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { MenuComponent } from './menu/menu.component';
import { SignupComponent } from './signup/signup.component';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { interceptorStringProvider } from './interceptors/api-rest.interceptor';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

//external
import { NgxSpinnerModule } from "ngx-spinner";
import { ListComponent } from './cliente/list.component';
import { CreateComponent } from './cliente/create.component';
import { UpdateComponent } from './cliente/update.component';
import { DetailComponent } from './cliente/detail.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    MenuComponent,
    SignupComponent,
    ListComponent,
    CreateComponent,
    UpdateComponent,
    DetailComponent
  ],
  imports: [
    BrowserModule,
    NgxSpinnerModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [interceptorStringProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
