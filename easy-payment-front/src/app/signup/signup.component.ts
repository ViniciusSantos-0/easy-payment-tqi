import { Iuser } from '../models/iuser';
import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { CognitoUserPool, CognitoUserAttribute } from 'amazon-cognito-identity-js';
import { Router } from '@angular/router';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  email: string;
  giveName: string;
  password: string;
  renda: string;
  cpf: string;
  rg: string;
  endereco: string;



  constructor(
    private router: Router

  ) { }

  ngOnInit(): void {
  }

  onRegister(): void {
    var poolData = {
      UserPoolId: environment.UserPoolId, // Your user pool id here
      ClientId: environment.ClientId, // Your client id here
    };
    var userPool = new CognitoUserPool(poolData);
    //set attributes
    var attrList = [];
    var iuser: Iuser = {
      email: this.email
    }
    for (let key in iuser) {
      var attrData = {
        Name: key,
        Value: iuser[key]
      }
      var attr = new CognitoUserAttribute(attrData);
      attrList.push(attr);
    }
    // sing up
    userPool.signUp(this.email, this.password, attrList, [], (err, result) => {
      if (err) {
        alert(err.message || JSON.stringify(err));
        return;
      }
      var newUser = result.user;
      console.log(JSON.stringify(newUser));
      alert('Enviamos um código de verificação para o seu e-mail');
      this.router.navigate(['/login']);

    });
  }


}
