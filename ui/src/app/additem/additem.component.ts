import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {AppService} from "../app.service";


@Component({
  selector: 'app-additem',
  templateUrl: './additem.component.html',
  styleUrls: ['./additem.component.css']
})
export class AdditemComponent implements OnInit {

  AddBookForm: FormGroup;
  AddDVDForm : FormGroup;
  title: String;
  postRequestResponse: String;


  constructor(private http: HttpClient, private appservice: AppService) {

    this.appservice.book().subscribe((data: any) => {
      this.title = data.content;
  } )

    this.appservice.dvd().subscribe((data: any) => {
      this.title = data.content;
    } )

  }

  ngOnInit() {

    this.AddBookForm = new FormGroup({

      Bisbn: new FormControl('', Validators.required),
      title: new FormControl('', Validators.required),
      pname: new FormControl('', Validators.required),
      pdate: new FormControl('', Validators.required),
      author: new FormControl('', Validators.required),
      pages: new FormControl('', Validators.required),
      sector: new FormControl('', Validators.required),
    })

    this.AddDVDForm = new FormGroup( {

      DIsbn: new FormControl('',Validators.required),
      DVDname: new FormControl('',Validators.required),
      pubdate: new FormControl('',Validators.required),
      producer: new FormControl('',Validators.required),
      actors: new FormControl('',Validators.required),
      language: new FormControl('',Validators.required),
      subAvailable: new FormControl('',Validators.required),
      dsector: new FormControl('', Validators.required),

    })

  }

  addBookItem(){

    let all = JSON.stringify(this.AddBookForm.value);
    this.appservice.addbook(all).subscribe((data: any) => {

      this.postRequestResponse = data.content;

      this.AddBookForm.reset();
  })
  }

  addDVDItem() {
    let DVD = JSON.stringify(this.AddDVDForm.value);
    this.appservice.adddvd(DVD).subscribe((data: any) => {

      this.postRequestResponse = data.content;

      this.AddDVDForm.reset()
    })


  }

}
