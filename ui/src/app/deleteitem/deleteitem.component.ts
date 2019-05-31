import { Component, OnInit } from '@angular/core';
import {AppService} from "../app.service";
import {HttpClient} from "@angular/common/http";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-deleteitem',
  templateUrl: './deleteitem.component.html',
  styleUrls: ['./deleteitem.component.css']
})
export class DeleteitemComponent implements OnInit {

  deleteItemForm:FormGroup;
  title: String;
  postRequestResponse: String;

  constructor(private http: HttpClient, private appservice: AppService) {
    this.appservice.delete().subscribe((data: any) => {
      this.title = data.content;
    } )
  }

  ngOnInit() {
    this.deleteItemForm = new FormGroup( {
      IsbnremoveItem: new FormControl('',Validators.required),
    })
  }

  deleteItem() {
    let remvITem = JSON.stringify(this.deleteItemForm.value);
    this.appservice.deleteItm(remvITem).subscribe((data: any) => {

      this.postRequestResponse = data.content;


      this.deleteItemForm.reset()
    })

  }
}
