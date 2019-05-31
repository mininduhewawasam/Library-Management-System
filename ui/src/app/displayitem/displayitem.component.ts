import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AppService} from "../app.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-displayitem',
  templateUrl: './displayitem.component.html',
  styleUrls: ['./displayitem.component.css']
})
export class DisplayitemComponent implements OnInit {

  viewLibItemForm: FormGroup;
  title: String;
  postRequestResponse: String;

  constructor(private http: HttpClient, private appservice: AppService) { }

  ngOnInit() {

    this.viewLibItemForm = new FormGroup({

      ItemName: new FormControl('', Validators.required),

    })
  }

  viewItem(){

    let all = JSON.stringify(this.viewLibItemForm.value);
    this.appservice.ViewLib(all).subscribe((data: any) => {

      this.postRequestResponse = data.content;

      this.viewLibItemForm.reset();
    })
  }

}
