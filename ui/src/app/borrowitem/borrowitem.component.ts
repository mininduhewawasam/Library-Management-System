import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AppService} from "../app.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-borrowitem',
  templateUrl: './borrowitem.component.html',
  styleUrls: ['./borrowitem.component.css']
})
export class BorrowitemComponent implements OnInit {

  borrowItemform: FormGroup;
  title: String;
  postRequestResponse: String;

  constructor(private http: HttpClient, private appservice: AppService) {

    this.appservice.borrow().subscribe((data: any) => {
      this.title = data.content;
    })
  }

  ngOnInit() {
    this.borrowItemform = new FormGroup({

      IsbnNo: new FormControl('', Validators.required),
      readerID: new FormControl('', Validators.required),
      DateofBorrowed: new FormControl('', Validators.required),
      TimeofBorrowed: new FormControl('', Validators.required)
    })
  }

  borrowItem() {
    let Bitem = JSON.stringify(this.borrowItemform.value);
    this.appservice.borrowitem(Bitem).subscribe((data: any) => {

      this.postRequestResponse = data.content;


      this.borrowItemform.reset()
    })

  }
}
