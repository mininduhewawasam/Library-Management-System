import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {AppService} from "../app.service";

@Component({
  selector: 'app-addreader',
  templateUrl: './addreader.component.html',
  styleUrls: ['./addreader.component.css']
})
export class AddreaderComponent implements OnInit {

  AddreaderForm:FormGroup;
  title: String;
  postRequestResponse: String;

  constructor(private http: HttpClient, private appservice: AppService) {
    this.appservice.addrdr().subscribe((data: any) => {
      this.title = data.content;
    } )
  }

  ngOnInit() {

    this.AddreaderForm = new FormGroup({

      Id: new FormControl('', Validators.required),
      rname: new FormControl('', Validators.required),
      mobileNo: new FormControl('', Validators.required),
      Email: new FormControl('', Validators.required),

    })
  }
  addreader(){

    let all = JSON.stringify(this.AddreaderForm.value);
    this.appservice.AddReader(all).subscribe((data: any) => {

      this.postRequestResponse = data.content;


      this.AddreaderForm.reset()
    })

  }

}
