import {Component, OnInit} from '@angular/core';
import {Category} from './model/category';
import {TestData} from './model/test-data';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MatTableDataSource} from '@angular/material';
import {TestDateService} from './service/testDataService/test-date.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [TestDateService]
})
export class AppComponent implements OnInit {
  listCategories: Category[];
  displayedColumns: string[] = ['date', 'description', 'category', 'amount'];
  dataSource = new MatTableDataSource();
  testDataForm: FormGroup;
  dataArray: TestData[];
  serverError = '';

  constructor(private fBuilder: FormBuilder, private dataService: TestDateService) {
    this.listCategories = [];
    this.dataArray = [];

    for (let i = 1; i <= 10; i++) {
      this.listCategories.push(new Category('Category ' + i));
    }

    this.testDataForm = fBuilder.group({
      formDate: ['', Validators.required],
      formDescription: ['', Validators.required],
      formCategory: ['', Validators.required],
      formAmount: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.dataService.get().subscribe(
      (response: any) => {
        this.dataSource.data = response;
        response.forEach(item => {
          this.dataArray.push(item);
        });
      },
      error => this.serverError = error
    );
  }

  addToDataSourse() {
    if (this.testDataForm.invalid) {
      return;
    }
    this.serverError = '';

    const data = new TestData(
      new Date(this.testDataForm.controls.formDate.value).toLocaleDateString(),
      this.testDataForm.controls.formDescription.value,
      this.testDataForm.controls.formCategory.value,
      this.testDataForm.controls.formAmount.value);

    this.dataService.send(data).subscribe(
      response => {
        this.dataArray.push(response);
        this.dataSource.data = this.dataArray;
      },
      error => this.serverError = error
    );
  }

  getTotalAmount() {
    return this.dataArray.map(t => t.amount).reduce((acc, value) => acc + value, 0);
  }
}
