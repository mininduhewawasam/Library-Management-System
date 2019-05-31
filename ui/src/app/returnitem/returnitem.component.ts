import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AppService} from "../app.service";

@Component({
  selector: 'app-returnitem',
  templateUrl: './returnitem.component.html',
  styleUrls: ['./returnitem.component.css']
})
export class ReturnitemComponent implements OnInit {

  returnitemForm : FormGroup;
  title: String;
  postRequestResponse: String;


  constructor(private http: HttpClient, private appservice: AppService) {
    this.appservice.return().subscribe((data: any) => {
      this.title = data.content;
    } )
  }

  ngOnInit() {

    this.returnitemForm = new FormGroup( {

      IsbnNoReturn: new FormControl('',Validators.required),
      ReaderIDreturn : new FormControl('',Validators.required),
      DateofBorrowed : new FormControl('',Validators.required),
      TimeofBorrowed : new FormControl('',Validators.required),


    })
  }

  returnItem() {
    let Bitem = JSON.stringify(this.returnitemForm.value);
    this.appservice.returnitem(Bitem).subscribe((data: any) => {

      this.postRequestResponse = data.content;

      this.returnitemForm.reset()
    })

  }


}
