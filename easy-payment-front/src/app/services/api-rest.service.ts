import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiRestService {

  springURL = environment.springURL;

  constructor(
    private httpclient: HttpClient
  ) { }

  public getHelloAdmin():Observable<any>{
    return this.httpclient.get<any>(this.springURL + 'hello-admin');
  }
  public getHelloUser():Observable<any>{
    return this.httpclient.get<any>(this.springURL + 'hello-user');
  }


}
