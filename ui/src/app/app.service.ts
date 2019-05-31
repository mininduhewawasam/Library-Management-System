import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { map } from 'rxjs/operators';
import { Observable } from 'rxjs/index';
/**
 * Class representing application service.
 *
 * @class AppService.
 */
@Injectable()
export class AppService {

  private addbookItem = '/api/addbook';
  private adddvdItem= '/api/adddvd';
  private addreader= 'api/AddReader';
  private borrowItem='/api/borrowitem';
  private deleteItem='/api/deleteItm';
  private returnItem='/api/returnItm';
  private viewItem='/api/ViewLib';

  constructor(private http: HttpClient) {}

  public book() {
    return this.http.get(this.addbookItem).pipe(
      map(response => response)
    );
  }

  public dvd() {
    return this.http.get(this.adddvdItem).pipe(
      map(response => response)
    );
  }

  public borrow() {
    return this.http.get(this.borrowItem).pipe(
      map(response => response)
    );
  }

  public addrdr() {
    return this.http.get(this.addreader).pipe(
      map(response => response)
    );
  }

  public delete() {
    return this.http.get(this.deleteItem).pipe(
      map(response => response)
    );
  }

  public return() {
    return this.http.get(this.returnItem).pipe(
      map(response => response)
    );
  }


  public addbook(data: any): Observable<any> {
    return this.http.post(this.addbookItem, data);
  }

  public adddvd(data: any): Observable<any> {
    return this.http.post(this.adddvdItem, data);
  }
  public borrowitem(data: any): Observable<any> {
    return this.http.post(this.borrowItem, data);
  }

  public returnitem(data: any): Observable<any> {
    return this.http.post(this.returnItem, data);
  }

  public deleteItm(data: any): Observable<any> {
    return this.http.post(this.deleteItem, data);
  }
  public AddReader(data: any): Observable<any> {
    return this.http.post(this.addreader, data);
  }
  public ViewLib(data: any): Observable<any> {
    return this.http.post(this.viewItem, data);
  }


}
