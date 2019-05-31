import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppService } from './app.service';
import { AppHttpInterceptorService } from './http-interceptor.service';
import { AdditemComponent } from './additem/additem.component';
import { HomeComponent } from './home/home.component';
import { BorrowitemComponent } from './borrowitem/borrowitem.component';
import { DisplayitemComponent } from './displayitem/displayitem.component';
import { DeleteitemComponent } from './deleteitem/deleteitem.component';
import { GenreportComponent } from './genreport/genreport.component';
import { ReturnitemComponent } from './returnitem/returnitem.component';
import { AddreaderComponent } from './addreader/addreader.component';

const routes: Routes = [

  {
    path:'',
    component: HomeComponent
  },
  {
    path: 'additem',
    component: AdditemComponent
  }
  ,
  {
    path:'borrowitem',
    component: BorrowitemComponent
  }
  ,
  {
    path:'deleteitem',
    component: DeleteitemComponent
  },
  {
    path:'displayitem',
    component: DisplayitemComponent
  },
  {
    path:'genreport',
    component: GenreportComponent
  },
  {
    path:'returnitem',
    component: ReturnitemComponent
  },
  {
    path:'addreader',
    component: AddreaderComponent
  }

];

@NgModule({
  declarations: [
    AppComponent,
    AdditemComponent,
    HomeComponent,
    BorrowitemComponent,
    DisplayitemComponent,
    DeleteitemComponent,
    GenreportComponent,
    ReturnitemComponent,
    AddreaderComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'Csrf-Token',
      headerName: 'Csrf-Token',
    }),
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  providers: [
    AppService,
    {
      multi: true,
      provide: HTTP_INTERCEPTORS,
      useClass: AppHttpInterceptorService
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
